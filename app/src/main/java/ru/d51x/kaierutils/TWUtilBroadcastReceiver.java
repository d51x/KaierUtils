package ru.d51x.kaierutils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;

import java.util.ArrayList;
import java.util.List;

public class TWUtilBroadcastReceiver extends BroadcastReceiver {


	public static int prevVolume = -1;
	private static final String TAG = "TWUtilBroadcastReceiver";


    public TWUtilBroadcastReceiver () {
   }

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
			if (App.GS.isAutoStart) {
				Intent mIntent = new Intent(context, MainActivity.class);
				mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(mIntent);


			}
		}
        // устройство выключается
		else if ( action.equals ( Intent.ACTION_SHUTDOWN ) ||
				  action.equals ( TWUtilConst.TW_BROADCAST_ACTION_SHUTDOWN))
		{	// изменение уровня громкости при выключении
			SetVolumeAtStartUp();
            App.obd.saveFuelTank();
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
            App.obd.saveFuelTank();
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
            App.obd.loadFuelTank();
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
			TWUtilEx.setVolumeLevel( prevVolume );  // вернем громкость обратно, которая была до включения заднего хода
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
        else if ( action.equals( OBDII.OBD_BROADCAST_ACTION_PARKING_SENSORS_CHANGED )) {
//            int rear_inner_left = intent.getIntExtra("parking_sensors_rear_left_inner", -1);
//            int rear_outer_left = intent.getIntExtra("parking_sensors_rear_left_outer", -1);
//            int rear_inner_right = intent.getIntExtra("parking_sensors_rear_right_inner", -1);
//            int rear_outer_right = intent.getIntExtra("parking_sensors_rear_right_outer", -1);
           ArrayList<Integer> buffer = intent.getIntegerArrayListExtra("parking_sensors");

            if ( App.GS.isReverseMode ) {
                App.sensorsToast.cancel();
                App.sensorsToast.SetSensors( buffer );
                App.sensorsToast.showToast();
            }

        }
	}

	private void changeVolumeAtReverse() {
		Log.d ("TWUtilBroadcastReceiver", "changeVolumeAtReverse ");
        // не нужно уменьшать громкость при включении заднего хода
		if ( !App.GS.isNeedSoundDecreaseAtReverse  ) {	return; }

        // уменьшаем гроскость
		if ( App.GS.isFixedVolumeAtReverse ) {
            // уменьшаем громкость до фиксированного значения, если громкость до включения была выше этого значения
			if ( App.GS.FixedVolumeLevelAtReverse <= prevVolume ) {
				TWUtilEx.setVolumeLevel( App.GS.FixedVolumeLevelAtReverse );
			}
		} else if (  App.GS.isPercentVolumeAtReverse ) {
            // уменьшаем громкость до заданного процента от текущей
			int newLevel = Math.round(prevVolume * App.GS.PercentVolumeLevelAtReverse / 100);
			TWUtilEx.setVolumeLevel( newLevel );
		}
	}

	private void SetVolumeAtStartUp(){
		Log.d ("TWUtilBroadcastReceiver", "SetVolumeAtStartUp ");
		if ( App.GS.isNeedSoundDecreaseAtStartUp ) {
			int level = App.GS.VolumeLevelAtStartUp;
			App.GS.setVolumeLevel(level, true);
		}
	}

	private void SetVolumeAtWakeUp(){
		Log.d ("TWUtilBroadcastReceiver", "SetVolumeAtWakeUp ");
		if ( App.GS.isNeedSoundDecreaseAtWakeUp ) {
			int level = App.GS.VolumeLevelAtWakeUp;
			App.GS.setVolumeLevel(level, true);
		}
	}
}
