package com.supinfo.supcrowdfunder;

import com.supinfo.sup.dao.DaoFactory;
import com.supinfo.sup.dao.UserDao;
import com.supinfo.sup.entity.User;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ProfileActivity extends Activity {
	public EditText adreess;
	public EditText password;
	public Button Ok;
	public Button cancle;
	public UserDao UD;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		adreess=(EditText)findViewById(R.id.Change_Email);
		password=(EditText)findViewById(R.id.Change_Password);
		Ok=(Button)findViewById(R.id.Profile_Ok);
		cancle=(Button)findViewById(R.id.Profile_cancle);
		 Context ctx = ProfileActivity.this;  
		SharedPreferences sp = ctx.getSharedPreferences("SP", MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.commit();
		adreess.setHint(sp.getString("email", "null"));
		password.setHint(sp.getString("password", "null"));
		 cancle.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View arg0) {
				
				Intent intent =new Intent(ProfileActivity.this,MeActivity.class);
				startActivity(intent);
				finish();
			}});
		 Ok.setOnClickListener(new Button.OnClickListener(){
			 
				@Override
				public void onClick(View arg0) {
					String newaddress = adreess.getText().toString();
					String newpassword=password.getText().toString();
					UD=DaoFactory.retreiveUserDaoFactory();
					User u = new User();
					u.setEmail(newaddress);
					u.setPassword(newpassword);
					UD.insertuser(u);
					Intent intent =new Intent(ProfileActivity.this,MeActivity.class);
					startActivity(intent);
					finish();
				}});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.profile, menu);
		return true;
	}
}