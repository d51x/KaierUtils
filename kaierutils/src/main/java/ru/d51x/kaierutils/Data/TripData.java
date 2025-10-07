package ru.d51x.kaierutils.Data;


import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Calendar;

import ru.d51x.kaierutils.App;

public class TripData {

    public float fuel_remains;                  // остаток топлива в баке
    public float fuel_usage;                    // использование топлива за поездку
    public float fuel_usage_for_display;        // использование топлива за поездку, обнуляетяся при заправке, используется при расчете остатка
    public float fuel_usage_wo_stops;           // использование топлива за поездку без простоев
    public float fuel_cons_lph;                 // мгновенный расход (л/час)
    public float fuel_cons_lp100km_inst;        // мгновенный расход (л/100км)
    public float fuel_cons_lp100km_avg;         // средний расход (л/100км) за поездку
    public float fuel_cons_lp100km_avg_wo_stops;    // средний расход (л/100км) за поездку без остановок
    public float distance;                      // дистанция за поездку
    public float trip_time;                     // время за поездку
    public float trip_time_wo_stops;            // время за поездку без остановок
    private String mPrefix;
    private boolean isStoreData;
    private long timeStamp;
    private float prevOffset = 0; // для хранения предыдущего tripA

    public TripData(String prefix, boolean isStoreData) {
        mPrefix = prefix;
        this.isStoreData = isStoreData;
        resetData();
        loadData();
    }

    public void Destroy() {
        saveData();
    }

    public void loadData() {
        if ( !isStoreData ) return;
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences (App.getInstance());
        fuel_remains = prefs.getFloat("fuel_remains_" + mPrefix, 0f);
        fuel_usage = prefs.getFloat("fuel_usage_" + mPrefix, 0f);
        fuel_usage_for_display = fuel_usage;
        fuel_usage_wo_stops = prefs.getFloat("fuel_usage_wo_stops_" + mPrefix, 0f);
        fuel_cons_lph = prefs.getFloat("fuel_cons_lph_" + mPrefix, 0f);
        fuel_cons_lp100km_inst = prefs.getFloat("fuel_cons_lp100km_inst_" + mPrefix, 0f);
        fuel_cons_lp100km_avg = prefs.getFloat("fuel_cons_lp100km_avg_" + mPrefix, 0f);
        fuel_cons_lp100km_avg_wo_stops = prefs.getFloat("fuel_cons_lp100km_avg_wo_stops_" + mPrefix, 0f);
        distance = prefs.getFloat("distance_" + mPrefix, 0f);
        trip_time = prefs.getFloat("trip_time_" + mPrefix, 0f);
        trip_time_wo_stops = prefs.getFloat("trip_time_wo_stops_" + mPrefix, 0f);
        timeStamp = prefs.getLong("timestamp_" + mPrefix, 0);

        if ( mPrefix.equals( "today" )) {
            Calendar calendar_old = Calendar.getInstance();
            Calendar calendar_new = Calendar.getInstance();
            calendar_old.setTimeInMillis(timeStamp);

            int d_old = calendar_old.get(Calendar.DAY_OF_YEAR );
            int d_new = calendar_new.get(Calendar.DAY_OF_YEAR );

            if ( (timeStamp == 0) ||
                 ( d_new - d_old > 0 ) )
                    resetData();
        }

    }

    public void saveData() {
        if ( !isStoreData ) return;
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(App.getInstance());
        prefs.edit().putFloat("fuel_remains_" + mPrefix, fuel_remains).apply();
        prefs.edit().putFloat("fuel_usage_" + mPrefix, fuel_usage).apply();
        prefs.edit().putFloat("fuel_usage_wo_stops_" + mPrefix, fuel_usage_wo_stops).apply();
        prefs.edit().putFloat("fuel_cons_lph_" + mPrefix, fuel_cons_lph).apply();
        prefs.edit().putFloat("fuel_cons_lp100km_inst_" + mPrefix, fuel_cons_lp100km_inst).apply();
        prefs.edit().putFloat("fuel_cons_lp100km_avg_" + mPrefix, fuel_cons_lp100km_avg).apply();
        prefs.edit().putFloat("fuel_cons_lp100km_avg_wo_stops_" + mPrefix, fuel_cons_lp100km_avg_wo_stops).apply();
        prefs.edit().putFloat("distance_" + mPrefix, distance).apply();
        prefs.edit().putFloat("trip_time_" + mPrefix, trip_time).apply();
        prefs.edit().putFloat("trip_time_wo_stops_" + mPrefix, trip_time_wo_stops).apply();
        timeStamp = System.currentTimeMillis();
        prefs.edit().putLong("timestamp_" + mPrefix, timeStamp).apply();
    }

    public void resetData() {
        fuel_cons_lph = 0;
        fuel_remains = 0;
        fuel_usage = 0;
        fuel_usage_for_display = 0;
        fuel_usage_wo_stops = 0;
        fuel_cons_lph = 0;
        fuel_cons_lp100km_inst = 0;
        fuel_cons_lp100km_avg = 0;
        fuel_cons_lp100km_avg_wo_stops = 0;
        distance = 0;
        trip_time = 0;
        trip_time_wo_stops = 0;
    }

    public void updateData(boolean isFullTank, float remain, float tank) {
        fuel_remains = remain;
        if ( isFullTank ) {
            // залит полный бак (авторежим по кнопке)
            fuel_usage_for_display = 0;
        } else {
            // коррекция остатка топлива и объема бака
            fuel_usage_for_display = tank - remain;
        }
        saveData();
    }

    public void calculateFuelConsumptionbyMAF() {
        /*
            Формула расчёта расхода топлива по данным с датчика массового расхода воздуха (MAF):
            Расход топлива (л/100 км) = (MAF × 0,746 × 100) / (AFR × ρ × V), где:
            MAF — массовый расход воздуха (г/с);
            AFR — коэффициент воздух-топливо (стехиометрия: 14,7 для бензина);
            ρ — плотность бензина (примерно 0,745 кг/л);
            V — средняя скорость (км/ч).
        */

    }

    public void calculateData(float speed, float maf, long deltaTime) {
        // литры в секунду
        float afr = 14.7f;
        float fuel_consump_lpsec = ( maf / afr) / 720;
        // литры в час
        fuel_cons_lph = fuel_consump_lpsec * 3600;
        //float usedmaf = fuel_consump_lpsec;

        // учетом того, что получаем данные с MAF не раз в секунду, а реже
        float usedmaf = fuel_consump_lpsec * deltaTime / 1000f; //TODO: check this calculation

        // использовано топлива
        fuel_usage += usedmaf; // с учетом порстоя
        fuel_usage_for_display +=usedmaf;
        fuel_cons_lp100km_avg = fuel_usage * 100 / (distance/1000f);

        if ( speed > 2 ) {
            fuel_cons_lp100km_inst = (100 * fuel_cons_lph) / speed;
            fuel_usage_wo_stops += usedmaf;
            fuel_cons_lp100km_avg_wo_stops = fuel_usage_wo_stops * 100 / (distance/1000f);
        }

        // остаток топлива в баке
        fuel_remains = fuel_remains - usedmaf;
        if ( fuel_remains < 0 )  fuel_remains = 0;
    }

    public void updateDistance(float offset) {
        if (prevOffset == 0 || prevOffset > offset) {
            // только начали отсчет или был сброс счетчика tripA
            // с учетом, что данные получаем часто, а за 5 сек много не проедем,
            // то пропустим одно вычисление
            prevOffset = offset;
        }
        if (prevOffset < offset) {
            distance += offset - prevOffset;
            prevOffset = offset;
        }
    }
}
