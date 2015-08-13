package ru.d51x.kaierutils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.Context;

/**
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
    private Context context;

    public NotifyData(Context context ) {
        NotifyID = NOTIFY_ID;
        smallIcon = R.drawable.notify_auto;
        Title = NOTIFICATION_TITLE;
        Text = "";
        volumeLevel = -1;
        flags =  0;
        ActivityClass = MainActivity.class;
        number = 0;
        this.context = context;
    }

    public Notification show() {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.cancel(this.NotifyID);
        Notification.Builder builder = new Notification.Builder(context);
        builder.setContentTitle( this.Title );
        builder.setContentText( this.Text );
        builder.setAutoCancel(false);
        //builder.setWhen( App.GS.startDate);
        if ( this.smallIcon > 0 ) builder.setSmallIcon( this.smallIcon );
        Notification notification = builder.build();
        notification.flags |= this.flags;
        notification.number =  this.number;
        if ( this.ActivityClass != null) {
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, new Intent(context, this.ActivityClass), 0);
            notification.setLatestEventInfo(context,  this.Title, this.Text, pendingIntent);
        }
        notificationManager.notify(this.NotifyID, notification);
        return notification;

    }

}
