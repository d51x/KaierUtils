package ru.d51x.kaierutils;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.Context;
import android.preference.PreferenceManager;

public class DynamicSoundSettingsActivity extends PreferenceActivity  implements SharedPreferences.OnSharedPreferenceChangeListener {
    /**
     * Determines whether to always show the simplified settings UI, where
     * settings are presented in a single list. When false, settings are shown
     * as a master/detail two-pane view on tablets. When true, a single pane is
     * shown on tablets.
     */
    private static final boolean ALWAYS_SIMPLE_PREFS = false;



    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        addPreferencesFromResource (R.xml.settings_dynamic_sound);

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
        if ( key.equals ( "CAR_SETTINGS__DYNAMIC_SOUND_CONTROL__DO_CHAGE" )) {
            App.mGlSets.dsc_isAvailable = sharedPreferences.getBoolean ( key, false);
        } else if (key.equals ("CAR_SETTINGS__DYNAMIC_SOUND_CONTROL__FIRST_SPEED")) {
            App.mGlSets.dsc_FirstSpeed = sharedPreferences.getInt (key, 40);
        } else if (key.equals ("CAR_SETTINGS__DYNAMIC_SOUND_CONTROL__STEP_SPEED")) {
            App.mGlSets.dsc_StepSpeed = sharedPreferences.getInt (key, 20);
        }  else if ( key.equals ("CAR_SETTINGS__DYNAMIC_SOUND_CONTROL__STEP_VOLUME")) {
            App.mGlSets.dsc_StepVolume = sharedPreferences.getInt (key, 1);
        } else if ( key.equals ("CAR_SETTINGS__DYNAMIC_SOUND_CONTROL__TIME_TO_CHANGE")) {
            App.mGlSets.dsc_TimeToChange = sharedPreferences.getInt (key, 10);
        } else if ( key.equals ("CAR_SETTINGS__DYNAMIC_SOUND_CONTROL__DELTA_TO_CHANGE")) {
            App.mGlSets.dsc_DeltaToChange = sharedPreferences.getInt (key, 5);
        }
    }




}
