package com.xory.adapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.xory.helloworld.R;
import com.xory.utility.BaseFunction;

public class ActivityBaseAdapter extends Activity implements OnClickListener, OnItemClickListener {
	private ListView mListView = null;
	private Toast mToast;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mToast = BaseFunction.getToast( this, Toast.LENGTH_SHORT );
		setContentView( R.layout.activity_adapter_base );
		
		SampleBaseAdapter adapter = new SampleBaseAdapter( this , this );
		mListView = (ListView)findViewById( R.id.lv_baseadapter );
		mListView.setAdapter( adapter );
		mListView.setOnItemClickListener( this );
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,	long id) {
		TextView tv = (TextView)view.findViewById( R.id.tv_name );
		if ( null != tv ){
			mToast.setText( "item click: " + tv.getText() );
			mToast.show();
		}
	}
	
	@Override
	public void onClick(View v) {
		Integer pos = (Integer)v.getTag();
		if ( pos >= 0 ){
			View itemView = mListView.getChildAt( pos - mListView.getFirstVisiblePosition() );
			if ( null != itemView ){
				TextView tv = (TextView)itemView.findViewById( R.id.tv_name );
				if ( null != tv ){
					mToast.setText( "btn click: " + tv.getText() );
					mToast.show();
				}
			}
		}
	}

}
