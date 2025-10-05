package ru.d51x.kaierutils.Settings;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

import ru.d51x.kaierutils.App;
import ru.d51x.kaierutils.R;

public class SettingsPowerAmpFragment extends PreferenceFragment implements OnSharedPreferenceChangeListener {


	public void onCreate (Bundle savedInstanceState) {
		super.onCreate (savedInstanceState);
		addPreferencesFromResource (R.xml.poweramp);

		Context context = App.getInstance();
		SharedPreferences prefs =
				PreferenceManager.getDefaultSharedPreferences(context);
		prefs.registerOnSharedPreferenceChangeListener(this);
	}

	public void onPause() {
		super.onPause();
		getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
	}

	public void onResume() {
		super.onResume();
		getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
	}

	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
		if ( key.equals ( "CAR_SETTINGS__CONTROL_POWERAMP_AVAILABLE" )) {
			App.GS.powerAmpOpt.interactWithPowerAmp = sharedPreferences.getBoolean ( key, false);
		} else if (key.equals ("CAR_SETTINGS__CONTROL_POWERAMP_SHOW_TOAST")) {
			App.GS.powerAmpOpt.isShowTrackInfoToast = sharedPreferences.getBoolean ( key, false);
		} else if (key.equals ("CAR_SETTINGS__CONTROL_POWERAMP_WATCH_SLEEP")) {
			App.GS.powerAmpOpt.needWatchSleepPowerAmp = sharedPreferences.getBoolean ( key, false);
		} else if (key.equals ("CAR_SETTINGS__CONTROL_POWERAMP_WATCH_WAKEUP")) {
			App.GS.powerAmpOpt.needWatchWakeUpPowerAmp = sharedPreferences.getBoolean (key, false);
		} else if (key.equals ("CAR_SETTINGS__CONTROL_POWERAMP_WATCH_BOOTUP")) {
			App.GS.powerAmpOpt.needWatchBootUpPowerAmp = sharedPreferences.getBoolean (key, false);
		}  else if ( key.equals ("CAR_SETTINGS__CONTROL_POWERAMP_NEXT_FOLDER")) {
			App.GS.powerAmpOpt.pressNextFolderPowerAmp = sharedPreferences.getBoolean (key, false);
		} else if ( key.equals ("CAR_SETTINGS__CONTROL_POWERAMP_PREV_FOLDER")) {
			App.GS.powerAmpOpt.pressPrevFolderPowerAmp = sharedPreferences.getBoolean (key, false);
		} else if ( key.equals ("CAR_SETTINGS__CONTROL_POWERAMP_START_DELAY")) {
			App.GS.powerAmpOpt.startDelayForPowerAmp = sharedPreferences.getInt(key, 3000);
		} else if ( key.equals ("CAR_SETTINGS__CONTROL_POWERAMP_WAKEUP_DELAY")) {
			App.GS.powerAmpOpt.resumeDelayForPowerAmp = sharedPreferences.getInt(key, 3000);
		} else if ( key.equals ("CAR_SETTINGS__CONTROL_POWERAMP_CODE_NEXT_CAT")) {
			App.GS.powerAmpOpt.codeNextFolder = Integer.parseInt (sharedPreferences.getString (key, "22"));
		} else if ( key.equals ("CAR_SETTINGS__CONTROL_POWERAMP_CODE_PREV_CAT")) {
			App.GS.powerAmpOpt.codePrevFolder = Integer.parseInt (sharedPreferences.getString (key, "23"));
		} else if ( key.equals ("CAR_SETTINGS__CONTROL_POWERAMP_SHOW_MUSIC_INFO")) {
			App.GS.uiOptions.isShowMusicInfo = sharedPreferences.getBoolean (key, false);
		} else if ( key.equals ("CAR_SETTINGS__CONTROL_POWERAMP_SHOW_TOAST2")) {
			App.GS.uiOptions.dontShowMusicInfoWhenMainActive = sharedPreferences.getBoolean (key, true);
		}
	}
}
