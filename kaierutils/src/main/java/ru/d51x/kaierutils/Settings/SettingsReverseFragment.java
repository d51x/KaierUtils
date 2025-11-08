package ru.d51x.kaierutils.Settings;


import static androidx.preference.PreferenceManager.getDefaultSharedPreferences;

import static ru.d51x.kaierutils.Settings.SettingConstants.SETTINGS_REVERSE_FIXED_VOLUME;
import static ru.d51x.kaierutils.Settings.SettingConstants.SETTINGS_REVERSE_FIXED_VOLUME_DEFAULT;
import static ru.d51x.kaierutils.Settings.SettingConstants.SETTINGS_REVERSE_FIXED_VOLUME_LEVEL;
import static ru.d51x.kaierutils.Settings.SettingConstants.SETTINGS_REVERSE_FIXED_VOLUME_LEVEL_DEFAULT;
import static ru.d51x.kaierutils.Settings.SettingConstants.SETTINGS_REVERSE_PERCENT_VOLUME;
import static ru.d51x.kaierutils.Settings.SettingConstants.SETTINGS_REVERSE_PERCENT_VOLUME_DEFAULT;
import static ru.d51x.kaierutils.Settings.SettingConstants.SETTINGS_REVERSE_PERCENT_VOLUME_LEVEL;
import static ru.d51x.kaierutils.Settings.SettingConstants.SETTINGS_REVERSE_PERCENT_VOLUME_LEVEL_DEFAULT;
import static ru.d51x.kaierutils.Settings.SettingConstants.SETTINGS_REVERSE_VOLUME;
import static ru.d51x.kaierutils.Settings.SettingConstants.SETTINGS_REVERSE_VOLUME_DEFAULT;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.preference.PreferenceFragmentCompat;

import java.util.Objects;

import ru.d51x.kaierutils.App;
import ru.d51x.kaierutils.R;

public class SettingsReverseFragment extends PreferenceFragmentCompat implements OnSharedPreferenceChangeListener {
	@Override
	public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
		setPreferencesFromResource (R.xml.reverse, rootKey);
		if (getActivity() != null) {
			getActivity().setTitle(getString(R.string.header_reverse_title));
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
            case SETTINGS_REVERSE_VOLUME ->
                    App.GS.volumeOptions.isNeedSoundDecreaseAtReverse = sharedPreferences.getBoolean(key, SETTINGS_REVERSE_VOLUME_DEFAULT);
            case SETTINGS_REVERSE_FIXED_VOLUME ->
                    App.GS.volumeOptions.isFixedVolumeAtReverse = sharedPreferences.getBoolean(key, SETTINGS_REVERSE_FIXED_VOLUME_DEFAULT);
            case SETTINGS_REVERSE_PERCENT_VOLUME ->
                    App.GS.volumeOptions.isPercentVolumeAtReverse = sharedPreferences.getBoolean(key, SETTINGS_REVERSE_PERCENT_VOLUME_DEFAULT);
            case SETTINGS_REVERSE_FIXED_VOLUME_LEVEL ->
                    App.GS.volumeOptions.fixedVolumeLevelAtReverse = sharedPreferences.getInt(key, SETTINGS_REVERSE_FIXED_VOLUME_LEVEL_DEFAULT);
            case SETTINGS_REVERSE_PERCENT_VOLUME_LEVEL ->
                    App.GS.volumeOptions.percentVolumeLevelAtReverse = sharedPreferences.getInt(key, SETTINGS_REVERSE_PERCENT_VOLUME_LEVEL_DEFAULT);
        }
	}
}
