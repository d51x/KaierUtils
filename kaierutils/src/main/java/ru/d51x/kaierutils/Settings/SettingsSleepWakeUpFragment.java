package ru.d51x.kaierutils.Settings;


import static androidx.preference.PreferenceManager.getDefaultSharedPreferences;

import static ru.d51x.kaierutils.Settings.SettingConstants.SETTINGS_SLEEP_WAKEUP_VOLUME_STARTUP;
import static ru.d51x.kaierutils.Settings.SettingConstants.SETTINGS_SLEEP_WAKEUP_VOLUME_STARTUP_DEFAULT;
import static ru.d51x.kaierutils.Settings.SettingConstants.SETTINGS_SLEEP_WAKEUP_VOLUME_STARTUP_LEVEL;
import static ru.d51x.kaierutils.Settings.SettingConstants.SETTINGS_SLEEP_WAKEUP_VOLUME_STARTUP_LEVEL_DEFAULT;
import static ru.d51x.kaierutils.Settings.SettingConstants.SETTINGS_SLEEP_WAKEUP_VOLUME_WAKEUP;
import static ru.d51x.kaierutils.Settings.SettingConstants.SETTINGS_SLEEP_WAKEUP_VOLUME_WAKEUP_DEFAULT;
import static ru.d51x.kaierutils.Settings.SettingConstants.SETTINGS_SLEEP_WAKEUP_VOLUME_WAKEUP_LEVEL;
import static ru.d51x.kaierutils.Settings.SettingConstants.SETTINGS_SLEEP_WAKEUP_VOLUME_WAKEUP_LEVEL_DEFAULT;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.preference.PreferenceFragmentCompat;

import java.util.Objects;

import ru.d51x.kaierutils.App;
import ru.d51x.kaierutils.R;

public class SettingsSleepWakeUpFragment extends PreferenceFragmentCompat implements OnSharedPreferenceChangeListener {

	@Override
	public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
		setPreferencesFromResource (R.xml.sleep_wakeup, rootKey);
		if (getActivity() != null) {
			getActivity().setTitle(getString(R.string.header_sleep_wakeup_title));
		}
		Context context = App.getInstance();
		SharedPreferences prefs = getDefaultSharedPreferences(context);
		prefs.registerOnSharedPreferenceChangeListener(this);
	}

	public void onPause() {
		super.onPause();
		Objects.requireNonNull(getPreferenceScreen().getSharedPreferences()).unregisterOnSharedPreferenceChangeListener(this);
	}

	public void onResume() {
		super.onResume();
		Objects.requireNonNull(getPreferenceScreen().getSharedPreferences()).registerOnSharedPreferenceChangeListener(this);
	}

	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
		switch (Objects.requireNonNull(key)) {
            case SETTINGS_SLEEP_WAKEUP_VOLUME_STARTUP ->
                    App.GS.volumeOptions.isNeedSoundDecreaseAtStartUp = sharedPreferences.getBoolean(key, SETTINGS_SLEEP_WAKEUP_VOLUME_STARTUP_DEFAULT);
            case SETTINGS_SLEEP_WAKEUP_VOLUME_STARTUP_LEVEL ->
                    App.GS.volumeOptions.volumeLevelAtStartUp = sharedPreferences.getInt(key, SETTINGS_SLEEP_WAKEUP_VOLUME_STARTUP_LEVEL_DEFAULT);
            case SETTINGS_SLEEP_WAKEUP_VOLUME_WAKEUP ->
                    App.GS.volumeOptions.isNeedSoundDecreaseAtWakeUp = sharedPreferences.getBoolean(key, SETTINGS_SLEEP_WAKEUP_VOLUME_WAKEUP_DEFAULT);
            case SETTINGS_SLEEP_WAKEUP_VOLUME_WAKEUP_LEVEL ->
                    App.GS.volumeOptions.volumeLevelAtWakeUp = sharedPreferences.getInt(key, SETTINGS_SLEEP_WAKEUP_VOLUME_WAKEUP_LEVEL_DEFAULT);
        }
	}
}
