package ru.d51x.kaierutils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.maxmpz.poweramp.player.PowerampAPI;

/**
 * Created by Dmitriy on 18.02.2015.
 */
public class PowerAmpProcessingThread extends Thread {

	private Handler handler;
    private PowerAmpProcessing powerAmpProcessing;



	public PowerAmpProcessingThread() {
		super("PowerAmpProcessingThread");

	}

	@Override
	public void run() {
		try {
			Log.d ("PowerAmpProcessingThread", "run()");
			Looper.prepare();
			handler = new Handler();
            up();
			Looper.loop();
		} catch (Exception e) {
			Log.d ("PowerAmpProcessingThread", "ERROR: run() failed");
		}
	}

	public synchronized void tryStop() {
		Log.d ("PowerAmpProcessingThread", "tryStop()");
		handler.post(new Runnable() {
			@Override
			public void run() {
            down();
			Looper.myLooper().quit();
			}
		});
	}

    private synchronized void up(){
        if (powerAmpProcessing!=null)
            return;
        powerAmpProcessing = new PowerAmpProcessing(App.getInstance());

    }
    private synchronized void down(){
        if (powerAmpProcessing!=null){
            powerAmpProcessing.down();
            powerAmpProcessing = null;
        }
    }


}


