package com.supinfo.cubbyhole;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.supinfo.RESTful.DeleteFileDao;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DetailsActivity extends Activity {
	public static String filename;
	String sdcard=Environment.getExternalStorageDirectory()+"/";
	String filepath=sdcard+"download/";
	URL url;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);
		TextView N = (TextView)findViewById(R.id.filename);
		Bundle bundle = getIntent().getExtras();
		filename = (String) bundle.getSerializable("name");
		N.setText(filename);
		Button delete = (Button)findViewById(R.id.button1);
		delete.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View arg0) {
				DeleteFileDao DD = new DeleteFileDao();
				DD.Delete();
				Intent intent = new Intent(DetailsActivity.this,MainActivity.class);
				startActivity(intent);
			}});
		Button download = (Button)findViewById(R.id.button2);
		download.setOnClickListener(new Button.OnClickListener(){

			public void onClick(View arg0) {
					try {
						url=new URL("http://192.168.152.1:8080/download/"+filename);
						HttpURLConnection connection = (HttpURLConnection)url.openConnection();
						connection.setRequestProperty("Cookie", LoginActivity.session);
						InputStream inputStream = connection.getInputStream();
						File dir=new File(filepath);
						     if (!dir.exists()) {
						          dir.mkdir();
						                     }
						File file=new File(filepath+filename);
						file.createNewFile();
						OutputStream output=new FileOutputStream(file);
						byte[] buffer=new byte[1024*4];
						while (inputStream.read(buffer)!=-1) {
							      output.write(buffer);
							                     }
						output.flush();
						 output.close();
						inputStream.close();
						 Toast.makeText(DetailsActivity.this, filename, Toast.LENGTH_LONG).show();
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				
			}});
		Button Rename = (Button)findViewById(R.id.button3);
		Rename.setOnClickListener(new Button.OnClickListener(){

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			}});
		Button Share = (Button)findViewById(R.id.button4);
		Share.setOnClickListener(new Button.OnClickListener(){

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
			}});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.details, menu);
		return true;
	}

}
