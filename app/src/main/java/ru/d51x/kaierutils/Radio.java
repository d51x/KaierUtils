package ru.d51x.kaierutils;

import android.app.ActivityManager;
import android.content.Context;

import java.util.List;

/**
 */
public class Radio {

    protected static final CharSequence BLANK_STATION_NAME = "        ";
    protected static final String PACKAGE_NAME = "com.tw.radio";

    public boolean showToast;
    public boolean showInfo;
    public boolean activityRunning;
    public boolean skipSeekingMode;
    public boolean dontShowToastOnMainActivity;
    public int curNumber;
    public int curDiapazon;
    public String title;
    public String freq;

    public Radio(Context context) {
        showToast = false;
        showInfo = false;
        activityRunning = false;
        skipSeekingMode = false;
        dontShowToastOnMainActivity = false;
        title = "";
        freq = "";
        curNumber = -1;
        curDiapazon = -1;
    }

    public static void checkRadioActivityStarted(boolean kill) {
        App.GS.radio.activityRunning = false;
        ActivityManager activityManager = (ActivityManager) App.getInstance().getSystemService("activity");
        List<ActivityManager.RunningTaskInfo> tasks = activityManager.getRunningTasks(100);
        for (ActivityManager.RunningTaskInfo task:tasks) {
            if ( task.baseActivity.getPackageName().equalsIgnoreCase( Radio.PACKAGE_NAME )) {
                App.GS.radio.activityRunning = true;
                if ( kill ) {
                    //TODO activityManager.forceStopPackage("com.tw.radio");
                    activityManager.killBackgroundProcesses("com.tw.radio:RadioService");

                }
                return;
            }
        }
    }
}

/*

 handlerForRadio = new Handler(){

            private String title_RadioStation = "";
            private int frequency_RadioStation = 0;
            private int number_RadioStation = 0;
            private String diapazonRadio = "";
            private boolean dataReceived = false;

   @Override
            public void handleMessage(Message msg) {
                Log.d("Handle message from TWutilRadio: "+ dumpMessage(msg));
                switch (msg.what){

                    case  TWU_CONTEXT_RADIO_DATA:  // обработка радио-инфы
                        switch (msg.arg1) {
                            case 1:
                                diapazonRadio = getCurentDiapazonRadio(msg.arg2); // прилетает при запуске радио
                                                                                                              //и переключении бандов
                                break;
                            case 2:                                                                       // этот  арг всегда прилетает первым
                                frequency_RadioStation = msg.arg2;
                                title_RadioStation = String.valueOf(msg.obj);
                                dataReceived = true;
                                break;
                            case 4:                                                                     // получаем номер ячейки памяти и кидаем интент на  отрисовку
                                number_RadioStation = msg.arg2;
                                if (dataReceived) {
                                    Intent intent = new Intent();
                                    intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                                    intent.setAction(BRD_TAG_RADIO_INFO);
                                    intent.putExtra("number",String.valueOf(number_RadioStation +1));
                                    intent.putExtra("title",title_RadioStation);
                                    intent.putExtra("diapazon",diapazonRadio);
                                    intent.putExtra("frequency",String.format ("%1.2f",  (float) frequency_RadioStation / 100));
                                    App.getInstance().sendBroadcast(intent);
                                }
                                dataReceived = false;
                                number_RadioStation = 0;
                                title_RadioStation = "";
                                frequency_RadioStation = 0;
                                break;
                            default:
                                break;
                        }

                    default:
                        break;
                }



            }

            private String getCurentDiapazonRadio (int i) {
                switch (i) {
                    case 0:
                        return "FM1";
                    case 1:
                        return "FM2";
                    case 2:
                        return "AM";
                    default:
                        return "";
                }
            }

    };
 */