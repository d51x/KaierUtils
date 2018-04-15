package ru.d51x.kaierutils.GPS;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import ru.d51x.kaierutils.App;
import ru.d51x.kaierutils.GPS.GpsProcessing;

public class GpsProcessingThread extends Thread {

    private Handler handler;
    private GpsProcessing gpsProcessing;

    public GpsProcessingThread() {
        super("GpsProcessingThread");
    }

    @Override
    public void run() {
        try {
            Log.d ("GpsProcessingThread", "run()");
            Looper.prepare();
            handler = new Handler();
            if (gpsProcessing!=null) return;
            gpsProcessing = new GpsProcessing(App.getInstance());
            Looper.loop();
        } catch (Exception e) {
            Log.d ("GpsProcessingThread", "ERROR: run() failed");
            Log.d("GpsProcessingThread Exception: ", e.toString());
        }
    }

    public synchronized void finish() {
        Log.d ("GpsProcessingThread", "finish()");
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (gpsProcessing!=null){
                    gpsProcessing.Destroy();
                    gpsProcessing = null;
                }
                Looper.myLooper().quit();
            }
        });
    }
}

