package ru.d51x.kaierutils;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.UUID;


import pt.lighthouselabs.obd.commands.SpeedObdCommand;
import pt.lighthouselabs.obd.commands.engine.EngineRPMObdCommand;
import pt.lighthouselabs.obd.commands.engine.MassAirFlowObdCommand;
import pt.lighthouselabs.obd.commands.temperature.EngineCoolantTemperatureObdCommand;
import pt.lighthouselabs.obd.commands.temperature.AirIntakeTemperatureObdCommand;
import pt.lighthouselabs.obd.commands.control.ControlModuleVoltageObdCommand;


import pt.lighthouselabs.obd.commands.protocol.EchoOffObdCommand;
import pt.lighthouselabs.obd.commands.protocol.LineFeedOffObdCommand;
import pt.lighthouselabs.obd.commands.protocol.SelectProtocolObdCommand;
import pt.lighthouselabs.obd.enums.ObdProtocols;
import pt.lighthouselabs.obd.exceptions.*;
/**
 * Created by pyatyh_ds on 18.03.2015.
 */
public class OBDII {

    protected static final String OBD_BROADCAST_ACTION_STATUS_CHANGED = "ru.d51x.kaierutils.action.OBD_STATUS_CHANGED";
    protected static final String OBD_BROADCAST_ACTION_SPEED_CHANGED = "ru.d51x.kaierutils.action.OBD_SPEED_CHANGED";
    protected static final String OBD_BROADCAST_ACTION_ENGINE_RPM_CHANGED = "ru.d51x.kaierutils.action.OBD_ENGINE_RPM_CHANGED";
    protected static final String OBD_BROADCAST_ACTION_COOLANT_TEMP_CHANGED = "ru.d51x.kaierutils.action.OBD_COOLANT_TEMP_CHANGED";
    protected static final String OBD_BROADCAST_ACTION_FUEL_LEVEL_CHANGED = "ru.d51x.kaierutils.action.OBD_FUEL_LEVEL_CHANGED";
    protected static final String OBD_BROADCAST_ACTION_CMU_VOLTAGE_CHANGED = "ru.d51x.kaierutils.action.OBD_CMU_VOLTAGE_CHANGED";
    protected static final String OBD_BROADCAST_ACTION_FUEL_CONSUMPTION_CHANGED = "ru.d51x.kaierutils.action.OBD_FUEL_CONSUMPTION_CHANGED";
    protected static final String OBD_BROADCAST_ACTION_MAF_CHANGED = "ru.d51x.kaierutils.action.OBD_MAF_CHANGED";
    protected static final String OBD_BROADCAST_ACTION_AIR_INTAKE_TEMP_CHANGED = "ru.d51x.kaierutils.action.OBD_AIR_INTAKE_TEMP_CHANGED";

    private Context mContext;
    private String deviceName;
    private String deviceAddress;
    public boolean isConnected;

    private BluetoothSocket socket;
    public EngineRPMObdCommand engineRpmCommand;
    public SpeedObdCommand speedCommand;
    public EngineCoolantTemperatureObdCommand coolantTempCommand;
    // private FuelLevelObdCommand fuelLevelCommand;
    public ControlModuleVoltageObdCommand cmuVoltageCommand;
    //  private FuelEconomyObdCommand fuelEconomyObdCommand;
    public MassAirFlowObdCommand MAFObdCommand;
    public AirIntakeTemperatureObdCommand airIntakeTemperatureObdCommand;
    private Handler mHandler;

    public boolean useOBD;
    public OBDData obdData;

    public class OBDData {
        public float speed;
        public float rpm;
        public float coolant;
        public float maf;
        public float voltage;
        public float airIntake;
        public float fuel_consump_lph;
        public float fuel_consump_lpk_inst;
        public float fuel_consump_lpk_trip;

        public OBDData() {
            speed = -1;
            rpm = -1;
            coolant = -1000;
            maf = -1;
            voltage = -1;
            airIntake = -1000;

            fuel_consump_lph = -1;
            fuel_consump_lpk_inst = -1;
            fuel_consump_lpk_trip = -1;
        }

        public void calc_fuel_consump_lph() {
            fuel_consump_lph = (( maf / 14.7f) / 720) * 3600;
        }

        public void calc_fuel_consump_lpk_inst() {
            fuel_consump_lpk_inst = (100 * maf) / speed;
        }

        public void calc_fuel_consump_lpk_trip() {
            fuel_consump_lpk_trip = fuel_consump_lpk_inst;
        }

    };


    public OBDII(Context context) {
        mContext = context;
        deviceName = null;
        deviceAddress = null;
        isConnected = false;
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mContext);
        deviceAddress = prefs.getString("ODBII_DEVICE_ADDRESS", null);
        deviceName = prefs.getString("ODBII_DEVICE_NAME", null);
        socket = null;
        mHandler = new Handler();
        useOBD = prefs.getBoolean("ODBII_USE_BLUETOOTH", false);
        obdData = new OBDData();
        // запустить бесконечный цикл
        // если подключены, то выполняем команды
        // если не подключены, то пытаемся подключиться

    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String name) {
        deviceName = name;
    }

    public String getDeviceAddress() {
        return deviceAddress;
    }

    public void setDeviceAddress(String address) {
        deviceAddress = address;
    }

    public void show_devices_list() {


    }

    public void connect() {
	    Log.d("OBD2->connect()", "useOBD is " + String.valueOf (useOBD));
        if ( !useOBD ) return;
        if ( deviceAddress == null) return;
	    Log.d("OBD2->connect()", "isConnected is " + String.valueOf (isConnected));
        if ( isConnected ) return;
        BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();
        BluetoothDevice device = btAdapter.getRemoteDevice(deviceAddress);
        UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
        try {
            socket = device.createInsecureRfcommSocketToServiceRecord(uuid);
	        Log.d("OBD2->connect()", "try to connect to socket ");
            socket.connect();
            //
            if ( socket != null ) {
                Log.d("OBD2->connect()", "sleep after connect, waiting....");
                Thread.sleep(5000);
                isConnected = socket.isConnected();
                Log.d("OBD2->connect()", "socked connected is " + String.valueOf (isConnected));
                isConnected = true;
                Intent intent = new Intent();
                intent.setAction(OBD_BROADCAST_ACTION_STATUS_CHANGED);
                intent.putExtra("Status",  isConnected );
                mContext.sendBroadcast(intent);
            } else {
                Log.d("OBD2->connect()", "socket is NULL....");
            }

       } catch (Exception e) {
            Log.d("OBDII-->connect()", e.toString());
            isConnected = false;
        }
    }

    public void disconnect() {
        try {
	        Log.d("OBD2->connect()", "try to close socked ");
            socket.close();
	        Log.d("OBD2->connect()", "socket is closed");

        } catch (Exception e) {
            Log.d("OBDII-->disconnect()", e.toString());
        }
        isConnected = false;
        socket = null;
        Intent intent = new Intent();
        intent.setAction(OBD_BROADCAST_ACTION_STATUS_CHANGED);
        intent.putExtra("Status", isConnected);
        mContext.sendBroadcast(intent);
    }

    public void init() {
	    Log.d("OBD2->init()", "");
        try {
            // echo off: "AT E0"
            new EchoOffObdCommand().run(socket.getInputStream(), socket.getOutputStream());

            // "AT L0"
            new LineFeedOffObdCommand().run(socket.getInputStream(), socket.getOutputStream());

            // AT ST
            //new TimeoutObdCommand(0).run(socket.getInputStream(), socket.getOutputStream());

            // AT SP
	        Log.d("OBD2->init()", "set protocol to ISO 15765-4 CAN");
            new SelectProtocolObdCommand(ObdProtocols.ISO_15765_4_CAN).run(socket.getInputStream(), socket.getOutputStream());

        } catch (Exception e2) {
            Log.d("OBDII-->init()", e2.toString());
        }
    }

    public void prepareData() {
        if ( !isConnected ) return;
        engineRpmCommand = new EngineRPMObdCommand();
        speedCommand = new SpeedObdCommand();
        coolantTempCommand = new EngineCoolantTemperatureObdCommand();
        // fuelLevelCommand = new FuelLevelObdCommand();        // not working  01 2f  no data
        cmuVoltageCommand = new ControlModuleVoltageObdCommand();
        // fuelEconomyObdCommand = new FuelEconomyObdCommand();   // not working  01 5e   n0 data
        MAFObdCommand = new MassAirFlowObdCommand();
        airIntakeTemperatureObdCommand = new AirIntakeTemperatureObdCommand();
    }

    public void processData() {
        if ( !isConnected ) return;
        Log.d("OBDII-->processData()", "");
        processOBD_EngineRPM();         // RPM:             01 0C
        processOBD_Speed();             // Speed:           01 0D
        processOBD_coolantTemp();       // Coolant:         01 05
        processOBD_CMVoltage();         // Voltage:         01 42
        processOBD_MAF();               // MAF:             01 10
        processOBD_AirIntake();         // AirIntake:       01 0F
    }



    private void processOBD_EngineRPM() {
        try {
            engineRpmCommand.run(socket.getInputStream(), socket.getOutputStream());
            obdData.rpm = engineRpmCommand.getRPM();
            SendBroadcastAction(OBD_BROADCAST_ACTION_ENGINE_RPM_CHANGED, "engineRPM", engineRpmCommand.getFormattedResult());
            Log.d("OBDII-->processOBD_EngineRPM()", "RPM: " + engineRpmCommand.getFormattedResult());
        } catch ( NoDataException e) {
            Log.d("OBDII-->processOBD_EngineRPM()", e.toString());
        } catch ( Exception e2) {
            Log.d("OBDII-->processOBD_EngineRPM()", e2.toString());
        }
    }

    private void processOBD_Speed() {
        try {
            speedCommand.run(socket.getInputStream(), socket.getOutputStream());
            obdData.speed = speedCommand.getMetricSpeed();
            SendBroadcastAction(OBD_BROADCAST_ACTION_SPEED_CHANGED, "speed", speedCommand.getFormattedResult());
            Log.d("OBDII-->processOBD_Speed()", "Speed: " + speedCommand.getFormattedResult());
        } catch ( NoDataException e) {
            Log.d("OBDII-->processOBD_Speed()", e.toString());
        } catch ( Exception e2) {
            Log.d("OBDII-->processOBD_Speed()", e2.toString());
        }
    }

    private void processOBD_coolantTemp() {
        try {
            coolantTempCommand.run(socket.getInputStream(), socket.getOutputStream());
            obdData.coolant = coolantTempCommand.getTemperature();
            Intent intent = new Intent();
            intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
            intent.putExtra("coolantTemp", coolantTempCommand.getFormattedResult());
            intent.putExtra("coolantTempD", obdData.coolant);
            intent.setAction(OBD_BROADCAST_ACTION_COOLANT_TEMP_CHANGED);
            App.getInstance ().sendBroadcast(intent);

            Log.d("OBDII-->processOBD_coolantTemp()", "coolantTemp: " + coolantTempCommand.getFormattedResult());
        } catch ( NoDataException e) {
            Log.d("OBDII-->processOBD_coolantTemp()", e.toString());
        } catch ( Exception e2) {
            Log.d("OBDII-->processOBD_coolantTemp()", e2.toString());
        }
    }

    private void processOBD_CMVoltage() {
        try {
            cmuVoltageCommand.run(socket.getInputStream(), socket.getOutputStream());
            obdData.voltage = cmuVoltageCommand.getVoltage();
            Intent intent = new Intent();
            intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
            intent.putExtra("cmuVoltage", cmuVoltageCommand.getFormattedResult());
            intent.putExtra("cmuVoltageD", obdData.voltage);
            intent.setAction(OBD_BROADCAST_ACTION_CMU_VOLTAGE_CHANGED);
            App.getInstance ().sendBroadcast(intent);
            Log.d("OBDII-->processOBD_CMVoltage()", "cmuVoltage: " + cmuVoltageCommand.getFormattedResult());
        } catch ( NoDataException e) {
            Log.d("OBDII-->processOBD_CMVoltage()", e.toString());
        } catch ( Exception e2) {
            Log.d("OBDII-->processOBD_CMVoltage()", e2.toString());
        }
    }

    private void processOBD_MAF() {
        try {
            MAFObdCommand.run(socket.getInputStream(), socket.getOutputStream());
            obdData.maf = MAFObdCommand.getMAF();

            obdData.calc_fuel_consump_lph();
            obdData.calc_fuel_consump_lpk_inst();

            Intent intent = new Intent();
            intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
            intent.putExtra("sMAF", MAFObdCommand.getFormattedResult());
            intent.putExtra("dMAF", obdData.maf);
            intent.setAction(OBD_BROADCAST_ACTION_MAF_CHANGED);
            App.getInstance ().sendBroadcast(intent);
            Log.d("OBDII-->processOBD_MAF()", "MAF: " + MAFObdCommand.getFormattedResult());
        } catch ( NoDataException e) {
            Log.d("OBDII-->processOBD_MAF()", e.toString());
        } catch ( Exception e2) {
            Log.d("OBDII-->processOBD_MAF()", e2.toString());
        }
    }

    private void processOBD_AirIntake() {
        try {
            airIntakeTemperatureObdCommand.run(socket.getInputStream(), socket.getOutputStream());
            obdData.airIntake = airIntakeTemperatureObdCommand.getTemperature();
            Intent intent = new Intent();
            intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
            intent.putExtra("sAirIntakeTemp", airIntakeTemperatureObdCommand.getFormattedResult());
            intent.putExtra("dAirIntakeTemp", obdData.airIntake);
            intent.setAction(OBD_BROADCAST_ACTION_AIR_INTAKE_TEMP_CHANGED);
            App.getInstance ().sendBroadcast(intent);
            Log.d("OBDII-->processOBD_AirIntake()", "AirIntakeTemperature: " + airIntakeTemperatureObdCommand.getFormattedResult());
        } catch ( NoDataException e) {
            Log.d("OBDII-->processOBD_AirIntake()", e.toString());
        } catch ( Exception e2) {
            Log.d("OBDII-->processOBD_AirIntake()", e2.toString());
        }
    }



    private void SendBroadcastAction(String action, String key, String value) {
        Log.d ("OBDII", "SendBroadcastAction " + action + " key = " + key + " value = " + value);
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        if ( key != null ) {
            intent.putExtra(key, value);
        }
        intent.setAction(action);
        App.getInstance ().sendBroadcast(intent);
    }


}
/*
* ATSP6\nATAL\nATSH7E0\nATCRA7E8\nATST32\nATSW00
* запросы 21F0 и 2102
*
* - долговременная балансировка - смесь, работа форсунок, работа лямбд, качество топлива
- вторая лямбда - исправность ката
- температура ОЖ - удобно смотреть в цифирках
- обороты - удобно смотреть
- средний расход - понятно зачем
- расход мл/мин - можно быстро оценить расход и качество бензина
- напряжение бортсети

для поездки

- скорость - удобно смотреть в цифирках
- пробег - удобно сбрасывать
- пробег за поездку
- ср расход за поездку
- потрачено топлива за поездку
- ср. скорость в пути
- время езды/ время простоя
*
* */


/*
как идея
отображаем уровень в баке,
после заправки надо на него тыкнуть и сказать, полный бак или сколько литров долито
вычисляем по расходу и пробегу
*/
