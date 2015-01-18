package com.supinfo.supcrowdfunder;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MeActivity extends Activity {
	public RadioButton index_button;
	public RadioButton category_button;
	public RadioButton project_button;
	public RadioButton me_button;
	public Button log;
	public Button reg;
	public LinearLayout LL;
	public Button me;
	public Button out;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_me);
	  content(); 
	  Context ctx = MeActivity.this;       
		SharedPreferences sp = ctx.getSharedPreferences("SP", MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.commit();
		if(sp.getString("email", "null")!="null"){IsMe();}
	}
	public void IsMe(){ 
		 LL=(LinearLayout)findViewById(R.id.profile);
		  me = new Button(this);
		  me.setText("Profile");
		  out = new Button(this);
		  out.setText("Log-out");
		  LL.addView(me);
		  LL.addView(out);
		  me.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MeActivity.this,ProfileActivity.class);
				startActivity(intent);
				onDestroy();
			}});
			out.setOnClickListener(new Button.OnClickListener(){
				@Override
				public void onClick(View v) {
					Context ctx = MeActivity.this;       
			  		SharedPreferences sp = ctx.getSharedPreferences("SP", MODE_PRIVATE);
			  		Editor editor = sp.edit();
			  		editor.clear();
			  		editor.commit();
			  		Intent intent = new Intent(MeActivity.this,MeActivity.class);
			  		startActivity(intent);
			  		onDestroy();
				}
			});
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.me, menu);
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
					 Intent intent = new Intent(MeActivity.this, SupCrowdfunderActivity.class);
					 startActivity(intent);
					 onDestroy();
			        } 
				else if (checkedId == category_button.getId()) {  
					 Intent intent = new Intent(MeActivity.this, CategoryActivity.class);
					 startActivity(intent);
					onDestroy();
				}
				else if (checkedId == project_button.getId()){
					Intent intent = new Intent(MeActivity.this, ProjectActivity.class);
					startActivity(intent);
					onDestroy();
				}
				else if (checkedId == me_button.getId()){
					Intent intent = new Intent(MeActivity.this, MeActivity.class);
					startActivity(intent);
					onDestroy();
				}
			}    
  		}
      );
  	log=(Button)findViewById(R.id.Login);
  	reg=(Button)findViewById(R.id.Reg);
  		Context ctx = MeActivity.this;       
  		SharedPreferences sp = ctx.getSharedPreferences("SP", MODE_PRIVATE);
  		Editor editor = sp.edit();
  		
  		editor.commit();
  		if(sp.getString("email", "null")!="null"){
  			log.setVisibility(View.INVISIBLE);
  			reg.setVisibility(View.INVISIBLE);
  		}
  		log.setOnClickListener(new Button.OnClickListener(){

  		@Override
  		public void onClick(View v) {
  			Intent intent = new Intent(MeActivity.this,LoginActivity.class);
  			startActivity(intent);
  			onDestroy();
  		}
  		
  	});
  	
  	reg.setOnClickListener(new Button.OnClickListener(){

  		@Override
  		public void onClick(View v) {
  			Intent intent = new Intent(MeActivity.this,RegisterActivity.class);
  			startActivity(intent);
  			onDestroy();}});
    }
}
