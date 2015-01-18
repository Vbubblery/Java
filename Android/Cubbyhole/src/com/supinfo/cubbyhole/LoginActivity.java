package com.supinfo.cubbyhole;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.view.View;
import android.webkit.CookieManager;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity {
	EditText Email;
	EditText Password;
	Button login;
	public static String session;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		 Email =(EditText)findViewById(R.id.editText1);
		 Password = (EditText)findViewById(R.id.editText2);
		 login = (Button) findViewById(R.id.LoginBt);
		login.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View arg0) {
				String email = Email.getText().toString();
				String password = Password.getText().toString();
				dopost(email,password);
					 Intent intent = new Intent(LoginActivity.this,MainActivity.class);
					 startActivity(intent);
					 Context ctx = LoginActivity.this;       
					 SharedPreferences sp = ctx.getSharedPreferences("SP", MODE_PRIVATE);
					 Editor editor = sp.edit();
					 editor.putString("email", email);
					 editor.putString("password", password);
					 editor.commit();
					 onDestroy();
           }
			});}
	private void dopost(String val,String PWS){
	      Map<String, String> parmas = new HashMap<String, String>();
	      parmas.put("email", val);
	      parmas.put("password", PWS);
	      DefaultHttpClient client = new DefaultHttpClient();
	      HttpPost httpPost = new HttpPost("http://192.168.152.1:8080/Login");
	      ArrayList<BasicNameValuePair> pairs = new ArrayList<BasicNameValuePair>();
	      if(parmas != null){
	          Set<String> keys = parmas.keySet();
	          for(Iterator<String> i = keys.iterator(); i.hasNext();) {
	               String key = (String)i.next();
	               pairs.add(new BasicNameValuePair(key, parmas.get(key)));
	          }
	     }
	    
	   try {
	    UrlEncodedFormEntity p_entity = new UrlEncodedFormEntity(pairs, "utf-8");
	          httpPost.setEntity(p_entity);
	          HttpResponse response = client.execute(httpPost);
	          List<Cookie> cookies = client.getCookieStore().getCookies();
	          Cookie a = cookies.get(0);
	          session =a.getName()+"="+a.getValue();
	   } catch (IllegalStateException e) {
	    e.printStackTrace();
	   } catch (IOException e) {
	    e.printStackTrace();
	   }
	 }
	 

	 


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

}
