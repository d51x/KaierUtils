package ru.d51x.kaierutils;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

/**
 * Created by Dmitriy on 18.02.2015.
 */
public class PowerAmpProcessingThread extends Thread {

	private Handler handler;
    private PowerAmpProcessing powerAmpProcessing;



	public PowerAmpProcessingThread() {	super("PowerAmpProcessingThread");	}

	@Override
	public void run() {
		try {
			Log.d ("PowerAmpProcessingThread", "run()");
			Looper.prepare();
			handler = new Handler();
            if (powerAmpProcessing!=null) return;
            powerAmpProcessing = new PowerAmpProcessing(App.getInstance());
			Looper.loop();
		} catch (Exception e) {
			Log.d ("PowerAmpProcessingThread", "ERROR: run() failed");
		}
	}

	public synchronized void finish() {
		Log.d ("PowerAmpProcessingThread", "finish()");
		handler.post(new Runnable() {
			@Override
			public void run() {
                if (powerAmpProcessing!=null){
                    powerAmpProcessing.Destroy();
                    powerAmpProcessing = null;
                }
			Looper.myLooper().quit();
			}
		});
	}
}


