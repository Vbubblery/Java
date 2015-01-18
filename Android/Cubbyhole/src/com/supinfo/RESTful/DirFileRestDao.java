package com.supinfo.RESTful;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.supinfo.cubbyhole.LoginActivity;
import com.supinfo.cubbyhole.MainActivity;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;

public class DirFileRestDao extends Service{
	public static String url = "http://192.168.152.1:8080/getdata";
	public JSONObject getdata(){
		RestTaskGetAll rt = new RestTaskGetAll();
		rt.execute();
		return null;
	}
	
	
	
	@Override
	public IBinder onBind(Intent intent) {
		
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		getdata();
		return super.onStartCommand(intent, flags, startId);
	}

	public class RestTaskGetAll extends AsyncTask<Void, Void, JSONArray>{
		JSONArray file = null;
		JSONArray file2 = null;
		@Override
		protected JSONArray doInBackground(Void... params) {
			JSONObject quotesObject = null;
			try {
				URL url = new URL(DirFileRestDao.url);
				HttpURLConnection con= (HttpURLConnection) url.openConnection(); 
		    	 HttpClient client = new DefaultHttpClient();
			        //Create the request with a get method
		            HttpGet get = new HttpGet(DirFileRestDao.url);
		            //specify that we want to retrieve a json
			        get.setHeader("Accept", "application/json");
			        get.setHeader("Cookie", LoginActivity.session);
			        //create our response object
			        HttpResponse response;
			        //Run the request
					response = client.execute(get);
					//Retrieve the response as string
			        String responseStr = EntityUtils.toString(response.getEntity());		
			        quotesObject = new JSONObject(responseStr);
			        file= quotesObject.getJSONArray("dir");
			        file2 = quotesObject.getJSONArray("file");
			        for (int i = 0; i < file2.length(); i++) {
						file.put(file2.get(i));
					}
			        //get it as array
/*			        dirfile.put(quotesObject.getJSONArray("dir"));
			        dirfile.put(quotesObject.getJSONArray("file"));*/
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return file;
		}
		@Override
		protected void onPostExecute(JSONArray result) {
			
			super.onPostExecute(result);
			MainActivity.df=result;
			MainActivity.adapter.notifyDataSetChanged();
//			SupCrowdfunderActivity.pro.add(result);
//			SupCrowdfunderActivity.adapter.notifyDataSetChanged();
	}}
}
