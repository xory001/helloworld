/**
 * 
 */
package com.xory.helloworld;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author xory
 * @Created data 2014.08.21 14:50
 * @ 显示指定包名下面的Activity    
 */
public class ActivityManager extends ListActivity {
	private final String KEY_TITLE = "title";
	private final String KEY_INTENT = "intent";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
        Intent intent = getIntent();
        String strPath = intent.getStringExtra( Const.EXTRA_KEY );
        
        List< Map< String, Object >> list = null;
        if ( null == strPath ) {
        	strPath = "";
        	AddListViewHeadTitle( "empty package path" );
        	list = new ArrayList< Map< String, Object>>();
        }
        else{
            list = getData( strPath );
        	if ( list.isEmpty() ){
        		AddListViewHeadTitle( "no activity in path: " + strPath );
        	}
        }
    	setListAdapter(new SimpleAdapter(this, list,
                android.R.layout.simple_list_item_1, new String[] { KEY_TITLE },
                new int[] { android.R.id.text1 }));

	}
	
	@Override
	@SuppressWarnings("unchecked")
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		
        Map<String, Object> map = (Map<String, Object>)l.getItemAtPosition(position);
        if ( null != map ){
	        Intent intent = (Intent)map.get( KEY_INTENT );
	        if ( null != intent ){
	        	startActivity(intent);	
	        }
        }
        else{
        	Toast.makeText( this, "please return", Toast.LENGTH_SHORT ).show();
        }
	}
	
	protected void AddListViewHeadTitle( String strErrInfo ){
		TextView tv = new TextView( this );
		tv.setText( strErrInfo );
		getListView().addHeaderView( tv );
	}
	
	protected List< Map< String, Object >> getData( String strPath ){
		List< Map< String, Object >> list = new ArrayList< Map< String, Object >>();
		if ( strPath.isEmpty() ){
			return list;
		}

        Intent mainIntent = new Intent( Const.ACTION, null );
        mainIntent.addCategory( Const.CATEGORY );

        PackageManager pm = getPackageManager();
        List<ResolveInfo> listRosolveInfo = pm.queryIntentActivities(mainIntent, 0);

        if ( null != listRosolveInfo ){
        	for( int i = 0; i < listRosolveInfo.size(); i++ ){
        		ResolveInfo info = listRosolveInfo.get( i );
        		String strClassName = info.activityInfo.name;
        		if ( strClassName.startsWith( strPath ) ){
        			Intent intent = new Intent();
        			intent.setClassName( info.activityInfo.applicationInfo.packageName,
        					info.activityInfo.name );
        			String[] prefix = strClassName.split( "\\." );
        			Map< String, Object > map = new HashMap< String, Object >();
        			map.put( KEY_TITLE, prefix[prefix.length - 1 ].toString() );
        			map.put( KEY_INTENT, intent );
        			list.add( map );
        		}
        	}
        }
	
		return list;
	}
}
