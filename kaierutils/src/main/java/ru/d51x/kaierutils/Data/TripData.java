package ru.d51x.kaierutils.Data;


import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Calendar;

import ru.d51x.kaierutils.App;

public class TripData {

    public float fuelRemains;                  // остаток топлива в баке
    public float fuelUsage;                    // использование топлива за поездку
    public float fuelUsageForDisplay;        // использование топлива за поездку, обнуляетяся при заправке, используется при расчете остатка
    public float fuelUsageWoStops;           // использование топлива за поездку без простоев
    public float fuelConsumptionLph;                 // мгновенный расход (л/час)
    public float fuelConsLp100KmInst;        // мгновенный расход (л/100км)
    public float fuelConsLp100KmAvg;         // средний расход (л/100км) за поездку
    public float fuelConsumptionLp100kmAvgWoStops;    // средний расход (л/100км) за поездку без остановок
    public float distance;                      // дистанция за поездку
    public float tripTime;                     // время за поездку
    public float tripTimeWoStops;            // время за поездку без остановок
    private String mPrefix;
    private boolean isStoreData;
    private long timeStamp;
    private float prevOffset = 0; // для хранения предыдущего tripA

    private int averageSpeed = 0;
    private int maxSpeed = 0;

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
        fuelRemains = prefs.getFloat("fuel_remains_" + mPrefix, 0f);
        fuelUsage = prefs.getFloat("fuel_usage_" + mPrefix, 0f);
        fuelUsageForDisplay = fuelUsage;
        fuelUsageWoStops = prefs.getFloat("fuel_usage_wo_stops_" + mPrefix, 0f);
        fuelConsumptionLph = prefs.getFloat("fuel_cons_lph_" + mPrefix, 0f);
        fuelConsLp100KmInst = prefs.getFloat("fuel_cons_lp100km_inst_" + mPrefix, 0f);
        fuelConsLp100KmAvg = prefs.getFloat("fuel_cons_lp100km_avg_" + mPrefix, 0f);
        fuelConsumptionLp100kmAvgWoStops = prefs.getFloat("fuel_cons_lp100km_avg_wo_stops_" + mPrefix, 0f);
        distance = prefs.getFloat("distance_" + mPrefix, 0f);
        tripTime = prefs.getFloat("trip_time_" + mPrefix, 0f);
        tripTimeWoStops = prefs.getFloat("trip_time_wo_stops_" + mPrefix, 0f);
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
        prefs.edit().putFloat("fuel_remains_" + mPrefix, fuelRemains).apply();
        prefs.edit().putFloat("fuel_usage_" + mPrefix, fuelUsage).apply();
        prefs.edit().putFloat("fuel_usage_wo_stops_" + mPrefix, fuelUsageWoStops).apply();
        prefs.edit().putFloat("fuel_cons_lph_" + mPrefix, fuelConsumptionLph).apply();
        prefs.edit().putFloat("fuel_cons_lp100km_inst_" + mPrefix, fuelConsLp100KmInst).apply();
        prefs.edit().putFloat("fuel_cons_lp100km_avg_" + mPrefix, fuelConsLp100KmAvg).apply();
        prefs.edit().putFloat("fuel_cons_lp100km_avg_wo_stops_" + mPrefix, fuelConsumptionLp100kmAvgWoStops).apply();
        prefs.edit().putFloat("distance_" + mPrefix, distance).apply();
        prefs.edit().putFloat("trip_time_" + mPrefix, tripTime).apply();
        prefs.edit().putFloat("trip_time_wo_stops_" + mPrefix, tripTimeWoStops).apply();
        timeStamp = System.currentTimeMillis();
        prefs.edit().putLong("timestamp_" + mPrefix, timeStamp).apply();
    }

    public void resetData() {
        fuelConsumptionLph = 0;
        fuelRemains = 0;
        fuelUsage = 0;
        fuelUsageForDisplay = 0;
        fuelUsageWoStops = 0;
        fuelConsumptionLph = 0;
        fuelConsLp100KmInst = 0;
        fuelConsLp100KmAvg = 0;
        fuelConsumptionLp100kmAvgWoStops = 0;
        distance = 0;
        tripTime = 0;
        tripTimeWoStops = 0;
    }

    public void updateData(boolean isFullTank, float remain, float tank) {
        fuelRemains = remain;
        if ( isFullTank ) {
            // залит полный бак (авторежим по кнопке)
            fuelUsageForDisplay = 0;
        } else {
            // коррекция остатка топлива и объема бака
            fuelUsageForDisplay = tank - remain;
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
        fuelConsumptionLph = fuel_consump_lpsec * 3600;
        //float usedmaf = fuel_consump_lpsec;

        // учетом того, что получаем данные с MAF не раз в секунду, а реже
        float usedmaf = fuel_consump_lpsec * deltaTime / 1000f; //TODO: check this calculation

        // использовано топлива
        fuelUsage += usedmaf; // с учетом порстоя
        fuelUsageForDisplay +=usedmaf;
        fuelConsLp100KmAvg = fuelUsage * 100 / (distance/1000f) / 1000f;

        if ( speed > 2 ) {
            fuelConsLp100KmInst = (100 * fuelConsumptionLph) / speed;
            fuelUsageWoStops += usedmaf;
            fuelConsumptionLp100kmAvgWoStops = fuelUsageWoStops * 100 / (distance/1000f) / 1000f;
        }

        // остаток топлива в баке
        fuelRemains = fuelRemains - usedmaf;
        if ( fuelRemains < 0 )  fuelRemains = 0;
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

    public int getAverageSpeed() {
        return averageSpeed;
    }

    public void setAverageSpeed(int speed) {
        if (speed > 3) {
            this.averageSpeed = (this.averageSpeed + speed) / 2;
        }
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int speed) {
        if (speed > maxSpeed) {
            this.maxSpeed = speed;
        }
    }
}
