package com.xory.helloworld;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import android.app.ListActivity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.xory.app.StdSubParcelable;
import com.xory.lib.sys.StorageManagerEx;
import com.xory.lib.sys.StorageVolume;
import com.xory.lib.utility.BaseFunction;
import com.xory.lib.utility.LogInfo;
import com.xory.syntax.StandardSubClass;

public class MainActivity extends ListActivity implements OnClickListener,Observer{

	final private String TAG = "helloworld.MainActivity";
	private NotificationManager  notifyMgr;
	private List< Map< String, Object > > mListActivity = new ArrayList< Map< String, Object > >();

	public MainActivity(){
								//title						package
		String[] strItemText = {"Adapter and AdapterView", "com.xory.adapter",
								"multi thread and async operate", "com.xory.thread",
								"graphics", "com.xory.graphics",
								"app class", "com.xory.app",
								"views", "com.xory.views"};
		for( int i = 0; i < strItemText.length; i += 2 ){
			Map< String, Object > mapActivity = new HashMap< String, Object >();
			mapActivity.put( "title", strItemText[i] );
			mapActivity.put( "package", strItemText[i + 1 ] );
			mListActivity.add( mapActivity );
		}

	}

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		 setListAdapter(new SimpleAdapter(this, mListActivity,
	                android.R.layout.simple_list_item_1, new String[] { "title" },
	                new int[] { android.R.id.text1 }));
		//setContentView(R.layout.activity_main);
		 
		 StorageManagerEx storageMgr = new StorageManagerEx( this );
		 StorageVolume[] volumes = storageMgr.getVolumeList();
		 String[] mountPath = storageMgr.getVolumePaths();
		 StorageManagerEx.GetFreeStorageSize( "/mnt/sdcard" );
	
		//syntax package
		 syntaxTest();
		 
		// BaseFunction.getDisplayMetrics( this );
		String[] volumePaths = BaseFunction.getVolumeList( this );
		String strDcim = Environment.getExternalStoragePublicDirectory( Environment.DIRECTORY_DCIM ).toString();
		String strSd = Environment.getRootDirectory().toString();
		String strSDcard = Environment.getExternalStorageDirectory().toString();
		String strSdcardUrl =  Environment.getExternalStorageDirectory().toURI().toString();
		for ( String strPath: volumePaths ){
			if ( strPath.equals( strSDcard )){
				boolean bremove = Environment.isExternalStorageRemovable();
				int j = 0;
				j++;
			}
		}
		Environment.getExternalStorageState();

		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
    @Override
    @SuppressWarnings("unchecked")
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Map<String, Object> map = (Map<String, Object>)l.getItemAtPosition(position);
    	Intent intent = new Intent( this, ActivityManager.class );
    	StdSubParcelable ssb = new StdSubParcelable();
    	ssb.init();
    	//ssb.mnContainerHashCode = ssb.hashCode();
    	intent.putExtra("data",  ssb );
       	intent.putExtra( Const.EXTRA_KEY, map.get( "package" ).toString() );
    	startActivity(intent);
    }

	@Override
	public void onClick(View v) {
		switch ( v.getId() )
		{
		case R.id.BoundServiceTest:
			Log.i( TAG, "BoundServiceTest clicked" );
			break;
			
		case R.id.StartedServiceTest:
			Log.i( TAG, "StartedServiceTest clicked" ); 
			startActivity( new Intent( this, StartedServiceActivity.class ) );
			break;
			
		case R.id.btn_draw_activity:
			startActivity( new Intent( this, DrawActivity.class ) );
			break;
			
		case R.id.btn_send_global_cast:
			sendBroadcast( new Intent( BroadcastRecvTest.TEST_ACTION ) );
			break;
			
		case R.id.btn_send_inner_cast:
			sendBroadcast(  new Intent( BroadcastActivity.TEST_ACTION ) );
			break;	
			
		case R.id.btn_start_notify_activity:
			startActivity(  new Intent( this, ActivityNotification.class ) );
			break;	
			
		case R.id.btn_bc_activity:
			startActivity( new Intent( this, BroadcastActivity.class ) );
			break;	
			
		case R.id.btn_notify_show:
			Notification notifyShow = new Notification();
			notifyShow.icon = R.drawable.ic_launcher;
			notifyShow.tickerText = "it is notify show";
			notifyShow.defaults = Notification.DEFAULT_SOUND|Notification.DEFAULT_VIBRATE;
			notifyShow.flags = Notification.FLAG_AUTO_CANCEL;
			PendingIntent nullPendIntent = PendingIntent.getActivity( this, 0, new Intent(), 0 );
			notifyShow.setLatestEventInfo( this, "notifyTitle", "notifyText", nullPendIntent );
			notifyMgr.notify( 0x100, notifyShow );
			break;	
			
		case R.id.btn_notify_start:
			Notification notifyStart = new Notification();
			notifyStart.icon = R.drawable.ic_launcher;
			notifyStart.tickerText = "it is notify start";
			notifyStart.flags = Notification.FLAG_AUTO_CANCEL;
			notifyStart.defaults = Notification.DEFAULT_SOUND;
			Intent acIntent = new Intent( this, ActivityNotification.class );
			PendingIntent pdIntent = PendingIntent.getActivity( this, 0, acIntent, 0 );
			notifyStart.setLatestEventInfo( this, "notifyTitle", "notifyText", pdIntent );
			notifyMgr.notify( 0x111 , notifyStart );
			break;	
			
			
		default:
			break;
		}
		
	}



	@Override
	public void update(Observable observable, Object data) {
				
	}
	
	void syntaxTest(){
//		int n =  ClassInitSquence.n3;
//		ClassInitSquence cis = new ClassInitSquence();
//		int n1 = ClassInitSquence.n3;
		try {
			Class Class_ClassInitSquence = Class.forName( "com.xory.syntax.ClassInitSquence", false, getClassLoader() );
			Field fld = Class_ClassInitSquence.getDeclaredField( "n3" );
			int n3 = fld.getInt( null );
			ClassLoader cld = Class_ClassInitSquence.getClassLoader();
			LogInfo.i("");
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		StandardSubClass ssc = new StandardSubClass( "standard sub class", "data0" );
		
		LogInfo.i( ssc.toString() );
	}
}
