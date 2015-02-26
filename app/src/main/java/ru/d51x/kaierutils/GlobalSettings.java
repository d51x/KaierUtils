package ru.d51x.kaierutils;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Dmitriy on 21.02.2015.
 */
public class GlobalSettings {

	//public static final boolean IN_EMULATOR = true;
	private int Volume;
	//private int Brightness;
	//private int BrightnessMode;

	public boolean isNeedSoundDecreaseAtStartUp;
	public int VolumeLevelAtStartUp;
	public boolean isNeedSoundDecreaseAtWakeUp;
	public int VolumeLevelAtWakeUp;

	public boolean isNeedSoundDecreaseAtReverse;
	public boolean isFixedVolumeAtReverse;
	public boolean isPercentVolumeAtReverse;
	public int FixedVolumeLevelAtReverse;
	public int PercentVolumeLevelAtReverse;

    public int ReverseActivityCount;
    public int SleepModeCount;
    public long startDate;
    public long workTime;
    public long lastSleep;

    public boolean interactWithPowerAmp;
    public boolean needWatchSleepPowerAmp;
    public boolean needWatchWakeUpPowerAmp;
    public boolean needWatchBootUpPowerAmp;

    public boolean pressNextFolderPowerAmp;
    public boolean pressPrevFolderPowerAmp;

    public boolean pa_isPlaying;
    public boolean pa_isStarted;

    public int powerampResumeDelay = 3000;

	public GlobalSettings() {
		Volume = 3;
		//Brightness = 7;
		//BrightnessMode = 0;
		isNeedSoundDecreaseAtStartUp = false;
		isNeedSoundDecreaseAtWakeUp = false;
		VolumeLevelAtStartUp = 3;
		VolumeLevelAtWakeUp = 3;

		isNeedSoundDecreaseAtReverse = false;
		isFixedVolumeAtReverse = false;
		isPercentVolumeAtReverse = false;
		FixedVolumeLevelAtReverse = 3;
		PercentVolumeLevelAtReverse = 30;
        ReverseActivityCount = 0;
        SleepModeCount = 0;
        startDate = System.currentTimeMillis();
        lastSleep = 0;
        workTime = System.currentTimeMillis() - startDate;

        interactWithPowerAmp = false;
        needWatchSleepPowerAmp = false;
        needWatchWakeUpPowerAmp = false;
        needWatchBootUpPowerAmp = false;
        pressNextFolderPowerAmp = false;
        pressPrevFolderPowerAmp = false;




        pa_isPlaying = false;
        pa_isStarted = false;

		try {
			Volume = getVolumeLevel ();
			//Brightness = getBrightnessLevel ();
			//BrightnessMode = getBrightnessMode ();

			SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences (App.getInstance ());
			isNeedSoundDecreaseAtStartUp = prefs.getBoolean ("CAR_SETTINGS__VOLUME_AT_START_UP__DO_CHANGE", false);
			VolumeLevelAtStartUp = prefs.getInt("CAR_SETTINGS__VOLUME_AT_START_UP__LEVEL", 3);
			isNeedSoundDecreaseAtWakeUp = prefs.getBoolean ("CAR_SETTINGS__VOLUME_AT_WAKE_UP__DO_CHANGE", false);
			VolumeLevelAtWakeUp = prefs.getInt("CAR_SETTINGS__VOLUME_AT_WAKE_UP__LEVEL", 3);


			isNeedSoundDecreaseAtReverse = prefs.getBoolean ("CAR_SETTINGS__VOLUME_AT_REVERSE__DO_CHANGE", false);
			isFixedVolumeAtReverse = prefs.getBoolean ("CAR_SETTINGS__VOLUME_AT_REVERSE__FIXED_LEVEL_AVAILABLE", false);
			isPercentVolumeAtReverse = prefs.getBoolean ("CAR_SETTINGS__VOLUME_AT_REVERSE__PERCENTAGE_LEVEL_AVAILABLE", false);
			FixedVolumeLevelAtReverse = prefs.getInt("CAR_SETTINGS__VOLUME_AT_REVERSE__FIXED_LEVEL", 3);
			PercentVolumeLevelAtReverse = prefs.getInt("CAR_SETTINGS__VOLUME_AT_REVERSE__PERCENTAGE_LEVEL", 30);

		} catch (Exception e) {

		}
	}

	private void InitValues() {

	}

	public int getVolumeLevel () {
		return Volume;
	}

	public boolean setVolumeLevel (int level, boolean change) {
		if ( !change ) {
			Volume = level;
			return true;
		}
		if ( TWUtilEx.setVolumeLevel( level )) {
			Volume = level;
			return true;
		} else { return false;}
	}

//	public int getBrightnessLevel () {
//		return Brightness;
//	}

//	public boolean setBrightnessLevel (int level, boolean change) {
//		if ( !change ) {
//			Brightness = level;
//			return true;
//		}
//
//		if ( TWUtilEx.setBrightnessLevel (level)) {
//			Brightness = level;
//			return true;
//		} else { return false;}
//	}

//	public int getBrightnessMode() {
//		return BrightnessMode;
//	}

//	public boolean setBrightnessMode(int mode, boolean change) {
//		if ( !change ) {
//			BrightnessMode = mode;
//			return true;
//		}
//		if ( TWUtilEx.setBrightnessMode ( mode ) ) {
//			BrightnessMode = mode;
//			return true;
//		} else { return false; }
//	}

}
