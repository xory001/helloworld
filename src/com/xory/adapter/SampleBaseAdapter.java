package com.xory.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.xory.helloworld.R;
import com.xory.utility.BaseFunction;

public class SampleBaseAdapter extends BaseAdapter {
	private OnClickListener mClickListener = null;
	private Context mContext = null;
	private LayoutInflater mInflater;
	private List< Map< String, Object >> mListImage = new ArrayList< Map< String, Object >>();
	
	public SampleBaseAdapter() {
	}
	
	public SampleBaseAdapter( Context context, OnClickListener listener )
	{
		mContext = context;
		mClickListener = listener;
		mInflater = LayoutInflater.from( mContext );
		mListImage = BaseFunction.getAllImages( mContext );
	}

	@Override
	public int getCount() {
		//return mListImage.size();
		return 3;
	}

	@Override
	public Object getItem(int position) {
		return mListImage.get( position  );
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if ( null == convertView )
		{
			convertView = mInflater.inflate( R.layout.layout_adapter_base_item, null );
		}
		
		((ImageView)convertView.findViewById( R.id.iv )).
					setImageURI( Uri.parse( mListImage.get( position ).get( "path").toString() ) );
		((TextView)convertView.findViewById( R.id.tv_name )).
		setText( mListImage.get( position ).get( "size").toString() );
		if ( null != mClickListener ){
			Button btn = (Button)convertView.findViewById( R.id.btn );
			btn.setOnClickListener( mClickListener );
			btn.setTag( Integer.valueOf( position ) );
		}
		return convertView;
	}

}
