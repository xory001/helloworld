package com.xory.lib.utility;

import android.util.Log;

/**
 * 
 * @author 朱起伟
 * @category Open Declaration boolean android.util.Log.isLoggable(String tag,
 *           int level)
 * 
 * 
 *           Checks to see whether or not a log for the specified tag is
 *           loggable at the specified level. The default level of any tag is
 *           set to INFO. This means that any level above and including INFO
 *           will be logged. Before you make any calls to a logging method you
 *           should check to see if your tag should be logged. You can change
 *           the default level by setting a system property: 'setprop
 *           log.tag.<YOUR_LOG_TAG> <LEVEL>' Where level is either VERBOSE,
 *           DEBUG, INFO, WARN, ERROR, ASSERT, or SUPPRESS. SUPPRESS will turn
 *           off all logging for your tag. You can also create a local.prop file
 *           that with the following in it: 'log.tag.<YOUR_LOG_TAG>=<LEVEL>' and
 *           place that in /data/local.prop.
 * @exception Throws
 *                IllegalArgumentException - is thrown if the tag.length() > 23
 * 
 * 
 */
public class LogInfo {
	final private static String TAG = "com.xory.lib";

	public static void v(String tag, String msg) {
		if (Log.isLoggable(tag, Log.VERBOSE)) {
			Log.v(tag, msg);
		}
	}

	public static void v(String msg) {
		if (Log.isLoggable(TAG, Log.VERBOSE)) {
			Log.v(TAG, msg);
		}
	}

	public static void d(String tag, String msg) {
		if (Log.isLoggable(tag, Log.DEBUG)) {
			Log.d(tag, msg);
		}
	}

	public static void d(String msg) {
		if (Log.isLoggable(TAG, Log.DEBUG)) {
			Log.d(TAG, msg);
		}
	}

	public static void i(String tag, String msg) {
		if (Log.isLoggable(tag, Log.INFO)) {
			Log.i(tag, msg);
		}
	}

	public static void i(String msg) {
		if (Log.isLoggable(TAG, Log.INFO)) {
			Log.i(TAG, msg);
		}
	}

	public static void w(String tag, String msg) {
		if (Log.isLoggable(tag, Log.WARN)) {
			Log.w(tag, msg);
		}
	}

	public static void w(String msg) {
		if (Log.isLoggable(TAG, Log.WARN)) {
			Log.w(TAG, msg);
		}
	}

	public static void e(String tag, String msg) {
		if (Log.isLoggable(tag, Log.ERROR)) {
			Log.e(tag, msg);
		}
	}

	public static void e(String msg) {
		if (Log.isLoggable(TAG, Log.ERROR)) {
			Log.e(TAG, msg);
		}
	}

	public static void wtf(String tag, String msg) { // ASSERT
		if (Log.isLoggable(tag, Log.ASSERT)) {
			Log.wtf(tag, msg);
		}
	}

	public static void wtf(String msg) { // ASSERT
		if (Log.isLoggable(TAG, Log.ASSERT)) {
			Log.wtf(TAG, msg);
		}
	}

}
