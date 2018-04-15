package ru.d51x.kaierutils.Data;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.Context;

import ru.d51x.kaierutils.MainActivity;
import ru.d51x.kaierutils.R;

/**
 */
public class NotifyData {

    public static final int NOTIFY_ID = 1;
    public static final int NOTIFY_SPEED_ID = 2;
    public static final int NOTIFY_AUDIO_FOCUS_ID = 3;
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

    public String line_inway_time;
    public String line_inway_distance;


    public NotifyData(Context context ) {
        NotifyID = NOTIFY_ID;
        smallIcon = R.drawable.notify_auto;
        Title = NOTIFICATION_TITLE;
        Text = "";
        volumeLevel = -1;
        //flags =  0;
        flags =  Notification.FLAG_ONGOING_EVENT;;
        ActivityClass = MainActivity.class;
        number = 0;

        line_inway_time = "";
        line_inway_distance = "";

        this.context = context;
    }

    public Notification show() {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.cancel(this.NotifyID);
        Notification.Builder builder = new Notification.Builder(context);
        builder.setContentTitle(this.Title);
        builder.setContentText(this.Text);
        builder.setAutoCancel(false);

       // builder.addAction();

        //builder.setWhen( App.GS.startDate);
        if ( this.smallIcon > 0 ) builder.setSmallIcon( this.smallIcon );
        //Notification notification = builder.build();
        Notification notification = new Notification.InboxStyle(builder)
                .addLine(line_inway_time)
                .addLine(line_inway_distance)
                .build();

        notification.flags |= this.flags;
        notification.number =  this.number;
        if ( this.ActivityClass != null) {
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, new Intent(context, this.ActivityClass), 0);
            //notification.setLatestEventInfo(context,  this.Title, this.Text, pendingIntent);
        }
        notificationManager.notify(this.NotifyID, notification);
        return notification;

    }

}
