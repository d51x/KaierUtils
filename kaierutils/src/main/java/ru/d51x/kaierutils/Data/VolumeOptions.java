package ru.d51x.kaierutils.Data;

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
import static ru.d51x.kaierutils.Settings.SettingConstants.SETTINGS_SLEEP_WAKEUP_VOLUME_STARTUP;
import static ru.d51x.kaierutils.Settings.SettingConstants.SETTINGS_SLEEP_WAKEUP_VOLUME_STARTUP_DEFAULT;
import static ru.d51x.kaierutils.Settings.SettingConstants.SETTINGS_SLEEP_WAKEUP_VOLUME_STARTUP_LEVEL;
import static ru.d51x.kaierutils.Settings.SettingConstants.SETTINGS_SLEEP_WAKEUP_VOLUME_STARTUP_LEVEL_DEFAULT;
import static ru.d51x.kaierutils.Settings.SettingConstants.SETTINGS_SLEEP_WAKEUP_VOLUME_WAKEUP;
import static ru.d51x.kaierutils.Settings.SettingConstants.SETTINGS_SLEEP_WAKEUP_VOLUME_WAKEUP_DEFAULT;
import static ru.d51x.kaierutils.Settings.SettingConstants.SETTINGS_SLEEP_WAKEUP_VOLUME_WAKEUP_LEVEL;
import static ru.d51x.kaierutils.Settings.SettingConstants.SETTINGS_SLEEP_WAKEUP_VOLUME_WAKEUP_LEVEL_DEFAULT;

import android.content.SharedPreferences;

import ru.d51x.kaierutils.App;

public class VolumeOptions {
    public boolean isNeedSoundDecreaseAtStartUp;
    public int volumeLevelAtStartUp;
    public boolean isNeedSoundDecreaseAtWakeUp;
    public int volumeLevelAtWakeUp;
    public boolean isNeedSoundDecreaseAtReverse;
    public boolean isFixedVolumeAtReverse;
    public boolean isPercentVolumeAtReverse;
    public int fixedVolumeLevelAtReverse;
    public int percentVolumeLevelAtReverse;
    public VolumeOptions() {
        isNeedSoundDecreaseAtStartUp = false;
        isNeedSoundDecreaseAtWakeUp = false;
        volumeLevelAtStartUp = 3;
        volumeLevelAtWakeUp = 3;
        isNeedSoundDecreaseAtReverse = false;
        isFixedVolumeAtReverse = false;
        isPercentVolumeAtReverse = false;
        fixedVolumeLevelAtReverse = 3;
        percentVolumeLevelAtReverse = 30;
    }

//    public void save() {
//        SharedPreferences prefs = getDefaultSharedPreferences (App.getInstance ());
//
//    }

    public void load() {
        SharedPreferences prefs = getDefaultSharedPreferences (App.getInstance ());
        isNeedSoundDecreaseAtStartUp = prefs.getBoolean (SETTINGS_SLEEP_WAKEUP_VOLUME_STARTUP, SETTINGS_SLEEP_WAKEUP_VOLUME_STARTUP_DEFAULT);
        volumeLevelAtStartUp = prefs.getInt(SETTINGS_SLEEP_WAKEUP_VOLUME_STARTUP_LEVEL, SETTINGS_SLEEP_WAKEUP_VOLUME_STARTUP_LEVEL_DEFAULT);
        isNeedSoundDecreaseAtWakeUp = prefs.getBoolean (SETTINGS_SLEEP_WAKEUP_VOLUME_WAKEUP, SETTINGS_SLEEP_WAKEUP_VOLUME_WAKEUP_DEFAULT);
        volumeLevelAtWakeUp = prefs.getInt(SETTINGS_SLEEP_WAKEUP_VOLUME_WAKEUP_LEVEL, SETTINGS_SLEEP_WAKEUP_VOLUME_WAKEUP_LEVEL_DEFAULT);


        isNeedSoundDecreaseAtReverse = prefs.getBoolean (SETTINGS_REVERSE_VOLUME, SETTINGS_REVERSE_VOLUME_DEFAULT);
        isFixedVolumeAtReverse = prefs.getBoolean (SETTINGS_REVERSE_FIXED_VOLUME, SETTINGS_REVERSE_FIXED_VOLUME_DEFAULT);
        isPercentVolumeAtReverse = prefs.getBoolean (SETTINGS_REVERSE_PERCENT_VOLUME, SETTINGS_REVERSE_PERCENT_VOLUME_DEFAULT);
        fixedVolumeLevelAtReverse = prefs.getInt(SETTINGS_REVERSE_FIXED_VOLUME_LEVEL, SETTINGS_REVERSE_FIXED_VOLUME_LEVEL_DEFAULT);
        percentVolumeLevelAtReverse = prefs.getInt(SETTINGS_REVERSE_PERCENT_VOLUME_LEVEL, SETTINGS_REVERSE_PERCENT_VOLUME_LEVEL_DEFAULT);
    }
}
