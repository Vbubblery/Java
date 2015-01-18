package com.supinfo.supcrowdfunder;

import com.supinfo.sup.dao.DaoFactory;
import com.supinfo.sup.dao.ProjectDao;
import com.supinfo.sup.entity.Project;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class DonationActivity extends Activity {
	private EditText ET;
	private Button Ok;
	public ProjectDao PD;
	public Project GetProject;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_donation);
		Bundle bundle = getIntent().getExtras();
		GetProject = (Project) bundle.getSerializable("project");
		ET=(EditText)findViewById(R.id.number);
		Ok=(Button)findViewById(R.id.Ok);
		Ok.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				try {
					PD=DaoFactory.retreiveDaoFactory();
					GetProject.setNowPrice(GetProject.getNowPrice()+
					new Float(ET.getText().toString()));
					PD.updateProject(GetProject);
					Intent intent = new Intent(DonationActivity.this, SupCrowdfunderActivity.class);
					startActivity(intent);
					onDestroy();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}	
		});
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.donation, menu);
		return true;
	}

}
