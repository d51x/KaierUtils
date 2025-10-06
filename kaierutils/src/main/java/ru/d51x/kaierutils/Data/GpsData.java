package ru.d51x.kaierutils.Data;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import ru.d51x.kaierutils.App;

public class GpsData {
    public int speed = 0;
    public int maxSpeed = 0;
    public long prevTimeAtWay = 0;
    public long timeAtWay = 0;
    public long timeAtWayWithoutStops = 0;
    public long prevTimeAtWayWithoutStops = 0;
    public long timeForAverageSpeed = 0;
    public long prevTimeForAverageSpeed = 0;
    public long firstTimeAtWay = 0;
    public int averageSpeed = 0;
    public int timeAtWayType = 0;   // 0 - не учитывать простои, 1 - учитывать простои
    public boolean isGpsHangs = false;
    public int cntGpsHangs = 0;
    //public boolean isFirstRunGPS = false;
    public boolean isFirstFixGPS = false;

    public long locRequestUpdateTime = 1; //0; // msec
    public float locRequestMinDistance = 1f; // meters
    public float totalDistance = 0;
    public float totalDistanceForAverageSpeed = 0;
    public GpsData() {

    }

    public void load() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences (App.getInstance ());
        timeAtWayType = prefs.getInt("CAR_SETTINGS__GPS_TIME_AT_WAY_TYPE", 0);
    }

    public void setMaxSpeed(int speed) {
        if ( speed > maxSpeed)
            maxSpeed = speed;
    }
    public void setAverageSpeed(int speed) {
        if ( speed > 0 && timeForAverageSpeed > 0)
            averageSpeed = Math.round(totalDistanceForAverageSpeed * 3600 / timeForAverageSpeed);

    }
}
