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

import java.io.IOException;
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
        private float fuel_consump_lpk_inst_prev;
        public float fuel_consump_lpk_trip;  // без учета простоя в пробках
        public float fuel_consump_lpk_trip2; // с учетом простоя в пробках
        public float distance_to_fuel_consump;
        public float distance_to_fuel_consump2; //нужно для сохраняемого расчета остатка
        public float fuel_usage;  // нужно просто для информации и не обнуляется при заправке
        public float fuel_usage2; // нужно для расчета, изменяется при заправке
        public float fuel_usage_with_stops; // нужно для расчета, изменяется при заправке, учитывает простои в пробках
        public float fuel_remain; // сохраняем в преференсы и читаем оттуда
        public float fuel_tank; // читаем из преференсов

        private long prevMafTime = 0;

        public OBDData() {
            speed = 0;
            rpm = 0;
            coolant = 0;
            maf = 0;
            voltage = 0;
            airIntake = 0;

            fuel_consump_lph = 0;
            fuel_consump_lpk_inst = 0;
            fuel_consump_lpk_trip = 0;
            fuel_consump_lpk_trip2 = 0;
            fuel_consump_lpk_inst_prev = 0;

            distance_to_fuel_consump = 0;
            fuel_usage = 0;

            fuel_remain = 0;
            fuel_tank = 0;
            fuel_usage2 = 0;
            fuel_usage_with_stops = 0;
            distance_to_fuel_consump2 = 0;

        }



        public void calc_fuel_consump() {
            // литры в секунду
            float fuel_consump_lpsec = ( maf / 14.7f) / 720;
            // литры в час
            fuel_consump_lph = fuel_consump_lpsec * 3600;

            long curMafTime = System.currentTimeMillis();
            long delta = curMafTime - prevMafTime;
            // использовано литров за delta секунд (между первым показанием и вторым)
            float usedmaf = fuel_consump_lpsec *1000 / delta;



            fuel_usage_with_stops += usedmaf; // с учетом порстоя
            fuel_consump_lpk_trip2 = fuel_usage_with_stops * 100 / (distance_to_fuel_consump2/1000f);

            if ( speed > 2 ) {
                // мгновенный расход л/100км
                fuel_consump_lpk_inst = (100 * fuel_consump_lph) / speed;

                // средний расход л/100км (среднее между мгновенным текущим и мгновенным предыдущим)
                // в процессе будет накапливаться среднее арифметическое мгновенных расходов - л/00км
                //fuel_consump_lpk_trip = (fuel_consump_lpk_inst + fuel_consump_lpk_inst_prev) / 2f;
                //fuel_consump_lpk_trip = (fuel_consump_lpk_trip + fuel_consump_lpk_inst) / 2f;
                //fuel_consump_lpk_inst_prev = fuel_consump_lpk_inst;

                // кол-во израсходованного топлива для отображения (должно накапливаться в течение поездки)
                //fuel_usage = (fuel_consump_lpk_trip * (distance_to_fuel_consump/1000f)) / 100f;
                fuel_usage += usedmaf;

                // кол-во израсходованного топлива для подсчета остатка в баке
                //fuel_usage2 = (fuel_consump_lpk_trip * (distance_to_fuel_consump2/1000f)) / 100f;
                fuel_usage2 += usedmaf;
                fuel_consump_lpk_trip = fuel_usage2 * 100 / (distance_to_fuel_consump2/1000f);


            }

            // сейчас на машинах баков менье 40 литров не бывает?
            // и размер бака не может быть меньше израсходованного количества?
            if ( fuel_tank >= 40 && fuel_tank >= fuel_usage_with_stops) {
                // остаток топлива в баке
                fuel_remain = fuel_tank - fuel_usage_with_stops;
            } else {
                fuel_remain = 0;
            }


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
        loadFuelRemain();
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

    public boolean connect() {
	    Log.d("OBD2->connect()", "useOBD is " + String.valueOf (useOBD));
        if ( !useOBD ) return false;
        if ( deviceAddress == null) return false;
	    Log.d("OBD2->connect()", "isConnected is " + String.valueOf (isConnected));
        //if ( isConnected ) return;
        BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();
        BluetoothDevice device = btAdapter.getRemoteDevice(deviceAddress);
        UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
        try {
            try {
                btAdapter.cancelDiscovery();
            } catch (Exception e11) {
                Log.d("OBD2->connect()", e11.getMessage());
                return false;
            }
            socket = device.createInsecureRfcommSocketToServiceRecord(uuid);
            //
            if ( socket != null ) {
                Thread.sleep(1000);
                Log.d("OBD2->connect()", "try to connect to socket ");
                socket.connect();
                Log.d("OBD2->connect()", "sleep after connect, waiting....");
                Thread.sleep(1000);
                boolean res = init();
                SendBroadcastAction(OBD_BROADCAST_ACTION_STATUS_CHANGED, "Status", res);
                return res;
            } else {
                Log.d("OBD2->connect()", "socket is NULL....");
                return false;
            }

       } catch (Exception e) {
            Log.d("OBDII-->connect()", e.toString());
            return false;
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
        notify_disconnect();
    }

    public boolean init() {
        boolean res = true;
	    Log.d("OBD2->init()", "_");
        try {

            // echo off: "AT E0"
            EchoOffObdCommand cmd1 = new EchoOffObdCommand();
            cmd1.run(socket.getInputStream(), socket.getOutputStream());
            Log.d("OBD2->init(): ", "echoOff result:  !" + cmd1.getFormattedResult());
            res = res && (cmd1.getFormattedResult().equalsIgnoreCase("OK"));
            if ( !res ) return res;

            // "AT L0"
            LineFeedOffObdCommand cmd2 = new LineFeedOffObdCommand();
            cmd2.run(socket.getInputStream(), socket.getOutputStream());
            Log.d("OBD2->init(): ", "LineFeedOff result:  !" + cmd2.getFormattedResult());
            res = res && (cmd2.getFormattedResult().equalsIgnoreCase("OK"));
            if ( !res ) return res;

            // AT ST
            //new TimeoutObdCommand(0).run(socket.getInputStream(), socket.getOutputStream());

            // AT SP
	        Log.d("OBD2->init()", "set protocol to ISO 15765-4 CAN");
            SelectProtocolObdCommand cmd3 = new SelectProtocolObdCommand(ObdProtocols.ISO_15765_4_CAN);
            cmd3.run(socket.getInputStream(), socket.getOutputStream());
            Log.d("OBD2->init(): ", "SelectProtocol result:  !" + cmd3.getFormattedResult());
            res = res && (cmd3.getFormattedResult().equalsIgnoreCase("OK"));
            if ( !res ) return res;
            obdData.prevMafTime = System.currentTimeMillis();
            return res;
        } catch (Exception e2) {
            Log.d("OBDII-->init()", e2.toString());
            return false;
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
        Log.d("OBDII-->processData()", "_");
        processOBD_EngineRPM();         // RPM:             01 0C
        processOBD_Speed();             // Speed:           01 0D
        processOBD_coolantTemp();       // Coolant:         01 05
        processOBD_CMVoltage();         // Voltage:         01 42
        processOBD_MAF();               // MAF:             01 10
        processOBD_AirIntake();         // AirIntake:       01 0F

        //Thread.sleep(500);
    }



    private void processOBD_EngineRPM() {
        try {
            engineRpmCommand.run(socket.getInputStream(), socket.getOutputStream());
            obdData.rpm = engineRpmCommand.getRPM();
            SendBroadcastAction(OBD_BROADCAST_ACTION_ENGINE_RPM_CHANGED, "engineRPM", engineRpmCommand.getFormattedResult());
            Log.d("OBDII-->processOBD_EngineRPM()", "RPM: " + engineRpmCommand.getFormattedResult());
        } catch ( NoDataException e) {
            Log.d("OBDII-->processOBD_EngineRPM()", e.toString());
        } catch (UnableToConnectException e3) {
            Log.d("OBDII-->processOBD_EngineRPM()", e3.toString());
            notify_disconnect();
        } catch (IOException e4) {
            Log.d("OBDII-->processOBD_EngineRPM()", e4.toString());
            notify_disconnect();
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
        } catch (UnableToConnectException e3) {
            Log.d("OBDII-->processOBD_Speed()", e3.toString());
            notify_disconnect();
        } catch (IOException e4) {
            Log.d("OBDII-->processOBD_Speed()", e4.toString());
            notify_disconnect();
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
        } catch (UnableToConnectException e3) {
            Log.d("OBDII-->processOBD_coolantTemp()", e3.toString());
            notify_disconnect();
        } catch (IOException e4) {
            Log.d("OBDII-->processOBD_coolantTemp()", e4.toString());
            notify_disconnect();
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
        } catch (UnableToConnectException e3) {
            Log.d("OBDII-->processOBD_CMVoltage()", e3.toString());
            notify_disconnect();
        } catch (IOException e4) {
            Log.d("OBDII-->processOBD_CMVoltage()", e4.toString());
            notify_disconnect();
        } catch ( Exception e2) {
            Log.d("OBDII-->processOBD_CMVoltage()", e2.toString());
        }
    }

    private void processOBD_MAF() {
        try {
            obdData.prevMafTime = System.currentTimeMillis();
            MAFObdCommand.run(socket.getInputStream(), socket.getOutputStream());
            obdData.maf = MAFObdCommand.getMAF();

            obdData.calc_fuel_consump();

            Intent intent = new Intent();
            intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
            intent.putExtra("sMAF", MAFObdCommand.getFormattedResult());
            intent.putExtra("dMAF", obdData.maf);
            intent.setAction(OBD_BROADCAST_ACTION_MAF_CHANGED);
            App.getInstance ().sendBroadcast(intent);
            Log.d("OBDII-->processOBD_MAF()", "MAF: " + MAFObdCommand.getFormattedResult());
        } catch ( NoDataException e) {
            Log.d("OBDII-->processOBD_MAF()", e.toString());
        } catch (UnableToConnectException e3) {
            Log.d("OBDII-->processOBD_MAF()", e3.toString());
            notify_disconnect();
        } catch (IOException e4) {
            Log.d("OBDII-->processOBD_MAF()", e4.toString());
            notify_disconnect();
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
        } catch (UnableToConnectException e3) {
            Log.d("OBDII-->processOBD_AirIntake()", e3.toString());
            notify_disconnect();
        } catch (IOException e4) {
            Log.d("OBDII-->processOBD_AirIntake()", e4.toString());
            notify_disconnect();
        } catch ( Exception e2) {
            Log.d("OBDII-->processOBD_AirIntake()", e2.toString());
        }
    }

    private void notify_disconnect() {
        isConnected = false;
        socket = null;
        SendBroadcastAction(OBD_BROADCAST_ACTION_STATUS_CHANGED, "Status", false);
    }

    private void SendBroadcastAction(String action, String key, String value) {
       // Log.d ("OBDII", "SendBroadcastAction " + action + " key = " + key + " value = " + value);
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        if ( key != null ) {
            intent.putExtra(key, value);
        }
        intent.setAction(action);
        App.getInstance ().sendBroadcast(intent);
    }

    private void SendBroadcastAction(String action, String key, boolean value) {
        // Log.d ("OBDII", "SendBroadcastAction " + action + " key = " + key + " value = " + value);
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        if ( key != null ) {
            intent.putExtra(key, value);
        }
        intent.setAction(action);
        App.getInstance ().sendBroadcast(intent);
    }


    public void saveFuelRemain() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(App.getInstance());
        prefs.edit().putFloat("kaierutils_fuel_remain", obdData.fuel_remain).commit();
        prefs.edit().putFloat("kaierutils_fuel_tank", obdData.fuel_tank).commit();
        //prefs.edit().putFloat("kaierutils_fuel_usage", obdData.fuel_usage2).commit();
        prefs.edit().putFloat("kaierutils_fuel_usage", obdData.fuel_usage_with_stops).commit();
        prefs.edit().putFloat("kaierutils_fuel_distance", obdData.distance_to_fuel_consump2).commit();

    }

    public void loadFuelRemain() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences (App.getInstance ());
        obdData.fuel_remain = prefs.getFloat("kaierutils_fuel_remain", 60f);
        obdData.fuel_tank = prefs.getFloat("kaierutils_fuel_tank", 60f);
        //obdData.fuel_usage2 = prefs.getFloat("kaierutils_fuel_usage", 0f);
        obdData.fuel_usage_with_stops = prefs.getFloat("kaierutils_fuel_usage", 0f);
        obdData.distance_to_fuel_consump2 = prefs.getFloat("kaierutils_fuel_distance", 0f);
    }

    public void setFullTank() {
        obdData.fuel_remain =  obdData.fuel_tank;
        obdData.fuel_usage2 = 0;
        obdData.fuel_usage_with_stops = 0;
        obdData.distance_to_fuel_consump2 = 0;
        saveFuelRemain();
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
