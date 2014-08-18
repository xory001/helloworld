package com.xory.utility;

import android.util.Log;

public class LogInfo {
	//final private String TAG_INFO = "xory.info";
	
	public static void i( String tag, String msg ){
		if ( Log.isLoggable( ConstUtil.TAG_XORY_UTIL, Log.INFO ) )
		{
			Log.i( tag , msg );
		}
	}
}
