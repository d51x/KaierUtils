package ru.d51x.kaierutils.utils;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import java.io.Serializable;

import ru.d51x.kaierutils.App;

public class MessageUtils {

    public static void sendMessage(Handler handler, int id, Serializable obj) {
        Message message = new Message();
        message.what = id;
        message.obj = obj;
        handler.sendMessage(message);
    }

    public static void SendBroadcastAction(String action, String key, String value) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        if ( key != null ) {
            intent.putExtra(key, value);
        }
        intent.setAction(action);
        App.getInstance ().sendBroadcast(intent);
    }

    public static void SendBroadcastAction(String action, String key, int value) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        if ( key != null ) {
            intent.putExtra(key, value);
        }
        intent.setAction(action);
        App.getInstance ().sendBroadcast(intent);
    }

    public static void SendBroadcastAction(String action, String key, boolean value) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        if ( key != null ) {
            intent.putExtra(key, value);
        }
        intent.setAction(action);
        App.getInstance ().sendBroadcast(intent);
    }

    public static void SendBroadcastAction(String action, String key, Serializable value) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        if ( key != null ) {
            intent.putExtra(key, value);
        }
        intent.setAction(action);
        App.getInstance ().sendBroadcast(intent);
    }
}
