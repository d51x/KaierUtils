package ru.d51x.kaierutils.TWUtils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import ru.d51x.kaierutils.App;
import ru.d51x.kaierutils.BackgroundService;
import ru.d51x.kaierutils.MainActivity;

public class TWUtilBroadcastReceiver extends BroadcastReceiver {


	public static int prevVolume = -1;
	private static final String TAG = "TWUtilBroadcastReceiver";


    public TWUtilBroadcastReceiver () {
   }

	@Override
	public void onReceive (Context context, Intent intent) {

		// TODO: надо ли так делать или достаточно при загрузке?
		// context.startService(new Intent(context, TWUtilService.class));
		Log.d (TAG, "onReceive ");

		String action = intent.getAction();
		Log.d(TAG, String.format("onReceive(), action %s", action));

        // устройство загрузилось, запустим фоновый сервис
		if ( Intent.ACTION_BOOT_COMPLETED.equals(action) ) {
			Log.d(TAG, "Boot completed");

			context.startService(new Intent(context, BackgroundService.class));

			Toast toast = Toast.makeText(context.getApplicationContext(),
					"Boot completed", Toast.LENGTH_LONG);
			toast.show();

			if (App.GS.ui.isAutoStart) {
				Intent mIntent = new Intent(context, MainActivity.class);
				mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(mIntent);
			} else if (App.GS.ui.isAutoStartFloating) {
				App.floatingWindow.show();
			}
		}
        // устройство выключается
		else if ( Intent.ACTION_SHUTDOWN.equals(action) ||
				TWUtilConst.TW_BROADCAST_ACTION_SHUTDOWN.equals(action))
		{	// изменение уровня громкости при выключении
			SetVolumeAtStartUp();
            App.obd.oneTrip.saveData();
            App.obd.todayTrip.saveData();
            App.obd.totalTrip.saveData();

			SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(App.getInstance());
			prefs.edit().putInt("last_audio_focus_id", App.GS.curAudioFocusID).apply();

		}
        // устройство засыпает
		else if ( TWUtilConst.TW_BROADCAST_ACTION_SLEEP.equals(action))
		{	// изменение уровня громкости при sleep
			SetVolumeAtWakeUp();
            // запомним время ухода в SleepMode
            App.GS.lastSleep = System.currentTimeMillis();
            App.GS.isStopedAfterWakeUp = false;
            App.obd.oneTrip.saveData();
            App.obd.todayTrip.saveData();
            App.obd.totalTrip.saveData();

			SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(App.getInstance());
			prefs.edit().putInt("last_audio_focus_id", App.GS.curAudioFocusID).apply();

		}
        // устройство проснулось (вышло из SleepMode)
		else if ( TWUtilConst.TW_BROADCAST_ACTION_WAKE_UP.equals(action))
		{
	        App.GS.SleepModeCount++; // увеличим счетчик просыпаний
            App.GS.isStopedAfterWakeUp = true;
            App.GS.wakeUpTime =  System.currentTimeMillis();
            App.obd.oneTrip.loadData();
            App.obd.todayTrip.loadData();
            App.obd.totalTrip.loadData();
			SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences (App.getInstance ());
			App.GS.curAudioFocusID = prefs.getInt("last_audio_focus_id", -1);

		}
        // включился задний ход
		else if ( TWUtilConst.TW_BROADCAST_ACTION_REVERSE_ACTIVITY_START.equals(action))
		{
			prevVolume = App.GS.getVolumeLevel(); // запомнили текущую громкость
            App.GS.ReverseActivityCount++;         // увеличим счетчик включений заднего хода
			changeVolumeAtReverse();
		}
        // задний ход выключился
		else if ( TWUtilConst.TW_BROADCAST_ACTION_REVERSE_ACTIVITY_FINISH.equals(action))
		{
			TWUtilEx.setVolumeLevel(prevVolume);  // вернем громкость обратно, которая была до включения заднего хода
			App.GS.getVolumeLevel();
		}
	}

	private void changeVolumeAtReverse() {
		Log.d ("TWUtilBroadcastReceiver", "changeVolumeAtReverse ");
        // не нужно уменьшать громкость при включении заднего хода
		if ( !App.GS.volumeOptions.isNeedSoundDecreaseAtReverse  ) {	return; }

        // уменьшаем гроскость
		if ( App.GS.volumeOptions.isFixedVolumeAtReverse ) {
            // уменьшаем громкость до фиксированного значения, если громкость до включения была выше этого значения
			if ( App.GS.volumeOptions.fixedVolumeLevelAtReverse <= prevVolume ) {
				TWUtilEx.setVolumeLevel( App.GS.volumeOptions.fixedVolumeLevelAtReverse);
			}
		} else if (  App.GS.volumeOptions.isPercentVolumeAtReverse ) {
            // уменьшаем громкость до заданного процента от текущей
			int newLevel = Math.round((float) (prevVolume * App.GS.volumeOptions.percentVolumeLevelAtReverse) / 100);
			TWUtilEx.setVolumeLevel( newLevel );
		}
	}

	private void SetVolumeAtStartUp(){
		Log.d ("TWUtilBroadcastReceiver", "SetVolumeAtStartUp ");
		if ( App.GS.volumeOptions.isNeedSoundDecreaseAtStartUp ) {
			int level = App.GS.volumeOptions.volumeLevelAtStartUp;
			App.GS.setVolumeLevel(level, true);
		}
	}

	private void SetVolumeAtWakeUp(){
		Log.d ("TWUtilBroadcastReceiver", "SetVolumeAtWakeUp ");
		if ( App.GS.volumeOptions.isNeedSoundDecreaseAtWakeUp ) {
			int level = App.GS.volumeOptions.volumeLevelAtWakeUp;
			App.GS.setVolumeLevel(level, true);
		}
	}
}
