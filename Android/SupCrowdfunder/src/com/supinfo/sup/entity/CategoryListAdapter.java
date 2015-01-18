package com.supinfo.sup.entity;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.supinfo.supcrowdfunder.CategoryActivity;
import com.supinfo.supcrowdfunder.SupCrowdfunderActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CategoryListAdapter extends BaseAdapter{
	private Context mContext;
 	private int mResource;
 	private LayoutInflater mInflater;
 	public CategoryListAdapter(Context context, int resource){
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
		Category category = (Category)getItem(position);
		text.setText(category.getName());
		return view;
	}
 	@Override
	public int getCount() {
		return CategoryActivity.cat.size();
	}

	@Override
	public Category getItem(int index) {
		return CategoryActivity.cat.get(index);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}
}
