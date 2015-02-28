package ru.d51x.kaierutils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.util.Log;

import com.maxmpz.poweramp.player.PowerampAPI;

public class PowerAmpProcessing {
    private BroadcastReceiver powerAmpReceiver;
    private Context context;
    private Handler mHandler;

    public PowerAmpProcessing(Context context) {
        Log.d("PowerAmpProcessing", "PowerAmpProcessing");

        this.context = context;
        mHandler = new Handler();

        powerAmpReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                processIntent(intent);
            }
        };

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(PowerampAPI.ACTION_STATUS_CHANGED);
        intentFilter.addAction(TWUtilConst.TWUTIL_BROADCAST_ACTION_KEY_PRESSED);
        intentFilter.addAction(TWUtilConst.TWUTIL_BROADCAST_ACTION_SHUTDOWN);
        intentFilter.addAction(TWUtilConst.TWUTIL_BROADCAST_ACTION_SLEEP);
        intentFilter.addAction(TWUtilConst.TWUTIL_BROADCAST_ACTION_WAKE_UP);
        context.registerReceiver(powerAmpReceiver, intentFilter);
    }

    private void processIntent(Intent intent) {
        if (intent != null) {
            String action = intent.getAction();
            Log.d("PowerAmpProcessing -> OnReceive: ", action);

            if ( action.equals(TWUtilConst.TWUTIL_BROADCAST_ACTION_SLEEP) ) {
                setPowerAmpPaused();
            }
            else if ( action.equals(TWUtilConst.TWUTIL_BROADCAST_ACTION_SHUTDOWN) ) {
                setPowerAmpPaused();
            }
            else if (action.equals(TWUtilConst.TWUTIL_BROADCAST_ACTION_WAKE_UP)) {
                setPowerAmpResumed();
            }
            else if (action.equals (Intent.ACTION_BOOT_COMPLETED )) {
                setPowerAmpStarted();
            }
            else if (action.equals(TWUtilConst.TWUTIL_BROADCAST_ACTION_KEY_PRESSED)) {
                int key = intent.getIntExtra (TWUtilConst.TWUTIL_BROADCAST_ACTION_KEY_PRESSED, -1);
                sendPowerAmpKeyPressed(key);
            }
            else if (action.equals(PowerampAPI.ACTION_STATUS_CHANGED)) {
                int status = intent.getIntExtra(PowerampAPI.STATUS, -1);
                boolean paused = intent.getBooleanExtra(PowerampAPI.PAUSED, false);
                App.mGlSets.isPowerAmpPlaying = ((status == PowerampAPI.Status.TRACK_PLAYING) && (!paused));
            }
        }
    }

    private void setPowerAmpPaused() {
        if (App.mGlSets.interactWithPowerAmp &&
            App.mGlSets.needWatchSleepPowerAmp &&
            App.mGlSets.isPowerAmpPlaying)
        {
            context.startService(new Intent(PowerampAPI.ACTION_API_COMMAND).putExtra(PowerampAPI.COMMAND,
                                            PowerampAPI.Commands.PAUSE));
        }
    }

    private void setPowerAmpResumed() {
        if (App.mGlSets.interactWithPowerAmp &&
            App.mGlSets.needWatchWakeUpPowerAmp )
        {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    context.startService(new Intent(PowerampAPI.ACTION_API_COMMAND).putExtra(PowerampAPI.COMMAND,
                                                    PowerampAPI.Commands.RESUME));
                }
            }, App.mGlSets.resumeDelayForPowerAmp);
        }
    }

    private void setPowerAmpStarted() {
        if ( App.mGlSets.interactWithPowerAmp &&
             App.mGlSets.needWatchBootUpPowerAmp )
        {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    context.startService(new Intent(PowerampAPI.ACTION_API_COMMAND).putExtra(PowerampAPI.COMMAND,
                                                    PowerampAPI.Commands.TOGGLE_PLAY_PAUSE));
                }
            }, App.mGlSets.resumeDelayForPowerAmp);
        }
    }

    private void sendSetNextFolder() {
        if (App.mGlSets.pressNextFolderPowerAmp) {
            context.startService(new Intent(PowerampAPI.ACTION_API_COMMAND).putExtra(PowerampAPI.COMMAND,
                                            PowerampAPI.Commands.NEXT_IN_CAT));
        }
    }

    private void sendSetPrevFolder() {
        if (App.mGlSets.pressPrevFolderPowerAmp) {
            context.startService(new Intent(PowerampAPI.ACTION_API_COMMAND).putExtra(PowerampAPI.COMMAND,
                                            PowerampAPI.Commands.PREVIOUS_IN_CAT));
        }
    }

    private void sendPowerAmpKeyPressed(int key) {
        if (App.mGlSets.interactWithPowerAmp &&  App.mGlSets.isPowerAmpPlaying) {
            switch (key) {
                case TWUtilConst.TWUTIL_SVC_BUTTON_NEXT:
                    sendSetNextFolder();
                    break;
                case TWUtilConst.TWUTIL_SVC_BUTTON_PREV:
                    sendSetPrevFolder();
                    break;
                default:
                    break;
            }
        }
    }

    public void Destroy() {
        context.unregisterReceiver(powerAmpReceiver);
    }
}
