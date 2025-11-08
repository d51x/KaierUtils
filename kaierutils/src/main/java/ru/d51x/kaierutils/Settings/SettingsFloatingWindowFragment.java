package ru.d51x.kaierutils.Settings;


import static androidx.preference.PreferenceManager.getDefaultSharedPreferences;
import static ru.d51x.kaierutils.FloatingWindow.DEFAULT_ICON_SIZE;
import static ru.d51x.kaierutils.FloatingWindow.DEFAULT_MAIN_TEXT_SIZE;
import static ru.d51x.kaierutils.FloatingWindow.DEFAULT_SECOND_TEXT_SIZE;
import static ru.d51x.kaierutils.FloatingWindow.DEFAULT_UNITS_TEXT_SIZE;
import static ru.d51x.kaierutils.Settings.SettingConstants.SETTINGS_FLOATING_PANEL_BATTERY;
import static ru.d51x.kaierutils.Settings.SettingConstants.SETTINGS_FLOATING_PANEL_BATTERY_DEFAULT;
import static ru.d51x.kaierutils.Settings.SettingConstants.SETTINGS_FLOATING_PANEL_COOLANT;
import static ru.d51x.kaierutils.Settings.SettingConstants.SETTINGS_FLOATING_PANEL_COOLANT_DEFAULT;
import static ru.d51x.kaierutils.Settings.SettingConstants.SETTINGS_FLOATING_PANEL_CVT;
import static ru.d51x.kaierutils.Settings.SettingConstants.SETTINGS_FLOATING_PANEL_CVT_DEFAULT;
import static ru.d51x.kaierutils.Settings.SettingConstants.SETTINGS_FLOATING_PANEL_DISTANCE;
import static ru.d51x.kaierutils.Settings.SettingConstants.SETTINGS_FLOATING_PANEL_DISTANCE_DEFAULT;
import static ru.d51x.kaierutils.Settings.SettingConstants.SETTINGS_FLOATING_PANEL_FUEL_LEVEL;
import static ru.d51x.kaierutils.Settings.SettingConstants.SETTINGS_FLOATING_PANEL_FUEL_LEVEL_DEFAULT;
import static ru.d51x.kaierutils.Settings.SettingConstants.SETTINGS_FLOATING_PANEL_FUEL_RATE;
import static ru.d51x.kaierutils.Settings.SettingConstants.SETTINGS_FLOATING_PANEL_FUEL_RATE_DEFAULT;
import static ru.d51x.kaierutils.Settings.SettingConstants.SETTINGS_FLOATING_PANEL_ICON_SIZE;
import static ru.d51x.kaierutils.Settings.SettingConstants.SETTINGS_FLOATING_PANEL_MAIN_TEXT_SIZE;
import static ru.d51x.kaierutils.Settings.SettingConstants.SETTINGS_FLOATING_PANEL_SECOND_TEXT_SIZE;
import static ru.d51x.kaierutils.Settings.SettingConstants.SETTINGS_FLOATING_PANEL_UNITS;
import static ru.d51x.kaierutils.Settings.SettingConstants.SETTINGS_FLOATING_PANEL_UNITS_DEFAULT;
import static ru.d51x.kaierutils.Settings.SettingConstants.SETTINGS_FLOATING_PANEL_UNITS_SIZE;
import static ru.d51x.kaierutils.Settings.SettingConstants.SETTINGS_FLOATING_PANEL_SPEED;
import static ru.d51x.kaierutils.Settings.SettingConstants.SETTINGS_FLOATING_PANEL_SPEED_DEFAULT;
import static ru.d51x.kaierutils.Settings.SettingConstants.SETTINGS_FLOATING_WINDOW_VERTICAL;
import static ru.d51x.kaierutils.Settings.SettingConstants.SETTINGS_FLOATING_WINDOW_VERTICAL_DEFAULT;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.annotation.Nullable;
import androidx.preference.EditTextPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import java.util.Objects;

import ru.d51x.kaierutils.App;
import ru.d51x.kaierutils.R;

public class SettingsFloatingWindowFragment extends PreferenceFragmentCompat implements OnSharedPreferenceChangeListener {

	private final Preference.SummaryProvider<EditTextPreference> summaryProvider = preference -> {
        String text = preference.getText();
		switch (preference.getKey()) {
			case SETTINGS_FLOATING_PANEL_ICON_SIZE:
				if (!TextUtils.isEmpty(text))
					return String.format(getString(R.string.menu_floating_panel_icon_size_summary), text);
			case SETTINGS_FLOATING_PANEL_MAIN_TEXT_SIZE:
				if (!TextUtils.isEmpty(text))
					return String.format(getString(R.string.menu_floating_panel_main_text_size_summary), text);
			case SETTINGS_FLOATING_PANEL_SECOND_TEXT_SIZE:
				if (!TextUtils.isEmpty(text))
					return String.format(getString(R.string.menu_floating_panel_second_text_size_summary), text);
			case SETTINGS_FLOATING_PANEL_UNITS_SIZE:
				if (!TextUtils.isEmpty(text))
					return String.format(getString(R.string.menu_floating_panel_units_text_size_summary), text);
			default:
				return "not set";
		}
    };

    @Override
	public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
		setPreferencesFromResource (R.xml.floating_panel, rootKey);
		if (getActivity() != null) {
			getActivity().setTitle(getString(R.string.header_floating_window_title));
		}
		Context context = App.getInstance();
		SharedPreferences prefs = getDefaultSharedPreferences(context);
		prefs.registerOnSharedPreferenceChangeListener(this);

		EditTextPreference iconSizePref = findPreference(SETTINGS_FLOATING_PANEL_ICON_SIZE);
		iconSizePref.setSummaryProvider(summaryProvider);

		EditTextPreference mainSizePref = findPreference(SETTINGS_FLOATING_PANEL_MAIN_TEXT_SIZE);
		mainSizePref.setSummaryProvider(summaryProvider);

		EditTextPreference secondSizePref = findPreference(SETTINGS_FLOATING_PANEL_SECOND_TEXT_SIZE);
		secondSizePref.setSummaryProvider(summaryProvider);

		EditTextPreference unitSizePref = findPreference(SETTINGS_FLOATING_PANEL_UNITS_SIZE);
		unitSizePref.setSummaryProvider(summaryProvider);
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
		if (SETTINGS_FLOATING_PANEL_SPEED.equals(key)) {
			App.floatingWindow.showSpeed = sharedPreferences.getBoolean ( key, SETTINGS_FLOATING_PANEL_SPEED_DEFAULT);
		}
		else if (SETTINGS_FLOATING_PANEL_BATTERY.equals(key)) {
			App.floatingWindow.showBatteryLevel = sharedPreferences.getBoolean ( key, SETTINGS_FLOATING_PANEL_BATTERY_DEFAULT);
		}
		else if (SETTINGS_FLOATING_PANEL_COOLANT.equals(key)) {
			App.floatingWindow.showCoolantTemperature = sharedPreferences.getBoolean ( key, SETTINGS_FLOATING_PANEL_COOLANT_DEFAULT);
		}
		else if (SETTINGS_FLOATING_PANEL_CVT.equals(key)) {
			App.floatingWindow.showCvtTemperature = sharedPreferences.getBoolean ( key, SETTINGS_FLOATING_PANEL_CVT_DEFAULT);
		}
		else if (SETTINGS_FLOATING_PANEL_FUEL_LEVEL.equals(key)) {
			App.floatingWindow.showFuelLevel = sharedPreferences.getBoolean ( key, SETTINGS_FLOATING_PANEL_FUEL_LEVEL_DEFAULT);
		}
		else if (SETTINGS_FLOATING_PANEL_FUEL_RATE.equals(key)) {
			App.floatingWindow.showFuelConsumption = sharedPreferences.getBoolean ( key, SETTINGS_FLOATING_PANEL_FUEL_RATE_DEFAULT);
		}
		else if (SETTINGS_FLOATING_PANEL_DISTANCE.equals(key)) {
			App.floatingWindow.showDistance = sharedPreferences.getBoolean ( key, SETTINGS_FLOATING_PANEL_DISTANCE_DEFAULT);
		}
		else if (SETTINGS_FLOATING_PANEL_ICON_SIZE.equals(key)) {
			App.floatingWindow.iconSize = Integer.parseInt(sharedPreferences.getString(key, Integer.toString(DEFAULT_ICON_SIZE)));
		}
		else if (SETTINGS_FLOATING_PANEL_MAIN_TEXT_SIZE.equals(key)) {
			App.floatingWindow.mainTextSize = Integer.parseInt(sharedPreferences.getString(key, Integer.toString(DEFAULT_MAIN_TEXT_SIZE)));
		}
		else if (SETTINGS_FLOATING_PANEL_SECOND_TEXT_SIZE.equals(key)) {
			App.floatingWindow.secondTextSize = Integer.parseInt(sharedPreferences.getString(key, Integer.toString(DEFAULT_SECOND_TEXT_SIZE)));
		}
		else if (SETTINGS_FLOATING_PANEL_UNITS_SIZE.equals(key)) {
			App.floatingWindow.unitsTextSize = Integer.parseInt(sharedPreferences.getString(key, Integer.toString(DEFAULT_UNITS_TEXT_SIZE)));
		}
		else if (SETTINGS_FLOATING_PANEL_UNITS.equals(key)) {
			App.floatingWindow.floatingWindowShowUnits = sharedPreferences.getBoolean(key, SETTINGS_FLOATING_PANEL_UNITS_DEFAULT);
		}
		else if (SETTINGS_FLOATING_WINDOW_VERTICAL.equals(key)) {
			App.GS.ui.floatingWindowVertical = sharedPreferences.getBoolean(key, SETTINGS_FLOATING_WINDOW_VERTICAL_DEFAULT);
		}
	}
}
