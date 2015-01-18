package com.supinfo.RESTful;

import org.json.JSONException;

import com.supinfo.cubbyhole.MainActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class dirfileadapter extends BaseAdapter{
	private Context mContext;
 	private int mResource;
 	private LayoutInflater mInflater;
 	public dirfileadapter(Context context, int resource){
        this.mContext = context;
        this.mResource = resource;
        this.mInflater = (LayoutInflater)context.
        		getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 	}
	public int getCount() {
		// TODO Auto-generated method stub
		return MainActivity.df.length();
	}

	public Object getItem(int index) {
		try {
			return MainActivity.df.get(index);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return index;
	}

	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

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
		text.setText(getItem(position).toString());
		return view;
	}

}
