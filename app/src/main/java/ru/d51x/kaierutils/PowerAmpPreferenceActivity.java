package ru.d51x.kaierutils;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.Context;
import android.preference.PreferenceManager;


public class PowerAmpPreferenceActivity extends PreferenceActivity implements OnSharedPreferenceChangeListener {

    private static final boolean ALWAYS_SIMPLE_PREFS = false;


   @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        addPreferencesFromResource (R.xml.settings_poweramp);

        Context context = getApplicationContext();
        SharedPreferences prefs =
                PreferenceManager.getDefaultSharedPreferences(context);
        prefs.registerOnSharedPreferenceChangeListener(this);
    }

    protected void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    protected void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if ( key.equals ( "CAR_SETTINGS__CONTROL_POWERAMP_AVAILABLE" )) {
            App.mGlSets.interactWithPowerAmp = sharedPreferences.getBoolean ( key, false);
        } else if (key.equals ("CAR_SETTINGS__CONTROL_POWERAMP_WATCH_SLEEP")) {
            App.mGlSets.needWatchSleepPowerAmp = sharedPreferences.getBoolean ( key, false);
        } else if (key.equals ("CAR_SETTINGS__CONTROL_POWERAMP_WATCH_WAKEUP")) {
            App.mGlSets.needWatchWakeUpPowerAmp = sharedPreferences.getBoolean (key, false);
        } else if (key.equals ("CAR_SETTINGS__CONTROL_POWERAMP_WATCH_BOOTUP")) {
            App.mGlSets.needWatchBootUpPowerAmp = sharedPreferences.getBoolean (key, false);
        }  else if ( key.equals ("CAR_SETTINGS__CONTROL_POWERAMP_NEXT_FOLDER")) {
            App.mGlSets.pressNextFolderPowerAmp = sharedPreferences.getBoolean (key, false);
        } else if ( key.equals ("CAR_SETTINGS__CONTROL_POWERAMP_PREV_FOLDER")) {
            App.mGlSets.pressPrevFolderPowerAmp = sharedPreferences.getBoolean (key, false);
        } else if ( key.equals ("CAR_SETTINGS__CONTROL_POWERAMP_START_DELAY")) {
            App.mGlSets.resumeDelayForPowerAmp = sharedPreferences.getInt(key, 3000);
        }
//        else if ( key.equals ("CAR_SETTINGS__VOLUME_AT_REVERSE__FIXED_LEVEL")) {
//            App.mGlSets.FixedVolumeLevelAtReverse = sharedPreferences.getInt(key, 3);
//        } else if ( key.equals ("CAR_SETTINGS__VOLUME_AT_REVERSE__PERCENTAGE_LEVEL")) {
//            App.mGlSets.PercentVolumeLevelAtReverse = sharedPreferences.getInt(key, 30);
//        }
    }
}