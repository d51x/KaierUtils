package ru.d51x.kaierutils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.util.Log;

import com.maxmpz.poweramp.player.PowerampAPI;

/**
 * Created by pyatyh_ds on 26.02.2015.
 */
public class PowerAmpProcessing {
    private boolean receiveStatusIntent = false;
    private boolean isPowerampPlaying = false;
    private boolean isNeedPlayOnWakeUp = false;
    private BroadcastReceiver powerampReceiver;
    private BroadcastReceiver sleepReceiver;

    private Context context;
    private Handler handler;

    public PowerAmpProcessing(Context context) {
        this.context = context;
        handler = new Handler();

        powerampReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (!App.mGlobalSettings.interactWithPowerAmp) return;
                if (intent != null) {
                    String action = intent.getAction();
                    receiveStatusIntent = true;
                    if (action.equals(TWUtilConst.TWUTIL_COMMAND_KEY_PRESS)) {
                        // next folder
                        int keypressed = intent.getIntExtra (TWUtilConst.TWUTIL_BROADCAST_ACTION_KEY_PRESSED, -1);
                        switch (keypressed) {
                            case TWUtilConst.TWUTIL_SVC_BUTTON_NEXT:
                                if (App.mGlobalSettings.pressNextFolderPowerAmp)
                                    App.getInstance().startService(new Intent(PowerampAPI.ACTION_API_COMMAND).putExtra(PowerampAPI.COMMAND, PowerampAPI.Commands.NEXT_IN_CAT));
                                break;
                            case TWUtilConst.TWUTIL_SVC_BUTTON_PREV:
                                if (App.mGlobalSettings.pressPrevFolderPowerAmp)
                                    App.getInstance().startService(new Intent(PowerampAPI.ACTION_API_COMMAND).putExtra(PowerampAPI.COMMAND, PowerampAPI.Commands.PREVIOUS_IN_CAT));
                                break;
                            default:
                                break;
                        }
                    } else if (action.equals(PowerampAPI.ACTION_STATUS_CHANGED)) {
                        int status = intent.getIntExtra(PowerampAPI.STATUS, -1);
                        boolean paused = intent.getBooleanExtra(PowerampAPI.PAUSED, false);
                        boolean failed = intent.getBooleanExtra(PowerampAPI.FAILED, false);
                        receiveStatusIntent = ((status == PowerampAPI.Status.TRACK_PLAYING) && (!paused));
                    }

                }
            }
        };

        sleepReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (!App.mGlobalSettings.interactWithPowerAmp) return;
                if (intent != null) {
                    String action = intent.getAction();
                    if (action.equals(TWUtilConst.TWUTIL_BROADCAST_ACTION_SLEEP)) {
                        doSleep(isPowerampPlaying);
                    } else if (action.equals(TWUtilConst.TWUTIL_BROADCAST_ACTION_SHUTDOWN)) {
                        doSleep(isPowerampPlaying);
                    } else if (action.equals(TWUtilConst.TWUTIL_BROADCAST_ACTION_WAKE_UP)) {
                        doWakeUp();
                    } else if (action.equals (Intent.ACTION_BOOT_COMPLETED )) {
                        doBootUp();

                    }
                }
            }
        };

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(PowerampAPI.ACTION_STATUS_CHANGED);
        intentFilter.addAction(TWUtilConst.TWUTIL_BROADCAST_ACTION_KEY_PRESSED);
        context.registerReceiver(powerampReceiver, intentFilter);

        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction(TWUtilConst.TWUTIL_BROADCAST_ACTION_SHUTDOWN);
        intentFilter2.addAction(TWUtilConst.TWUTIL_BROADCAST_ACTION_SLEEP);
        intentFilter2.addAction(TWUtilConst.TWUTIL_BROADCAST_ACTION_WAKE_UP);
        context.registerReceiver(sleepReceiver, intentFilter2);
    }

    private void doSleep(boolean a_powerampPlaying) {
        if (!App.mGlobalSettings.interactWithPowerAmp) return;
        if (!App.mGlobalSettings.needWatchSleepPowerAmp) return;
        isNeedPlayOnWakeUp = receiveStatusIntent && a_powerampPlaying;
        if (isNeedPlayOnWakeUp) {
            context.startService(new Intent(PowerampAPI.ACTION_API_COMMAND).putExtra(PowerampAPI.COMMAND, PowerampAPI.Commands.PAUSE));
        }
    }

    private void doWakeUp() {
        if (!App.mGlobalSettings.interactWithPowerAmp) return;
        if (!App.mGlobalSettings.needWatchWakeUpPowerAmp) return;
        if (isNeedPlayOnWakeUp) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    context.startService(new Intent(PowerampAPI.ACTION_API_COMMAND).putExtra(PowerampAPI.COMMAND, PowerampAPI.Commands.RESUME)); // ставим на плай
                }
            }, App.mGlobalSettings.powerampResumeDelay);
        }
    }

    private void doBootUp() {
        if (!App.mGlobalSettings.interactWithPowerAmp) return;
        if (!App.mGlobalSettings.needWatchBootUpPowerAmp) return;
        if (isNeedPlayOnWakeUp) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    context.startService(new Intent(PowerampAPI.ACTION_API_COMMAND).putExtra(PowerampAPI.COMMAND, PowerampAPI.Commands.RESUME)); // ставим на плай
                }
            }, App.mGlobalSettings.powerampResumeDelay);
        }
    }

    public void down() {
        context.unregisterReceiver(powerampReceiver);
        context.unregisterReceiver(sleepReceiver);
    }
}
