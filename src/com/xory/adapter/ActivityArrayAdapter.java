/**
 * 
 */
package com.xory.adapter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.xory.helloworld.R;

/**
 * @author 朱起伟
 *
 */
public class ActivityArrayAdapter extends Activity {
	 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>( this , android.R.layout.simple_list_item_1 );
		ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>( this , R.layout.layout_adapter_array_item, R.id.textview1 );
		for( int i = 0; i < 20; i++ )
		{
			arrayAdapter.add( Integer.valueOf( i ) );
		}
		
		setContentView( R.layout.activity_adapter_array );
		((ListView)findViewById( R.id.lv_arrayadapter )).setAdapter( arrayAdapter );
	}
}
