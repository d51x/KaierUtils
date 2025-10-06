package ru.d51x.kaierutils.OBD2;

import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

import ru.d51x.kaierutils.App;

/**
 * Created by Dmitriy on 18.02.2015.
 */
public class OBDThread extends Thread {

    private long readingTime1, readingTime2;
    private long mafRequestTimePrev, mafRequestTime;
    Timer obdTimer;
    ObdTimerTask obdTimerTask;

	public OBDThread() {
        super("OBDThread");
        isActive = true;
    }
    public volatile boolean isActive = false;

    class ObdTimerTask extends TimerTask {
        @Override
        public void run() {
                if ( ! App.GS.isReverseMode ) {
                    if (!App.obd.newObdProcess) {
                        App.obd.processOBD_MAF();
                        App.obd.processData();
                    }
                }

            }
        }


	@Override
	public void run() {
        boolean firstStart = true;

        try {
			Log.d ("OBDThread", "run()");
            //while (true) {
            readingTime1 = System.currentTimeMillis();
            readingTime2 = System.currentTimeMillis();

            obdTimer = new Timer();
            obdTimerTask = new ObdTimerTask();


            while (!Thread.currentThread().isInterrupted() || isActive )
            {
                if ( App.obd.isConnected) {
                    // send or process data

                    if ( firstStart ) {
                        obdTimer.schedule(obdTimerTask, 1000, 1000);
                        firstStart = false;
                    }

                    if (App.obd.newObdProcess) {
                        App.obd.newProcessAllData();
                    }

                    //------- reqiesting MAF period 1 sec
                    mafRequestTime = System.currentTimeMillis();
                    if ( (mafRequestTime - mafRequestTimePrev) > 1000) // 1 sec
                    {

                        mafRequestTimePrev = mafRequestTime;
                    }
                    // получим остальные данные, все кроме MAF
                    // долбим постоянно

                    //App.obd.processData();
                    //Thread.sleep(50);
                    if ( App.GS.isReverseMode && (! App.obd.activeMAF) && (! App.obd.activeOther)) {

                        App.obd.process_parking_sensors();
                        Thread.sleep(50);
                    }



                    readingTime2 = System.currentTimeMillis();
                    //if ( (TimeStamp2 - TimeStamp1) > (1000*60)) // 1 min
                    if ( (readingTime2 - readingTime1) > (1000*30)) // 30 sec
                    {
                        App.obd.oneTrip.saveData();
                        App.obd.todayTrip.saveData();
                        App.obd.totalTrip.saveData();
                        readingTime1 = readingTime2;
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

            if (obdTimer != null) {
                obdTimer.cancel();
                obdTimer = null;
            }

            App.obd.disconnect();
		} catch (Exception e) {
			Log.d ("OBDThread", "ERROR: run() failed");
		}
	}

	public synchronized void finish() {
		Log.d ("OBDThread", "finish()");
        interrupt();
        if (obdTimer != null) {
            obdTimer.cancel();
            obdTimer = null;
        }
        isActive = false;
        App.obd.disconnect();

	}
}


