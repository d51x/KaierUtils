package ru.d51x.kaierutils;

import ru.d51x.kaierutils.Data.GpsData;
import ru.d51x.kaierutils.Data.PopupWindowOption;
import ru.d51x.kaierutils.Data.PowerAmpOptions;
import ru.d51x.kaierutils.Data.UiOptions;
import ru.d51x.kaierutils.Data.VolumeOptions;
import ru.d51x.kaierutils.Radio.Radio;
import ru.d51x.kaierutils.TWUtils.TWUtilEx;

/**
 * Created by Dmitriy on 21.02.2015.
 */
public class GlSets {

    public static final String GPS_BROADCAST_ACTION_LOCATION_CHANGED = "ru.d51x.kaierutils.action.LOCATION_CHANGED";
    public static final String GPS_BROADCAST_ACTION_FIRST_FIX = "ru.d51x.kaierutils.action.GPS_EVENT_FIRST_FIX";
    public static final String GPS_BROADCAST_ACTION_AGPS_RESET = "ru.d51x.kaierutils.action.GPS_EVENT_AGPS_RESET";
    public static final String PWRAMP_BROADCAST_ACTION_TRACK_CHANGED = "ru.d51x.kaierutils.action.TRACK_CHANGED";

    public Boolean btState = false;

    public Radio radio;
	private int volume;

    public VolumeOptions volumeOptions = new VolumeOptions();

    public int ReverseActivityCount;
    public int SleepModeCount;
    public boolean isStopedAfterWakeUp = false;
    public boolean isSleepMode = false;
    public boolean isReverseMode = false;
    public boolean isMoving = false; // едем сейчас или стоим больше
    public boolean isFirstStart = false; // едем сейчас или стоим больше
    public long prevTime = 0;
    public long SpeedStopTime = 4*60*1000;  // интервал с нулевой скоростью в минутах, если простояли дольше, значит вообще остановилилсь и не движемся, по хорошему, это надо вынести в настройки

    public long startDate;
    public long workTime;
    public long lastSleep;
    public long wakeUpTime;

    public PowerAmpOptions powerAmpOpt = new PowerAmpOptions();

    public GpsData gpsData = new GpsData();

    String BT_deviceAddress = "unknown";

    public PopupWindowOption popupWindowOption = new PopupWindowOption();
    public UiOptions ui = new UiOptions();

    public int curAudioFocusID = -1;



    public boolean isShowingFloatingPanel = false;
    public boolean showFloatingPanelButton = true;


	public GlSets() {
		volume = 3;
		//Brightness = 7;
		//BrightnessMode = 0;

        ReverseActivityCount = 0;
        SleepModeCount = 0;
        startDate = System.currentTimeMillis();
        lastSleep = 0;
        workTime = System.currentTimeMillis() - startDate;

        radio = new Radio(App.getInstance());

		try {
			volume = getVolumeLevel ();
            ui.load();
            volumeOptions.load();
            powerAmpOpt.load();
            popupWindowOption.load();
            radio.load();
            gpsData.load();
		} catch (Exception ignored) {

		}
	}

	private void InitValues() {

	}

	public int getVolumeLevel () {
		return volume;
	}

	public void setVolumeLevel (int level, boolean change) {
		if ( !change ) {
			volume = level;
			return;
		}
		if ( TWUtilEx.setVolumeLevel(level)) {
			volume = level;
        }
	}



}
