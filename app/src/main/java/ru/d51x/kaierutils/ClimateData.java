package ru.d51x.kaierutils;


import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class ClimateData {

    public int fan_speed;                  // остаток топлива в баке
    public int blow_direction;                  // остаток топлива в баке
    public double temperature ;                  // остаток топлива в баке
    public boolean ac_state ;                  // остаток топлива в баке
    public boolean recirculation_state ;                  // остаток топлива в баке
    public boolean defogger_state ;                  // остаток топлива в баке


  public ClimateData() {
      fan_speed = -1;                  // остаток топлива в баке
      blow_direction = -1;                  // остаток топлива в баке
      temperature = -1;                  // остаток топлива в баке
      ac_state = false;                  // остаток топлива в баке
      recirculation_state = false;                  // остаток топлива в баке
      defogger_state = false;
  }


}
