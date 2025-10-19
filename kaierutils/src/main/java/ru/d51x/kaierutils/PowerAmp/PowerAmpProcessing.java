package ru.d51x.kaierutils.PowerAmp;

import static androidx.preference.PreferenceManager.getDefaultSharedPreferences;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.core.content.ContextCompat;

import com.maxmpz.poweramp.player.PowerampAPI;
import com.maxmpz.poweramp.player.PowerampAPI.Track;

import java.util.List;

import ru.d51x.kaierutils.App;
import ru.d51x.kaierutils.GlSets;
import ru.d51x.kaierutils.TWUtils.TWUtilConst;

public class PowerAmpProcessing {
    private final BroadcastReceiver powerAmpReceiver;
    private final Context context;
    private final Handler mHandler;

    public PowerAmpProcessing(Context context, int startId) {
        Log.d("PowerAmpProcessing", "PowerAmpProcessing");


        this.context = context;
        mHandler = new Handler();

        SharedPreferences prefs = getDefaultSharedPreferences(App.getInstance());
        int status = prefs.getInt("kaierutils_power_amp_status", -1);
        if ( ( App.GS.curAudioFocusID != TWUtilConst.TW_AUDIO_FOCUS_RADIO_ID ) &&
                ( status == PowerampAPI.Status.TRACK_PLAYING) &&
                ( startId == 1)
                )
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
        intentFilter.addAction(TWUtilConst.TW_BROADCAST_ACTION_KEY_PRESSED);
        intentFilter.addAction(TWUtilConst.TW_BROADCAST_ACTION_SHUTDOWN);
        intentFilter.addAction(TWUtilConst.TW_BROADCAST_ACTION_SLEEP);
        intentFilter.addAction(TWUtilConst.TW_BROADCAST_ACTION_WAKE_UP);
        intentFilter.addAction(TWUtilConst.TW_BROADCAST_ACTION_AUDIO_FOCUS_CHANGED);
        ContextCompat.registerReceiver(context, powerAmpReceiver, intentFilter, ContextCompat.RECEIVER_NOT_EXPORTED);
    }

    private void processIntent(Intent intent) {
        if (intent != null) {
            String action = intent.getAction();
            assert action != null;
            Log.d("PowerAmpProcessing -> OnReceive: ", action);

            if ( TWUtilConst.TW_BROADCAST_ACTION_SLEEP.equals(action) ) {
                setPowerAmpPaused();
            }
            else if ( TWUtilConst.TW_BROADCAST_ACTION_SHUTDOWN.equals(action) ) {
                setPowerAmpPaused();
            }
            else if (TWUtilConst.TW_BROADCAST_ACTION_WAKE_UP.equals(action)) {

                SharedPreferences prefs = getDefaultSharedPreferences(App.getInstance());
                int status = prefs.getInt("kaierutils_power_amp_status", -1);
                if ( ( App.GS.curAudioFocusID != TWUtilConst.TW_AUDIO_FOCUS_RADIO_ID ) && ( status == PowerampAPI.Status.TRACK_PLAYING))
                    setPowerAmpResumed();
            }
            else if (Intent.ACTION_BOOT_COMPLETED.equals (action )) {

                SharedPreferences prefs = getDefaultSharedPreferences(App.getInstance());
                int status = prefs.getInt("kaierutils_power_amp_status", -1);
                if ( ( App.GS.curAudioFocusID != TWUtilConst.TW_AUDIO_FOCUS_RADIO_ID ) && ( status == PowerampAPI.Status.TRACK_PLAYING))
                    setPowerAmpStarted();
            }
            else if (TWUtilConst.TW_BROADCAST_ACTION_KEY_PRESSED.equals(action)) {
                int key = intent.getIntExtra (TWUtilConst.TW_BROADCAST_ACTION_KEY_PRESSED, -1);
                sendPowerAmpKeyPressed(key);
            } else if (TWUtilConst.TW_BROADCAST_ACTION_AUDIO_FOCUS_CHANGED.equals(action)) {
                int af_id = intent.getIntExtra("audio_focus_id", -1);
                switch (af_id) {
                    case TWUtilConst.TW_AUDIO_FOCUS_BT_ID:
                        setPowerAmpPaused();
                        break;
                    case 0:
                    case TWUtilConst.TW_AUDIO_FOCUS_MUSIC_ID:
                        setPowerAmpPlayed();
                        break;
                    default:
                        break;
                }
            }
            else if (PowerampAPI.ACTION_STATUS_CHANGED.equals(action)) {
                int status = intent.getIntExtra(PowerampAPI.STATUS, -1);
                boolean paused = intent.getBooleanExtra(PowerampAPI.PAUSED, false);
                App.GS.powerAmpOpt.isPowerAmpPlaying = ((status == PowerampAPI.Status.TRACK_PLAYING) && (!paused));

                SharedPreferences prefs = getDefaultSharedPreferences(App.getInstance());
                prefs.edit().putInt("kaierutils_power_amp_status", status).apply();

            }
            else if (PowerampAPI.ACTION_TRACK_CHANGED.equals(action)) {

	            Bundle bundle = intent.getBundleExtra(PowerampAPI.TRACK);
	            String artist = bundle.getString(Track.ARTIST);
	            String album = bundle.getString(Track.ALBUM);
	            String track = bundle.getString(Track.TITLE);
	            App.GS.powerAmpOpt.PowerAmp_TrackTitle = (track != null) ? track : "";
	            App.GS.powerAmpOpt.PowerAmp_AlbumArtist = "";
	            if (artist != null) {
		            App.GS.powerAmpOpt.PowerAmp_AlbumArtist = artist;
	            }
	            if (album != null) {
		            App.GS.powerAmpOpt.PowerAmp_AlbumArtist += " (" + album + ")";
	            }

	            Intent intent2 = new Intent();
	            intent2.setAction(GlSets.PWRAMP_BROADCAST_ACTION_TRACK_CHANGED);
	            intent2.putExtra("TrackTitle", App.GS.powerAmpOpt.PowerAmp_TrackTitle);
	            intent2.putExtra("AlbumArtist", App.GS.powerAmpOpt.PowerAmp_AlbumArtist);
	            intent2.putExtra("AlbumArt", App.GS.powerAmpOpt.PowerAmp_AlbumArt);

	            App.getInstance().sendBroadcast(intent2);

	            if ( App.GS.powerAmpOpt.interactWithPowerAmp && App.GS.powerAmpOpt.isShowTrackInfoToast && App.GS.powerAmpOpt.isPowerAmpPlaying )
	            {
                    if ( !(App.GS.curAudioFocusID == TWUtilConst.TW_AUDIO_FOCUS_MUSIC_ID || App.GS.curAudioFocusID == 0 )) return;
                    ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		            List<ActivityManager.RunningTaskInfo> taskInfo = activityManager.getRunningTasks (1);
		            String activeWnd = ((ActivityManager.RunningTaskInfo) taskInfo.get(0)).topActivity.getPackageName();
		            String activeActivity = ((ActivityManager.RunningTaskInfo) taskInfo.get(0)).topActivity.getClassName();

                    if ( taskInfo.size() <= 0 ||
	                     !(activeWnd.equalsIgnoreCase (PowerampAPI.PACKAGE_NAME))
				       )
		            {
			            if ( App.GS.ui.dontShowMusicInfoWhenMainActive &&
                                activeWnd.equalsIgnoreCase("ru.d51x.kaierutils") &&
                                activeActivity.equalsIgnoreCase("ru.d51x.kaierutils.MainActivity")) return;
						App.mToast.cancel();
						App.mToast.setTrackTitle (App.GS.powerAmpOpt.PowerAmp_TrackTitle);
			            App.mToast.setArtistAlbum ( App.GS.powerAmpOpt.PowerAmp_AlbumArtist);
						App.mToast.showToast();
		            }
	            }
            }
            else if (action.equals(PowerampAPI.ACTION_AA_CHANGED)) {
	            Bitmap bmp = (Bitmap) intent.getParcelableExtra(PowerampAPI.ALBUM_ART_BITMAP);



	            if ( App.GS.powerAmpOpt.interactWithPowerAmp && App.GS.powerAmpOpt.isShowTrackInfoToast && App.GS.powerAmpOpt.isPowerAmpPlaying)
	            {
                    //if ( !bmp.sameAs( App.GS.PowerAmp_AlbumArt )) {
                    Intent intent3 = new Intent();
                    intent3.setAction(GlSets.PWRAMP_BROADCAST_ACTION_TRACK_CHANGED);
                    intent3.putExtra("TrackTitle", App.GS.powerAmpOpt.PowerAmp_TrackTitle);
                    intent3.putExtra("AlbumArtist", App.GS.powerAmpOpt.PowerAmp_AlbumArtist);
                    intent3.putExtra("AlbumArt", bmp);

                   // if ( bmp != null )    {
                        App.GS.powerAmpOpt.PowerAmp_AlbumArt = bmp;
                    //} else {

                    //}
                    //intent3.putExtra("AlbumArt", App.GS.PowerAmp_AlbumArt);
                    //intent3.putExtra("AlbumArt", App.GS.PowerAmp_AlbumArt);
                    App.getInstance().sendBroadcast(intent3);
                        App.mToast.setAlbumArt (App.GS.powerAmpOpt.PowerAmp_AlbumArt);
                   // }

	            }
            }
        }
    }

    private void setPowerAmpPaused() {
        if (App.GS.powerAmpOpt.interactWithPowerAmp &&
            App.GS.powerAmpOpt.needWatchSleepPowerAmp &&
            App.GS.powerAmpOpt.isPowerAmpPlaying)
        {
	        App.GS.powerAmpOpt.isNeedPowerAmpToPlayAfterWakeup = true;
	        context.startService(new Intent(PowerampAPI.ACTION_API_COMMAND).putExtra(PowerampAPI.COMMAND,
                                            PowerampAPI.Commands.PAUSE));
        }
    }

    private void setPowerAmpResumed() {
	    // всегда запускает poweramp после sleep, даже елси до этого он не играл?

        if (App.GS.powerAmpOpt.interactWithPowerAmp &&
            App.GS.powerAmpOpt.needWatchWakeUpPowerAmp &&
		    App.GS.powerAmpOpt.isNeedPowerAmpToPlayAfterWakeup)
        {
	        App.GS.powerAmpOpt.isNeedPowerAmpToPlayAfterWakeup = false;
	        mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    context.startService(new Intent(PowerampAPI.ACTION_API_COMMAND).putExtra(PowerampAPI.COMMAND,
                                                    PowerampAPI.Commands.RESUME));
                }
            }, App.GS.powerAmpOpt.resumeDelayForPowerAmp);
        }
    }

    private void setPowerAmpPlayed() {
        if (App.GS.powerAmpOpt.interactWithPowerAmp) {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    context.startService(new Intent(PowerampAPI.ACTION_API_COMMAND).putExtra(PowerampAPI.COMMAND,
                            PowerampAPI.Commands.RESUME));
                }
            }, 500);
        }
    }

    private void setPowerAmpStarted() {
	    Log.d("setPowerAmpStarted", "started");
        if ( App.GS.powerAmpOpt.interactWithPowerAmp &&
             App.GS.powerAmpOpt.needWatchBootUpPowerAmp )
        {
            //TWUtilEx.setAudioFocus( 3 );
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    context.startService(new Intent(PowerampAPI.ACTION_API_COMMAND).putExtra(PowerampAPI.COMMAND,
                                                    PowerampAPI.Commands.RESUME));
                }
            }, App.GS.powerAmpOpt.startDelayForPowerAmp);
           
        }
    }

    private void sendSetNextFolder() {
        if (App.GS.powerAmpOpt.pressNextFolderPowerAmp) {
            context.startService(new Intent(PowerampAPI.ACTION_API_COMMAND).putExtra(PowerampAPI.COMMAND,
                                            PowerampAPI.Commands.NEXT_IN_CAT));
        }
    }

    private void sendSetPrevFolder() {
        if (App.GS.powerAmpOpt.pressPrevFolderPowerAmp) {
            context.startService(new Intent(PowerampAPI.ACTION_API_COMMAND).putExtra(PowerampAPI.COMMAND,
                                            PowerampAPI.Commands.PREVIOUS_IN_CAT));
        }
    }

    private void sendPowerAmpKeyPressed(int key) {
        if (App.GS.powerAmpOpt.interactWithPowerAmp &&  App.GS.powerAmpOpt.isPowerAmpPlaying) {
            switch (key) {
                case TWUtilConst.TW_SVC_BUTTON_NEXT:
                    sendSetNextFolder();
                    break;
                case TWUtilConst.TW_SVC_BUTTON_PREV:
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
