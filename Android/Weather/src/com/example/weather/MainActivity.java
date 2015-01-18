package com.example.weather;

import android.annotation.SuppressLint;

import android.content.Intent;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;

import android.speech.RecognizerIntent;

import android.support.v4.app.Fragment;

import android.support.v7.app.ActionBarActivity;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.view.View.OnClickListener;

import android.view.ViewGroup;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Dao.ChineseToPinyin;

import com.example.Entity.CityWeatherInfo;
import com.example.Entity.Coord;
import com.example.Entity.Sys;
import com.example.Entity.Weather;
import com.example.Entity.WeatherMain;
import com.example.Entity.Wind;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

import java.net.HttpURLConnection;
import java.net.URL;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@SuppressLint({"ShowToast",
    "NewApi"
})
public class MainActivity extends ActionBarActivity {
    private static final int VOICE_RECOGNITION_REQUEST_CODE = 1234;
    public static String url;
    public static String imageurl = "http://openweathermap.org/img/w/";
    RelativeLayout Rlout;
    Button speak;
    TextView temp;
    TextView weatherDesc;
    ImageView icon;
    TextView humidity;
    TextView windspeed;
    TextView Country;
    TextView Position;
    TextView Weather_TempAround;
    TextView UpdateTime;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll()
                                                                                  .build();
            StrictMode.setThreadPolicy(policy);
        }

        speak = (Button) this.findViewById(R.id.btn_speek);
        temp = (TextView) findViewById(R.id.TV_TEMP);
        weatherDesc = (TextView) findViewById(R.id.Temp_Desc);
        humidity = (TextView) findViewById(R.id.Weather_humidity);
        windspeed = (TextView) findViewById(R.id.Wind_Speed);
        Country = (TextView) findViewById(R.id.Country);
        Position = (TextView) findViewById(R.id.Position);
        icon = (ImageView) findViewById(R.id.Weather_icon);
        Rlout = (RelativeLayout) findViewById(R.id.MainL);
        Weather_TempAround = (TextView) findViewById(R.id.Weather_TempAround);
        UpdateTime = (TextView) findViewById(R.id.updatetime);
        Rlout.setBackgroundResource(R.drawable.b1);
        speak.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    try { 
                        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH); 
                        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM); 
                        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "开始语音"); 
                        startActivityForResult(intent,
                            VOICE_RECOGNITION_REQUEST_CODE);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "找不到语音设备", 1)
                             .show();
                    }
                }
            });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) { 
        if ((requestCode == VOICE_RECOGNITION_REQUEST_CODE) &&
                (resultCode == RESULT_OK)) {  
            ArrayList<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            String resultString = "";
            resultString = results.get(0);
            Pattern p = Pattern.compile("[.。,\"\\?!:']"); 
            Matcher m = p.matcher(resultString);
            resultString = m.replaceAll("");
            p = Pattern.compile(" {2,}"); 
            m = p.matcher(resultString);
            resultString = m.replaceAll(" ");
            resultString = ChineseToPinyin.toPinYin(resultString.charAt(0));
            SearchCity(resultString);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    public void SearchCity(String city) {
        url = "http://api.openweathermap.org/data/2.5/weather?q=" + city;
        RestTaskGetAll rt = new RestTaskGetAll();
        rt.execute();
    }

    public static CityWeatherInfo convertToQuote(JSONObject jsonObject) {
        CityWeatherInfo project = new CityWeatherInfo();
        JSONObject json;
        JSONArray jay;
        Coord coor = new Coord();
        Sys sys = new Sys();
        Weather weather = new Weather();
        WeatherMain weathermain = new WeatherMain();
        Wind wind = new Wind();

        try {
            project.setCityName(jsonObject.getString("name"));
            project.setCityId(jsonObject.getString("id"));
            json = (JSONObject) jsonObject.get("coord");
            coor.setLat(json.getString("lat"));
            coor.setLon(json.getString("lon"));
            project.setCoord(coor);
            json = (JSONObject) jsonObject.get("sys");
            sys.setCityCountry(json.getString("country"));
            project.setSys(sys);
            jay = (JSONArray) jsonObject.get("weather");
            json = (JSONObject) jay.get(0);
            weather.setWeatherDescription(json.getString("main"));
            weather.setWeatherIcon(json.getString("icon"));
            project.setWeather(weather);
            json = (JSONObject) jsonObject.get("main");
            weathermain.setTemp(json.getString("temp"));
            weathermain.setHumidity(json.getString("humidity"));
            weathermain.setTemp_max(json.getString("temp_max"));
            weathermain.setTemp_min(json.getString("temp_min"));
            project.setWeathermain(weathermain);
            json = (JSONObject) jsonObject.get("wind");
            wind.setWindSpeed(json.getString("speed"));
            project.setWind(wind);
            project.setDt(jsonObject.getString("dt"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return project;
    }

    @SuppressLint("SimpleDateFormat")
	public String TimeStamp2Date(String timestampString) {
        Long timestamp = Long.parseLong(timestampString) * 1000;
        String date = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new java.util.Date(timestamp));

        return date;
    }

    public static Bitmap getHttpBitmap(String url) {
        URL myFileURL;
        Bitmap bitmap = null;

        try {
            myFileURL = new URL(url); 
            HttpURLConnection conn = (HttpURLConnection) myFileURL.openConnection(); 
            conn.setConnectTimeout(6000); 
            conn.setDoInput(true); 
            conn.setUseCaches(false); 
            InputStream is = conn.getInputStream(); 
            bitmap = BitmapFactory.decodeStream(is); 
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bitmap;
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.activity_main, container,
                    false);

            return rootView;
        }
    }

    public class RestTaskGetAll extends AsyncTask<Void, Void, CityWeatherInfo> {
        @Override
        protected CityWeatherInfo doInBackground(Void... params) {
            JSONObject quotesObject = null;
            CityWeatherInfo CWI = null;

            try {
                HttpClient client = new DefaultHttpClient();
                HttpGet get = new HttpGet(url);
                HttpResponse response = client.execute(get);
                String responseStr = EntityUtils.toString(response.getEntity());
                quotesObject = new JSONObject(responseStr);
                if ((quotesObject.getInt("cod") != 200) ||
                        (quotesObject.getString("name") == "")) {
                    Toast.makeText(getApplicationContext(),
                        quotesObject.getString("message"), 1).show();
                } else {
                    CWI = MainActivity.convertToQuote(quotesObject);
                }
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return CWI;
        }

        @Override
        protected void onPostExecute(CityWeatherInfo result) {
            super.onPostExecute(result);
            getActionBar().setTitle(result.getCityName());

            float tmp = Float.parseFloat(result.getWeathermain().getTemp());
            int tmp2 = (int) (tmp - 273.15);
            temp.setText(tmp2 + "°");
            weatherDesc.setText(result.getWeather().getWeatherDescription());
            humidity.setText("Humidity:" +
                result.getWeathermain().getHumidity());
            windspeed.setText("Windspeed: Level " +
                result.getWind().getWindSpeed());
            Country.setText(result.getSys().getCityCountry());
            Position.setText("Lon:"+result.getCoord().getLon() + " : " +
                "Lat:"+result.getCoord().getLat());
            icon.setImageBitmap(getHttpBitmap(imageurl +
                    result.getWeather().getWeatherIcon() + ".png"));
            tmp = Float.parseFloat(result.getWeathermain().getTemp_max());
            tmp2 = (int) (tmp - 273.15);
            float tmp3 = Float.parseFloat(result.getWeathermain().getTemp_min());
            int tmp4 = (int) (tmp3 - 273.15);
            Weather_TempAround.setText(tmp4 + "° ~ " + tmp2 + "°");
            UpdateTime.setText("Updated by: " + TimeStamp2Date(result.getDt()));

            if ((result.getWeather().getWeatherIcon() == "09d") ||
                    (result.getWeather().getWeatherIcon() == "09n") ||
                    (result.getWeather().getWeatherIcon() == "10d") ||
                    (result.getWeather().getWeatherIcon() == "10n")) {
                Rlout.setBackgroundResource(R.drawable.xiayu);
            } else if ((result.getWeather().getWeatherIcon() == "11d") ||
                    (result.getWeather().getWeatherIcon() == "11n")) {
                Rlout.setBackgroundResource(R.drawable.leitian2);
            } else {
                Rlout.setBackgroundResource(R.drawable.qingtian);
            }
        }
    }
}
