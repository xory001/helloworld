/**
 * 
 */
package com.xory.thread;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.net.Uri;
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
		
		//http://www.baidu.com/img/bd_logo.png
		new Thread(){
			@Override
			public void run() {
				URL url; 
				try {
					url = new URL( "http://www.baidu.com/img/bd_logo.png" );
				} catch (MalformedURLException e) {
					e.printStackTrace();
					return;
				}
				try {
					HttpURLConnection httpCnn = (HttpURLConnection)url.openConnection();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.start();
	}

	
	
	protected void DownloadImage( Uri url ){
		//AsyncTask asyncTask = new AsyncTask( void, void ,void )();
	}
}
