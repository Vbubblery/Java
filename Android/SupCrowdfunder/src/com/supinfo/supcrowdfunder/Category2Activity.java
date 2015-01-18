package com.supinfo.supcrowdfunder;

import java.util.ArrayList;
import com.supinfo.sup.dao.DaoFactory;
import com.supinfo.sup.dao.ProjectDao;
import com.supinfo.sup.entity.Category;
import com.supinfo.sup.entity.Project;
import com.supinfo.sup.entity.ProjectListAdapter;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ListView;

public class Category2Activity extends Activity {
	public static ArrayList<Project> pro =new ArrayList<Project>();
	private Category Cg;
	private ListView LV;
	public Category c;
	public ProjectDao mPdao;
	public static ProjectListAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_category2);
		Bundle bundle = getIntent().getExtras();
		c = (Category) bundle.getSerializable("project");
		c.getID();
		Long id = new Long(1);
		mPdao = DaoFactory.retreiveDaoFactory();
	    mPdao.retrieveprojectById(id);
		adapter = new ProjectListAdapter(this, android.R.layout.simple_list_item_1);
		LV =(ListView)findViewById(R.id.lv_project);
		LV.setAdapter(adapter);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.category2, menu);
		return true;
	}

}
