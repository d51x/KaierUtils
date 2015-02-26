package ru.d51x.kaierutils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.TextView;
import android.view.View;

public class TWUtilBroadcastReceiver extends BroadcastReceiver {
	public TWUtilBroadcastReceiver () {
	}

	public static int prevVolume = -1;
	private static final String TAG = "TWUtilBroadcastReceiver";

	@Override
	public void onReceive (Context context, Intent intent) {

		// TODO: надо ли так делать или достаточно при загрузке?
		// context.startService(new Intent(context, TWUtilService.class));
		Log.d ("TWUtilBroadcastReceiver", "onReceive ");

		String action = intent.getAction();
		DebugLogger.ToLog (TAG, String.format("onReceive(), action %s", action));
		if ( action.equals (Intent.ACTION_BOOT_COMPLETED ) ) {
			context.startService(new Intent(context, BackgroundService.class));
		}
		else if ( action.equals ( Intent.ACTION_SHUTDOWN ) ||
				  action.equals ( TWUtilConst.TWUTIL_BROADCAST_ACTION_SHUTDOWN ))
		{	// изменение уровня громкости при выключении
			SetVolumeAtStartUp();
		}
		else if ( action.equals ( TWUtilConst.TWUTIL_BROADCAST_ACTION_SLEEP ))
		{	// изменение уровня громкости при sleep
			SetVolumeAtWakeUp();
            App.mGlobalSettings.lastSleep = System.currentTimeMillis();
		}
		else if ( action.equals ( TWUtilConst.TWUTIL_BROADCAST_ACTION_WAKE_UP ))
		{
			// TODO: что-то делаем при просыпании
            App.mGlobalSettings.SleepModeCount++;
		}
		else if ( action.equals ( TWUtilConst.TWUTIL_BROADCAST_ACTION_REVERSE_ACTIVITY_START ))
		{
			prevVolume = App.mGlobalSettings.getVolumeLevel ();
            App.mGlobalSettings.ReverseActivityCount++;
			changeVolumeAtReverse();
		}
		else if ( action.equals ( TWUtilConst.TWUTIL_BROADCAST_ACTION_REVERSE_ACTIVITY_FINISH ))
		{
			TWUtilEx.setVolumeLevel( prevVolume );
			//App.mGlobalSettings.setVolumeLevel(prevVolume, true);
			App.mGlobalSettings.getVolumeLevel ();
		}
	}

	private void changeVolumeAtReverse() {
		Log.d ("TWUtilBroadcastReceiver", "changeVolumeAtReverse ");
		if ( !App.mGlobalSettings.isNeedSoundDecreaseAtReverse  ) {
			return;
		}
		if ( App.mGlobalSettings.isFixedVolumeAtReverse ) {
			if ( App.mGlobalSettings.FixedVolumeLevelAtReverse <= prevVolume ) {
				//App.mGlobalSettings.setVolumeLevel(App.mGlobalSettings.FixedVolumeLevelAtReverse, true);
				TWUtilEx.setVolumeLevel( App.mGlobalSettings.FixedVolumeLevelAtReverse );
			}
			return;
		} else if (  App.mGlobalSettings.isPercentVolumeAtReverse ) {
			int newLevel = Math.round(prevVolume * App.mGlobalSettings.PercentVolumeLevelAtReverse / 100);
			//App.mGlobalSettings.setVolumeLevel(newLevel, true);
			TWUtilEx.setVolumeLevel( newLevel );
		}
	}

	private void SetVolumeAtStartUp(){
		Log.d ("TWUtilBroadcastReceiver", "SetVolumeAtStartUp ");
		if ( App.mGlobalSettings.isNeedSoundDecreaseAtStartUp ) {
			int level = App.mGlobalSettings.VolumeLevelAtStartUp;
			App.mGlobalSettings.setVolumeLevel(level, true);
		}
	}
	private void SetVolumeAtWakeUp(){
		Log.d ("TWUtilBroadcastReceiver", "SetVolumeAtWakeUp ");
		if ( App.mGlobalSettings.isNeedSoundDecreaseAtWakeUp ) {
			int level = App.mGlobalSettings.VolumeLevelAtWakeUp;
			App.mGlobalSettings.setVolumeLevel(level, true);
		}
	}
}
