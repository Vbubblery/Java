package com.supinfo.cubbyhole;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.supinfo.RESTful.DirFileRestDao;
import com.supinfo.RESTful.dirfileadapter;
import com.supinfo.cubbyhole.R;
import com.supinfo.cubbyhole.R.layout;
import com.supinfo.cubbyhole.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.ComponentName;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity{
	private ListView LV;
	public static JSONArray df = new JSONArray();
	public static dirfileadapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
			 startService(new Intent(getApplicationContext(), DirFileRestDao.class));
			 adapter=new dirfileadapter(this,android.R.layout.simple_list_item_1);
			 LV=(ListView)findViewById(R.id.list_dirfile);
			 LV.setAdapter(adapter);
			 LV.setOnItemClickListener(new AdapterView.OnItemClickListener() {

				public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
					Intent intent =new Intent(MainActivity.this,DetailsActivity.class);
					String name = adapter.getItem(index).toString();
					intent.putExtra("name", name);
					startActivityForResult(intent, 1);
				}
			});
			 Button mydisk = (Button)findViewById(R.id.MyDisk);
			 mydisk.setOnClickListener(new Button.OnClickListener(){

				public void onClick(View arg0) {
					Intent intent = new Intent(MainActivity.this,LoginActivity.class);
					startActivity(intent);
				}});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
    public void onStart(){
    	super.onStart();

    }
    @Override
    public void onResume(){
    	super.onResume();
    }
    @Override
    public void onPause(){
    	super.onPause();
    }    
    @Override
    public void onStop(){
    	super.onStop();
    }    
    @Override
    public void onDestroy() {
    	super.onDestroy();
    }

}
