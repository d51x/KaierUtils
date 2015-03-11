package ru.d51x.kaierutils;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.util.Log;
import android.os.Bundle;
import android.graphics.Bitmap;

import com.maxmpz.poweramp.player.PowerampAPI;
import com.maxmpz.poweramp.player.PowerampAPI.Track;

import java.util.List;

public class PowerAmpProcessing {
    private BroadcastReceiver powerAmpReceiver;
    private Context context;
    private Handler mHandler;

    public PowerAmpProcessing(Context context) {
        Log.d("PowerAmpProcessing", "PowerAmpProcessing");


        this.context = context;
        mHandler = new Handler();

	    setPowerAmpStarted();

        powerAmpReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                processIntent(intent);
            }
        };

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(PowerampAPI.ACTION_STATUS_CHANGED);
        intentFilter.addAction(PowerampAPI.ACTION_AA_CHANGED);
        intentFilter.addAction(PowerampAPI.ACTION_TRACK_CHANGED);
        intentFilter.addAction(Intent.ACTION_BOOT_COMPLETED);
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
                App.GS.isPowerAmpPlaying = ((status == PowerampAPI.Status.TRACK_PLAYING) && (!paused));
            }
            else if (action.equals(PowerampAPI.ACTION_TRACK_CHANGED)) {
	            if ( App.GS.interactWithPowerAmp && App.GS.isShowTrackInfoToast )
	            {
		            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
		            List<ActivityManager.RunningTaskInfo> taskInfo = activityManager.getRunningTasks (1);
		            if ( taskInfo.size() <= 0 ||
	                     !((ActivityManager.RunningTaskInfo) taskInfo.get(0)).topActivity.getPackageName().contentEquals(PowerampAPI.PACKAGE_NAME)
				       )
		            {
			            Bundle bundle = intent.getBundleExtra(PowerampAPI.TRACK);
			            String artist = bundle.getString(Track.ARTIST);
			            String album = bundle.getString(Track.ALBUM);
			            String track = bundle.getString(Track.TITLE);

						App.mToast.cancel();
						App.mToast.setTrackTitle (track);
			            App.mToast.setArtistAlbum (artist, album);
						App.mToast.showToast();
		            }
	            }
            }
            else if (action.equals(PowerampAPI.ACTION_AA_CHANGED)) {
	            if ( App.GS.interactWithPowerAmp && App.GS.isShowTrackInfoToast )
	            {
	                App.mToast.setAlbumArt ((Bitmap) intent.getParcelableExtra(PowerampAPI.ALBUM_ART_BITMAP));
	            }
            }
        }
    }

    private void setPowerAmpPaused() {
        if (App.GS.interactWithPowerAmp &&
            App.GS.needWatchSleepPowerAmp &&
            App.GS.isPowerAmpPlaying)
        {
	        App.GS.isNeedPowerAmpToPlayAfterWakeup = true;
	        context.startService(new Intent(PowerampAPI.ACTION_API_COMMAND).putExtra(PowerampAPI.COMMAND,
                                            PowerampAPI.Commands.PAUSE));
        }
    }

    private void setPowerAmpResumed() {
	    // всегда запускает poweramp после sleep, даже елси до этого он не играл?

        if (App.GS.interactWithPowerAmp &&
            App.GS.needWatchWakeUpPowerAmp &&
		    App.GS.isNeedPowerAmpToPlayAfterWakeup)
        {
	        App.GS.isNeedPowerAmpToPlayAfterWakeup = false;
	        mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    context.startService(new Intent(PowerampAPI.ACTION_API_COMMAND).putExtra(PowerampAPI.COMMAND,
                                                    PowerampAPI.Commands.RESUME));
                }
            }, App.GS.resumeDelayForPowerAmp);
        }
    }

    private void setPowerAmpStarted() {
	    Log.d("setPowerAmpStarted", "started");
        if ( App.GS.interactWithPowerAmp &&
             App.GS.needWatchBootUpPowerAmp )
        {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    context.startService(new Intent(PowerampAPI.ACTION_API_COMMAND).putExtra(PowerampAPI.COMMAND,
                                                    PowerampAPI.Commands.TOGGLE_PLAY_PAUSE));
                }
            }, App.GS.startDelayForPowerAmp);
        }
    }

    private void sendSetNextFolder() {
        if (App.GS.pressNextFolderPowerAmp) {
            context.startService(new Intent(PowerampAPI.ACTION_API_COMMAND).putExtra(PowerampAPI.COMMAND,
                                            PowerampAPI.Commands.NEXT_IN_CAT));
        }
    }

    private void sendSetPrevFolder() {
        if (App.GS.pressPrevFolderPowerAmp) {
            context.startService(new Intent(PowerampAPI.ACTION_API_COMMAND).putExtra(PowerampAPI.COMMAND,
                                            PowerampAPI.Commands.PREVIOUS_IN_CAT));
        }
    }

    private void sendPowerAmpKeyPressed(int key) {
        if (App.GS.interactWithPowerAmp &&  App.GS.isPowerAmpPlaying) {
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
