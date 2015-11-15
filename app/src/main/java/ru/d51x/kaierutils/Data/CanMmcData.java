package ru.d51x.kaierutils.Data;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class CanMmcData {
    private Context mContext;
    public int can_mmc_speed;                  // скорость по CAN
    public int can_mmc_rpm;                  // обороты по CAN

    public int can_mmc_fuel_remain;                  // остаток топлива в баке
    public int can_mmc_fuel_remain_update_time;       // время обновления данных об остатке топлива в баке
    public boolean can_mmc_fuel_remain_show;        // читать остаток топлива по can

    public int can_mmc_cvt_temp;                    // температура масла в CVT
    public int can_mmc_cvt_temp_update_time;        // время обновления температуры масла CVT
    public boolean can_mmc_cvt_temp_show;        // читать температуру CVT

    public int can_mmc_cvt_degradation_level;     // степень деградации масла в CVT
    public int can_mmc_cvt_degradation_update_time;     // время обновления степени деградации масла в CVT
    public boolean can_mmc_cvt_degr_show;        // читать деградацию CVT

    public int can_mmc_engine_temp;                  // температура ОЖ

    public boolean can_mmc_ac_data_show;            // отображать и читать данные климата
    public boolean can_mmc_parking_data_show;            // отображать и читать данные парковочных сенсоров

    public boolean engine_fan_show;

    public long FuelLevel_TimeStamp1, FuelLevel_TimeStamp2;
    public long CVT_oil_degr_TimeStamp1, CVT_oil_degr_TimeStamp2;
    public long CVT_oil_temp_TimeStamp1, CVT_oil_temp_TimeStamp2;


public enum State {off, on};

    public State fan_state;

  public CanMmcData(Context context) {
      mContext = context;

      fan_state = State.off;

      can_mmc_speed = -1;
      can_mmc_rpm = -1;
      can_mmc_fuel_remain = -1;
      can_mmc_fuel_remain_update_time = -1;
      can_mmc_fuel_remain_show = false;

      can_mmc_cvt_temp = -255;
      can_mmc_cvt_temp_update_time = -1;
      can_mmc_cvt_temp_show = false;

      can_mmc_cvt_degradation_level = -255;
      can_mmc_cvt_degradation_update_time = -1;
      can_mmc_cvt_degr_show = false;

      can_mmc_engine_temp = -1;

      engine_fan_show = true;
      can_mmc_ac_data_show = false;
      can_mmc_parking_data_show = false;

      SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mContext);
      can_mmc_fuel_remain_update_time = prefs.getInt("ODBII_CAN_MMC_FUEL_REMAIN_UPDATE_TIME", 5);
      can_mmc_fuel_remain_show = prefs.getBoolean("ODBII_CAN_MMC_FUEL_REMAIN_SHOW", false);

      can_mmc_cvt_temp_update_time = prefs.getInt("ODBII_CAN_MMC_CVT_TEMP_UPDATE_TIME", 5);
      can_mmc_cvt_temp_show = prefs.getBoolean("ODBII_CAN_MMC_CVT_TEMP_SHOW", false);

     can_mmc_cvt_degradation_update_time = prefs.getInt("ODBII_CAN_MMC_CVT_DEGR_UPDATE_TIME", 5);
     can_mmc_cvt_degr_show = prefs.getBoolean("ODBII_CAN_MMC_CVT_DEGR_SHOW", false);


     can_mmc_ac_data_show = prefs.getBoolean("ODBII_CAN_MMC_AC_DATA_SHOW", false);
     can_mmc_parking_data_show = prefs.getBoolean("ODBII_CAN_MMC_PARKING_DATA_SHOW", false);

      FuelLevel_TimeStamp1  =  FuelLevel_TimeStamp2  = System.currentTimeMillis();
      CVT_oil_degr_TimeStamp1 = CVT_oil_degr_TimeStamp2 = System.currentTimeMillis();
      CVT_oil_temp_TimeStamp1 = CVT_oil_temp_TimeStamp2 = System.currentTimeMillis();
  }


}
