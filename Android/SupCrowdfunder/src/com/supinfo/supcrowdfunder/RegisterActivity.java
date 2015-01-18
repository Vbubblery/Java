package com.supinfo.supcrowdfunder;

import com.supinfo.sup.dao.CategoryDao;
import com.supinfo.sup.dao.DaoFactory;
import com.supinfo.sup.dao.UserDao;
import com.supinfo.sup.entity.Category;
import com.supinfo.sup.entity.User;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends Activity {
	public EditText Email;
	public EditText Password;
	public Button submit;
	public TextView result;
	public UserDao UD;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		Email=(EditText)findViewById(R.id.reg_email);
		Password=(EditText)findViewById(R.id.reg_password);
		submit = (Button)findViewById(R.id.reg_in_button);
		result = (TextView)findViewById(R.id.reg_result);
		submit.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v) {
				String email = Email.getText().toString();
				String password =Password.getText().toString();
				UD=DaoFactory.retreiveUserDaoFactory();
				try {
					User u = new User();
					u.setEmail(email);
					u.setPassword(password);
					UD.insertuser(u);
					Context ctx = RegisterActivity.this;       
					 SharedPreferences sp = ctx.getSharedPreferences("SP", MODE_PRIVATE);
					 Editor editor = sp.edit();
					 editor.clear();
					 editor.putString("email", email);
					 editor.putString("password", password);
					 editor.commit();
				} catch (Exception e) {
				}
				Intent intent =new Intent(RegisterActivity.this,MeActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register, menu);
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
}
