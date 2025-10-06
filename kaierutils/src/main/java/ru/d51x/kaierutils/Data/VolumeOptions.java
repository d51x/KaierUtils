package ru.d51x.kaierutils.Data;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

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

    public void save() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences (App.getInstance ());

    }

    public void load() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences (App.getInstance ());
        isNeedSoundDecreaseAtStartUp = prefs.getBoolean ("CAR_SETTINGS__VOLUME_AT_START_UP__DO_CHANGE", false);
        volumeLevelAtStartUp = prefs.getInt("CAR_SETTINGS__VOLUME_AT_START_UP__LEVEL", 3);
        isNeedSoundDecreaseAtWakeUp = prefs.getBoolean ("CAR_SETTINGS__VOLUME_AT_WAKE_UP__DO_CHANGE", false);
        volumeLevelAtWakeUp = prefs.getInt("CAR_SETTINGS__VOLUME_AT_WAKE_UP__LEVEL", 3);


        isNeedSoundDecreaseAtReverse = prefs.getBoolean ("CAR_SETTINGS__VOLUME_AT_REVERSE__DO_CHANGE", false);
        isFixedVolumeAtReverse = prefs.getBoolean ("CAR_SETTINGS__VOLUME_AT_REVERSE__FIXED_LEVEL_AVAILABLE", false);
        isPercentVolumeAtReverse = prefs.getBoolean ("CAR_SETTINGS__VOLUME_AT_REVERSE__PERCENTAGE_LEVEL_AVAILABLE", false);
        fixedVolumeLevelAtReverse = prefs.getInt("CAR_SETTINGS__VOLUME_AT_REVERSE__FIXED_LEVEL", 3);
        percentVolumeLevelAtReverse = prefs.getInt("CAR_SETTINGS__VOLUME_AT_REVERSE__PERCENTAGE_LEVEL", 30);
    }
}
