package ru.d51x.kaierutils.Data;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import ru.d51x.kaierutils.App;

public class PopupWindowOption {

    public int radioToastLine1TextSize;
    public int radioToastLine2TextSize;
    public int musicToastLine1TextSize;
    public int musicToastLine2TextSize;
    public int musicToastPictureWidth;
    public int musicToastPictureHeight;

    public PopupWindowOption() {

    }

    public void load() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences (App.getInstance ());
        radioToastLine1TextSize  = Integer.parseInt (prefs.getString("CAR_SETTINGS__RADIO_TOAST_TEXT1_SIZE", "48"));
        radioToastLine2TextSize  = Integer.parseInt (prefs.getString("CAR_SETTINGS__RADIO_TOAST_TEXT2_SIZE", "25"));

        musicToastLine1TextSize  = Integer.parseInt (prefs.getString("CAR_SETTINGS__MUSIC_TOAST_TEXT1_SIZE", "32"));
        musicToastLine2TextSize  = Integer.parseInt (prefs.getString("CAR_SETTINGS__MUSIC_TOAST_TEXT2_SIZE", "22"));
        musicToastPictureWidth  = Integer.parseInt (prefs.getString("CAR_SETTINGS__MUSIC_TOAST_PICTURE_WIDTH", "128"));
        musicToastPictureHeight  = Integer.parseInt (prefs.getString("CAR_SETTINGS__MUSIC_TOAST_PICTURE_HEIGHT", "128"));


    }

    public void save() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences (App.getInstance ());
    }
}
