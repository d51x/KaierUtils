package ru.d51x.kaierutils.TWUtils;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import ru.d51x.kaierutils.App;
import ru.d51x.kaierutils.TWUtils.TWUtilEx;

/**
 * Created by Dmitriy on 18.02.2015.
 */
public class TWUtilProcessingThread extends Thread {

	private Handler handler;
	public TWUtilEx twUtil;

	public TWUtilProcessingThread () {
		super("TWUtilProcessingThread");
		twUtil = null;
	}

	@Override
	public void run() {
		try {
			Log.d ("TWUtilProcessingThread", "run()");
			Looper.prepare();
			handler = new Handler();
			if (( twUtil == null ) & ( TWUtilEx.isTWUtilAvailable() ) ) {
				twUtil = new TWUtilEx(App.getInstance());
				twUtil.Init ();
			}
			Looper.loop();
		} catch (Exception e) {
			Log.d ("TWUtilProcessingThread", "ERROR: run() failed");
		}
	}

	public synchronized void finish() {
		Log.d ("TWUtilProcessingThread", "finish()");
		handler.post(new Runnable() {
			@Override
			public void run() {
				if ( twUtil != null){
					twUtil.Destroy();
					twUtil = null;
				}
			Looper.myLooper().quit();
			}
		});
	}

}
