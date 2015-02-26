package ru.d51x.kaierutils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.Context;

/**
 * Created by pyatyh_ds on 26.02.2015.
 */
public class NotifyData {

    public static final int NOTIFY_ID = 1;
    public static final int NOTIFY_VOLUME_CHANGED_ID = 2;
    public static final String NOTIFICATION_TITLE = "KaierUtils";
    public static final String OPTION_SHOW_NOTIFICATION_ICON = "kaierutils_show_notification_icon";
    public static final String OPTION_SHOW_VOLUME_ON_NOTIFICATION_ICON = "kaierutils_show_volume_on_notification_icon";

    public int NotifyID;
    public int smallIcon; // -1 - нет иконки
    public String Title;
    public String Text;
    public int volumeLevel;
    public int flags;
    public Class ActivityClass;
    public int number;

    public NotifyData( ) {
        NotifyID = NOTIFY_ID;
        smallIcon = R.drawable.notify_auto;
        Title = NOTIFICATION_TITLE;
        Text = "";
        volumeLevel = -1;
        flags =  0;
        ActivityClass = MainActivity.class;
        number = 0;
    }

}
