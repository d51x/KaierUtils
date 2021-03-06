package ru.d51x.kaierutils.OBD2;

import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Set;

import ru.d51x.kaierutils.App;
import ru.d51x.kaierutils.BackgroundService;
import ru.d51x.kaierutils.R;


public class OBDIIActivity extends Activity implements View.OnClickListener {


    private Button btnSelectDevice;
    private Button btnConnect;
    private Button btnDisconnect;
    private TextView tvDeviceName;
    private TextView tvDeviceStatus;
    private TextView tvOBDEngineRPM;
    private TextView tvOBDSpeed;
    private TextView tvOBD_CoolantTemp;
    private TextView tvOBD_FuelUsage;
    private TextView tvOBD_FuelUsage2;
    private TextView tvOBD_CMUVoltage;
    private TextView tvGPS_Distanse;
    private TextView tvOBD_FuelConsumption_lph;
    private TextView tvOBD_FuelConsumption_mpg;
    private TextView tvOBD_FuelConsumption_avg;
    private TextView tvOBD_FuelConsumption_avg2;
    private TextView tvOBD_FuelConsumption_total;
    private TextView tvOBD_FuelUsageTotal;

    private TextView tvOBD_FuelConsumption_today;
    private TextView tvOBD_FuelUsageToday;
    private TextView tvGPS_Distanse_Today;
    private TextView tvGPS_Distanse_Total;




    private TextView tv_can_2110_cvt_oil_degr;
    private TextView tv_can_2103_cvt_temp_count;




    private TextView tvFuelLevel;

    private TextView tvOBD_MAF;
    private Switch swUseOBD;
    private CheckBox cbCanMMC;

    private TableRow trMMCHeader;
    private TableRow trMMCValues;

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(OBDII.OBD_BROADCAST_ACTION_STATUS_CHANGED)) {
                boolean res = intent.getBooleanExtra("Status", false);
                tvDeviceStatus.setText(String.format(getString(R.string.odbii_device_status), res ? "Подключен" : "Не подключен"));
            } else if (action.equals(OBDII.OBD_BROADCAST_ACTION_ENGINE_RPM_CHANGED)) {
                tvOBDEngineRPM.setText(String.format(getString(R.string.text_obd_engine_rpm_f), intent.getStringExtra("engineRPM")));
            } else if (action.equals(OBDII.OBD_BROADCAST_ACTION_SPEED_CHANGED)) {
                tvOBDSpeed.setText(String.format(getString(R.string.text_obd_speed_f), intent.getStringExtra("speed")));
            } else if (action.equals(OBDII.OBD_BROADCAST_ACTION_COOLANT_TEMP_CHANGED)) {
                tvOBD_CoolantTemp.setText(String.format(getString(R.string.text_obd_coolant_temp_f), intent.getStringExtra("coolantTemp")));
            } else if (action.equals(OBDII.OBD_BROADCAST_ACTION_FUEL_LEVEL_CHANGED)) {

            } else if (action.equals(OBDII.OBD_BROADCAST_ACTION_CMU_VOLTAGE_CHANGED)) {
                tvOBD_CMUVoltage.setText(String.format(getString(R.string.text_obd_cmu_voltage_f), intent.getStringExtra("cmuVoltage")));
            } else if (action.equals(OBDII.OBD_BROADCAST_ACTION_FUEL_CONSUMPTION_CHANGED)) {

            } else if (action.equals(OBDII.OBD_BROADCAST_ACTION_MAF_CHANGED)) {
                tvOBD_MAF.setText(String.format(getString(R.string.text_obd_fuel_consumption_maf_f), intent.getStringExtra("sMAF")));
                tvOBD_FuelConsumption_lph.setText(String.format(getString(R.string.text_obd_fuel_consumption_lph_f), App.obd.oneTrip.fuel_cons_lph));
                tvOBD_FuelConsumption_mpg.setText(String.format(getString(R.string.text_obd_fuel_consumption_mpg_f), App.obd.oneTrip.fuel_cons_lp100km_inst));
                tvOBD_FuelConsumption_avg.setText(String.format(getString(R.string.text_obd_fuel_consumption_avg_f), App.obd.oneTrip.fuel_cons_lp100km_avg));
                tvOBD_FuelUsage.setText(String.format(getString(R.string.text_obd_fuel_usage_f), App.obd.oneTrip.fuel_usage));
                tvGPS_Distanse.setText(String.format(getString(R.string.text_obd_distanse_f), App.obd.oneTrip.distance / 1000f));

                tvOBD_FuelConsumption_total.setText(String.format(getString(R.string.text_obd_fuel_consumption_avg_f), App.obd.totalTrip.fuel_cons_lp100km_avg));
                tvOBD_FuelUsageTotal.setText(String.format(getString(R.string.text_obd_fuel_usage_f), App.obd.totalTrip.fuel_usage));

                tvOBD_FuelConsumption_today.setText(String.format(getString(R.string.text_obd_fuel_consumption_avg_f), App.obd.todayTrip.fuel_cons_lp100km_avg));
                tvOBD_FuelUsageToday.setText(String.format(getString(R.string.text_obd_fuel_usage_f), App.obd.todayTrip.fuel_usage));
                tvGPS_Distanse_Today.setText(String.format(getString(R.string.text_obd_distanse_f), App.obd.todayTrip.distance / 1000f));
                tvGPS_Distanse_Total.setText(String.format(getString(R.string.text_obd_distanse_f), App.obd.totalTrip.distance / 1000f));
            }

            // ----------------------------------------------------------------------------
            // CVT: Oil Degradation Level
            else if (action.equals(OBDII.OBD_BROADCAST_ACTION_ECU_CVT_OIL_DEGR_CHANGED)) {
                int i = App.obd.canMmcData.can_mmc_cvt_degradation_level;
                String s = (i > -255) ? Integer.toString(i) : "---";
                tv_can_2110_cvt_oil_degr.setText(String.format(getString(R.string.text_can_2110_cvt_oil_degr), s));
            }
            // CVT: Oil Temperature
            else if (action.equals(OBDII.OBD_BROADCAST_ACTION_ECU_CVT_OIL_TEMP_CHANGED)) {
                int i = App.obd.canMmcData.can_mmc_cvt_temp;
                String s = (i > -255) ? Integer.toString(i) : "---";
                tv_can_2103_cvt_temp_count.setText(String.format(getString(R.string.text_can_2103_cvt_temp_count), s));
            }
            // ----------------------------------------------------------------------------
            //Air Conditioner: external temperature
//            else if ( action.equals( OBDII.OBD_BROADCAST_ACTION_AC_EXT_TEMP_CHANGED )) {
//                int i = App.obd.climateData.ext_temperature;
//                String s = ( i > -255 ) ? Integer.toString(i) : "---";
//                tv_air_cond_external_temp.setText(String.format( getString( R.string.text_air_cond_external_temp), s));
//            }
            // ----------------------------------------------------------------------------
            //Combination meter: fuel level
            else if (action.equals(OBDII.OBD_BROADCAST_ACTION_ECU_COMBINEMETER_FUEL_TANK_CHANGED)) {
                int i = App.obd.canMmcData.can_mmc_fuel_remain;
                String s = (i > -1) ? Integer.toString(i) : "---";
                tvFuelLevel.setText(String.format(getString(R.string.text_obd_can_fuel_level), s));
            }
        }
    };

    public static void show_odb_device_select_dialog(Context context) {
        ArrayList<String> deviceStrs = new ArrayList<String>();
        final ArrayList<String> devices = new ArrayList<String>();
        final ArrayList<String> devicesName = new ArrayList<String>();
        BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();
        Set<BluetoothDevice> pairedDevices = btAdapter.getBondedDevices();
        if (pairedDevices.size() > 0) {
            for (BluetoothDevice device : pairedDevices) {
                deviceStrs.add(device.getName() + "\n" + device.getAddress());
                devices.add(device.getAddress());
                devicesName.add(device.getName());
            }
        }
        // show list
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.select_dialog_singlechoice,
                deviceStrs.toArray(new String[deviceStrs.size()]));

        alertDialog.setSingleChoiceItems(adapter, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                int position = ((AlertDialog) dialog).getListView().getCheckedItemPosition();
                App.obd.setDeviceAddress(devices.get(position));
                App.obd.setDeviceName(devicesName.get(position));
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(App.getInstance());
                prefs.edit().putString("ODBII_DEVICE_ADDRESS", App.obd.getDeviceAddress()).commit();
                prefs.edit().putString("ODBII_DEVICE_NAME", App.obd.getDeviceName()).commit();
            }
        });

        alertDialog.setTitle("Choose Bluetooth device");
        alertDialog.show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obdii);

        btnSelectDevice = (Button) findViewById(R.id.btnSelectDevice);
        btnSelectDevice.setOnClickListener(this);
        btnConnect = (Button) findViewById(R.id.btnOBDConnect);
        btnConnect.setOnClickListener(this);
        btnDisconnect = (Button) findViewById(R.id.btnOBDDisconnect);
        btnDisconnect.setOnClickListener(this);
        tvDeviceName = (TextView) findViewById(R.id.tvDeviceName);
        tvDeviceStatus = (TextView) findViewById(R.id.tvDeviceStatus);
        tvOBDEngineRPM = (TextView) findViewById(R.id.tvOBDEngineRPM);
        tvOBDSpeed = (TextView) findViewById(R.id.tvOBD_Speed);
        tvOBD_CoolantTemp = (TextView) findViewById(R.id.tvOBD_CoolantTemp);
        tvOBD_FuelUsage = (TextView) findViewById(R.id.tvOBD_FuelUsage);
        tvOBD_CMUVoltage = (TextView) findViewById(R.id.tvOBD_CMUVoltage);
        tvGPS_Distanse = (TextView) findViewById(R.id.tvGPS_Distanse);
        tvOBD_FuelConsumption_lph = (TextView) findViewById(R.id.tvOBD_FuelConsumption_lph);
        tvOBD_FuelConsumption_mpg = (TextView) findViewById(R.id.tvOBD_FuelConsumption_mpg);
        tvOBD_FuelConsumption_avg = (TextView) findViewById(R.id.tvOBD_FuelConsumption_avg);
        tvOBD_FuelConsumption_total = (TextView) findViewById(R.id.tvOBD_FuelConsumption_total);
        tvOBD_FuelUsageTotal = (TextView) findViewById(R.id.tvOBD_FuelUsageTotal);


        tvOBD_FuelConsumption_today = (TextView) findViewById(R.id.tvOBD_FuelConsumption_today);
        tvOBD_FuelUsageToday = (TextView) findViewById(R.id.tvOBD_FuelUsageToday);
        tvGPS_Distanse_Today = (TextView) findViewById(R.id.tvGPS_Distanse_Today);
        tvGPS_Distanse_Total = (TextView) findViewById(R.id.tvGPS_Distanse_Total);


        tv_can_2110_cvt_oil_degr = (TextView) findViewById(R.id.tv_can_2110_cvt_oil_degr);
        tv_can_2103_cvt_temp_count = (TextView) findViewById(R.id.tv_can_2103_cvt_temp_count);

        tvOBD_MAF = (TextView) findViewById(R.id.tvOBD_MAF);


        tvFuelLevel = (TextView) findViewById(R.id.tvFuelLevel);


        swUseOBD = (Switch) findViewById(R.id.swUseOBD);
	    swUseOBD.setOnClickListener(this);
	    swUseOBD.setChecked(App.obd.useOBD);

        cbCanMMC = (CheckBox) findViewById(R.id.cbCanMMC);
        cbCanMMC.setOnClickListener(this);
        cbCanMMC.setChecked(App.obd.MMC_CAN);


        trMMCHeader = (TableRow) findViewById(R.id.trMMCHeader);
        trMMCHeader.setVisibility(App.obd.MMC_CAN ? View.VISIBLE : View.GONE);

        trMMCValues = (TableRow) findViewById(R.id.trMMCValues);
        trMMCValues.setVisibility(App.obd.MMC_CAN ? View.VISIBLE : View.GONE);


        tvOBDEngineRPM.setText(String.format(getString(R.string.text_obd_engine_rpm_f), "---"));
        tvOBDSpeed.setText(String.format(getString(R.string.text_obd_speed_f), "---"));
        tvOBD_CoolantTemp.setText(String.format(getString(R.string.text_obd_coolant_temp_f), "---"));
        tvOBD_FuelUsage.setText(String.format(getString(R.string.text_obd_fuel_usage_f), 0.0f));
        tvOBD_CMUVoltage.setText(String.format(getString(R.string.text_obd_cmu_voltage_f), "---"));
        tvGPS_Distanse.setText(String.format(getString(R.string.text_obd_distanse_f), 0.0f));

        tvOBD_MAF.setText(String.format(getString(R.string.text_obd_fuel_consumption_maf_f), "0.0"));
        tvOBD_FuelConsumption_lph.setText(String.format(getString(R.string.text_obd_fuel_consumption_lph_f), 0.0f));
        tvOBD_FuelConsumption_mpg.setText(String.format(getString(R.string.text_obd_fuel_consumption_mpg_f), 0.0f));
        tvOBD_FuelConsumption_avg.setText(String.format(getString(R.string.text_obd_fuel_consumption_avg_f), 0.0f));
        tvOBD_FuelConsumption_total.setText(String.format(getString(R.string.text_obd_fuel_consumption_avg_f), 0.0f));
        tvOBD_FuelUsageTotal.setText(String.format(getString(R.string.text_obd_fuel_usage_f), 0.0f));

        tvOBD_FuelConsumption_today.setText(String.format(getString(R.string.text_obd_fuel_consumption_avg_f), 0.0f));
        tvOBD_FuelUsageToday.setText(String.format(getString(R.string.text_obd_fuel_usage_f), 0.0f));
        tvGPS_Distanse_Today.setText(String.format(getString(R.string.text_obd_distanse_f), 0.0f));
        tvGPS_Distanse_Total.setText(String.format(getString(R.string.text_obd_distanse_f), 0.0f));


        tv_can_2110_cvt_oil_degr.setText(String.format( getString( R.string.text_can_2110_cvt_oil_degr), "---"));
        tv_can_2103_cvt_temp_count.setText(String.format( getString( R.string.text_can_2103_cvt_temp_count), "---", "---"));



        tvFuelLevel.setText(String.format( getString( R.string.text_obd_can_fuel_level), "---"));



        registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_STATUS_CHANGED));
        registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_SPEED_CHANGED));
        registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_ENGINE_RPM_CHANGED));
        registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_COOLANT_TEMP_CHANGED));
        registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_FUEL_LEVEL_CHANGED));
        registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_CMU_VOLTAGE_CHANGED));
        registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_FUEL_CONSUMPTION_CHANGED));
        registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_MAF_CHANGED));
        registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_AC_STATE_CHANGED));
        registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_ENGINE_FAN_STATE_CHANGED));

        registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_ECU_ENGINE_CHANGED));
        registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_ECU_CVT_CHANGED));
        registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_ECU_CVT_OIL_DEGR_CHANGED));
        registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_ECU_CVT_OIL_TEMP_CHANGED));
        registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_ECU_COMBINEMETER_CHANGED));
        registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_ECU_AWC_CHANGED));


        registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_AC_EXT_TEMP_CHANGED));

    }

    public void onResume() {
        super.onResume();
        try {
            tvDeviceStatus.setText(String.format(getString(R.string.odbii_device_status), App.obd.isConnected ? "Подключен" : "Не подключен"));
            String str = "";
            if (App.obd.getDeviceName() != null) {
                str += App.obd.getDeviceName() + " / " + App.obd.getDeviceAddress();
            }
            tvDeviceName.setText(String.format(getString(R.string.odbii_device_name), str));
        } catch (Exception e) {
            Log.d("ODBActivity->OnResume", e.toString());
        }

        int i = App.obd.canMmcData.can_mmc_fuel_remain;
        String s = ( i > -1 ) ? Integer.toString(i) : "---";
        tvFuelLevel.setText(String.format( getString( R.string.text_obd_can_fuel_level), s));


        i = App.obd.canMmcData.can_mmc_cvt_temp;
        s = ( i > -255 ) ? Integer.toString(i) : "---";
        tv_can_2103_cvt_temp_count.setText(String.format( getString( R.string.text_can_2103_cvt_temp_count), s));

        i = App.obd.canMmcData.can_mmc_cvt_degradation_level;
        s = ( i > -255 ) ? Integer.toString(i) : "---";
        tv_can_2110_cvt_oil_degr.setText(String.format(getString(R.string.text_can_2110_cvt_oil_degr), s));

    }

    @Override
    public void onClick(View v) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(App.getInstance());
        switch (v.getId()) {
            case R.id.btnSelectDevice:
                show_odb_device_select_dialog( this );
                break;
            case R.id.btnOBDConnect:
                if ( !App.obd.isConnected) BackgroundService.startOBDThread();
                break;
            case R.id.btnOBDDisconnect:
                BackgroundService.stopOBDThread();
                break;
            case R.id.swUseOBD:
                App.obd.useOBD = swUseOBD.isChecked();
                prefs.edit().putBoolean("ODBII_USE_BLUETOOTH", App.obd.useOBD).apply();
                // connect or disconnect
                setOnOffOBD( App.obd.useOBD );
                break;
            case R.id.cbCanMMC:
                App.obd.MMC_CAN = cbCanMMC.isChecked();

                trMMCHeader.setVisibility(App.obd.MMC_CAN ? View.VISIBLE : View.GONE);
                trMMCValues.setVisibility(App.obd.MMC_CAN ? View.VISIBLE : View.GONE);


                prefs.edit().putBoolean("ODBII_USE_MMC_CAN", App.obd.MMC_CAN).apply();
                break;
            default:
                break;
        }
    }

    public void setOnOffOBD(boolean state) {
        if ( state )
        {
           //on
           if ( !App.obd.isConnected) BackgroundService.startOBDThread();
        } else {
            //off
            if ( App.obd.isConnected) BackgroundService.stopOBDThread();
        }
    }

    protected void onDestroy() {
        unregisterReceiver(receiver);
        super.onDestroy();
    }


}
