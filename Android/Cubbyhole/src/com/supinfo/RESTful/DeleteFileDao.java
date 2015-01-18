package com.supinfo.RESTful;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.supinfo.cubbyhole.DetailsActivity;
import com.supinfo.cubbyhole.LoginActivity;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;

public class DeleteFileDao{
	public static String url = "http://192.168.152.1:8080/delete/";
	public void Delete(){
		DeleteFile rt = new DeleteFile();
		rt.execute();
	}

	public class DeleteFile extends AsyncTask<Void,Void,Void>{
		@Override
		protected Void doInBackground(Void... arg0) {
			try{
				HttpClient client = new DefaultHttpClient();
				HttpGet get = new HttpGet(DeleteFileDao.url+DetailsActivity.filename);
				get.setHeader("Cookie", LoginActivity.session);
				client.execute(get);
			}catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} 
			return null;
		}
		
	}
}
