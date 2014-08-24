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
 *       *** 关于 Handler mHandler = new Handler(){...} 的警告 "This Handler class should be static or leaks might occur" 
 * 原因如下: 
 * 1. Android程序的UI线程中的各种Activity使用了同一个消息队列( Looper.getMainLooper() ) 
 * 2. 在UI线程中, Handler mHandler = new Handler() 会使得 mHandler 关联到 MainLooper()
 *       (其他 Handler 构造函数可以指定一个Looper) 
 * 3. 通过 mHandler.sendMessage( msg ) 发送的消息, 使得 msg.target = mHandler , 消息保持了对Handler的引用 
 * 4. 如果 Activity OnDestroy() 的时候, 此 Activity 内部对象 mHandler 发送的消息还没有处理完, 
 *       那么会导致 mHandler 对象不能GC, 也间接导致    Activity 不能被GC,因此造成资源泄露 
 *       
 *解决方法1: 
 *1. 定义一个继承自 Handler 的类 MyHandler, 在内部保持对  MyActivity 的弱引用, WeakReference<MyActivity> mActivity 
 *2. 如果 MyHandler 定义在 MyActivity 的内部, 则应该定义成 static 
 *3. 在使用 mActivity 前, 判断activity是否为空:
 *       MyActivity activity = mActivity.get(); 
 *       if ( null != activity ){ 
 *           do something 
 *       }
 *4. 在 MyActivity OnDestroy() 的时候, handler.removeCallbacksAndMessages(null), 清除该handler的所有消息
 *
 * 解决方法2:
 * 1. 定义一个继承自 Handler 的类 MyHandler, 内部成员变 MyActivity mActivity, 保持对   MyActivity 的强引用 
 * 2. 如果 MyHandler 定义在 MyActivity 的内部, 则应该定义成 static 
 * 3. 在    Activity OnDestroy() 的时候, handler.removeCallbacksAndMessages(null), 清除该handler的所有消息, 
 *       同时, 设置 MyHandler 的 mActivity = null 来解除对 MyActivity 的强引用
 *       
 *       ***以上描述可参考: http://my.oschina.net/dragonboyorg/blog/160986 
 *       ***本类    ActivityMultiThread 采用第一种方法,弱引用
 * 
 */
public class ActivityMultiThread extends Activity implements OnClickListener {

	private TextView mTVInfo;
	private Thread mThreadClock;
	private boolean mbRunning = true;
	private HandlerMsg mHandler;

	//参考:https://www.ibm.com/developerworks/cn/java/j-lo-enum/
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
					activity.mTVInfo.setText("逝去了 " + msg.obj + " 秒");
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
