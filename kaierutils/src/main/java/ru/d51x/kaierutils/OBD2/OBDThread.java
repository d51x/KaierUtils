package ru.d51x.kaierutils.OBD2;

import android.util.Log;
import java.util.Timer;
import java.util.TimerTask;

import ru.d51x.kaierutils.App;

/**
 * Created by Dmitriy on 18.02.2015.
 */
public class OBDThread extends Thread {

    private long TimeStamp1, TimeStamp2;
    Timer mTimer;
    MAF_TimerTask mMAFTimerTask;

	public OBDThread() {
        super("OBDThread");
        isActive = true;
    }
    public volatile boolean isActive = false;

    class MAF_TimerTask extends TimerTask {
        @Override
        public void run() {
                if ( ! App.GS.isReverseMode ) {
                    App.obd.processOBD_MAF();
                    App.obd.processData();
                }

            }
        }


	@Override
	public void run() {
        boolean firstStart = true;

        try {
			Log.d ("OBDThread", "run()");
            //while (true) {
            TimeStamp1 = System.currentTimeMillis();
            TimeStamp2 = System.currentTimeMillis();

            mTimer = new Timer();
            mMAFTimerTask = new MAF_TimerTask();


            while (!Thread.currentThread().isInterrupted() || isActive )
            {
                if ( App.obd.isConnected) {
                    // send or process data

                    if ( firstStart ) {
                        mTimer.schedule(mMAFTimerTask, 1000, 1000);
                        firstStart = false;
                    }
                    // получим остальные данные, все кроме MAF
                    // долбим постоянно

                    //App.obd.processData();
                    //Thread.sleep(50);
                    if ( App.GS.isReverseMode && (! App.obd.activeMAF) && (! App.obd.activeOther)) {

                        App.obd.process_parking_sensors();
                        Thread.sleep(50);
                    }



                    TimeStamp2 = System.currentTimeMillis();
                    //if ( (TimeStamp2 - TimeStamp1) > (1000*60)) // 1 min
                    if ( (TimeStamp2 - TimeStamp1) > (1000*30)) // 30 sec
                    {
                        App.obd.oneTrip.saveData();
                        App.obd.todayTrip.saveData();
                        App.obd.totalTrip.saveData();
                        TimeStamp1 = TimeStamp2;
                    }

                } else {
                    //try to connect
//                        if (App.GS.btState) {
                            App.obd.isConnected = App.obd.connect();
                            if (App.obd.isConnected) {
                                App.obd.prepareData();
                            } else {
                                Thread.sleep(3000);
                            }
//                        }
                }
            }

            if (mTimer != null) {
                mTimer.cancel();
                mTimer = null;
            }

            App.obd.disconnect();
		} catch (Exception e) {
			Log.d ("OBDThread", "ERROR: run() failed");
		}
	}

	public synchronized void finish() {
		Log.d ("OBDThread", "finish()");
        interrupt();
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
        isActive = false;
        App.obd.disconnect();

	}
}


