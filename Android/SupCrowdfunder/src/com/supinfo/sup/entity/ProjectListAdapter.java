package com.supinfo.sup.entity;


import com.supinfo.supcrowdfunder.SupCrowdfunderActivity;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ProjectListAdapter extends BaseAdapter{
	private Context mContext;
 	private int mResource;
 	private LayoutInflater mInflater;
 	public ProjectListAdapter(Context context, int resource){
        this.mContext = context;
        this.mResource = resource;
        this.mInflater = (LayoutInflater)context.
        		getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view;
		TextView text;
		if (convertView == null) {
		    view = this.mInflater.inflate(this.mResource, parent, false);
		} else {
			view = convertView;
		}
		text = (TextView) view;
		String body =null;
		Project project = (Project) getItem(position);
		 if(project.getBody().length()>=19){
			 body= project.getBody().substring(0, 20);
		 	}else
		 		{
		 			body=project.getBody();
		 		}
		text.setText("Title:"+project.getTitle()+"\n"+
						"Body:"+body+"\n"+project.getNowPrice()+"/"+project.getPrice());
		
		return view;
	}

	@Override
	public int getCount() {
		return SupCrowdfunderActivity.pro.size();
	}

	@Override
	public Project getItem(int index) {
		return SupCrowdfunderActivity.pro.get(index);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}
}
