package com.xory.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.xory.helloworld.R;

public class ActivitySimpleAdapter extends Activity implements OnItemClickListener {
	private Toast mToast = null;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView( R.layout.activity_adapter_simple );
		
		List< Map< String, Integer > > list = new ArrayList< Map< String, Integer > >();
		for( int i = 0; i < 20; i++ )
		{
			Map< String, Integer > map = new HashMap< String, Integer >();
			map.put( "image", R.drawable.btn_radio_on_selected );
			map.put( "key1", Integer.valueOf( i * 10 ) );
			map.put( "key2", Integer.valueOf( i * 100 ) );
			list.add( map );
		}
		
		//int[] arr1 = new int[]{ 1, 2, 3 };
		//int[] arr2 = { 1, 2, 3 };
		String[] keys = new String[]{ "image", "key1", "key2" };
		int[] ids = new int[]{ R.id.iv, R.id.tv_name, R.id.tv_size };
		
		SimpleAdapter simpleAdapter = new SimpleAdapter( this, list, 
				R.layout.layout_adapter_simple_item, keys, ids );
		
		
		ListView lv = (ListView)findViewById( R.id.lv_simpleadapter );
		lv.setAdapter( simpleAdapter );
		lv.setOnItemClickListener( this );
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		if ( parent instanceof ListView ){
			String strInfo = "key1: " + ((TextView)view.findViewById( R.id.tv_name )).getText() 
					+ ", key2: " + ((TextView)view.findViewById( R.id.tv_size )).getText();
			if ( null == mToast )
			{
				mToast = Toast.makeText( this , strInfo, Toast.LENGTH_LONG );
			}
			else
			{
				mToast.setText( strInfo );
			}
			mToast.show();
		}
	}

}
