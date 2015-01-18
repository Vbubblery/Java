package com.supinfo.sup.rest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;

import com.supinfo.sup.dao.UserDao;
import com.supinfo.sup.entity.User;
import com.supinfo.supcrowdfunder.SupCrowdfunderActivity;

public class UserRestDao extends Service implements UserDao{
	public static String url = "http://192.168.82.1:8080/SupcrowServer/resources/user";
	@Override
	public User insertuser(User user) {
		RestTaskPost rtp = new RestTaskPost();
		rtp.execute(user);
		return user;
	}

	@Override
	public void updateuser(User user) {
		RestTaskUpdate rtu = new RestTaskUpdate();
		rtu.execute(user);
		
	}

	@Override
	public User retrieveAlluserById(Long id) {
		RestTaskGetUser rt = new RestTaskGetUser();
		url+="/"+id;
		rt.execute();
		return null;
	}
	public static User convertToQuote(JSONObject jsonObject){
		User user = new User();
		try {
			user.setId(jsonObject.getLong("ID"));
			user.setEmail(jsonObject.getString("email"));
			user.setPassword(jsonObject.getString("password"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	public static JSONObject convertToJson(User user) throws JSONException {
	    
        JSONObject jsonUser = new JSONObject();
        
        
        if(user.getEmail() != null){
        	jsonUser.put("email", user.getEmail());      
        }      
          
        if(user.getPassword() != null){
        	jsonUser.put("password", user.getPassword());
        } 
        return jsonUser;
      	}
      	@Override
      	public IBinder onBind(Intent intent) {
      		// TODO Auto-generated method stub
      		return null;
      	}
      	public class RestTaskPost extends AsyncTask<User, Void, User>{

      		@SuppressLint("UseValueOf")
      		@Override
      		protected User doInBackground(User... user) {
      			User  UserObject = user[0];
      			 try {
      				url = "http://192.168.82.1:8080/SupcrowServer/resources/user";
      		        	HttpClient client = new DefaultHttpClient();
      		        	HttpPost post = new HttpPost(url);   
      					JSONObject mProduct = convertToJson(UserObject);
      					post.setHeader("Content-Type", "application/json");
      					post.setEntity(new StringEntity(mProduct.toString()));
    		        	HttpResponse response = client.execute(post);
    		        	String location = response.getHeaders("Location")[0].getValue();
    		        	Long id = new Long(location.split("/")[6]);
    		        	UserObject.setId(id);
      		        	return UserObject;
      		        } catch (JSONException e) {
      					e.printStackTrace();
      				} catch (UnsupportedEncodingException e) {
      					// TODO Auto-generated catch block
      					e.printStackTrace();
      				} catch (ClientProtocolException e) {
      					// TODO Auto-generated catch block
      					e.printStackTrace();
      				} catch (IOException e) {
      					// TODO Auto-generated catch block
      					e.printStackTrace();
      				}
      			return UserObject;
      		}
      		@Override
      		protected void onPostExecute(User result) {
      			super.onPostExecute(result);

      	}
      	}
      	public class RestTaskUpdate extends AsyncTask<User, Void, Void>{

      		@Override
      		protected Void doInBackground(User... user) {
      			User mUser = user[0];
      	        try {
      	            HttpClient client = new DefaultHttpClient();
      	            HttpPut put = new HttpPut(url);
      	            put.setHeader("Content-type", "application/json");
      				put.setEntity(new StringEntity(convertToJson(mUser).toString()));
      				
      		        client.execute(put);
      			} catch (UnsupportedEncodingException e) {
      				// TODO Auto-generated catch block
      				e.printStackTrace();
      			} catch (JSONException e) {
      				// TODO Auto-generated catch block
      				e.printStackTrace();
      			} catch (ClientProtocolException e) {
      				// TODO Auto-generated catch block
      				e.printStackTrace();
      			} catch (IOException e) {
      				// TODO Auto-generated catch block
      				e.printStackTrace();
      			}
      			return null;
      		}
      		
      	}
      	public class RestTaskGetUser extends AsyncTask<Void, Void, ArrayList<User>>{
      		//Method runned in background
      		@Override
      		protected ArrayList<User> doInBackground(Void... params) {
      			ArrayList<User> mUser = new ArrayList<User>();
      		    try {
      		    	 HttpClient client = new DefaultHttpClient();
      			        //Create the request with a get method
      		            HttpGet get = new HttpGet(UserRestDao.url);
      		            //specify that we want to retrieve a json
      			        get.setHeader("Accept", "application/json");
      			        //create our response object
      			        HttpResponse response;
      			        //Run the request
      					response = client.execute(get);
      					//Retrieve the response as string
      			        String responseStr = EntityUtils.toString(response.getEntity());
      			        JSONObject quotesObject = new JSONObject(responseStr);
      			        //get it as array
      		        //Add every single quote from the array to our list
      		        
      		        	mUser.add(UserRestDao.convertToQuote(quotesObject));
      		        
      		        
      			} catch (ClientProtocolException e) {
      				e.printStackTrace();
      			} catch (IOException e) {
      				e.printStackTrace();
      			} catch (JSONException e) {
      				e.printStackTrace();
      			}
      		    return mUser;
      		}
      		@Override
      		protected void onPostExecute(ArrayList<User> result) {
      			// TODO Auto-generated method stub
      			super.onPostExecute(result);
      			SupCrowdfunderActivity.pro2 = result;
      		}


      		
      }
}
