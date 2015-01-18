package com.supinfo.supcrowdfunder;

import java.util.ArrayList;

import com.supinfo.sup.dao.DaoFactory;
import com.supinfo.sup.dao.ProjectDao;
import com.supinfo.sup.dao.UserDao;
import com.supinfo.sup.entity.Project;
import com.supinfo.sup.entity.ProjectListAdapter;
import com.supinfo.sup.entity.User;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.RadioGroup.OnCheckedChangeListener;

@SuppressLint("NewApi")
public class SupCrowdfunderActivity extends Activity {
	public static ArrayList<Project> pro =new ArrayList<Project>();
	public static ArrayList<User> pro2 =new ArrayList<User>();
	public RadioButton index_button;
	public RadioButton category_button;
	public RadioButton project_button;
	public RadioButton me_button;
	public ProjectDao mPdao;
	private ListView myProject;
	public static ProjectListAdapter adapter;
    @SuppressLint("UseValueOf")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sup_crowdfunder);
        content();
        mPdao = DaoFactory.retreiveDaoFactory();
        mPdao.retrieveAllprojects();
        adapter = new ProjectListAdapter(this, android.R.layout.simple_list_item_1);
        myProject = (ListView) findViewById(R.id.lv_project);
        myProject.setAdapter(adapter);
        myProject.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
				Intent intent = new Intent(SupCrowdfunderActivity.this, DetailActivity.class);
				Project project = adapter.getItem(index);
				intent.putExtra("project", project);
				startActivityForResult(intent, 1);
			}
        });
       }
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sup_crowdfunder, menu);
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
    public void content(){
    	RadioGroup group = (RadioGroup)this.findViewById(R.id.main_tab_group);
 		index_button=(RadioButton)findViewById(R.id.main_index);
 		category_button=(RadioButton)findViewById(R.id.main_category);
 		project_button=(RadioButton)findViewById(R.id.main_newproject);
 		me_button=(RadioButton)findViewById(R.id.main_me);     
 		group.setOnCheckedChangeListener(new OnCheckedChangeListener() {
 			@Override
 			public void onCheckedChanged(RadioGroup group, int checkedId) {
 				if (checkedId == index_button.getId()) {  
 					 Intent intent = new Intent(SupCrowdfunderActivity.this, SupCrowdfunderActivity.class);
 					 startActivity(intent);
 					 onDestroy();
 			        } 
 				else if (checkedId == category_button.getId()) {  
 					 Intent intent = new Intent(SupCrowdfunderActivity.this, CategoryActivity.class);
 					 startActivity(intent);
 					onDestroy();
 				}
 				else if (checkedId == project_button.getId()){
 					Intent intent = new Intent(SupCrowdfunderActivity.this, ProjectActivity.class);
 					startActivity(intent);
 					onDestroy();
 				}
 				else if (checkedId == me_button.getId()){
 					Intent intent = new Intent(SupCrowdfunderActivity.this, MeActivity.class);
 					startActivity(intent);
 					onDestroy();
 				}
 			}    
 		}
     );
    }
}
