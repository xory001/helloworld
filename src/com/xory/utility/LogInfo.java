package com.xory.utility;

import android.util.Log;

public class LogInfo {
	final private String TAG_INFO = "xory.info";
	
	public void i( String tag, String msg ){
		if ( Log.isLoggable( TAG_INFO, Log.INFO ) )
		{
			Log.i( tag , msg );
		}
	}

}
