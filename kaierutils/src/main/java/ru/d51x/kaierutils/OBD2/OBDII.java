package ru.d51x.kaierutils.OBD2;


import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

import pt.lighthouselabs.obd.commands.SpeedObdCommand;
import pt.lighthouselabs.obd.commands.can.CanObdCommand;
import pt.lighthouselabs.obd.commands.control.ControlModuleVoltageObdCommand;
import pt.lighthouselabs.obd.commands.engine.EngineRPMObdCommand;
import pt.lighthouselabs.obd.commands.engine.MassAirFlowObdCommand;
import pt.lighthouselabs.obd.commands.protocol.EchoOffObdCommand;
import pt.lighthouselabs.obd.commands.protocol.LineFeedOffObdCommand;
import pt.lighthouselabs.obd.commands.protocol.ObdResetCommand;
import pt.lighthouselabs.obd.commands.protocol.SelectHeaderObdCommand;
import pt.lighthouselabs.obd.commands.protocol.SelectProtocolObdCommand;
import pt.lighthouselabs.obd.commands.temperature.EngineCoolantTemperatureObdCommand;
import pt.lighthouselabs.obd.enums.ObdProtocols;
import pt.lighthouselabs.obd.exceptions.MisunderstoodCommandException;
import pt.lighthouselabs.obd.exceptions.NoDataException;
import pt.lighthouselabs.obd.exceptions.NonNumericResponseException;
import pt.lighthouselabs.obd.exceptions.StoppedException;
import pt.lighthouselabs.obd.exceptions.UnableToConnectException;
import ru.d51x.kaierutils.App;
import ru.d51x.kaierutils.BuildConfig;
import ru.d51x.kaierutils.Data.CanMmcData;
import ru.d51x.kaierutils.Data.ClimateData;
import ru.d51x.kaierutils.Data.TripData;
import ru.d51x.kaierutils.utils.OBDCalculations;

/**
 */
public class OBDII  {
    public static final boolean localDebug = BuildConfig.SIMULATE_OBD;

    public static final String TAG = "OBD2";
    public static final String OBD_BROADCAST_ACTION_STATUS_CHANGED = "ru.d51x.kaierutils.action.OBD_STATUS_CHANGED";
    public static final String OBD_BROADCAST_ACTION_SPEED_CHANGED = "ru.d51x.kaierutils.action.OBD_SPEED_CHANGED";
    public static final String OBD_BROADCAST_ACTION_ENGINE_RPM_CHANGED = "ru.d51x.kaierutils.action.OBD_ENGINE_RPM_CHANGED";
    public static final String OBD_BROADCAST_ACTION_COOLANT_TEMP_CHANGED = "ru.d51x.kaierutils.action.OBD_COOLANT_TEMP_CHANGED";
    public static final String OBD_BROADCAST_ACTION_FUEL_LEVEL_CHANGED = "ru.d51x.kaierutils.action.OBD_FUEL_LEVEL_CHANGED";
    public static final String OBD_BROADCAST_ACTION_CMU_VOLTAGE_CHANGED = "ru.d51x.kaierutils.action.OBD_CMU_VOLTAGE_CHANGED";
    public static final String OBD_BROADCAST_ACTION_FUEL_CONSUMPTION_CHANGED = "ru.d51x.kaierutils.action.OBD_FUEL_CONSUMPTION_CHANGED";
    public static final String OBD_BROADCAST_ACTION_MAF_CHANGED = "ru.d51x.kaierutils.action.OBD_MAF_CHANGED";
    public static final String OBD_BROADCAST_ACTION_ENGINE_FAN_STATE_CHANGED = "ru.d51x.kaierutils.action.OBD_CAN_ENGINE_FAN_STATE_CHANGED";
    public static final String OBD_BROADCAST_ACTION_ECU_ENGINE_CHANGED = "ru.d51x.kaierutils.action.OBD_CAN_ECU_ENGINE_CHANGED";
    public static final String OBD_BROADCAST_ACTION_ECU_CVT_CHANGED = "ru.d51x.kaierutils.action.OBD_CAN_ECU_CVT_CHANGED";
    public static final String OBD_BROADCAST_ACTION_ECU_CVT_OIL_DEGR_CHANGED = "ru.d51x.kaierutils.action.OBD_BROADCAST_ACTION_ECU_CVT_OIL_DEGR_CHANGED";
    public static final String OBD_BROADCAST_ACTION_ECU_CVT_OIL_TEMP_CHANGED = "ru.d51x.kaierutils.action.OBD_BROADCAST_ACTION_ECU_CVT_OIL_TEMP_CHANGED";
    public static final String OBD_BROADCAST_ACTION_ECU_COMBINEMETER_CHANGED = "ru.d51x.kaierutils.action.OBD_CAN_ECU_COMBINEMETER_CHANGED";
    public static final String OBD_BROADCAST_ACTION_ECU_COMBINEMETER_FUEL_TANK_CHANGED = "ru.d51x.kaierutils.action.OBD_CAN_ECU_COMBINEMETER_FUEL_TANK_CHANGED";
    public static final String OBD_BROADCAST_ACTION_ECU_AWC_CHANGED = "ru.d51x.kaierutils.action.OBD_CAN_ECU_AWC_CHANGED";
    public static final String OBD_BROADCAST_ACTION_AC_FAN_SPEED_CHANGED = "ru.d51x.kaierutils.action.OBD_AC_FAN_SPEED_CHANGED";
    public static final String OBD_BROADCAST_ACTION_AC_FAN_MODE_CHANGED = "ru.d51x.kaierutils.action.OBD_AC_FAN_MODE_CHANGED";
    public static final String OBD_BROADCAST_ACTION_AC_EXT_TEMP_CHANGED = "ru.d51x.kaierutils.action.OBD_AC_EXT_TEMP_CHANGED";
    public static final String OBD_BROADCAST_ACTION_AC_TEMP_CHANGED = "ru.d51x.kaierutils.action.OBD_AC_TEMP_CHANGED";
    public static final String OBD_BROADCAST_ACTION_AC_BLOW_DIRECTION_CHANGED = "ru.d51x.kaierutils.action.OBD_AC_BLOW_DIRECTION_CHANGED";
    public static final String OBD_BROADCAST_ACTION_AC_BLOW_MODE_CHANGED = "ru.d51x.kaierutils.action.OBD_AC_BLOW_MODE_CHANGED";
    public static final String OBD_BROADCAST_ACTION_AC_DEFOGGER_CHANGED = "ru.d51x.kaierutils.action.OBD_AC_DEFOGGER_CHANGED";
    public static final String OBD_BROADCAST_ACTION_AC_RECIRCULATION_CHANGED = "ru.d51x.kaierutils.action.OBD_AC_RECIRCULATION_CHANGED";
    public static final String OBD_BROADCAST_ACTION_AC_STATE_CHANGED = "ru.d51x.kaierutils.action.OBD_CAN_STATE_CHANGED";
    public static final String OBD_BROADCAST_ACTION_PARKING_SENSORS_CHANGED = "ru.d51x.kaierutils.action.OBD_CAN_PARKING_SENSORS_CHANGED";
    public static final String OBD_BROADCAST_ACTION_PARKING_SENSORS_REAR_LEFT_INNER_CHANGED = "ru.d51x.kaierutils.action.OBD_CAN_PARKING_SENSORS_REAR_LEFT_INNER_CHANGED";
    public static final String OBD_BROADCAST_ACTION_PARKING_SENSORS_REAR_LEFT_OUTER_CHANGED = "ru.d51x.kaierutils.action.OBD_CAN_PARKING_SENSORS_REAR_LEFT_OUTER_CHANGED";
    public static final String OBD_BROADCAST_ACTION_PARKING_SENSORS_REAR_RIGHT_INNER_CHANGED = "ru.d51x.kaierutils.action.OBD_CAN_PARKING_SENSORS_REAR_RIGHT_INNER_CHANGED";
    public static final String OBD_BROADCAST_ACTION_PARKING_SENSORS_REAR_RIGHT_OUTER_CHANGED = "ru.d51x.kaierutils.action.OBD_CAN_PARKING_SENSORS_REAR_RIGHT_OUTER_CHANGED";
    public static final int MESSAGE_OBD_CAN_ENGINE = 0x07E00000;
    public static final int MESSAGE_OBD_CAN_ENGINE_FAN_STATE = 0x07E00001;
    public static final int MESSAGE_OBD_CAN_CVT = 0x07E10000;
    public static final int MESSAGE_OBD_CAN_CVT_OIL_DEGR = 0x07E10001;
    public static final int MESSAGE_OBD_CAN_CVT_OIL_TEMP = 0x07E10002;
    public static final int MESSAGE_OBD_CAN_AIR_COND = 0x06880000;
    public static final int MESSAGE_OBD_CAN_AIR_COND_STATE = 0x06880001;
    public static final int MESSAGE_OBD_CAN_AIR_COND_FAN_MODE = 0x06880002;
    public static final int MESSAGE_OBD_CAN_AIR_COND_FAN_SPEED = 0x06880003;
    public static final int MESSAGE_OBD_CAN_AIR_COND_BLOW_MODE = 0x06880004;
    public static final int MESSAGE_OBD_CAN_AIR_COND_BLOW_DIRECTION = 0x06880005;
    public static final int MESSAGE_OBD_CAN_AIR_COND_TEMPERATURE = 0x06880006;
    public static final int MESSAGE_OBD_CAN_AIR_COND_EXTERNAL_TEMPERATURE = 0x06880007;
    public static final int MESSAGE_OBD_CAN_AIR_COND_RECIRCULATION_STATE = 0x06880008;
    public static final int MESSAGE_OBD_CAN_AIR_COND_DEFOGGER_STATE = 0x06880009;
    public static final int MESSAGE_OBD_CAN_COMBINE_METER = 0x06A00000;
    public static final int MESSAGE_OBD_CAN_COMBINE_METER_FUEL_TANK = 0x06A00001;
    public static final int MESSAGE_OBD_CAN_PARKING_SENSORS = 0x07630000;
    protected static final boolean newMethod = true;
    public boolean isConnected;
    public boolean battery_show = false;
    public boolean engine_temp_show = false;
    public boolean fuel_data_show = false;
    public boolean fuel_consump_show = false;
    public int voltage_update_time = 5; // сек
    public int engine_temp_update_time = 5; // сек
    public EngineRPMObdCommand engineRpmCommand;
    public SpeedObdCommand speedCommand;
    public EngineCoolantTemperatureObdCommand coolantTempCommand;
    public ControlModuleVoltageObdCommand cmuVoltageCommand;
    public MassAirFlowObdCommand MAFObdCommand;
    public boolean useOBD;
    public OBDData obdData;
    public boolean activeMAF = false;
    public boolean activeOther = true;
    public boolean MMC_CAN;
    public TripData totalTrip;
    public TripData oneTrip;
    public TripData todayTrip;
    public ClimateData climateData;
    public ClimateData climateDataPrev;
    public CanMmcData canMmcData;
    public CanMmcData canMmcDataPrev;
    protected int timeout;
    private Context mContext;
    private String deviceName;
    private String deviceAddress;
    private Handler mHandler = new Handler();
    private BluetoothSocket socket;
    private long MAF_TimeStamp1, MAF_TimeStamp2;
    private long Voltage_TimeStamp1, Voltage_TimeStamp2;
    private long Coolant_TimeStamp1, Coolant_TimeStamp2;

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
        MMC_CAN = prefs.getBoolean("ODBII_USE_MMC_CAN", false);

        battery_show = prefs.getBoolean("ODBII_BATTERY_SHOW", false);
        engine_temp_show = prefs.getBoolean("ODBII_ENGINE_TEMP_SHOW", false);
        fuel_data_show = prefs.getBoolean("ODBII_FUEL_DATA_SHOW", false);
        fuel_consump_show = prefs.getBoolean("ODBII_FUEL_CONSUMP_SHOW", false);

        voltage_update_time = prefs.getInt("ODBII_VOLTAGE_UPDATE_TIME", 5); // сек
        engine_temp_update_time = prefs.getInt("ODBII_ENGINE_TEMP_UPDATE_TIME", 5); // сек


        obdData = new OBDData();
        totalTrip = new TripData("total", true);
        oneTrip = new TripData("trip", false);
        todayTrip = new TripData("today", true);

        climateData = new ClimateData();
        climateDataPrev = new ClimateData();

        canMmcData = new CanMmcData(mContext);
        canMmcDataPrev = new CanMmcData(mContext);
        loadFuelTank();
        // запустить бесконечный цикл
        // если подключены, то выполняем команды
        // если не подключены, то пытаемся подключиться

        MAF_TimeStamp1 = System.currentTimeMillis();
        MAF_TimeStamp2 = System.currentTimeMillis();

        Voltage_TimeStamp1 = System.currentTimeMillis();
        Voltage_TimeStamp2 = System.currentTimeMillis();
        Coolant_TimeStamp1 = System.currentTimeMillis();
        Coolant_TimeStamp2 = System.currentTimeMillis();

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message message) {
                process_handler(message);
            }
        };
    }

    ;

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

    public boolean connect() {
	    Log.d(TAG, "OBD2->connect(), useOBD is " + String.valueOf (useOBD));
        if ( !useOBD ) return false;
        if ( deviceAddress == null) {
            Log.w(TAG, "No device selected");
            return false;
        }
	    Log.d(TAG, "OBD2->connect(), isConnected is " + String.valueOf (isConnected));
        //if ( isConnected ) return;
        BluetoothManager btManager = (BluetoothManager) mContext.getSystemService(Context.BLUETOOTH_SERVICE);
        BluetoothAdapter btAdapter = btManager.getAdapter();

        BluetoothDevice device = btAdapter.getRemoteDevice(deviceAddress);
        UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
        try {
            try {
                if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.BLUETOOTH_SCAN) == PackageManager.PERMISSION_GRANTED) {
                    btAdapter.cancelDiscovery();
                }
            } catch (Exception e11) {
                Log.d(TAG, "OBD2->connect(): " + e11.getMessage());
                return false;
            }
            socket = device.createInsecureRfcommSocketToServiceRecord(uuid);
            //
            if ( socket != null ) {
                Thread.sleep(1000);
                Log.d(TAG, "OBD2->connect(), try to connect to socket ");
                socket.connect();
                Log.d(TAG, "OBD2->connect(), sleep after connect, waiting....");
                Thread.sleep(1000);
                boolean res = init();
                SendBroadcastAction(OBD_BROADCAST_ACTION_STATUS_CHANGED, "Status", res);
                return res;
            } else {
                Log.d(TAG, "OBD2->connect(), socket is NULL....");
                return false;
            }

       } catch (Exception e) {
            Log.d(TAG, "OBDII-->connect(): " + e.toString());
            return false;
        }
    }

    public void disconnect() {
        try {
            new ObdResetCommand().run(socket.getInputStream(), socket.getOutputStream());
            Log.d(TAG, "OBD2->disconnect(): try to close socked ");
            socket.close();
	        Log.d(TAG, "OBD2->disconnect() socket is closed");

        } catch (Exception e) {
            Log.d(TAG, "OBDII-->disconnect(): " + e.toString());
        }
        notify_disconnect();
    }

    public boolean init() {
        boolean res = true;
	    Log.d("OBD2->init()", "_");
        try {
            String result = "";
            new ObdResetCommand().run(socket.getInputStream(), socket.getOutputStream());
            // echo off: "AT E0"
            Log.d(TAG, "Send AT E0");
            EchoOffObdCommand cmd1 = new EchoOffObdCommand();
            cmd1.run(socket.getInputStream(), socket.getOutputStream());
            result = cmd1.getFormattedResult().replace("\n", "");
            Log.d(TAG, "echoOff result: " + result);
            res = res && (result.equalsIgnoreCase("OK"));
            if ( !res ) return res;

            // "AT L0"
            Log.d(TAG, "Send AT L0");
            LineFeedOffObdCommand cmd2 = new LineFeedOffObdCommand();
            cmd2.run(socket.getInputStream(), socket.getOutputStream());
            result = cmd2.getFormattedResult();
            Log.d(TAG, "LineFeedOff result: " + result);
            res = res && (result.equalsIgnoreCase("OK"));
            if ( !res ) return res;

            // AT ST
            //new TimeoutObdCommand(0).run(socket.getInputStream(), socket.getOutputStream());

            // AT SP
            Log.d(TAG, "Send AT SP 6");
	        Log.d(TAG, "set protocol to ISO 15765-4 CAN");
            SelectProtocolObdCommand cmd3 = new SelectProtocolObdCommand(ObdProtocols.ISO_15765_4_CAN);
            cmd3.run(socket.getInputStream(), socket.getOutputStream());
            result = cmd3.getFormattedResult();
            Log.d(TAG, "SelectProtocol result: " + result);
            res = res && (result.equalsIgnoreCase("OK"));
            if ( !res ) return res;
            return res;
        } catch (Exception e2) {
            Log.d(TAG, e2.toString());
            return false;
        }
    }

    public void prepareData() {
        if ( !isConnected ) return;
        engineRpmCommand = new EngineRPMObdCommand();
        speedCommand = new SpeedObdCommand();
        coolantTempCommand = new EngineCoolantTemperatureObdCommand();
        cmuVoltageCommand = new ControlModuleVoltageObdCommand();
        MAFObdCommand = new MassAirFlowObdCommand();

     }

    public void processData() {
        if ( !isConnected ) return;
        //Log.d("OBDII-->processData()", "_");

        if ( MMC_CAN )  {
            // переходим на режим работы MMC CAN и пиды 21хх
            request_MMC_ECU_ENGINE();

            request_MMC_ECU_CVT();
            request_MMC_ECU_COMBINATION_METER();
            request_MMC_ECU_AIR_COND();
            //request_MMC_ECU_AWC();

        } else {
            // переходим на дефолтный режим работы по стандартным пидам 01 хх
            SetHeaders("7E0", "7E8", false); // TODO: сделать это 1 раз после включения галки

            processOBD_EngineRPM();         // RPM:             01 0C
            processOBD_Speed();             // Speed:           01 0D
            processOBD_coolantTemp();       // Coolant:         01 05
            processOBD_CMVoltage();         // Voltage:         01 42
            // к этому моменту уже должна пройти 1 сек = 5 функций с задержкой 200 мсек
            //processOBD_MAF();               // MAF:             01 10
        }
    }

    private void processOBD_EngineRPM() {
        if ( activeMAF ) return;
        if ( App.GS.isReverseMode ) return;
        activeOther = true;
        try {
            long t = System.currentTimeMillis();
            engineRpmCommand.run(socket.getInputStream(), socket.getOutputStream());
            obdData.rpm = engineRpmCommand.getRPM();
            Log.d(TAG, String.format("Command RPM :: %d ms", System.currentTimeMillis() - t));

            SendBroadcastAction(OBD_BROADCAST_ACTION_ENGINE_RPM_CHANGED, "engineRPM", engineRpmCommand.getFormattedResult());
           // Log.d("OBDII-->processOBD_EngineRPM()", "RPM: " + engineRpmCommand.getFormattedResult());
        } catch ( NonNumericResponseException e5) {
            activeOther = false;
            disconnect();
            Log.d("OBDII-->processOBD_EngineRPM()", e5.toString());
        }
        catch (UnableToConnectException | IOException e3) {
            activeOther = false;
            Log.d("OBDII-->processOBD_EngineRPM()", e3.toString());
            disconnect();
        } catch ( Exception e2) {
            activeOther = false;
            Log.d("OBDII-->processOBD_EngineRPM()", e2.toString());

        }
        activeOther = false;
    }

    private void processOBD_Speed() {
        if ( activeMAF ) return;
        if ( App.GS.isReverseMode ) return;
        activeOther = true;
        try {
            long t = System.currentTimeMillis();
            speedCommand.run(socket.getInputStream(), socket.getOutputStream());
            obdData.speed = speedCommand.getMetricSpeed();
            Log.d(TAG, String.format("Command Speed :: %d ms", System.currentTimeMillis() - t));

            SendBroadcastAction(OBD_BROADCAST_ACTION_SPEED_CHANGED, "speed", speedCommand.getFormattedResult());
            //Log.d("OBDII-->processOBD_Speed()", "Speed: " + speedCommand.getFormattedResult());
        }  catch ( NonNumericResponseException e5) {
            activeOther = false;
            disconnect();
            Log.d("OBDII-->processOBD_Speed()", e5.toString());
        }
        catch ( StoppedException e6) {
            activeOther = false;
            Log.d("OBDII-->processOBD_Speed()", e6.toString());
        }
        catch (UnableToConnectException | IOException e3) {
            activeOther = false;
            Log.d("processOBD_Speed()", e3.toString());
            disconnect();
        } catch ( Exception e2) {
            activeOther = false;
            Log.d("processOBD_Speed()", e2.toString());
        }
        activeOther = false;
    }

    private void processOBD_coolantTemp() {
        if ( activeMAF ) return;
        if ( App.GS.isReverseMode ) return;
        if ( !engine_temp_show ) return;

        Coolant_TimeStamp2 = System.currentTimeMillis();
        long t = Coolant_TimeStamp2 - Coolant_TimeStamp1;
        if ( t < ( engine_temp_update_time * 1000L)) {return;}


        activeOther = true;
        try {
            long tt = System.currentTimeMillis();
            coolantTempCommand.run(socket.getInputStream(), socket.getOutputStream());
            obdData.coolant = coolantTempCommand.getTemperature();
            Log.d(TAG, String.format("Command Coolant :: %d ms", System.currentTimeMillis() - tt));

            Coolant_TimeStamp1 = Coolant_TimeStamp2;

            Intent intent = new Intent();
            intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
            intent.putExtra("coolantTemp", coolantTempCommand.getFormattedResult());
            intent.putExtra("coolantTempD", obdData.coolant);
            intent.setAction(OBD_BROADCAST_ACTION_COOLANT_TEMP_CHANGED);
            App.getInstance ().sendBroadcast(intent);

            //Log.d("OBDII-->processOBD_coolantTemp()", "coolantTemp: " + coolantTempCommand.getFormattedResult());
        }  catch ( NonNumericResponseException e5) {
            activeOther = false;
            disconnect();
            Log.d("OBDII-->processOBD_EngineRPM()", e5.toString());
        }
        catch ( StoppedException e6) {
            activeOther = false;
            Log.d("OBDII-->processOBD_coolantTemp()", e6.toString());
        }
        catch (UnableToConnectException | IOException e3) {
            activeOther = false;
            Log.d("processOBD_coolantTemp()", e3.toString());
            disconnect();
        } catch ( Exception e2) {
            activeOther = false;
            Log.d("processOBD_coolantTemp()", e2.toString());
        }
    }

    private void processOBD_CMVoltage() {
        if ( activeMAF ) return;
        if ( ! battery_show ) return;
        if ( App.GS.isReverseMode ) return;

        Voltage_TimeStamp2 = System.currentTimeMillis();
        long t = Voltage_TimeStamp2 - Voltage_TimeStamp1;
        if ( t < ( voltage_update_time * 1000L)) {return;}

        activeOther = true;
        try {
            long tt = System.currentTimeMillis();
            cmuVoltageCommand.run(socket.getInputStream(), socket.getOutputStream());
            obdData.voltage = cmuVoltageCommand.getVoltage();
            Log.d(TAG, String.format("Command Voltage :: %d ms", System.currentTimeMillis() - tt));

            Voltage_TimeStamp1 = Voltage_TimeStamp2;

            Intent intent = new Intent();
            intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
            intent.putExtra("cmuVoltage", cmuVoltageCommand.getFormattedResult());
            intent.putExtra("cmuVoltageD", obdData.voltage);
            intent.setAction(OBD_BROADCAST_ACTION_CMU_VOLTAGE_CHANGED);
            App.getInstance ().sendBroadcast(intent);
            //Log.d("OBDII-->processOBD_CMVoltage()", "cmuVoltage: " + cmuVoltageCommand.getFormattedResult());
        }  catch ( NonNumericResponseException e5) {
            activeOther = false;
            disconnect();
            Log.d("OBDII-->processOBD_EngineRPM()", e5.toString());
        }
        catch (UnableToConnectException | IOException e3) {
            activeOther = false;
            Log.d("OBDII-->processOBD_CMVoltage()", e3.toString());
            disconnect();
        } catch ( Exception e2) {
            activeOther = false;
            Log.d("OBDII-->processOBD_CMVoltage()", e2.toString());
        }
        activeOther = false;
    }

    public void processOBD_MAF() {
        //if ( activeOther ) return;
        try {

            // TODO: убрать костыль, когда найду нужный пид
            SetHeaders("7E0", "7E8", false);
            activeMAF = true;

            long tt = System.currentTimeMillis();
            MAFObdCommand.run(socket.getInputStream(), socket.getOutputStream());
            obdData.maf = MAFObdCommand.getMAF();
            Log.d(TAG, String.format("Command RPM :: %d ms", System.currentTimeMillis() - tt));

            MAF_TimeStamp2 = System.currentTimeMillis();
            long t = MAF_TimeStamp2 - MAF_TimeStamp1;
            MAF_TimeStamp1 = MAF_TimeStamp2;

            //Log.d("OBDII-->processOBD_MAF()", "MAF time: " + Float.toString( t / 1000f ) + " сек");


            oneTrip.calculateData(obdData.speed, obdData.maf, t);
            todayTrip.calculateData(obdData.speed, obdData.maf, t);
            totalTrip.calculateData(obdData.speed, obdData.maf, t);



            Intent intent = new Intent();
            intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
            intent.putExtra("sMAF", MAFObdCommand.getFormattedResult());
            intent.putExtra("dMAF", obdData.maf);
            intent.setAction(OBD_BROADCAST_ACTION_MAF_CHANGED);
            App.getInstance ().sendBroadcast(intent);
            //Log.d("OBDII-->processOBD_MAF()", "MAF: " + MAFObdCommand.getFormattedResult());
            activeMAF = false;

        }  catch ( NonNumericResponseException e5) {
            activeMAF = false;
            disconnect();
            Log.d("OBDII-->processOBD_EngineRPM()", e5.toString());
        }
        catch ( StoppedException e6) {
            activeOther = false;
            Log.d("OBDII-->processOBD_EngineRPM()", e6.toString());
        }
        catch (UnableToConnectException | IOException e3) {
            Log.d("OBDII-->processOBD_MAF()", e3.toString());
            activeMAF = false;
            disconnect();
        } catch ( Exception e2) {
            Log.d("OBDII-->processOBD_MAF()", e2.toString());
            activeMAF = false;
        } finally {
            activeMAF = false;
        }


    }

    public ArrayList<Integer> request_CAN_ECU(String PID, String CanAddress, String SenderAddress, boolean flowControl) {
        ArrayList<Integer> buffer = null;
        if ( activeMAF ) return buffer;
        activeOther = true;
        String function = "processCAN_" + PID + "__" + CanAddress + "()";

        try {
              //SetHeaders(ReceiverAddress, SenderAddress, flowControl);
            long tt = System.currentTimeMillis();
            CanObdCommand cmd =  new CanObdCommand(PID);
            cmd.run(socket.getInputStream(), socket.getOutputStream());
            Log.d(TAG, String.format("Command %s %s :: %d ms", CanAddress, PID, System.currentTimeMillis() - tt));

            buffer = cmd.getBuffer();

            //Log.d("OBDII-->" + function, "PID: " + PID + ", H: " + ReceiverAddress + ": " + cmd.getFormattedResult());
            activeOther = false;

        }  catch ( NonNumericResponseException e5) {
            activeOther = false;
            //disconnect();
            try {
                new ObdResetCommand().run(socket.getInputStream(), socket.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.d("OBDII-->" + function, e5.toString());
        }
        catch ( StoppedException e6) {
            activeOther = false;
            Log.d("OBDII-->" + function, e6.toString());
        }
        catch ( NoDataException e) {
            activeOther = false;
            Log.d("OBDII-->" + function, e.toString());
        } catch (UnableToConnectException e3) {
            activeOther = false;
            Log.d("OBDII-->" + function, e3.toString());
            disconnect();
        } catch (IOException e4) {
            activeOther = false;
            Log.d("OBDII-->" + function, e4.toString());
            disconnect();
        } catch ( Exception e2) {
            activeOther = false;
            Log.d("OBDII-->" + function, e2.toString());
        }
        activeOther = false;
        return buffer;
    }

    private void notify_disconnect() {
        isConnected = false;
        //App.obd.isConnected = false;
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

    private void SendBroadcastAction(String action, String key, int value) {
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

    public void saveFuelTank() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(App.getInstance());
        prefs.edit().putFloat("kaierutils_fuel_tank", obdData.fuel_tank).apply();
        //prefs.edit().putFloat("kaierutils_fuel_usage", obdData.fuel_usage2).apply();

    }

    public void loadFuelTank() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences (App.getInstance ());
        obdData.fuel_tank = prefs.getFloat("kaierutils_fuel_tank", 60f);
        //obdData.fuel_usage2 = prefs.getFloat("kaierutils_fuel_usage", 0f);
    }

    // заправили полный бак
    public void setFullTank() {

        oneTrip.updateData( true, obdData.fuel_tank, obdData.fuel_tank);
        todayTrip.updateData( true, obdData.fuel_tank, obdData.fuel_tank);
        totalTrip.updateData( true, obdData.fuel_tank, obdData.fuel_tank);
        saveFuelTank();
    }

    // заправили не полный бак / коррекция значений
    public void setCustomTank(float tank_volume, float fuel_remain) {
        obdData.fuel_tank = tank_volume;

        oneTrip.updateData( false, fuel_remain, tank_volume);
        todayTrip.updateData( false, fuel_remain, tank_volume);
        totalTrip.updateData( false, fuel_remain, tank_volume);
        saveFuelTank();
    }

    private void SetHeaders(String canAddr, String txAddr, boolean flowControl) {
        if (localDebug) return;
        if ( activeMAF ) return;
        activeOther = true;
        try {
            long tt = System.currentTimeMillis();
            new SelectHeaderObdCommand("ATSH " + canAddr).run(socket.getInputStream(), socket.getOutputStream());
            if (flowControl) {
                new SelectHeaderObdCommand("ATFCSH " + canAddr).run(socket.getInputStream(), socket.getOutputStream());
                new SelectHeaderObdCommand("ATFCSD 30080A").run(socket.getInputStream(), socket.getOutputStream());
                new SelectHeaderObdCommand("ATFCSM 1").run(socket.getInputStream(), socket.getOutputStream());
            }
            new SelectHeaderObdCommand("ATCRA " + txAddr).run(socket.getInputStream(), socket.getOutputStream());
            Log.d(TAG, String.format("SetHeader %s :: %d ms", flowControl ? "with flow control" : "", System.currentTimeMillis() - tt));
            activeOther = false;
        }
        catch (MisunderstoodCommandException e) {
            Log.e("OBD2", e.toString());
        }
        catch (NonNumericResponseException | StoppedException | NoDataException e) {
            activeOther = false;
            Log.d("OBDII-->SetHeaders()", e.toString());
        }
        catch (InterruptedException | IOException e4) {
            activeOther = false;
            e4.printStackTrace();
        } finally {
            activeOther = false;
        }
        activeOther = false;
    }

    private void request_MMC_ECU_ENGINE(){
        if ( activeMAF ) return;
        if ( App.GS.isReverseMode ) return;
        // set ENGINE ECU addresses
        SetHeaders("7E0", "7E8", false);

        processOBD_EngineRPM();         // RPM:             01 0C
        processOBD_Speed();             // Speed:           01 0D
        processOBD_coolantTemp();       // Coolant:         01 05
        processOBD_CMVoltage();         // Voltage:         01 42

        ArrayList<Integer> buffer = null;
        buffer = request_CAN_ECU("211D", "7E0", "7E8", false);
        sendObdMessage("211D", "7E0", buffer);
        buffer = request_CAN_ECU("211E", "7E0", "7E8", false);
        sendObdMessage("211E", "7E0", buffer);
        // TODO брать данные для:
        // RPM (01 0C)
        // Speed (01 0D)            Speed	2101	B*2
        // Coolant (01 05)          Coolant	2102	B-40
        // Voltage (01 42)          Volts	2101	A*18.68/255
        // MAF (01 10)

    }

    private void request_MMC_ECU_CVT(){
        if ( activeMAF ) return;
        if ( App.GS.isReverseMode ) return;
        // set CVT/AT ECU addresses
        ArrayList<Integer> buffer = null;
        if ( canMmcData.can_mmc_cvt_temp_show || canMmcData.can_mmc_cvt_degr_show) {


            canMmcData.CVT_oil_temp_TimeStamp2 = System.currentTimeMillis();
            canMmcData.CVT_oil_degr_TimeStamp2 = System.currentTimeMillis();
            long t_temp = canMmcData.CVT_oil_temp_TimeStamp2 - canMmcData.CVT_oil_temp_TimeStamp1;
            long t_degr = canMmcData.CVT_oil_degr_TimeStamp2 - canMmcData.CVT_oil_degr_TimeStamp1;

            if (
                    ( t_degr < (canMmcData.can_mmc_cvt_degradation_update_time * 1000L) )
                    &&
                    ( t_temp < (canMmcData.can_mmc_cvt_temp_update_time * 1000L))
               )
            {
                // пропускаем, время не вышло ни у одного параметра
                return;
            }

            activeOther = true;
            SetHeaders("7E1", "7E9", true);
            if ( canMmcData.can_mmc_cvt_temp_show ) {
                if ( t_temp < (canMmcData.can_mmc_cvt_temp_update_time * 1000L) ) return; //время не вышло
                buffer = request_CAN_ECU("2103", "7E1", "7E9", true); // // cvt_temp_count
                sendObdMessage("2103", "7E1", buffer);
                canMmcData.CVT_oil_temp_TimeStamp1 = canMmcData.CVT_oil_temp_TimeStamp2;
            }

            // request_CAN_ECU("2107", "7E1", "7E9", false, OBD_BROADCAST_ACTION_ECU_CVT_CHANGED);  // selector position

            // TODO: можно выполнять не часто, раз в 10 сек вполне достаточно или даже реже
            if ( canMmcData.can_mmc_cvt_degr_show ) {
                if ( t_temp < (canMmcData.can_mmc_cvt_degradation_update_time * 1000L) ) return; //время не вышло
                buffer = request_CAN_ECU("2110", "7E1", "7E9", true); // cvt_oil_degradation
                sendObdMessage("2110", "7E1", buffer);
                canMmcData.CVT_oil_degr_TimeStamp1 = canMmcData.CVT_oil_degr_TimeStamp2;
            }
            activeOther = false;
        }
    }

    private void request_MMC_ECU_COMBINATION_METER(){
        if ( activeMAF ) return;
        if ( App.GS.isReverseMode ) return;

        if ( App.obd.MMC_CAN && App.obd.canMmcData.can_mmc_fuel_remain_show ) {
            App.obd.canMmcData.FuelLevel_TimeStamp2 = System.currentTimeMillis();
            long t = App.obd.canMmcData.FuelLevel_TimeStamp2- App.obd.canMmcData.FuelLevel_TimeStamp1;

            if ( t < (canMmcData.can_mmc_fuel_remain_update_time * 1000L) ) { return; }

            activeOther = true;
            ArrayList<Integer> buffer = null;
            // set COMBINE METER ECU addresses
            SetHeaders("6A0", "514", false);
            buffer = request_CAN_ECU("21A3", "6A0", "514", false);
            sendObdMessage("21A3", "6A0", buffer);
            canMmcData.FuelLevel_TimeStamp1 = canMmcData.FuelLevel_TimeStamp2;
            activeOther = false;
        }
    }

    private void request_MMC_ECU_AIR_COND(){
        if ( activeMAF ) return;
        if ( App.GS.isReverseMode ) return;

        ArrayList<Integer> buffer = null;
        if (App.obd.canMmcData.can_mmc_ac_data_show) {
            activeOther = true;
            SetHeaders("688", "511", false);
            // внешняя температура
            buffer = request_CAN_ECU("2111", "688", "511", false);
            sendObdMessage("2111", "688", buffer);
            //request_CAN_ECU("2123", "688", "511", true, OBD_BROADCAST_ACTION_ECU_AIRCOND_CHANGED);
            // положения крутилок
            buffer = request_CAN_ECU("2160", "688", "511", false);
            sendObdMessage("2160", "688", buffer);
            // состояния индикаторов
            buffer = request_CAN_ECU("2161", "688", "511", false);
            sendObdMessage("2161", "688", buffer);
            activeOther = false;
        }
    }

    private void request_MMC_ECU_AWC(){
        if ( activeMAF ) return;
        ArrayList<Integer> buffer = null;
        SetHeaders( "7B6", "7B7", true);
        buffer = request_CAN_ECU("2130", "7B6", "7B7", true);
        sendObdMessage("2130", "7B6", buffer);
    }

    public void process_handler(Message message) {
         switch ( message.what ) {
             // ENGINE
             case MESSAGE_OBD_CAN_ENGINE_FAN_STATE:
                 CanMmcData.State fanState = CanMmcData.State.values()[ message.arg1 ];
                 SendBroadcastAction(OBD_BROADCAST_ACTION_ENGINE_FAN_STATE_CHANGED, "can_mmc_engine_fan_state", fanState.ordinal());
                 break;

             // CVT
             case MESSAGE_OBD_CAN_CVT_OIL_DEGR:
                 SendBroadcastAction(OBD_BROADCAST_ACTION_ECU_CVT_OIL_DEGR_CHANGED, "can_mmc_cvt_degradation_level", message.arg1);
                 break;
             case MESSAGE_OBD_CAN_CVT_OIL_TEMP:
                 SendBroadcastAction(OBD_BROADCAST_ACTION_ECU_CVT_OIL_TEMP_CHANGED, "can_mmc_cvt_temp", message.arg1);
                 break;

             // AIR CONDITIONER
             case MESSAGE_OBD_CAN_AIR_COND_EXTERNAL_TEMPERATURE:
                 SendBroadcastAction(OBD_BROADCAST_ACTION_AC_EXT_TEMP_CHANGED, "air_cond_external_temp", message.arg1);
                 break;
             case MESSAGE_OBD_CAN_AIR_COND_TEMPERATURE:
                 String s = (String) message.obj;
                 SendBroadcastAction(OBD_BROADCAST_ACTION_AC_TEMP_CHANGED, "air_cond_set_temperature", s);
                 break;
             case MESSAGE_OBD_CAN_AIR_COND_FAN_MODE:
                 ClimateData.FanMode fanMode = ClimateData.FanMode.values()[ message.arg1 ];
                 SendBroadcastAction(OBD_BROADCAST_ACTION_AC_FAN_MODE_CHANGED, "air_cond_fan_mode", fanMode.ordinal());
                 break;
             case MESSAGE_OBD_CAN_AIR_COND_BLOW_MODE:
                 ClimateData.BlowMode blowMode = ClimateData.BlowMode.values()[ message.arg1 ];
                 SendBroadcastAction(OBD_BROADCAST_ACTION_AC_BLOW_MODE_CHANGED, "air_cond_blow_mode", blowMode.ordinal());
                 break;
             case MESSAGE_OBD_CAN_AIR_COND_BLOW_DIRECTION:
                 ClimateData.BlowDirection blowDirection = ClimateData.BlowDirection.values()[ message.arg1 ];
                 SendBroadcastAction(OBD_BROADCAST_ACTION_AC_BLOW_DIRECTION_CHANGED, "air_cond_blow_direction_position", blowDirection.ordinal());
                 break;
             case MESSAGE_OBD_CAN_AIR_COND_FAN_SPEED:
                 ClimateData.FanSpeed fanSpeed = ClimateData.FanSpeed.values()[ message.arg1 ];
                 SendBroadcastAction(OBD_BROADCAST_ACTION_AC_FAN_SPEED_CHANGED, "air_cond_fan_speed_position", fanSpeed.ordinal());
                 break;
             case MESSAGE_OBD_CAN_AIR_COND_STATE:
                 ClimateData.State ac_state = ClimateData.State.values()[ message.arg1 ];
                 SendBroadcastAction(OBD_BROADCAST_ACTION_AC_STATE_CHANGED, "air_cond_state", ac_state.ordinal() );
                 break;
             case MESSAGE_OBD_CAN_AIR_COND_RECIRCULATION_STATE:
                 ClimateData.State recirculation_state = ClimateData.State.values()[ message.arg1 ];
                 SendBroadcastAction(OBD_BROADCAST_ACTION_AC_RECIRCULATION_CHANGED, "air_cond_recirculation_state", recirculation_state.ordinal());
                 break;
             case MESSAGE_OBD_CAN_AIR_COND_DEFOGGER_STATE:
                 ClimateData.State defogger_state = ClimateData.State.values()[ message.arg1 ];
                 SendBroadcastAction(OBD_BROADCAST_ACTION_AC_DEFOGGER_CHANGED, "air_cond_defogger_state", defogger_state.ordinal());
                 break;
             case MESSAGE_OBD_CAN_COMBINE_METER_FUEL_TANK:
                 SendBroadcastAction(OBD_BROADCAST_ACTION_ECU_COMBINEMETER_FUEL_TANK_CHANGED, "combine_meter_fuel_level", message.arg1);
                 break;
             case MESSAGE_OBD_CAN_PARKING_SENSORS:
                 if (Objects.nonNull(message.obj)) {
                     ArrayList<Integer> buffer = (ArrayList<Integer>) message.obj;

                     Intent intent = new Intent();
                     intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                     intent.setAction(OBD_BROADCAST_ACTION_PARKING_SENSORS_CHANGED);
                     intent.putExtra("parking_sensors", buffer);
                     //intent.putExtra("parking_sensors_rear_left_inner", rear_inner_left);
                     //intent.putExtra("parking_sensors_rear_left_outer", rear_outer_left);
                     //intent.putExtra("parking_sensors_rear_right_inner", rear_inner_right);
                     //intent.putExtra("parking_sensors_rear_right_outer", rear_outer_right);
                     App.getInstance().sendBroadcast(intent);
                 }
                 break;

             default:
                 break;
         }
    }

    public void sendObdMessage(String PID, String Addr, ArrayList<Integer> buffer) {
        if ( buffer == null ) return;

        Message msg = new Message();
        // BLOCK: INGINE
        if ( Addr.equalsIgnoreCase("7E0")) {
            // process engine
            if ( PID.equalsIgnoreCase("2101")) {
                //OBDCalculations.sendOBD_CVT_Temp(MESSAGE_OBD_CAN_CVT_OIL_TEMP, mHandler, buffer);
            }
            else if ( PID.equalsIgnoreCase("2102")) {
                //OBDCalculations.sendOBD_CVT_Temp(MESSAGE_OBD_CAN_CVT_OIL_TEMP, mHandler, buffer);
            }
            else if ( PID.equalsIgnoreCase("211D")) {
                OBDCalculations.sendOBD_ENGINE_Fan_State(MESSAGE_OBD_CAN_ENGINE_FAN_STATE, mHandler, buffer);
            }
            else if ( PID.equalsIgnoreCase("211E")) {
                //OBDCalculations.sendOBD_CVT_Temp(MESSAGE_OBD_CAN_CVT_OIL_TEMP, mHandler, buffer);
            }
        }
        // BLOCK: CVT
        else if (Addr.equalsIgnoreCase("7E1")) {
            //process CVT
            if ( PID.equalsIgnoreCase("2103")) {
                // CVT Temp Count
                OBDCalculations.sendOBD_CVT_Temp(MESSAGE_OBD_CAN_CVT_OIL_TEMP, mHandler, buffer);
            } else if ( PID.equalsIgnoreCase("2110")) {
                // CVT oil degradation 2110
                OBDCalculations.sendOBD_CVT_Degradation(MESSAGE_OBD_CAN_CVT_OIL_DEGR,  mHandler, buffer);
            }
        }
        // BLOCK: AIR CONDITION
        else if ( Addr.equalsIgnoreCase("688")) {
            // process Air Condition
            if ( PID.equalsIgnoreCase( "2111") ) {
                // external value
                OBDCalculations.sendOBD_AC_ExtTemp(MESSAGE_OBD_CAN_AIR_COND_EXTERNAL_TEMPERATURE, mHandler, buffer);
            }
            else if ( PID.equalsIgnoreCase("2160")) {
               // buttons & knob positions
                OBDCalculations.sendOBD_AC_Fan_Mode(MESSAGE_OBD_CAN_AIR_COND_FAN_MODE, mHandler, buffer);
                OBDCalculations.sendOBD_AC_Blow_Mode(MESSAGE_OBD_CAN_AIR_COND_BLOW_MODE, mHandler, buffer);
                OBDCalculations.sendOBD_AC_Temp(MESSAGE_OBD_CAN_AIR_COND_TEMPERATURE, mHandler, buffer);
            }
            else if ( PID.equalsIgnoreCase("2161")) {
              // ac indications
                OBDCalculations.sendOBD_AC_Blow_direction(MESSAGE_OBD_CAN_AIR_COND_BLOW_DIRECTION,  mHandler, buffer);
                OBDCalculations.sendOBD_AC_Fan_Speed(MESSAGE_OBD_CAN_AIR_COND_FAN_SPEED, mHandler, buffer);
                OBDCalculations.sendOBD_AC_State(MESSAGE_OBD_CAN_AIR_COND_STATE, mHandler, buffer);
                OBDCalculations.sendOBD_AC_Recirculation_State(MESSAGE_OBD_CAN_AIR_COND_RECIRCULATION_STATE, mHandler, buffer);
                OBDCalculations.sendOBD_AC_Defogger_State(MESSAGE_OBD_CAN_AIR_COND_DEFOGGER_STATE, mHandler, buffer);
            }
        }
        // BLOCK: COMBINATION METER
        else if (Addr.equalsIgnoreCase("6A0")) {
            // process Combination Meter
            OBDCalculations.sendOBD_CombineMeter_FuelLevel(MESSAGE_OBD_CAN_COMBINE_METER_FUEL_TANK, mHandler, buffer);

        }
        // BLOCK: PARKING SENSORS
        else if ( Addr.equalsIgnoreCase("763")) {
            OBDCalculations.sendOBD_ParkingSensors(MESSAGE_OBD_CAN_PARKING_SENSORS, mHandler, buffer);
        }
    }

    public void process_parking_sensors() {
        request_MMC_ECU_PARKING_SENSORS();
    }

    private void request_MMC_ECU_PARKING_SENSORS(){
        Log.d("request_MMC_ECU_PARKING_SENSORS: --->", "start function...");
        if ( activeOther ) {
            Log.d("request_MMC_ECU_PARKING_SENSORS: --->", "exit function...   activeOther...");
            return;
        }
        if ( activeMAF) {
            Log.d("request_MMC_ECU_PARKING_SENSORS: --->", "exit function...   activeMAF...");
            return;
        }
        if ( !App.GS.isReverseMode ) {
            Log.d("request_MMC_ECU_PARKING_SENSORS: --->", "exit function...   not reverse mode...");
            return;
        }
        if (App.obd.canMmcData.can_mmc_parking_data_show) {
            ArrayList<Integer> buffer = null;

            Log.d("request_MMC_ECU_PARKING_SENSORS: --->", "SetHeaders...   763, 764");
            SetHeaders("763", "764", false);

            Log.d("request_MMC_ECU_PARKING_SENSORS: --->", "send command...   2101");
            buffer = request_CAN_ECU("2101", "763", "764", false);

            Log.d("request_MMC_ECU_PARKING_SENSORS: --->", "buffer:  " + buffer.toString());
            sendObdMessage("2101", "763", buffer);
        }
    }

    public class OBDData {
        public float speed;
        public float rpm;
        public float coolant;
        public float maf;
        public float voltage;
        public float fuel_tank; // читаем из преференсов


        public OBDData() {
            speed = 0;
            rpm = 0;
            coolant = 0;
            maf = 0;
            voltage = 0;
            fuel_tank = 0;
        }
    }
 }
