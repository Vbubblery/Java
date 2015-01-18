package com.supinfo.supcrowdfunder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



public class LoginActivity extends Activity {
	public EditText Email;
	public EditText Password;
	public Button submit;
	public TextView result;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		Email=(EditText)findViewById(R.id.email);
		Password=(EditText)findViewById(R.id.password);
		submit = (Button)findViewById(R.id.sign_in_button);
		result = (TextView)findViewById(R.id.login_result);
		submit.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v) {
				String email = Email.getText().toString();
				String password =Password.getText().toString();
				if("admin".equals(email) && "123".equals(password)){
					 Intent intent = new Intent(LoginActivity.this,MeActivity.class);
					 startActivity(intent);
					 Context ctx = LoginActivity.this;       
					 SharedPreferences sp = ctx.getSharedPreferences("SP", MODE_PRIVATE);
					 Editor editor = sp.edit();
					 editor.putString("email", email);
					 editor.putString("password", password);
					 editor.commit();
					 onDestroy();
            }else{
                result.setText("Login failed. Invalid user or password incorrect.");
            }
			}
		}
				);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.login, menu);
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
