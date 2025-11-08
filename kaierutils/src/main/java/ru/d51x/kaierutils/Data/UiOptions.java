package ru.d51x.kaierutils.Data;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import ru.d51x.kaierutils.App;

public class UiOptions {
    public boolean isAutoStart;
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
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences (App.getInstance ());
        isAutoStart = prefs.getBoolean ("kaierutils_auto_start", false);
        isAutoStartFloating = prefs.getBoolean ("kaierutils_auto_start_floating", true);
        floatingWindowVertical = prefs.getBoolean ("floating_window_vertical", true);
        showFloatingOnMinimize = prefs.getBoolean ("floating_window_show_on_minimize", true);
        isColorSpeed = prefs.getBoolean ("kaierutils_show_color_speed", false);
        floatingWindowLeft = prefs.getInt ("floating_window_left", 0);
        floatingWindowTop = prefs.getInt ("floating_window_top", 0);
    }

    public void save() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences (App.getInstance ());
    }
}
