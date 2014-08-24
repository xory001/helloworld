/**
 * 
 */
package com.xory.thread;

import java.lang.ref.WeakReference;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.xory.helloworld.Const;
import com.xory.helloworld.R;

/**
 * @author xory
 * @data 2014.08.20 20:32 
 *
 * learn multi thread 
 * multi thread reference: http://tech.it168.com/a2011/0922/1250/000001250289_all.shtml thread and
 * handler reference: http://smallwoniu.blog.51cto.com/3911954/1252081
 * AsyncTask feference:
 *       http://www.cnblogs.com/xiaoluo501395377/p/3430542.html
 *       http://smallwoniu.blog.51cto.com/3911954/1252156
 * 
 *       *** ���� Handler mHandler = new Handler(){...} �ľ��� "This Handler class should be static or leaks might occur" 
 * ԭ������: 
 * 1. Android�����UI�߳��еĸ���Activityʹ����ͬһ����Ϣ����( Looper.getMainLooper() ) 
 * 2. ��UI�߳���, Handler mHandler = new Handler() ��ʹ�� mHandler ������ MainLooper()
 *       (���� Handler ���캯������ָ��һ��Looper) 
 * 3. ͨ�� mHandler.sendMessage( msg ) ���͵���Ϣ, ʹ�� msg.target = mHandler , ��Ϣ�����˶�Handler������ 
 * 4. ��� Activity OnDestroy() ��ʱ��, �� Activity �ڲ����� mHandler ���͵���Ϣ��û�д�����, 
 *       ��ô�ᵼ�� mHandler ������GC, Ҳ��ӵ���    Activity ���ܱ�GC,��������Դй¶ 
 *       
 *�������1: 
 *1. ����һ���̳��� Handler ���� MyHandler, ���ڲ����ֶ�  MyActivity ��������, WeakReference<MyActivity> mActivity 
 *2. ��� MyHandler ������ MyActivity ���ڲ�, ��Ӧ�ö���� static 
 *3. ��ʹ�� mActivity ǰ, �ж�activity�Ƿ�Ϊ��:
 *       MyActivity activity = mActivity.get(); 
 *       if ( null != activity ){ 
 *           do something 
 *       }
 *4. �� MyActivity OnDestroy() ��ʱ��, handler.removeCallbacksAndMessages(null), �����handler��������Ϣ
 *
 * �������2:
 * 1. ����һ���̳��� Handler ���� MyHandler, �ڲ���Ա�� MyActivity mActivity, ���ֶ�   MyActivity ��ǿ���� 
 * 2. ��� MyHandler ������ MyActivity ���ڲ�, ��Ӧ�ö���� static 
 * 3. ��    Activity OnDestroy() ��ʱ��, handler.removeCallbacksAndMessages(null), �����handler��������Ϣ, 
 *       ͬʱ, ���� MyHandler �� mActivity = null ������� MyActivity ��ǿ����
 *       
 *       ***���������ɲο�: http://my.oschina.net/dragonboyorg/blog/160986 
 *       ***����    ActivityMultiThread ���õ�һ�ַ���,������
 * 
 */
public class ActivityMultiThread extends Activity implements OnClickListener {

	private TextView mTVInfo;
	private Thread mThreadClock;
	private boolean mbRunning = true;
	private HandlerMsg mHandler;

	//�ο�:https://www.ibm.com/developerworks/cn/java/j-lo-enum/
	public enum WeekDayEnum {
		Mon, Tue, Wed, Thu, Fri, Sat, Sun
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_thread_mutilthread);

		Button btn = (Button) findViewById(R.id.btn_mutilthread_start);
		btn.setOnClickListener(this);
		btn = (Button) findViewById(R.id.btn_mutilthread_stop);
		btn.setOnClickListener(this);
		mTVInfo = (TextView) findViewById(R.id.tv_mutilthread_info);
		mHandler = new HandlerMsg(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_mutilthread_start:
			if (null == mThreadClock) {
				mbRunning = true;
				mThreadClock = new Thread(new Runnable() {
					@Override
					public void run() {
						int timer = 0;
						while (mbRunning) {
							try {
								Thread.sleep(1000);
								timer++;
								Message msg = mHandler.obtainMessage();
								msg.obj = timer;
								msg.what = 0;
								mHandler.sendMessage(msg);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				});

				mThreadClock.start();
			}

			break;

		case R.id.btn_mutilthread_stop:
			mbRunning = false;
			mThreadClock = null;
			break;
		}
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finish();
	}

	static class HandlerMsg extends Handler {
		private WeakReference<ActivityMultiThread> mHostActivity;

		public HandlerMsg(ActivityMultiThread activity) {
			mHostActivity = new WeakReference<ActivityMultiThread>(activity);
		}

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0:
				ActivityMultiThread activity = mHostActivity.get();
				if (null != activity) {
					activity.mTVInfo.setText("��ȥ�� " + msg.obj + " ��");
					Log.i(Const.TAG_APP,
							"ActivityMultiThread::Handler::handleMessage, activity.mTVInfo != null");
				} else {
					Log.i(Const.TAG_APP,
							"ActivityMultiThread::Handler::handleMessage, activity.mTVInfo == null");
				}
			}
		}
	}

	@Override
	protected void onDestroy() {
		mbRunning = false;
		mHandler.removeCallbacksAndMessages(null);
		super.onDestroy();
	}
}
