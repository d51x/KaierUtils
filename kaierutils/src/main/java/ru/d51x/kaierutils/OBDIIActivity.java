package ru.d51x.kaierutils;

import static ru.d51x.kaierutils.OBD2.ObdConstants.ACTION_OBD_AWC_2130_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.ACTION_OBD_CLIMATE_2110_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.ACTION_OBD_CLIMATE_2111_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.ACTION_OBD_CLIMATE_2113_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.ACTION_OBD_CLIMATE_2132_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.ACTION_OBD_CLIMATE_2160_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.ACTION_OBD_CLIMATE_2161_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.ACTION_OBD_CLIMATE_2180_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.ACTION_OBD_CVT_2103_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.ACTION_OBD_CVT_2110_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.ACTION_OBD_ENGINE_2101_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.ACTION_OBD_ENGINE_2102_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.ACTION_OBD_ENGINE_2103_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.ACTION_OBD_ENGINE_211D_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.ACTION_OBD_ENGINE_211E_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.ACTION_OBD_METER_21A1_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.ACTION_OBD_METER_21A2_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.ACTION_OBD_METER_21A3_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.ACTION_OBD_METER_21A6_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.ACTION_OBD_METER_21A8_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.ACTION_OBD_METER_21AD_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.ACTION_OBD_METER_21AE_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.ACTION_OBD_METER_21AF_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.ACTION_OBD_METER_21BC_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.ACTION_OBD_PARKING_2101_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.OBD_BROADCAST_ACTION_CMU_VOLTAGE_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.OBD_BROADCAST_ACTION_COOLANT_TEMP_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.OBD_BROADCAST_ACTION_ENGINE_RPM_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.OBD_BROADCAST_ACTION_FUEL_CONSUMPTION_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.OBD_BROADCAST_ACTION_MAF_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.OBD_BROADCAST_ACTION_SPEED_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.OBD_BROADCAST_ACTION_STATUS_CHANGED;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
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

import ru.d51x.kaierutils.Data.CombineMeterData;
import ru.d51x.kaierutils.Data.CvtData;


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
            if (action.equals(OBD_BROADCAST_ACTION_STATUS_CHANGED)) {
                boolean res = intent.getBooleanExtra("Status", false);
                tvDeviceStatus.setText(String.format(getString(R.string.odbii_device_status), res ? "Подключен" : "Не подключен"));
            }
            else if (action.equals(OBD_BROADCAST_ACTION_ENGINE_RPM_CHANGED)) {
                tvOBDEngineRPM.setText(String.format(getString(R.string.text_obd_engine_rpm_f), intent.getStringExtra("engineRPM")));
            }
            else if (action.equals(OBD_BROADCAST_ACTION_SPEED_CHANGED)) {
                tvOBDSpeed.setText(String.format(getString(R.string.text_obd_speed_f), intent.getStringExtra("speed")));
            }
            else if (action.equals(OBD_BROADCAST_ACTION_COOLANT_TEMP_CHANGED)) {
                tvOBD_CoolantTemp.setText(String.format(getString(R.string.text_obd_coolant_temp_f), intent.getStringExtra("coolantTemp")));
            }
            else if (action.equals(OBD_BROADCAST_ACTION_CMU_VOLTAGE_CHANGED)) {
                tvOBD_CMUVoltage.setText(String.format(getString(R.string.text_obd_cmu_voltage_f), intent.getStringExtra("cmuVoltage")));
            }
            else if (action.equals(OBD_BROADCAST_ACTION_FUEL_CONSUMPTION_CHANGED)) {

            }
            else if (action.equals(OBD_BROADCAST_ACTION_MAF_CHANGED)) {
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

            else if (action.equals(ACTION_OBD_CVT_2103_CHANGED)) {
                // CVT: Oil Temperature
                CvtData cvtData = (CvtData) intent.getSerializableExtra("obd_cvt_2103");
                int i = cvtData.getTemperature();
                String s = (i > -255) ? Integer.toString(i) : "---";
                tv_can_2103_cvt_temp_count.setText(String.format(getString(R.string.text_can_2103_cvt_temp_count), s));
            }
            else if (action.equals(ACTION_OBD_CVT_2110_CHANGED)) {
                // CVT: Oil Degradation Level
                CvtData cvtData = (CvtData) intent.getSerializableExtra("obd_cvt_2110");
                int i = cvtData.getOilDegradation();
                String s = (i >= 0) ? Integer.toString(i) : "---";
                tv_can_2110_cvt_oil_degr.setText(String.format(getString(R.string.text_can_2110_cvt_oil_degr), s));
            }

            //Combination meter: fuel level
            else if (action.equals(ACTION_OBD_METER_21A3_CHANGED)) {
                CombineMeterData meterData = (CombineMeterData) intent.getSerializableExtra("combine_meter_21A3");
                int i = meterData.getFuelLevel();
                String s = (i > -1) ? Integer.toString(i) : "---";
                tvFuelLevel.setText(String.format(getString(R.string.text_obd_can_fuel_level), s));
            }
        }
    };

    public static void show_odb_device_select_dialog(Context context) {
        ArrayList<String> deviceStrs = new ArrayList<String>();
        final ArrayList<String> devices = new ArrayList<String>();
        final ArrayList<String> devicesName = new ArrayList<String>();


        BluetoothManager btManager = (BluetoothManager) context.getSystemService(Context.BLUETOOTH_SERVICE);
        BluetoothAdapter btAdapter = btManager.getAdapter();

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
            Log.i("BT", "Bluetooth turn on .... Check permission BLUETOOTH_CONNECT");
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_GRANTED) {
                if (!btAdapter.isEnabled()) {
                    Log.i("BT", "BT adapter disabled. Enable it");
                    btAdapter.enable();
                    App.GS.btState = btAdapter.isEnabled();
                } else {
                    Log.i("BT", "BT adapter already enabled.");
                }
            }
        }



        //BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();
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
        tvGPS_Distanse_Total = findViewById(R.id.tvGPS_Distanse_Total);


        tv_can_2110_cvt_oil_degr = findViewById(R.id.tv_can_2110_cvt_oil_degr);
        tv_can_2103_cvt_temp_count = findViewById(R.id.tv_can_2103_cvt_temp_count);

        tvOBD_MAF = findViewById(R.id.tvOBD_MAF);


        tvFuelLevel = findViewById(R.id.tvFuelLevel);


        swUseOBD = findViewById(R.id.swUseOBD);
	    swUseOBD.setOnClickListener(this);
	    swUseOBD.setChecked(App.obd.useOBD);

        cbCanMMC = findViewById(R.id.cbCanMMC);
        cbCanMMC.setOnClickListener(this);
        cbCanMMC.setChecked(App.obd.MMC_CAN);


        trMMCHeader = findViewById(R.id.trMMCHeader);
        trMMCHeader.setVisibility(App.obd.MMC_CAN ? View.VISIBLE : View.GONE);

        trMMCValues = findViewById(R.id.trMMCValues);
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
        tv_can_2103_cvt_temp_count.setText(String.format( getString( R.string.text_can_2103_cvt_temp_count), "---"));



        tvFuelLevel.setText(String.format( getString( R.string.text_obd_can_fuel_level), "---"));

        App.obd.readExtendMeter = true;
        App.obd.readExtendCvt = true;
        App.obd.readExtendClimate = true;

        registerReceiver(receiver, new IntentFilter(OBD_BROADCAST_ACTION_STATUS_CHANGED));

        // OBD GENERIC
        registerReceiver(receiver, new IntentFilter(OBD_BROADCAST_ACTION_SPEED_CHANGED));
        registerReceiver(receiver, new IntentFilter(OBD_BROADCAST_ACTION_ENGINE_RPM_CHANGED));
        registerReceiver(receiver, new IntentFilter(OBD_BROADCAST_ACTION_COOLANT_TEMP_CHANGED));
        registerReceiver(receiver, new IntentFilter(OBD_BROADCAST_ACTION_CMU_VOLTAGE_CHANGED));
        registerReceiver(receiver, new IntentFilter(OBD_BROADCAST_ACTION_FUEL_CONSUMPTION_CHANGED));
        registerReceiver(receiver, new IntentFilter(OBD_BROADCAST_ACTION_MAF_CHANGED));

        // ENGINE
        //registerReceiver(receiver, new IntentFilter(ACTION_OBD_ENGINE_CHANGED));
        registerReceiver(receiver, new IntentFilter(ACTION_OBD_ENGINE_2101_CHANGED));
        registerReceiver(receiver, new IntentFilter(ACTION_OBD_ENGINE_2102_CHANGED));
        registerReceiver(receiver, new IntentFilter(ACTION_OBD_ENGINE_2103_CHANGED));
        registerReceiver(receiver, new IntentFilter(ACTION_OBD_ENGINE_211E_CHANGED));
        registerReceiver(receiver, new IntentFilter(ACTION_OBD_ENGINE_211D_CHANGED));

        // CVT
        //registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_ECU_CVT_CHANGED));
        registerReceiver(receiver, new IntentFilter(ACTION_OBD_CVT_2103_CHANGED));
        registerReceiver(receiver, new IntentFilter(ACTION_OBD_CVT_2110_CHANGED));

        // CLIMATE
        registerReceiver(receiver, new IntentFilter(ACTION_OBD_CLIMATE_2110_CHANGED));
        registerReceiver(receiver, new IntentFilter(ACTION_OBD_CLIMATE_2111_CHANGED));
        registerReceiver(receiver, new IntentFilter(ACTION_OBD_CLIMATE_2113_CHANGED));
        registerReceiver(receiver, new IntentFilter(ACTION_OBD_CLIMATE_2132_CHANGED));
        registerReceiver(receiver, new IntentFilter(ACTION_OBD_CLIMATE_2160_CHANGED));
        registerReceiver(receiver, new IntentFilter(ACTION_OBD_CLIMATE_2161_CHANGED));
        registerReceiver(receiver, new IntentFilter(ACTION_OBD_CLIMATE_2180_CHANGED));

        // COMBINE METER
        //registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_ECU_COMBINEMETER_CHANGED));
        registerReceiver(receiver, new IntentFilter(ACTION_OBD_METER_21A1_CHANGED));
        registerReceiver(receiver, new IntentFilter(ACTION_OBD_METER_21A2_CHANGED));
        registerReceiver(receiver, new IntentFilter(ACTION_OBD_METER_21A3_CHANGED));
        registerReceiver(receiver, new IntentFilter(ACTION_OBD_METER_21A6_CHANGED));
        registerReceiver(receiver, new IntentFilter(ACTION_OBD_METER_21A8_CHANGED));
        registerReceiver(receiver, new IntentFilter(ACTION_OBD_METER_21AD_CHANGED));
        registerReceiver(receiver, new IntentFilter(ACTION_OBD_METER_21AE_CHANGED));
        registerReceiver(receiver, new IntentFilter(ACTION_OBD_METER_21AF_CHANGED));
        registerReceiver(receiver, new IntentFilter(ACTION_OBD_METER_21BC_CHANGED));

        // PARKING SENSORS
        registerReceiver(receiver, new IntentFilter(ACTION_OBD_PARKING_2101_CHANGED));

        // AWC
        // registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_ECU_AWC_CHANGED));
        registerReceiver(receiver, new IntentFilter(ACTION_OBD_AWC_2130_CHANGED));

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

        int i = App.obd.can.meter.getFuelLevel();
        String s = ( i > -1 ) ? Integer.toString(i) : "---";
        tvFuelLevel.setText(String.format( getString( R.string.text_obd_can_fuel_level), s));


        i = App.obd.can.cvt.getTemperature();
        s = ( i > -255 ) ? Integer.toString(i) : "---";
        tv_can_2103_cvt_temp_count.setText(String.format( getString( R.string.text_can_2103_cvt_temp_count), s));

        i = App.obd.can.cvt.getOilDegradation();
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
        App.obd.readExtendMeter = false;
        App.obd.readExtendCvt = false;
        App.obd.readExtendClimate = false;

        super.onDestroy();
    }


}
