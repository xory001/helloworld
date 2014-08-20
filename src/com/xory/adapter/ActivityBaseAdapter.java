package com.xory.adapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.xory.helloworld.R;

public class ActivityBaseAdapter extends Activity implements OnClickListener {
	private ListView mListView = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView( R.layout.activity_adapter_base );
		
		SampleBaseAdapter adapter = new SampleBaseAdapter( this , this );
		mListView = (ListView)findViewById( R.id.lv_baseadapter );
		mListView.setAdapter( adapter );
	}

	@Override
	public void onClick(View v) {
		if ( v.getId() == (Integer)v.getTag() ){
			Integer pos = (Integer)v.getTag();
			if ( pos >= 0 ){
				View itemView = mListView.getChildAt( pos - mListView.getFirstVisiblePosition() );
				if ( null != itemView ){
					TextView tv = (TextView)itemView.findViewById( R.id.tv_name );
					if ( null != tv ){
						Toast.makeText( this, "pos: " + pos + ", " + tv.getText(), Toast.LENGTH_SHORT ).show();
					}
				}
			}
		}
	}

}
