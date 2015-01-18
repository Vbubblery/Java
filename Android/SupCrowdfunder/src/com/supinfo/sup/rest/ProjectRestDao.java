package com.supinfo.sup.rest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

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



import com.supinfo.sup.dao.ProjectDao;
import com.supinfo.sup.entity.Category;
import com.supinfo.sup.entity.Project;
import com.supinfo.sup.entity.User;
import com.supinfo.sup.rest.UserRestDao.RestTaskGetUser;
import com.supinfo.supcrowdfunder.Category2Activity;
import com.supinfo.supcrowdfunder.R;
import com.supinfo.supcrowdfunder.SupCrowdfunderActivity;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;

public class ProjectRestDao extends Service implements ProjectDao{
	public static String url = "http://192.168.82.1:8080/SupcrowServer/resources/project";

	
	@Override
	public Project addproject(Project project) {
		RestTaskPost rtp = new RestTaskPost();
		rtp.execute(project);
		return project;
	}
	@Override
	public void updateProject(Project project) {
		RestTaskUpdate rtu = new RestTaskUpdate();
		rtu.execute(project);
		
	}
	@Override
	public Project retrieveprojectById(Long id) {
		RestTaskGetUser rt = new RestTaskGetUser();
		url+="/"+id;
		rt.execute();
		return null;
	}

	@Override
	public ArrayList<Project> retrieveAllprojects() {
		RestTaskGetAll rt = new RestTaskGetAll();
		rt.execute();
		return null;
	}
	public static Project convertToQuote(JSONObject jsonObject){
		Project project = new Project();
		User user = new User();
		try {
			project.setId(jsonObject.getLong("ID"));
			project.setTitle(jsonObject.getString("Title"));
			project.setBody(jsonObject.getString("Body"));
			project.setPrice(new Float(jsonObject.getString("Price")));
			project.setNowPrice(new Float(jsonObject.getString("NowPrice")));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return project;
	}
    public static JSONObject convertToJson(Project project) throws JSONException {
        
        	JSONObject jsonProject = new JSONObject();
        	jsonProject.put("Title", project.getTitle());      
        	jsonProject.put("ID", project.getId());
        	jsonProject.put("Body", project.getBody()); 
        	jsonProject.put("Price", project.getPrice());
        	jsonProject.put("NowPrice", project.getNowPrice());
        	jsonProject.put("user_ID", project.getUser());
        return jsonProject;
	}
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	public class RestTaskPost extends AsyncTask<Project, Void, Project>{

		@SuppressLint("UseValueOf")
		@Override
		protected Project doInBackground(Project... project) {
			Project  ProjectObject = project[0];
			 try {
		        	HttpClient client = new DefaultHttpClient();
		        	HttpPost post = new HttpPost(url);        	
					JSONObject mProduct = convertToJson(ProjectObject);
					post.setHeader("Content-Type", "application/json");
		        	post.setEntity(new StringEntity(mProduct.toString()));
		        	HttpResponse response = client.execute(post);
		        	String location = response.getHeaders("Location")[0].getValue();
		        	Long id = new Long(location.split("/")[6]);
		        	ProjectObject.setId(id);
		        	return ProjectObject;
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
			return ProjectObject;
		}
		@Override
		protected void onPostExecute(Project result) {
			super.onPostExecute(result);
			SupCrowdfunderActivity.pro.add(result);
			SupCrowdfunderActivity.adapter.notifyDataSetChanged();
	}
	}
	public class RestTaskUpdate extends AsyncTask<Project, Void, Void>{

		@Override
		protected Void doInBackground(Project... project) {
			Project mProject = project[0];
	        try {
	            HttpClient client = new DefaultHttpClient();
	            HttpPut put = new HttpPut(url);
	            put.setHeader("Content-type", "application/json");
				put.setEntity(new StringEntity(convertToJson(mProject).toString()));
				
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
	public class RestTaskGetAll extends AsyncTask<Void, Void, ArrayList<Project>>{

		//Method runned in background
		@Override
		protected ArrayList<Project> doInBackground(Void... params) {

			ArrayList<Project> mProject = new ArrayList<Project>();
		    try {
		    	 HttpClient client = new DefaultHttpClient();
			        
			        //Create the request with a get method
		            HttpGet get = new HttpGet(ProjectRestDao.url);
		            
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
			        JSONArray projects = quotesObject.getJSONArray("project");
		        //Add every single quote from the array to our list
		        for (int i = 0; i < projects.length(); i++) {
		        	mProject.add(ProjectRestDao.convertToQuote(projects.getJSONObject(i)));
		        }
		        
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			}
		    return mProject;
		}
		@Override
		protected void onPostExecute(ArrayList<Project> result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			SupCrowdfunderActivity.pro = result;
			SupCrowdfunderActivity.adapter.notifyDataSetChanged();
			
		}


		
}
	public class RestTaskGetUser extends AsyncTask<Void, Void, ArrayList<Project>>{

		//Method runned in background
		@Override
		protected ArrayList<Project> doInBackground(Void... params) {

			ArrayList<Project> mProject = new ArrayList<Project>();
		    try {
		    	 HttpClient client = new DefaultHttpClient();
			        
			        //Create the request with a get method
		            HttpGet get = new HttpGet(ProjectRestDao.url);
		            
		            //specify that we want to retrieve a json
			        get.setHeader("Accept", "application/json");
			        //create our response object
			        HttpResponse response;
			        //Run the request
					response = client.execute(get);
					//Retrieve the response as string
			        String responseStr = EntityUtils.toString(response.getEntity());		
			        JSONObject quotesObject = new JSONObject(responseStr);

		        	mProject.add(ProjectRestDao.convertToQuote(quotesObject));
		       
		        
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			}
		    return mProject;
		}
		@Override
		protected void onPostExecute(ArrayList<Project> result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			SupCrowdfunderActivity.pro = result;
			SupCrowdfunderActivity.adapter.notifyDataSetChanged();
		}


		
}

}