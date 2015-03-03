package ru.d51x.kaierutils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.util.Log;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.location.GpsStatus;
import android.location.GpsSatellite;
import android.widget.Toast;
import java.util.Timer;
import java.util.TimerTask;

public class GpsProcessing implements LocationListener, GpsStatus.Listener {

    public static final int signal_quality = 5;
    private static final int min_good_sats = 3;
    private static final int delayTimer = 40;

    private static final int MINUTES_TWO = 1000 * 120;
    private BroadcastReceiver gpsReceiver;
    private Context context;
    private Handler mHandler;

    private LocationManager mLocationManager;
    private Location gpsLocation = null;

    private Location prevLocation = null;
    private Location firstLocation = null;
    private Location lastLocation = null;

    private int goodSatellitesCount = 0;
    private Timer mTimer;
    private Thread monThread;
    private int gpsevent = 0;
    private int pausedelay;
    private int timer, gpstime;

    private FirstRunTimerTask firstRunTimerTask;

    public GpsProcessing(Context context) {
        Log.d("GpsProcessing", "GpsProcessing");

        this.context = context;
        mHandler = new Handler();

        gpsReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                processIntent(intent);
            }
        };

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(TWUtilConst.TWUTIL_BROADCAST_ACTION_KEY_PRESSED);
        intentFilter.addAction(TWUtilConst.TWUTIL_BROADCAST_ACTION_SLEEP);
        intentFilter.addAction(TWUtilConst.TWUTIL_BROADCAST_ACTION_WAKE_UP);
        intentFilter.addAction(GlSets.GPS_BROADCAST_ACTION_AGPS_RESET);
        context.registerReceiver(gpsReceiver, intentFilter);

        mLocationManager = (LocationManager) context.getSystemService( Context.LOCATION_SERVICE);
        mLocationManager.addGpsStatusListener(this);
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, App.mGlSets.locRequestUpdateTime, App.mGlSets.locRequestMinDistance, this);

        // запуск первого таймера, задержка в 2 минуты после старта для стабилизации спутников
        if (mTimer != null) {
            mTimer.cancel();
        }
        mTimer = new Timer();

        firstRunTimerTask = new FirstRunTimerTask();
        mTimer.schedule(firstRunTimerTask, MINUTES_TWO);

        gpstime = 0;
        timer = 0;
    }

    public void onGpsStatusChanged(int event) {
        Intent intent = new Intent();
        gpsevent = 0;
        switch (event) {
            case GpsStatus.GPS_EVENT_STARTED:   // 1
                App.mGlSets.isFirstFixGPS = false;
                intent.setAction( GlSets.GPS_BROADCAST_ACTION_EVENT_STATUS );
                intent.putExtra("gps_event", GpsStatus.GPS_EVENT_STARTED);
                context.sendBroadcast(intent);
                break;
            case GpsStatus.GPS_EVENT_FIRST_FIX: // 3
                //Event sent when the GPS system has received its first fix since starting.
                App.mGlSets.isFirstFixGPS = true;
                intent.setAction( GlSets.GPS_BROADCAST_ACTION_EVENT_STATUS );
                intent.putExtra("gps_event", GpsStatus.GPS_EVENT_FIRST_FIX);
                context.sendBroadcast(intent);
                break;
            case GpsStatus.GPS_EVENT_STOPPED:   // 2
                App.mGlSets.isFirstFixGPS = false;
                intent.setAction( GlSets.GPS_BROADCAST_ACTION_EVENT_STATUS );
                intent.putExtra("gps_event", GpsStatus.GPS_EVENT_STOPPED);
                context.sendBroadcast(intent);
                break;
            case GpsStatus.GPS_EVENT_SATELLITE_STATUS: // 4
                // инфа о спутниках
                goodSatellitesCount = 0;
                GpsStatus status = mLocationManager.getGpsStatus(null);
                Iterable<GpsSatellite> itSatellites = status.getSatellites();
                int cntSats = 0;
                for (GpsSatellite it : itSatellites) {
                    cntSats++;
                    /*
                    SNR is mapped to signal strength [0,1,4-9] COMMENT
                    SNR: >500 >100 >50 >10 >5 >0 bad n/a COMMENT
                    sig: 9    8    7   6   5  4  1   0   COMMENT
                     */
                    if (it.getSnr() >= ((float) signal_quality)) {
                        goodSatellitesCount++;
                    }
                }

                intent.setAction( GlSets.GPS_BROADCAST_ACTION_SATELLITE_STATUS );
                intent.putExtra("SatellitesTotal", cntSats);
                intent.putExtra("SatellitesGoodQATotal", goodSatellitesCount);
                context.sendBroadcast(intent);

                if ( (cntSats > 0) && (goodSatellitesCount >= min_good_sats) &&
                        App.mGlSets.isFirstRunGPS && App.mGlSets.isFirstFixGPS )
                {
                    gpstime = timer;
                }

               if ( (cntSats > 0) && (goodSatellitesCount < min_good_sats) && ((timer - gpstime) > delayTimer) &&
                       App.mGlSets.isFirstRunGPS && App.mGlSets.isFirstFixGPS)
                {
                  // 40 сек подождать, вдруг появятся спутники, инчае...
                   gpstime = timer;
                   pausedelay = 60;
                   App.mGlSets.cntGpsHangs++;
                   intent.setAction( GlSets.GPS_BROADCAST_ACTION_AGPS_RESET );
                   context.sendBroadcast(intent);
                  // 60 сек подождать до начала следующего мониторинга, чтобы опять не делался сброс
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        // новые данные о местоположении
        if ( location != null) processLocation(location);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    private void processIntent(Intent intent) {
        if (intent != null) {
            String action = intent.getAction();
            if ( action.equals(TWUtilConst.TWUTIL_BROADCAST_ACTION_SLEEP) ) {
                // stop location listener
                mLocationManager.removeUpdates(this);
            }
            else if (action.equals(TWUtilConst.TWUTIL_BROADCAST_ACTION_WAKE_UP)) {
               // start location listener after 30 sec
                Location lastKnownLocation = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                firstLocation = lastKnownLocation;
                processLocation(lastKnownLocation);
                mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, App.mGlSets.locRequestUpdateTime, App.mGlSets.locRequestMinDistance, this);
            }
            else if (action.equals(GlSets.GPS_BROADCAST_ACTION_AGPS_RESET))
            {
                resetAGPS();
            }

        }
    }



    public void Destroy() {
        context.unregisterReceiver(gpsReceiver);
        mLocationManager.removeUpdates(this);
        mLocationManager.removeGpsStatusListener(this);

        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }

    }

    private void processLocation(Location location) {

        double Latitude = location.getLatitude();
        double Longitude = location.getLongitude();
        double Altitude = location.getAltitude();
        float Accuracy = location.getAccuracy();
        int Speed = Math.round(location.getSpeed() * 3600 / 1000);


        //getBearing – насколько я понял, это угол, на который текущая траектория движения отклоняется от траектории на север. Кто точно знает, напишите, плз, на форуме!
        // String.format(
        // "Coordinates: lat = %1$.4f, lon = %2$.4f, time = %3$tF %3$tT",
        //        location.getLatitude(), location.getLongitude(), new Date(
        //        location.getTime()));
        // У Location есть пара методов для определения расстояния между точками: distanceBetween и distanceTo.

        //Лучше расскажите как получить пройденный путь
        // - Получать расстояние между прошлой точкой и текущей и суммировать всё.
        //и исключить погрешности в метании gps координат
        // - Отбрасывать точки, к примеру, которые GPSи точность которых более 300 метров, а также кол-во спутников менее 4-х. Также советую почитать про протокол NMEA
        // фильтра Калмана для сглаживания этих прыжков
        // 1. Самый простой: 4 спутника и более (причем данные брать с протокола NMEA), при этом "Индикатор качества GPS сигнала" должен быть>0, при этом качество GPS координат лучше 250
        // Возникла проблемка. Помогите, если можно. Получаю координаты, формирую стринг.

        //location.distanceTo(prevLocation)

	    try {
		    if ( App.mGlSets.isFirstFixGPS )
                   App.mGlSets.totalDistance += location.distanceTo ( prevLocation );
	    } catch (Exception e) {

	    }

        Intent intent = new Intent();
        intent.setAction( GlSets.GPS_BROADCAST_ACTION_LOCATION_CHANGED );
        intent.putExtra("Latitude", Latitude);
        intent.putExtra("Longitude", Longitude);
        intent.putExtra("Altitude", String.format("%1$.2f", Altitude));
        intent.putExtra("Accuracy", String.format("%1$.0f", Accuracy));
        intent.putExtra("Speed", Speed);
        context.sendBroadcast(intent);





        if ( App.mGlSets.dsc_isAvailable) {
            int  t = Math.abs(Speed - App.mGlSets.dsc_FirstSpeed) / App.mGlSets.dsc_StepSpeed;
            App.mGlSets.gpsSpeed = Speed;

            if (App.mGlSets.gpsPrevSpeed > Speed) {
                App.mGlSets.gpsSpeedGrow = -1;  // скорость уменьшилась
            } else if (App.mGlSets.gpsPrevSpeed < Speed) {
                App.mGlSets.gpsSpeedGrow = 1;   // скорость увеличилась
            } else {
                App.mGlSets.gpsSpeedGrow = 0;
            }


            Intent intent2 = new Intent();
            intent2.setAction(GlSets.GPS_BROADCAST_ACTION_SPEED_CHANGED);
            intent2.putExtra("Speed", Speed);
            intent2.putExtra("SpeedGrow", App.mGlSets.gpsSpeedGrow);
            context.sendBroadcast(intent2);
        }


	    prevLocation = location;

    }

    private void resetAGPS() {
        Bundle bundle = new Bundle();
        mLocationManager.sendExtraCommand("gps", "delete_aiding_data", null);
        mLocationManager.sendExtraCommand("gps", "force_xtra_injection", bundle);
        mLocationManager.sendExtraCommand("gps", "force_time_injection", bundle);
        Toast.makeText(context, "GPS завис\nAGPS данные были обнулены", Toast.LENGTH_LONG).show();
        App.mGlSets.isFirstFixGPS = false;
    }

    class FirstRunTimerTask extends TimerTask {
        @Override
        public void run() {
            App.mGlSets.isFirstRunGPS = true;
            // надо запустить новый таймер или новый поток, чтобы каждую секунду срабатывал
            pausedelay = 60;
            monThread = new Thread(new Runnable() {
                public void run() {
                    while (true) {
                        if ( gpsevent > 10 ) {
                            pausedelay = 1;
                        } else {
                            gpsevent++;
                        }
                        if ( pausedelay == 0) {
                            timer++;
                        }
                        if ( pausedelay > 0) {
                            pausedelay--;
                        }
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            monThread.start();
        }
    }
}
