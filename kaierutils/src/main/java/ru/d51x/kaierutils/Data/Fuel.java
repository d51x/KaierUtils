package ru.d51x.kaierutils.Data;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import ru.d51x.kaierutils.App;

public class Fuel {

    public float getTankCapacity() {
        return tankCapacity;
    }

    public void setTankCapacity(int tankCapacity) {
        this.tankCapacity = tankCapacity;
    }

    private float tankCapacity;

    public Fuel() {

    }

    public void save() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(App.getInstance());
        prefs.edit().putFloat("kaierutils_fuel_tank", tankCapacity).apply();
        //prefs.edit().putFloat("kaierutils_fuel_usage", obdData.fuel_usage2).apply();

    }

    public void load() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences (App.getInstance ());
        tankCapacity = prefs.getFloat("kaierutils_fuel_tank", 60f);
    }
}
