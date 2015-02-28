package ru.d51x.kaierutils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class TWUtilBroadcastReceiver extends BroadcastReceiver {


	public static int prevVolume = -1;
	private static final String TAG = "TWUtilBroadcastReceiver";

    public TWUtilBroadcastReceiver () {	}
	@Override
	public void onReceive (Context context, Intent intent) {

		// TODO: надо ли так делать или достаточно при загрузке?
		// context.startService(new Intent(context, TWUtilService.class));
		Log.d ("TWUtilBroadcastReceiver", "onReceive ");

		String action = intent.getAction();
		DebugLogger.ToLog (TAG, String.format("onReceive(), action %s", action));

        // устройство загрузилось, запустим фоновый сервис
		if ( action.equals (Intent.ACTION_BOOT_COMPLETED ) ) {
			context.startService(new Intent(context, BackgroundService.class));
		}
        // устройство выключается
		else if ( action.equals ( Intent.ACTION_SHUTDOWN ) ||
				  action.equals ( TWUtilConst.TWUTIL_BROADCAST_ACTION_SHUTDOWN ))
		{	// изменение уровня громкости при выключении
			SetVolumeAtStartUp();
		}
        // устройство засыпает
		else if ( action.equals ( TWUtilConst.TWUTIL_BROADCAST_ACTION_SLEEP ))
		{	// изменение уровня громкости при sleep
			SetVolumeAtWakeUp();
            // запомним время ухода в SleepMode
            App.mGlSets.lastSleep = System.currentTimeMillis();
		}
        // устройство проснулось (вышло из SleepMode)
		else if ( action.equals ( TWUtilConst.TWUTIL_BROADCAST_ACTION_WAKE_UP ))
		{
	        App.mGlSets.SleepModeCount++; // увеличим счетчик просыпаний
		}
        // включился задний ход
		else if ( action.equals ( TWUtilConst.TWUTIL_BROADCAST_ACTION_REVERSE_ACTIVITY_START ))
		{
			prevVolume = App.mGlSets.getVolumeLevel (); // запомнили текущую громкость
            App.mGlSets.ReverseActivityCount++;         // увеличим счетчик включений заднего хода
			changeVolumeAtReverse();
		}
        // задний ход выключился
		else if ( action.equals ( TWUtilConst.TWUTIL_BROADCAST_ACTION_REVERSE_ACTIVITY_FINISH ))
		{
			TWUtilEx.setVolumeLevel( prevVolume );  // вернем громкость обратно, которая была до включения заднего хода
			App.mGlSets.getVolumeLevel ();
		}
	}

	private void changeVolumeAtReverse() {
		Log.d ("TWUtilBroadcastReceiver", "changeVolumeAtReverse ");
        // не нужно уменьшать громкость при включении заднего хода
		if ( !App.mGlSets.isNeedSoundDecreaseAtReverse  ) {	return; }

        // уменьшаем гроскость
		if ( App.mGlSets.isFixedVolumeAtReverse ) {
            // уменьшаем громкость до фиксированного значения, если громкость до включения была выше этого значения
			if ( App.mGlSets.FixedVolumeLevelAtReverse <= prevVolume ) {
				TWUtilEx.setVolumeLevel( App.mGlSets.FixedVolumeLevelAtReverse );
			}
		} else if (  App.mGlSets.isPercentVolumeAtReverse ) {
            // уменьшаем громкость до заданного процента от текущей
			int newLevel = Math.round(prevVolume * App.mGlSets.PercentVolumeLevelAtReverse / 100);
			TWUtilEx.setVolumeLevel( newLevel );
		}
	}

	private void SetVolumeAtStartUp(){
		Log.d ("TWUtilBroadcastReceiver", "SetVolumeAtStartUp ");
		if ( App.mGlSets.isNeedSoundDecreaseAtStartUp ) {
			int level = App.mGlSets.VolumeLevelAtStartUp;
			App.mGlSets.setVolumeLevel(level, true);
		}
	}

	private void SetVolumeAtWakeUp(){
		Log.d ("TWUtilBroadcastReceiver", "SetVolumeAtWakeUp ");
		if ( App.mGlSets.isNeedSoundDecreaseAtWakeUp ) {
			int level = App.mGlSets.VolumeLevelAtWakeUp;
			App.mGlSets.setVolumeLevel(level, true);
		}
	}
}
