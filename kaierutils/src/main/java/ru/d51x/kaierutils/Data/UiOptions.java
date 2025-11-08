package ru.d51x.kaierutils.Data;

import static androidx.preference.PreferenceManager.getDefaultSharedPreferences;

import static ru.d51x.kaierutils.Settings.SettingConstants.SETTINGS_AUTOSTART_FLOATING;
import static ru.d51x.kaierutils.Settings.SettingConstants.SETTINGS_AUTOSTART_FLOATING_DEFAULT;
import static ru.d51x.kaierutils.Settings.SettingConstants.SETTINGS_COLOR_SPEED;
import static ru.d51x.kaierutils.Settings.SettingConstants.SETTINGS_COLOR_SPEED_DEFAULT;
import static ru.d51x.kaierutils.Settings.SettingConstants.SETTINGS_FLOATING_WINDOW_ON_MININIMIZE;
import static ru.d51x.kaierutils.Settings.SettingConstants.SETTINGS_FLOATING_WINDOW_ON_MININIMIZE_DEFAULT;

import android.content.SharedPreferences;

import ru.d51x.kaierutils.App;

public class UiOptions {
    public boolean isAutoStartFloating = true;
    public boolean floatingWindowVertical = true;
    public boolean showFloatingOnMinimize = true;
    public boolean isShowStatistics;

    public boolean isColorSpeed = false;

    public int floatingWindowLeft = 0;
    public int floatingWindowTop = 0;

    public UiOptions() {

    }

    public void load() {
        SharedPreferences prefs = getDefaultSharedPreferences (App.getInstance ());
        isAutoStartFloating = prefs.getBoolean (SETTINGS_AUTOSTART_FLOATING, SETTINGS_AUTOSTART_FLOATING_DEFAULT);
        floatingWindowVertical = prefs.getBoolean ("floating_window_vertical", true);
        showFloatingOnMinimize = prefs.getBoolean (SETTINGS_FLOATING_WINDOW_ON_MININIMIZE, SETTINGS_FLOATING_WINDOW_ON_MININIMIZE_DEFAULT);
        isColorSpeed = prefs.getBoolean (SETTINGS_COLOR_SPEED, SETTINGS_COLOR_SPEED_DEFAULT);
        floatingWindowLeft = prefs.getInt ("floating_window_left", 0);
        floatingWindowTop = prefs.getInt ("floating_window_top", 0);
    }

//    public void save() {
//        SharedPreferences prefs = getDefaultSharedPreferences (App.getInstance ());
//    }
}
