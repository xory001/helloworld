package com.xory.adapter;

import com.xory.helloworld.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class SampleBaseAdapter extends BaseAdapter {
	private OnClickListener mClickListener = null;
	private Context mContext = null;
	private LayoutInflater mInflater;
	
	public SampleBaseAdapter() {
	}
	
	public SampleBaseAdapter( Context context, OnClickListener listener )
	{
		mContext = context;
		mClickListener = listener;
		mInflater = LayoutInflater.from( context );
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if ( null == convertView )
		{
			convertView = mInflater.inflate( R.layout.layout_adapter_base_item, null );
		}
		
		//TODO: set subview content
		
		return null;
	}

}
