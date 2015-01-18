package com.supinfo.supcrowdfunder;

import java.util.ArrayList;

import com.supinfo.sup.dao.CategoryDao;
import com.supinfo.sup.dao.DaoFactory;
import com.supinfo.sup.entity.Category;
import com.supinfo.sup.entity.CategoryListAdapter;
import com.supinfo.sup.entity.Project;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class CategoryActivity extends Activity {
	public static ArrayList<Category> cat =new ArrayList<Category>();
	public RadioButton index_button;
	public RadioButton category_button;
	public RadioButton project_button;
	public RadioButton me_button;
	public CategoryDao mPdao;
	private ListView myUser;
	public static CategoryListAdapter adapter;
	@SuppressLint("UseValueOf")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_catrgory);
		
	   content();
	   mPdao=DaoFactory.retreiveCategoryDaoFactory();
	   mPdao.retrieveAllCategory();
	   adapter = new CategoryListAdapter(this, android.R.layout.simple_list_item_1);
	   myUser =(ListView)findViewById(R.id.lv_category);
	   myUser.setAdapter(adapter);
	   myUser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
			Intent intent = new Intent(CategoryActivity.this, Category2Activity.class);
			Category category = adapter.getItem(index);
			intent.putExtra("project", category);
			startActivityForResult(intent, 1);
			onDestroy();
		}
	   });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.catrgory, menu);
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
   					 Intent intent = new Intent(CategoryActivity.this, SupCrowdfunderActivity.class);
   					 startActivity(intent);
   					 onDestroy();
   			        } 
   				else if (checkedId == category_button.getId()) {  
   					 Intent intent = new Intent(CategoryActivity.this, CategoryActivity.class);
   					 startActivity(intent);
   					 onDestroy();
   				}
   				else if (checkedId == project_button.getId()){
   					Intent intent = new Intent(CategoryActivity.this, ProjectActivity.class);
   					startActivity(intent);
   					onDestroy();
   				}
   				else if (checkedId == me_button.getId()){
   					Intent intent = new Intent(CategoryActivity.this, MeActivity.class);
   					startActivity(intent);
   					onDestroy();
   				}
   				
   			}  
     		}
         );
       }
}
