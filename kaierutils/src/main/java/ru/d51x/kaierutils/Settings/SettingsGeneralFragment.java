package ru.d51x.kaierutils.Settings;


import static androidx.preference.PreferenceManager.getDefaultSharedPreferences;

import static ru.d51x.kaierutils.Settings.SettingConstants.SETTINGS_AUTOSTART_FLOATING;
import static ru.d51x.kaierutils.Settings.SettingConstants.SETTINGS_AUTOSTART_FLOATING_DEFAULT;
import static ru.d51x.kaierutils.Settings.SettingConstants.SETTINGS_COLOR_SPEED;
import static ru.d51x.kaierutils.Settings.SettingConstants.SETTINGS_COLOR_SPEED_DEFAULT;
import static ru.d51x.kaierutils.Settings.SettingConstants.SETTINGS_FLOATING_WINDOW_ON_MININIMIZE;
import static ru.d51x.kaierutils.Settings.SettingConstants.SETTINGS_FLOATING_WINDOW_ON_MININIMIZE_DEFAULT;
import static ru.d51x.kaierutils.Settings.SettingConstants.SETTINGS_FLOATING_WINDOW_VERTICAL;
import static ru.d51x.kaierutils.Settings.SettingConstants.SETTINGS_FLOATING_WINDOW_VERTICAL_DEFAULT;
import static ru.d51x.kaierutils.Settings.SettingConstants.SETTINGS_GPS_SPEED;
import static ru.d51x.kaierutils.Settings.SettingConstants.SETTINGS_GPS_SPEED_DEFAULT;
import static ru.d51x.kaierutils.Settings.SettingConstants.SETTINGS_SHOW_STATISTICS;
import static ru.d51x.kaierutils.Settings.SettingConstants.SETTINGS_SHOW_STATISTICS_DEFAULT;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.preference.PreferenceFragmentCompat;

import java.util.Objects;

import ru.d51x.kaierutils.App;
import ru.d51x.kaierutils.R;

public class SettingsGeneralFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {

	@Override
	public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
		setPreferencesFromResource (R.xml.settings_general, rootKey);
		if (getActivity() != null) {
			getActivity().setTitle(getString(R.string.menu_general_title));
		}
		Context context = App.getInstance();
		SharedPreferences prefs = getDefaultSharedPreferences(context);
		prefs.registerOnSharedPreferenceChangeListener(this);
	}

	public void onPause() {
		super.onPause();
		Objects.requireNonNull(getPreferenceScreen().getSharedPreferences())
				.unregisterOnSharedPreferenceChangeListener(this);
	}

	public void onResume() {
		super.onResume();
		Objects.requireNonNull(getPreferenceScreen().getSharedPreferences())
				.registerOnSharedPreferenceChangeListener(this);
	}

	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
		if (key == null) return;
        switch (key) {
            case SETTINGS_AUTOSTART_FLOATING ->
                    App.GS.ui.isAutoStartFloating = sharedPreferences.getBoolean(key, SETTINGS_AUTOSTART_FLOATING_DEFAULT);
            case SETTINGS_FLOATING_WINDOW_ON_MININIMIZE ->
                    App.GS.ui.showFloatingOnMinimize = sharedPreferences.getBoolean(key, SETTINGS_FLOATING_WINDOW_ON_MININIMIZE_DEFAULT);
            case SETTINGS_FLOATING_WINDOW_VERTICAL ->
                    App.GS.ui.floatingWindowVertical = sharedPreferences.getBoolean(key, SETTINGS_FLOATING_WINDOW_VERTICAL_DEFAULT);
            case SETTINGS_COLOR_SPEED ->
                    App.GS.ui.isColorSpeed = sharedPreferences.getBoolean(key, SETTINGS_COLOR_SPEED_DEFAULT);
            case SETTINGS_SHOW_STATISTICS ->
                    App.GS.ui.isShowStatistics = sharedPreferences.getBoolean(key, SETTINGS_SHOW_STATISTICS_DEFAULT);
            case SETTINGS_GPS_SPEED ->
                    App.GS.isGpsSpeed = sharedPreferences.getBoolean(key, SETTINGS_GPS_SPEED_DEFAULT);
        }
	}
}
