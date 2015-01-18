package com.supinfo.supcrowdfunder;

import com.supinfo.sup.entity.Project;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class DetailActivity extends Activity {
	private Project GetProject;
	private TextView title;
	private TextView author;
	private TextView body;
	private TextView price;
	private Button submit;
	public RadioButton index_button;
	public RadioButton category_button;
	public RadioButton project_button;
	public RadioButton me_button;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		Bundle bundle = getIntent().getExtras();
		GetProject = (Project) bundle.getSerializable("project");
		title=(TextView)findViewById(R.id.title);
		author=(TextView)findViewById(R.id.author);
		body=(TextView)findViewById(R.id.body);
		price=(TextView)findViewById(R.id.price);
		submit=(Button)findViewById(R.id.donation);
		title.setText("Title:"+GetProject.getTitle());
		author.setText("Author:"+"Admin");
		body.setText("Body:"+GetProject.getBody());
		price.setText("Price:"+GetProject.getNowPrice()+"/"+GetProject.getPrice());
		submit.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				 Context ctx = DetailActivity.this;       
				 SharedPreferences sp = ctx.getSharedPreferences("SP", MODE_PRIVATE);
				 if(sp.getString("email", "none").equals("none"))
				 {
					 Intent intent = new Intent(DetailActivity.this,MeActivity.class);
					 startActivity(intent);
					 onDestroy();
				 }
				 else{
				Intent intent=new Intent(DetailActivity.this, DonationActivity.class);
				intent.putExtra("project", GetProject);
				startActivityForResult(intent,1);
				onDestroy();}
			}
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detail, menu);
		return true;
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
						 Intent intent = new Intent(DetailActivity.this, SupCrowdfunderActivity.class);
						 startActivity(intent);
						 onDestroy();
				        } 
					else if (checkedId == category_button.getId()) {  
						 Intent intent = new Intent(DetailActivity.this, CategoryActivity.class);
						 startActivity(intent);
						onDestroy();
					}
					else if (checkedId == project_button.getId()){
						Intent intent = new Intent(DetailActivity.this, ProjectActivity.class);
						startActivity(intent);
						onDestroy();
					}
					else if (checkedId == me_button.getId()){
						Intent intent = new Intent(DetailActivity.this, MeActivity.class);
						startActivity(intent);
						onDestroy();
					}
				}  
	  		}
	      );
	    }
}
