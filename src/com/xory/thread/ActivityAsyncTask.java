/**
 * 
 */
package com.xory.thread;

import android.app.Activity;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

/**
 * @author xory
 * @data   2014.08.20 20:32
 *  learn multi thread
 *  multi thread reference: http://tech.it168.com/a2011/0922/1250/000001250289_all.shtml
 *  thread and handler reference: http://smallwoniu.blog.51cto.com/3911954/1252081
 *  AsyncTask feference: http://www.cnblogs.com/xiaoluo501395377/p/3430542.html
 *                       http://smallwoniu.blog.51cto.com/3911954/1252156
 */
public class ActivityAsyncTask extends Activity {
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	
	protected void DownloadImage( Uri url ){
		//AsyncTask asyncTask = new AsyncTask( void, void ,void )();
	}
}
