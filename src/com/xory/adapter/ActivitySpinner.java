package com.xory.adapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.xory.helloworld.R;
import com.xory.lib.utility.BaseFunction;

public class ActivitySpinner extends Activity implements OnItemSelectedListener{
	
	private Toast mToast;
	
	public ActivitySpinner() {
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView( R.layout.activity_adapter_spinner );
		 mToast = BaseFunction.getToast( this, Toast.LENGTH_SHORT );
		
		
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>( this,android.R.layout.simple_spinner_item ){
	
//			@Override
//			public View getDropDownView(int position, View convertView,
//					ViewGroup parent) {
//				// TODO Auto-generated method stub
//				return super.getDropDownView(position, convertView, parent);
//			}
		};
		for( int i = 0; i < 20; i++ )
		{
			arrayAdapter.add( "spinner item: " + i );
		}
    	arrayAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );  
        
    	Spinner spinner = (Spinner)findViewById( R.id.spinner );  
		spinner.setAdapter( arrayAdapter );  
		spinner.setOnItemSelectedListener( this );
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		mToast.setText( "item selected, pos: " + position );
		mToast.show();
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		mToast.setText( "no item selected" );
		mToast.show();
	}

}
