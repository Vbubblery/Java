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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.supinfo.sup.dao.CategoryDao;
import com.supinfo.sup.entity.Category;
import com.supinfo.sup.entity.Project;
import com.supinfo.supcrowdfunder.CategoryActivity;


import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;

public class CategoryRestDao extends Service implements CategoryDao{
	public static String url = "http://192.168.82.1:8080/SupcrowServer/resources/category";
	public static Project pp=new Project();
	@Override
	public Category insertcategory(Category category) {
		RestTaskPost rtp = new RestTaskPost();
		rtp.execute(category);
		return category;
	}

	@Override
	public ArrayList<Category> retrieveAllCategory() {
		RestTaskGetAll rt = new RestTaskGetAll();
		rt.execute();
		return null;
	}
	public static Category convertToQuote(JSONObject jsonObject){
		Category category = new Category();
		try {
			category.setID(jsonObject.getLong("ID"));
			category.setName(jsonObject.getString("name"));
		} 	catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return category;
	}
    public static JSONObject convertToJson(Category category) throws JSONException {
        
        JSONObject jsonCategory = new JSONObject();
        
        if(category.getID()!= null){
        	jsonCategory.put("ID", category.getID());      
        }     
        if(category.getName()!=null){
        	jsonCategory.put("name", category.getName());
        }


        return jsonCategory;
	}
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	public class RestTaskPost extends AsyncTask<Category, Void, Category>{

		@SuppressLint("UseValueOf")
		@Override
		protected Category doInBackground(Category... category) {
			Category  CategoryObject = category[0];
			 try {
		        	HttpClient client = new DefaultHttpClient();
		        	HttpPost post = new HttpPost(url);        	
					JSONObject mProduct = convertToJson(CategoryObject);
					post.setHeader("Content-Type", "application/json");
		        	post.setEntity(new StringEntity(mProduct.toString()));
		        	HttpResponse response = client.execute(post);
		        	String location = response.getHeaders("Location")[0].getValue();
		        	Long id = new Long(location.split("/")[6]);
		        	CategoryObject.setID(id);
		        	return CategoryObject;
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
			return CategoryObject;
		}
		@Override
		protected void onPostExecute(Category result) {
			super.onPostExecute(result);

	}
	}
	
		
	
	public class RestTaskGetAll extends AsyncTask<Void, Void, ArrayList<Category>>{

		//Method runned in background
		@Override
		protected ArrayList<Category> doInBackground(Void... params) {
			ArrayList<Category> mCategory = new ArrayList<Category>();
		    try {
		    	 HttpClient client = new DefaultHttpClient();
			        
			        //Create the request with a get method
		            HttpGet get = new HttpGet(CategoryRestDao.url);
		            
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
			        JSONArray categorys = quotesObject.getJSONArray("category");
		        //Add every single quote from the array to our list
		        for (int i = 0; i < categorys.length(); i++) {
		        	mCategory.add(CategoryRestDao.convertToQuote(categorys.getJSONObject(i)));
		        }
		        
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			}
		    return mCategory;
		}
		@Override
		protected void onPostExecute(ArrayList<Category> result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			CategoryActivity.cat = result;
			CategoryActivity.adapter.notifyDataSetChanged();

			
		}

}
}
