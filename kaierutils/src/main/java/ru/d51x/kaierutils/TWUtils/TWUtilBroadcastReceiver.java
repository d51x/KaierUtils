package ru.d51x.kaierutils.TWUtils;

import static ru.d51x.kaierutils.OBD2.ObdConstants.ACTION_OBD_PARKING_2101_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.KEY_OBD_PARKING_2101;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ru.d51x.kaierutils.App;
import ru.d51x.kaierutils.BackgroundService;
import ru.d51x.kaierutils.DebugLogger;
import ru.d51x.kaierutils.MainActivity;
import ru.d51x.kaierutils.Radio.Radio;

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
		DebugLogger.ToLog(TAG, String.format("onReceive(), action %s", action));

        // устройство загрузилось, запустим фоновый сервис
		if ( action.equals (Intent.ACTION_BOOT_COMPLETED ) ) {
			Log.d(TAG, "Boot completed");
			context.startService(new Intent(context, BackgroundService.class));
			if (App.GS.ui.isAutoStart) {
				Intent mIntent = new Intent(context, MainActivity.class);
				mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(mIntent);
			} else if (App.GS.ui.isAutoStartFloating) {
				App.floatingWindow.show();
			}
		}
        // устройство выключается
		else if ( action.equals ( Intent.ACTION_SHUTDOWN ) ||
				  action.equals ( TWUtilConst.TW_BROADCAST_ACTION_SHUTDOWN))
		{	// изменение уровня громкости при выключении
			SetVolumeAtStartUp();
            App.obd.fuel.save();
            App.obd.oneTrip.saveData();
            App.obd.todayTrip.saveData();
            App.obd.totalTrip.saveData();

			SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(App.getInstance());
			prefs.edit().putInt("last_audio_focus_id", App.GS.curAudioFocusID).apply();

		}
        // устройство засыпает
		else if ( action.equals ( TWUtilConst.TW_BROADCAST_ACTION_SLEEP))
		{	// изменение уровня громкости при sleep
			SetVolumeAtWakeUp();
            // запомним время ухода в SleepMode
            App.GS.lastSleep = System.currentTimeMillis();
            App.GS.isStopedAfterWakeUp = false;
            App.obd.fuel.save();
            App.obd.oneTrip.saveData();
            App.obd.todayTrip.saveData();
            App.obd.totalTrip.saveData();

			SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(App.getInstance());
			prefs.edit().putInt("last_audio_focus_id", App.GS.curAudioFocusID).apply();

		}
        // устройство проснулось (вышло из SleepMode)
		else if ( action.equals ( TWUtilConst.TW_BROADCAST_ACTION_WAKE_UP))
		{
	        App.GS.SleepModeCount++; // увеличим счетчик просыпаний
            App.GS.isStopedAfterWakeUp = true;
            App.GS.wakeUpTime =  System.currentTimeMillis();
            App.obd.fuel.load();
            App.obd.oneTrip.loadData();
            App.obd.todayTrip.loadData();
            App.obd.totalTrip.loadData();
			SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences (App.getInstance ());
			App.GS.curAudioFocusID = prefs.getInt("last_audio_focus_id", -1);

		}
        // включился задний ход
		else if ( action.equals ( TWUtilConst.TW_BROADCAST_ACTION_REVERSE_ACTIVITY_START))
		{
			prevVolume = App.GS.getVolumeLevel (); // запомнили текущую громкость
            App.GS.ReverseActivityCount++;         // увеличим счетчик включений заднего хода
			changeVolumeAtReverse();
		}
        // задний ход выключился
		else if ( action.equals ( TWUtilConst.TW_BROADCAST_ACTION_REVERSE_ACTIVITY_FINISH))
		{
			TWUtilEx.setVolumeLevel(prevVolume);  // вернем громкость обратно, которая была до включения заднего хода
			App.GS.getVolumeLevel ();
		}
		else if ( action.equals ( TWUtilConst.TW_BROADCAST_ACTION_RADIO_CHANGED))
		{
			if ( App.GS.curAudioFocusID != TWUtilConst.TW_AUDIO_FOCUS_RADIO_ID ) return;
            ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
			List<RunningTaskInfo> taskInfo = activityManager.getRunningTasks(1);
			String activeWnd = ((RunningTaskInfo) taskInfo.get(0)).topActivity.getPackageName();
            String activeActivity = ((ActivityManager.RunningTaskInfo) taskInfo.get(0)).topActivity.getClassName();
			if ( taskInfo.size() <= 0 ||
				 !(activeWnd.equalsIgnoreCase ( Radio.PACKAGE_NAME )) //||
                 //!((RunningTaskInfo) taskInfo.get(0)).topActivity.getPackageName().contentEquals("ru.d51x.kaierutils")
				)
			{
                if ( App.GS.radio.dontShowToastOnMainActivity  &&
                        activeWnd.equalsIgnoreCase("ru.d51x.kaierutils") &&
                        activeActivity.equalsIgnoreCase("ru.d51x.kaierutils.MainActivity")) return;
                //App.rToast.isShowToastWhenActive = !((RunningTaskInfo) taskInfo.get(0)).topActivity.getPackageName().contentEquals("ru.d51x.kaierutils");
                String title = intent.getStringExtra("Title");
                title = (title != null) ? title : "";
				String freq = intent.getStringExtra ("Frequency");
				App.rToast.cancel();
				App.rToast.SetRadioText(title, freq);
				App.rToast.showToast();
			}
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
			int newLevel = Math.round(prevVolume * App.GS.volumeOptions.percentVolumeLevelAtReverse / 100);
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
