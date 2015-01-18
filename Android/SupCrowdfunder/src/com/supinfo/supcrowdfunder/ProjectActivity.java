package com.supinfo.supcrowdfunder;

import com.supinfo.sup.dao.DaoFactory;
import com.supinfo.sup.dao.ProjectDao;
import com.supinfo.sup.entity.Project;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class ProjectActivity extends Activity {
	public RadioButton index_button;
	public RadioButton category_button;
	public RadioButton project_button;
	public RadioButton me_button;
	public EditText title;
	public EditText body;
	public EditText price;
	public Button ok;
	public ProjectDao PD;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_project);
		Context ctx = ProjectActivity.this;       
		SharedPreferences sp = ctx.getSharedPreferences("SP", MODE_PRIVATE);
		if(sp.getString("email", "none").equals("none"))
		{
			Intent intent = new Intent(ProjectActivity.this,MeActivity.class);
			startActivity(intent);
			onDestroy();
		}
		else
		{
			content();
			title=(EditText)findViewById(R.id.title);
			body=(EditText)findViewById(R.id.body);
			price=(EditText)findViewById(R.id.price);
			ok =(Button)findViewById(R.id.button1);
		 }
		 ok.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View arg0) {
					try {
						String t=title.getText().toString();
						String b=body.getText().toString();
						Float p =new Float(price.getText().toString());
						PD=DaoFactory.retreiveDaoFactory();
						Project project = new Project();
						project.setTitle(t);
						project.setBody(b);
						project.setNowPrice(new Float(0));
						project.setPrice(p);
						PD.addproject(project);
						 Intent intent = new Intent(ProjectActivity.this, SupCrowdfunderActivity.class);
						 startActivity(intent);
						 onDestroy();
					} catch (Exception e) {
						Intent intent =new Intent(ProjectActivity.this,SupCrowdfunderActivity.class);
						startActivity(intent);
						onDestroy();
					}

				}});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.project, menu);
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
   	 					Intent intent = new Intent(ProjectActivity.this, SupCrowdfunderActivity.class);
   	 					startActivity(intent);
   	 					onDestroy();
			        } 
   	 				else if (checkedId == category_button.getId()) {  
   	 					Intent intent = new Intent(ProjectActivity.this, CategoryActivity.class);
   	 					startActivity(intent);
   	 					onDestroy();
				}
   	 				else if (checkedId == project_button.getId()){
   	 					Intent intent = new Intent(ProjectActivity.this, ProjectActivity.class);
   	 					startActivity(intent);
   	 					onDestroy();
				}
   	 				else if (checkedId == me_button.getId()){
   	 					Intent intent = new Intent(ProjectActivity.this, MeActivity.class);
   	 					startActivity(intent);
   	 					onDestroy();
				}
			}  
  		}
      );
    }
}
