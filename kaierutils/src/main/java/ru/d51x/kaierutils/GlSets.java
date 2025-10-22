package ru.d51x.kaierutils;

import ru.d51x.kaierutils.Data.GpsData;
import ru.d51x.kaierutils.Data.UiOptions;
import ru.d51x.kaierutils.Data.VolumeOptions;
import ru.d51x.kaierutils.TWUtils.TWUtilEx;

/**
 * Created by Dmitriy on 21.02.2015.
 */
public class GlSets {

    public static final String GPS_BROADCAST_ACTION_LOCATION_CHANGED = "ru.d51x.kaierutils.action.LOCATION_CHANGED";
    public static final String GPS_BROADCAST_ACTION_FIRST_FIX = "ru.d51x.kaierutils.action.GPS_EVENT_FIRST_FIX";
    public static final String GPS_BROADCAST_ACTION_AGPS_RESET = "ru.d51x.kaierutils.action.GPS_EVENT_AGPS_RESET";

    public Boolean btState = false;

	private int Volume;

    public boolean isGpsSpeed = false;
    public VolumeOptions volumeOptions = new VolumeOptions();

    public int ReverseActivityCount;
    public int SleepModeCount;
    public boolean isStopedAfterWakeUp = false;
    public boolean isSleepMode = false;
    public boolean isReverseMode = false;
    public boolean isMoving = false; // едем сейчас или стоим больше
    public boolean isFirstStart = false; // едем сейчас или стоим больше
    public long prevTime = 0;
    public long startDate;
    public long workTime;
    public long lastSleep;
    public long wakeUpTime;
    public GpsData gpsData = new GpsData();
    public UiOptions ui = new UiOptions();
    public int curAudioFocusID = -1;
    public boolean isShowingFloatingPanel = false;
    public boolean showFloatingPanelButton = true;


	public GlSets() {
		Volume = 3;
        ReverseActivityCount = 0;
        SleepModeCount = 0;
        startDate = System.currentTimeMillis();
        lastSleep = 0;
        workTime = System.currentTimeMillis() - startDate;

		try {
			Volume = getVolumeLevel ();
            ui.load();
            volumeOptions.load();
            gpsData.load();
		} catch (Exception ignored) {

		}
	}

	public int getVolumeLevel () {
		return Volume;
	}

	public void setVolumeLevel (int level, boolean change) {
		if ( !change ) {
			Volume = level;
			return;
		}
		if ( TWUtilEx.setVolumeLevel(level)) {
			Volume = level;
        }
	}



}
