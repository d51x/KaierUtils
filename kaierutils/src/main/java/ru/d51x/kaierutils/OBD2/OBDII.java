package ru.d51x.kaierutils.OBD2;

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
import static ru.d51x.kaierutils.OBD2.ObdConstants.BLOCK_688;
import static ru.d51x.kaierutils.OBD2.ObdConstants.BLOCK_688_PID_2110;
import static ru.d51x.kaierutils.OBD2.ObdConstants.BLOCK_688_PID_2160;
import static ru.d51x.kaierutils.OBD2.ObdConstants.BLOCK_688_PID_2161;
import static ru.d51x.kaierutils.OBD2.ObdConstants.BLOCK_6A0;
import static ru.d51x.kaierutils.OBD2.ObdConstants.BLOCK_6A0_PID_21A1;
import static ru.d51x.kaierutils.OBD2.ObdConstants.BLOCK_6A0_PID_21A3;
import static ru.d51x.kaierutils.OBD2.ObdConstants.BLOCK_6A0_PID_21AD;
import static ru.d51x.kaierutils.OBD2.ObdConstants.BLOCK_6A0_PID_21AE;
import static ru.d51x.kaierutils.OBD2.ObdConstants.BLOCK_6A0_PID_21BC;
import static ru.d51x.kaierutils.OBD2.ObdConstants.BLOCK_763;
import static ru.d51x.kaierutils.OBD2.ObdConstants.BLOCK_763_PID_2101;
import static ru.d51x.kaierutils.OBD2.ObdConstants.BLOCK_7B6;
import static ru.d51x.kaierutils.OBD2.ObdConstants.BLOCK_7E0;
import static ru.d51x.kaierutils.OBD2.ObdConstants.BLOCK_7E0_PID_2101;
import static ru.d51x.kaierutils.OBD2.ObdConstants.BLOCK_7E0_PID_2102;
import static ru.d51x.kaierutils.OBD2.ObdConstants.BLOCK_7E0_PID_211D;
import static ru.d51x.kaierutils.OBD2.ObdConstants.BLOCK_7E1;
import static ru.d51x.kaierutils.OBD2.ObdConstants.BLOCK_7E1_PID_2103;
import static ru.d51x.kaierutils.OBD2.ObdConstants.BLOCK_7E1_PID_2110;
import static ru.d51x.kaierutils.OBD2.ObdConstants.BLOCK_RX_511;
import static ru.d51x.kaierutils.OBD2.ObdConstants.BLOCK_RX_514;
import static ru.d51x.kaierutils.OBD2.ObdConstants.BLOCK_RX_7E9;
import static ru.d51x.kaierutils.OBD2.ObdConstants.KEY_OBD_PARKING_2101;
import static ru.d51x.kaierutils.OBD2.ObdConstants.MESSAGE_OBD_CLIMATE_2110;
import static ru.d51x.kaierutils.OBD2.ObdConstants.MESSAGE_OBD_CLIMATE_2111;
import static ru.d51x.kaierutils.OBD2.ObdConstants.MESSAGE_OBD_CLIMATE_2113;
import static ru.d51x.kaierutils.OBD2.ObdConstants.MESSAGE_OBD_CLIMATE_2132;
import static ru.d51x.kaierutils.OBD2.ObdConstants.MESSAGE_OBD_CLIMATE_2160;
import static ru.d51x.kaierutils.OBD2.ObdConstants.MESSAGE_OBD_CLIMATE_2161;
import static ru.d51x.kaierutils.OBD2.ObdConstants.MESSAGE_OBD_CLIMATE_2180;
import static ru.d51x.kaierutils.OBD2.ObdConstants.MESSAGE_OBD_COMBINE_METER_21A1;
import static ru.d51x.kaierutils.OBD2.ObdConstants.MESSAGE_OBD_COMBINE_METER_21A2;
import static ru.d51x.kaierutils.OBD2.ObdConstants.MESSAGE_OBD_COMBINE_METER_21A3;
import static ru.d51x.kaierutils.OBD2.ObdConstants.MESSAGE_OBD_COMBINE_METER_21A6;
import static ru.d51x.kaierutils.OBD2.ObdConstants.MESSAGE_OBD_COMBINE_METER_21A8;
import static ru.d51x.kaierutils.OBD2.ObdConstants.MESSAGE_OBD_COMBINE_METER_21AD;
import static ru.d51x.kaierutils.OBD2.ObdConstants.MESSAGE_OBD_COMBINE_METER_21AE;
import static ru.d51x.kaierutils.OBD2.ObdConstants.MESSAGE_OBD_COMBINE_METER_21AF;
import static ru.d51x.kaierutils.OBD2.ObdConstants.MESSAGE_OBD_COMBINE_METER_21BC;
import static ru.d51x.kaierutils.OBD2.ObdConstants.MESSAGE_OBD_CVT_2103;
import static ru.d51x.kaierutils.OBD2.ObdConstants.MESSAGE_OBD_CVT_2110;
import static ru.d51x.kaierutils.OBD2.ObdConstants.MESSAGE_OBD_ENGINE_2101;
import static ru.d51x.kaierutils.OBD2.ObdConstants.MESSAGE_OBD_ENGINE_2102;
import static ru.d51x.kaierutils.OBD2.ObdConstants.MESSAGE_OBD_ENGINE_2103;
import static ru.d51x.kaierutils.OBD2.ObdConstants.MESSAGE_OBD_ENGINE_211D;
import static ru.d51x.kaierutils.OBD2.ObdConstants.MESSAGE_OBD_ENGINE_211E;
import static ru.d51x.kaierutils.OBD2.ObdConstants.MESSAGE_OBD_PARKING_SENSORS;
import static ru.d51x.kaierutils.OBD2.ObdConstants.OBD_BROADCAST_ACTION_CMU_VOLTAGE_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.OBD_BROADCAST_ACTION_COOLANT_TEMP_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.OBD_BROADCAST_ACTION_ENGINE_RPM_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.OBD_BROADCAST_ACTION_MAF_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.OBD_BROADCAST_ACTION_SPEED_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.OBD_BROADCAST_ACTION_STATUS_CHANGED;
import static ru.d51x.kaierutils.utils.MessageUtils.SendBroadcastAction;

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
import ru.d51x.kaierutils.Data.CombineMeterData;
import ru.d51x.kaierutils.Data.CvtData;
import ru.d51x.kaierutils.Data.EngineData;
import ru.d51x.kaierutils.Data.Fuel;
import ru.d51x.kaierutils.Data.ObdData;
import ru.d51x.kaierutils.Data.TripData;

/**
 */
public class OBDII  {
    public static final boolean localDebug = BuildConfig.SIMULATE_OBD;

    public static final String TAG = "OBD2";


    public boolean isConnected;
    public boolean useOBD;
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

    public ObdData obdData;
    public boolean activeMAF = false;    // процесс чтения MAF
    public boolean activeOther = true;   // процесс чтения других PID
    public boolean readExtendCvt = false;
    public boolean readExtendMeter = false;
    public boolean readExtendClimate = false;
    public boolean newObdProcess = true;
    public boolean MMC_CAN;
    public boolean newDistanceCalc = true;
    public TripData totalTrip;
    public TripData oneTrip;
    public TripData todayTrip;

    public CanMmcData can;
    private Context mContext;
    private String deviceName;
    private String deviceAddress;
    private Handler mHandler = new Handler();
    private BluetoothSocket socket;
    private long MAF_TimeStamp1, MAF_TimeStamp2;
    private long Voltage_TimeStamp1, Voltage_TimeStamp2;
    private long Coolant_TimeStamp1, Coolant_TimeStamp2;
    public Fuel fuel = new Fuel();

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


        obdData = new ObdData();
        totalTrip = new TripData("total", true);
        oneTrip = new TripData("trip", false);
        todayTrip = new TripData("today", true);

        can = new CanMmcData(mContext);
        fuel.load();
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
                processHandler(message);
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
        if (!btAdapter.isEnabled()) {
            btAdapter.enable();
        }
        BluetoothDevice device = btAdapter.getRemoteDevice(deviceAddress);
        UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
        try {
            try {
                if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.BLUETOOTH_SCAN) == PackageManager.PERMISSION_GRANTED) {
                    btAdapter.cancelDiscovery();
                }
            } catch (Exception e11) {
                Log.e(TAG, "OBD2->connect(): " + e11.getMessage());
                e11.printStackTrace();
                return false;
            }
            socket = device.createInsecureRfcommSocketToServiceRecord(uuid);
            //
            try {
                if (socket != null) {
                    Thread.sleep(1000);
                    Log.d(TAG, "OBD2->connect(), try to connect to socket ");
                    socket.connect();
                    Log.d(TAG, "OBD2->connect(), sleep after connect, waiting....");
                    Thread.sleep(1000);
                    boolean res = init();
                    if (!res) {
                        new ObdResetCommand().run(socket.getInputStream(), socket.getOutputStream());
                        // коннект с сокетом есть, надо остановить все отправки и сделать резет и новую инициализацию
//                        socket.close();
//                        socket = null;
                    }
                    SendBroadcastAction(OBD_BROADCAST_ACTION_STATUS_CHANGED, "Status", res);
                    return res;
                } else {
                    Log.d(TAG, "OBD2->connect(), socket is NULL....");
                    return false;
                }
            } catch (IOException e) {
                // Unable to connect; close the socket and get out
                try {
                    socket.close();
                } catch (IOException closeException) {
                    Log.e(TAG, "Fail to close connection");
                    btAdapter.disable();
                }
                // java.io.IOException: Connection reset by peer
                e.printStackTrace();
                return false;
            }
            catch (Exception e) {
                //socket = null;
                //Log.e(TAG, e.toString());
                e.printStackTrace();
                return false;
            }


       } catch (Exception e) {
            //socket = null;
            Log.e(TAG, "OBDII-->connect(): " + e.toString());
            e.printStackTrace();
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
            Log.e(TAG, "OBDII-->disconnect(): " + e.toString());
            e.printStackTrace();
        }
        notify_disconnect();
    }

    public boolean init() {
        boolean res = true;
	    Log.d("OBD2->init()", "_");
        try {
            //new ObdResetCommand().run(socket.getInputStream(), socket.getOutputStream());
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
            res = res && (result.equalsIgnoreCase("OK")); // тут можем получить что то другое, но коннект с сокетом есть, рвать не надо
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
        }
        catch (IOException e) {
            Log.e(TAG, e.toString());
            e.printStackTrace();
            return false;
        }
        catch (NullPointerException e) {
            Log.e(TAG, e.toString());
            e.printStackTrace();
            //new ObdResetCommand().run(socket.getInputStream(), socket.getOutputStream());
            return false;
        }
        catch (Exception e2) {
            Log.e(TAG, e2.toString()); // socket = null;
            e2.printStackTrace();
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

     public void newProcessAllData() {
        // новый тип мониторинга без таймеров, просто сплошняком
        // блок 1 - примерно 1000-1200 мсек  без расширенных данных
        processOBD_MAF();
        requestMmcEngine(false, false);
        requestMmcAirCond(App.obd.readExtendClimate);
        // блок 2 - примерно 800-900 мсек без расширенных данных
        processOBD_MAF();
        requestMmcCvt(App.obd.readExtendCvt);
        // блок 3 - примерно 800 - 900 мсек без расширенных данных
        processOBD_MAF();
        requestMmcCombineMeter(App.obd.readExtendMeter);
     }

    public void processData() {
        if ( !isConnected ) return;
        //Log.d("OBDII-->processData()", "_");

        if ( MMC_CAN )  {
            // переходим на режим работы MMC CAN и пиды 21хх
            requestMmcEngine(false, false);
            requestMmcCvt(App.obd.readExtendCvt);
            requestMmcCombineMeter(App.obd.readExtendMeter);
            requestMmcAirCond(App.obd.readExtendClimate);
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
            Log.e("OBDII-->processOBD_EngineRPM()", e5.toString());
            e5.printStackTrace();
        }
        catch (UnableToConnectException | IOException e3) {
            activeOther = false;
            Log.e("OBDII-->processOBD_EngineRPM()", e3.toString());
            e3.printStackTrace();
            disconnect();
        } catch ( Exception e2) {
            activeOther = false;
            Log.e("OBDII-->processOBD_EngineRPM()", e2.toString());
            e2.printStackTrace();
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
            Log.e("OBDII-->processOBD_Speed()", e5.toString());
            e5.printStackTrace();
        }
        catch ( StoppedException e6) {
            activeOther = false;
            Log.e("OBDII-->processOBD_Speed()", e6.toString());
            e6.printStackTrace();
        }
        catch (UnableToConnectException | IOException e3) {
            activeOther = false;
            Log.e("processOBD_Speed()", e3.toString());
            e3.printStackTrace();
            disconnect();
        } catch ( Exception e2) {
            activeOther = false;
            Log.e("processOBD_Speed()", e2.toString());
            e2.printStackTrace();
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
            Log.e("OBDII-->processOBD_coolantTemp()", e5.toString());
            e5.printStackTrace();
        }
        catch ( StoppedException e6) {
            activeOther = false;
            Log.e("OBDII-->processOBD_coolantTemp()", e6.toString());
            e6.printStackTrace();
        }
        catch (UnableToConnectException | IOException e3) {
            activeOther = false;
            Log.e("processOBD_coolantTemp()", e3.toString());
            e3.printStackTrace();
            disconnect();
        } catch ( Exception e2) {
            activeOther = false;
            Log.e("processOBD_coolantTemp()", e2.toString());
            e2.printStackTrace();
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
            Log.e("OBDII-->processOBD_CMVoltage()", e5.toString());
            e5.printStackTrace();
        }
        catch (UnableToConnectException | IOException e3) {
            activeOther = false;
            Log.e("OBDII-->processOBD_CMVoltage()", e3.toString());
            e3.printStackTrace();
            disconnect();
        } catch ( Exception e2) {
            activeOther = false;
            Log.e("OBDII-->processOBD_CMVoltage()", e2.toString());
            e2.printStackTrace();
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
            Log.d(TAG, String.format("Command MAF :: %d ms", System.currentTimeMillis() - tt));

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
            Log.e("OBDII-->processOBD_MAF()", e5.toString());
            e5.printStackTrace();
        }
        catch ( StoppedException e6) {
            activeOther = false;
            Log.e("OBDII-->processOBD_MAF()", e6.toString());
            e6.printStackTrace();
        }
        catch (UnableToConnectException | IOException e3) {
            Log.e("OBDII-->processOBD_MAF()", e3.toString());
            e3.printStackTrace();
            activeMAF = false;
            disconnect();
        } catch ( Exception e2) {
            Log.e("OBDII-->processOBD_MAF()", e2.toString());
            e2.printStackTrace();
            activeMAF = false;
        } finally {
            activeMAF = false;
        }


    }

    private ArrayList<Integer> runObdCommand(String PID, BluetoothSocket sock) {
        try {
            CanObdCommand cmd = new CanObdCommand(PID);
            cmd.run(sock.getInputStream(), sock.getOutputStream());
            return cmd.getBuffer();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Integer> requestCanEcu(String PID, String CanAddress /*, String SenderAddress, boolean flowControl*/) {
        ArrayList<Integer> buffer = null;
        if ( activeMAF ) return buffer;
        activeOther = true;

        try {
            buffer = runObdCommand(PID, socket);
            activeOther = false;

        }  catch ( NonNumericResponseException e5) {
            activeOther = false;
            //disconnect();
            try {
                new ObdResetCommand().run(socket.getInputStream(), socket.getOutputStream());
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
            Log.e(TAG, e5.toString());
            e5.printStackTrace();
        }
        catch (StoppedException | NoDataException e6) {
            activeOther = false;
            Log.e(TAG, e6.toString());
            e6.printStackTrace();
        }
        catch (UnableToConnectException e3) {
            activeOther = false;
            Log.e(TAG, e3.toString());
            e3.printStackTrace();
            disconnect();
        } catch (Exception e2) {
            activeOther = false;
            Log.e(TAG, e2.toString());
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

    // заправили полный бак
    public void setFullTank() {

        oneTrip.updateData( true, obdData.fuelTankCapacity, obdData.fuelTankCapacity);
        todayTrip.updateData( true, obdData.fuelTankCapacity, obdData.fuelTankCapacity);
        totalTrip.updateData( true, obdData.fuelTankCapacity, obdData.fuelTankCapacity);
        fuel.save();
    }

    // заправили не полный бак / коррекция значений
    public void setCustomTank(float tank_volume, float fuel_remain) {
        obdData.fuelTankCapacity = tank_volume;

        oneTrip.updateData( false, fuel_remain, tank_volume);
        todayTrip.updateData( false, fuel_remain, tank_volume);
        totalTrip.updateData( false, fuel_remain, tank_volume);
        fuel.save();
    }

    private void SetHeaders(String canAddr, String txAddr, boolean flowControl) {
        if (localDebug) return;
        if ( activeMAF ) return;
        activeOther = true;
        try {
            long tt = System.currentTimeMillis();
            new SelectHeaderObdCommand("ATSH " + canAddr).run(socket.getInputStream(), socket.getOutputStream());

            if (flowControl) {
                //String str = "ATFCSH" + canAddr + "\n\r" + "ATFCSD30080A" + "\n\r" + "ATFCSM1";
               // new SelectHeaderObdCommand(str).run(socket.getInputStream(), socket.getOutputStream());
                new SelectHeaderObdCommand("ATFCSH " + canAddr).run(socket.getInputStream(), socket.getOutputStream());
                new SelectHeaderObdCommand("ATFCSD 30080A").run(socket.getInputStream(), socket.getOutputStream());
                new SelectHeaderObdCommand("ATFCSM 1").run(socket.getInputStream(), socket.getOutputStream());
            }
            new SelectHeaderObdCommand("ATCRA " + txAddr).run(socket.getInputStream(), socket.getOutputStream());
            Log.d(TAG, String.format("SetHeader %s :: %d ms", flowControl ? "with flow control" : "", System.currentTimeMillis() - tt));
            activeOther = false;
        }
        catch (NullPointerException e) {
            Log.e(TAG, e.toString());
            Log.e(TAG, e.getMessage());
            e.printStackTrace();
        }
        catch (MisunderstoodCommandException e) {
            Log.e("OBD2", e.toString());
            e.printStackTrace();
        }
        catch (NonNumericResponseException | StoppedException | NoDataException e) {
            activeOther = false;
            Log.e("OBDII-->SetHeaders()", e.toString());
            e.printStackTrace();
        }
        catch (InterruptedException | IOException e4) {
            activeOther = false;
            //socket = null;
            e4.printStackTrace();
        } finally {
            activeOther = false;
        }
        activeOther = false;
    }

    private void requestMmcEngine(boolean setHeader, boolean extended){
        if ( activeMAF ) return;
        if ( App.GS.isReverseMode ) return;
        // set ENGINE ECU addresses
        // если предыдущий запрос был по MAF, то здесь хедер можно не устанвливать,
        // т.к. он такой же, это экономит 100-200 ms, т.е. +1 лишний реквест в 7E0
        if (setHeader) {
            SetHeaders(BLOCK_7E0, "7E8", false);
        }

        ArrayList<Integer> buffer;
        // voltage, speed, rpm
        buffer = requestCanEcu(BLOCK_7E0_PID_2101, BLOCK_7E0);
        processObdCommandResult(BLOCK_7E0_PID_2101, BLOCK_7E0, buffer);

        // coolant
        buffer = requestCanEcu(BLOCK_7E0_PID_2102, BLOCK_7E0);
        processObdCommandResult(BLOCK_7E0_PID_2102, BLOCK_7E0, buffer);

        // air flow
//        buffer = request_CAN_ECU(BLOCK_7E0_PID_2103, BLOCK_7E0);
//        processObdCommandResult(BLOCK_7E0_PID_2103, BLOCK_7E0, buffer);

        // a/c cond relay
        buffer = requestCanEcu(BLOCK_7E0_PID_211D, BLOCK_7E0);
        processObdCommandResult(BLOCK_7E0_PID_211D, BLOCK_7E0, buffer);

        // fuel pump relay
//        buffer = request_CAN_ECU(BLOCK_7E0_PID_211E, BLOCK_7E0);
//        processObdCommandResult(BLOCK_7E0_PID_211E, BLOCK_7E0, buffer);
    }

    private void requestMmcCvt(boolean extended){
        if ( activeMAF ) return;
        if ( App.GS.isReverseMode ) return;
            /* время на выполнение команд max = 1000 ms
                header - 400 m
                21 03  - 250       cvt temp
                21 10  - 330        oil degr, work time
             */
        ArrayList<Integer> buffer;
        if ( can.can_mmc_cvt_temp_show || can.can_mmc_cvt_degr_show) {
            can.CVT_oil_temp_TimeStamp2 = System.currentTimeMillis();
            //can.CVT_oil_degr_TimeStamp2 = System.currentTimeMillis();
            long t_temp = can.CVT_oil_temp_TimeStamp2 - can.CVT_oil_temp_TimeStamp1;
            //long t_degr = can.CVT_oil_degr_TimeStamp2 - can.CVT_oil_degr_TimeStamp1;

//            if ((t_degr < (can.can_mmc_cvt_degradation_update_time * 1000L))
//                    && (t_temp < (can.can_mmc_cvt_temp_update_time * 1000L)))
            if (t_temp < (can.can_mmc_cvt_temp_update_time * 1000L))
            {
                // пропускаем, время не вышло ни у одного параметра
                return;
            }

            activeOther = true;
            SetHeaders(BLOCK_7E1, BLOCK_RX_7E9, true);

            if ( can.can_mmc_cvt_temp_show ) {
                if ( t_temp < (can.can_mmc_cvt_temp_update_time * 1000L) ) return; //время не вышло
                buffer = requestCanEcu(BLOCK_7E1_PID_2103, BLOCK_7E1); // // cvt_temp_count
                processObdCommandResult(BLOCK_7E1_PID_2103, BLOCK_7E1, buffer);
                can.CVT_oil_temp_TimeStamp1 = can.CVT_oil_temp_TimeStamp2;
            }

            // request_CAN_ECU("2107", "7E1", "7E9", false, OBD_BROADCAST_ACTION_ECU_CVT_CHANGED);  // selector position

            // TODO: можно выполнять не часто, раз в 10 сек вполне достаточно или даже реже
            if (extended) {
                if (can.can_mmc_cvt_degr_show) {
//                    if (t_temp < (can.can_mmc_cvt_degradation_update_time * 1000L))
//                        return; //время не вышло
                    buffer = requestCanEcu(BLOCK_7E1_PID_2110, BLOCK_7E1); // cvt_oil_degradation
                    processObdCommandResult(BLOCK_7E1_PID_2110, BLOCK_7E1, buffer);
                    //can.CVT_oil_degr_TimeStamp1 = can.CVT_oil_degr_TimeStamp2;
                }
            }
            activeOther = false;
        }
    }

    private void requestMmcCombineMeter(boolean extended){
        if ( activeMAF ) return;
        if ( App.GS.isReverseMode ) return;

        if ( App.obd.MMC_CAN && App.obd.can.can_mmc_fuel_remain_show ) {
            App.obd.can.FuelLevel_TimeStamp2 = System.currentTimeMillis();
            long t = App.obd.can.FuelLevel_TimeStamp2- App.obd.can.FuelLevel_TimeStamp1;

            if ( t < (can.can_mmc_fuel_remain_update_time * 1000L) ) { return; }

            activeOther = true;
            ArrayList<Integer> buffer;
            /* время на выполнение команд max = 1625 ms
                header - 400 ms
                21 A1  - 150        speed
                21 A2  - 160        rpm
                21 A3  - 125        fuel
                21 A6  - 110        luminocity day/night + backlight on/off
                21 A8  - 150        lights - high beam, low beam, fog lights, turn l/r, handbrake
                21 AD  - 110        mileage
                21 AE  - 170 (FC)   tripA, tripB
                21 AF  - 100        voltage
                21 BC  - 150 (FC)   service reminder
             */
            SetHeaders(BLOCK_6A0, BLOCK_RX_514, true);

//            buffer = requestCanEcu(BLOCK_6A0_PID_21A1, BLOCK_6A0); // speed - иногда отдает лажу
//            processObdCommandResult(BLOCK_6A0_PID_21A1, BLOCK_6A0, buffer);

//            buffer = requestCanEcu(BLOCK_6A0_PID_21A2, BLOCK_6A0); // rpm
//            processObdCommandResult(BLOCK_6A0_PID_21A2, BLOCK_6A0, buffer);

            buffer = requestCanEcu(BLOCK_6A0_PID_21A3, BLOCK_6A0); // fuel
            processObdCommandResult(BLOCK_6A0_PID_21A3, BLOCK_6A0, buffer);

//            buffer = requestCanEcu(BLOCK_6A0_PID_21A6, BLOCK_6A0); // luminocity and day/night
//            processObdCommandResult(BLOCK_6A0_PID_21A6, BLOCK_6A0, buffer);

//            buffer = requestCanEcu(BLOCK_6A0_PID_21A8, BLOCK_6A0); // lights - габариты, дальний, противотуманки перед/зад, поворотники, ручнник
//            processObdCommandResult(BLOCK_6A0_PID_21A8, BLOCK_6A0, buffer);

            buffer = requestCanEcu(BLOCK_6A0_PID_21AE, BLOCK_6A0); // trpA, tripB
            processObdCommandResult(BLOCK_6A0_PID_21AE, BLOCK_6A0, buffer);

//            buffer = requestCanEcu(BLOCK_6A0_PID_21AF, BLOCK_6A0); // voltage
//            processObdCommandResult(BLOCK_6A0_PID_21AF, BLOCK_6A0, buffer);

            if (extended) {
                buffer = requestCanEcu(BLOCK_6A0_PID_21AD, BLOCK_6A0); // mileage
                processObdCommandResult(BLOCK_6A0_PID_21AD, BLOCK_6A0, buffer);

                buffer = requestCanEcu(BLOCK_6A0_PID_21BC, BLOCK_6A0); // service reminder
                processObdCommandResult(BLOCK_6A0_PID_21BC, BLOCK_6A0, buffer);
            }
            can.FuelLevel_TimeStamp1 = can.FuelLevel_TimeStamp2;
            activeOther = false;
        }
    }

    private void requestMmcAirCond(boolean extended){
        if ( activeMAF ) return;
        if ( App.GS.isReverseMode ) return;

        ArrayList<Integer> buffer;
        if (App.obd.can.can_mmc_ac_data_show) {
            activeOther = true;
            SetHeaders(BLOCK_688, BLOCK_RX_511, false);
            /* время на выполнение команд max = 1000 ms
               header - 220 ms
                21 10 - 130         coolant t, air t, interior t
                21 11 - 150         ambient t
                21 13 - xxx         extern t, rpm, speed
                21 23 - 200         leak indicator
                21 60 - 120         btn/knob position
                21 61 - 150         ac indications
             */

            // coolant t, air t, interior t
            if (extended) {
                buffer = requestCanEcu(BLOCK_688_PID_2110, BLOCK_688);
                processObdCommandResult(BLOCK_688_PID_2110, BLOCK_688, buffer);
            }

            // ambient temperature - температура окружающей среды
//            buffer = requestCanEcu(BLOCK_688_PID_2111, BLOCK_688);
//            processObdCommandResult(BLOCK_688_PID_2111, BLOCK_688, buffer);

//            buffer = requestCanEcu(BLOCK_688_PID_2113, BLOCK_688);
//            processObdCommandResult(BLOCK_688_PID_2113, BLOCK_688, buffer);

            // cond leak indicator
            // buffer = request_CAN_ECU("2132", "688");

            // положение крутилок
            buffer = requestCanEcu(BLOCK_688_PID_2160, BLOCK_688);
            processObdCommandResult(BLOCK_688_PID_2160, BLOCK_688, buffer);

            // состояния индикаторов
            buffer = requestCanEcu(BLOCK_688_PID_2161, BLOCK_688);
            processObdCommandResult(BLOCK_688_PID_2161, BLOCK_688, buffer);

//            buffer = requestCanEcu(BLOCK_688_PID_2180, BLOCK_688);
//            processObdCommandResult(BLOCK_688_PID_2180, BLOCK_688, buffer);
            activeOther = false;
        }
    }

    private void requestMmcParkingSensors(){
        if (activeOther || activeMAF || !App.GS.isReverseMode ) return;

        if (App.obd.can.can_mmc_parking_data_show) {
            ArrayList<Integer> buffer;
            SetHeaders(BLOCK_763, "764", false);
            buffer = requestCanEcu(BLOCK_763_PID_2101, BLOCK_763);
            processObdCommandResult(BLOCK_763_PID_2101, BLOCK_763, buffer);
        }
    }

    private void requestMmcAwc(){
        if ( activeMAF ) return;
        ArrayList<Integer> buffer = null;
        SetHeaders( "7B6", "7B7", true);
        buffer = requestCanEcu("2130", "7B6");
        processObdCommandResult("2130", "7B6", buffer);
    }

    public void processHandler(Message message) {
         switch ( message.what ) {
             // ************ ENGINE ********************************************
             case MESSAGE_OBD_ENGINE_2101:
                 App.obd.can.engine.setSpeed(((EngineData) message.obj).getSpeed());
                 App.obd.can.engine.setRpm(((EngineData) message.obj).getRpm());
                 App.obd.can.engine.setVoltage(((EngineData) message.obj).getVoltage());

                 SendBroadcastAction(ACTION_OBD_ENGINE_2101_CHANGED, "obd_engine_2101", (EngineData) message.obj);
                 break;
             case MESSAGE_OBD_ENGINE_2102:
                 App.obd.can.engine.setCoolantTemperature(((EngineData) message.obj).getCoolantTemperature());

                 SendBroadcastAction(ACTION_OBD_ENGINE_2102_CHANGED, "obd_engine_2102", (EngineData) message.obj);
                 break;
             case MESSAGE_OBD_ENGINE_2103:
                 App.obd.can.engine.setAirFlowSensor(((EngineData) message.obj).getAirFlowSensor());

                 SendBroadcastAction(ACTION_OBD_ENGINE_2103_CHANGED, "obd_engine_2103", (EngineData) message.obj);
                 break;
             case MESSAGE_OBD_ENGINE_211D:
                 App.obd.can.engine.setAcFanRelay(((EngineData) message.obj).isAcFanRelay());
                 SendBroadcastAction(ACTION_OBD_ENGINE_211D_CHANGED, "can_mmc_engine_fan_state",
                         App.obd.can.engine.isAcFanRelay().ordinal());
                 break;
             case MESSAGE_OBD_ENGINE_211E:
                 App.obd.can.engine.setFuelPumpRelay(((EngineData) message.obj).isFuelPumpRelay());

                 SendBroadcastAction(ACTION_OBD_CVT_2103_CHANGED, "obd_engine_211E", (EngineData) message.obj);
                 break;

             // *********************** CVT ***********************************
             case MESSAGE_OBD_CVT_2103:
                 App.obd.can.cvt.setTemperature(((CvtData) message.obj).getTemperature());
                 SendBroadcastAction(ACTION_OBD_CVT_2103_CHANGED, "obd_cvt_2103", (CvtData) message.obj);
                 break;
             case MESSAGE_OBD_CVT_2110:
                 App.obd.can.cvt.setOilDegradation(((CvtData) message.obj).getOilDegradation());
                 App.obd.can.cvt.setWorkHoursHot(((CvtData) message.obj).getWorkHoursHot());
                 App.obd.can.cvt.setWorkHoursTotal(((CvtData) message.obj).getWorkHoursTotal());
                 SendBroadcastAction(ACTION_OBD_CVT_2110_CHANGED, "obd_cvt_2110", (CvtData) message.obj);
                 break;


             // ****************** CLIMATE **************************************
             case MESSAGE_OBD_CLIMATE_2110:
                 App.obd.can.climate.interiorTemperature = ((ClimateData) message.obj).interiorTemperature;
                 App.obd.can.climate.pressure = ((ClimateData) message.obj).pressure;
                 App.obd.can.climate.solarSensor = ((ClimateData) message.obj).solarSensor;
                 App.obd.can.climate.coolantTemperature = ((ClimateData) message.obj).coolantTemperature;
                 App.obd.can.climate.airThermoSensor = ((ClimateData) message.obj).airThermoSensor;

                 SendBroadcastAction(ACTION_OBD_CLIMATE_2110_CHANGED, "obd_climate_2110", (ClimateData) message.obj);
                 break;
             case MESSAGE_OBD_CLIMATE_2111:
                 App.obd.can.climate.ambientTemperature = ((ClimateData) message.obj).ambientTemperature;

                 SendBroadcastAction(ACTION_OBD_CLIMATE_2111_CHANGED, "obd_climate_2111", (ClimateData) message.obj);
                 break;
             case MESSAGE_OBD_CLIMATE_2113:
                 App.obd.can.climate.externalTemperature = ((ClimateData) message.obj).externalTemperature;
                 App.obd.can.climate.engineRpm = ((ClimateData) message.obj).engineRpm;
                 App.obd.can.climate.vehicleSpeed = ((ClimateData) message.obj).vehicleSpeed;

                 SendBroadcastAction(ACTION_OBD_CLIMATE_2113_CHANGED, "obd_climate_2113", (ClimateData) message.obj);
                 break;
             case MESSAGE_OBD_CLIMATE_2132:
                 App.obd.can.climate.leak = ((ClimateData) message.obj).leak;
                 App.obd.can.climate.leak20 = ((ClimateData) message.obj).leak20;

                 SendBroadcastAction(ACTION_OBD_CLIMATE_2132_CHANGED, "obd_climate_2132", (ClimateData) message.obj);
                 break;
             case MESSAGE_OBD_CLIMATE_2160:
                 App.obd.can.climate.fan_mode = ((ClimateData) message.obj).fan_mode;
                 App.obd.can.climate.blow_mode = ((ClimateData) message.obj).blow_mode;
                 App.obd.can.climate.temperature = ((ClimateData) message.obj).temperature;
                 SendBroadcastAction(ACTION_OBD_CLIMATE_2160_CHANGED, "obd_climate_2160", (ClimateData) message.obj);
                 break;
             case MESSAGE_OBD_CLIMATE_2161:
                 App.obd.can.climate.blow_direction = ((ClimateData) message.obj).blow_direction;
                 App.obd.can.climate.fan_speed = ((ClimateData) message.obj).fan_speed;
                 App.obd.can.climate.ac_state = ((ClimateData) message.obj).ac_state;
                 App.obd.can.climate.defogger_state = ((ClimateData) message.obj).defogger_state;
                  SendBroadcastAction(ACTION_OBD_CLIMATE_2161_CHANGED, "obd_climate_2161", (ClimateData) message.obj);
                 break;
             case MESSAGE_OBD_CLIMATE_2180:
                 App.obd.can.climate.condSysWorkTime = ((ClimateData) message.obj).condSysWorkTime;
                 SendBroadcastAction(ACTION_OBD_CLIMATE_2180_CHANGED, "obd_climatee_2180", (ClimateData) message.obj);
                 break;

             // ******************* COMBINE METER ***********************************************
             case MESSAGE_OBD_COMBINE_METER_21A1:
                 App.obd.can.meter.setVehicleSpeed(((CombineMeterData) message.obj).getVehicleSpeed());
                 SendBroadcastAction(ACTION_OBD_METER_21A1_CHANGED, "obd_meter_21A1", (CombineMeterData) message.obj);
                 break;
             case MESSAGE_OBD_COMBINE_METER_21A2:
                 App.obd.can.meter.setEngineRpm(((CombineMeterData) message.obj).getEngineRpm());
                 SendBroadcastAction(ACTION_OBD_METER_21A2_CHANGED, "obd_meter_21A2", (CombineMeterData) message.obj);
                 break;
             case MESSAGE_OBD_COMBINE_METER_21A3:
                 App.obd.can.meter.setFuelLevel(((CombineMeterData) message.obj).getFuelLevel());
                 SendBroadcastAction(ACTION_OBD_METER_21A3_CHANGED, "obd_meter_21A3", (CombineMeterData) message.obj);
                 break;
             case MESSAGE_OBD_COMBINE_METER_21A6:
                 SendBroadcastAction(ACTION_OBD_METER_21A6_CHANGED, "obd_meter_21A6", (CombineMeterData) message.obj);
                 break;
             case MESSAGE_OBD_COMBINE_METER_21A8:
                 SendBroadcastAction(ACTION_OBD_METER_21A8_CHANGED, "obd_meter_21A8", (CombineMeterData) message.obj);
                 break;
             case MESSAGE_OBD_COMBINE_METER_21AD:
                 App.obd.can.meter.setMileage(((CombineMeterData) message.obj).getMileage());
                 SendBroadcastAction(ACTION_OBD_METER_21AD_CHANGED, "obd_meter_21AD", (CombineMeterData) message.obj);
                 break;
             case MESSAGE_OBD_COMBINE_METER_21AE:
                 App.obd.can.meter.setTripA(((CombineMeterData) message.obj).getTripA());
                 App.obd.can.meter.setTripB(((CombineMeterData) message.obj).getTripB());
                 distanceCalculation(((CombineMeterData) message.obj).getTripA());
                 SendBroadcastAction(ACTION_OBD_METER_21AE_CHANGED, "obd_meter_21AE", (CombineMeterData) message.obj);
                 break;
             case MESSAGE_OBD_COMBINE_METER_21AF:
                 App.obd.can.meter.setVoltage(((CombineMeterData) message.obj).getVoltage());
                 SendBroadcastAction(ACTION_OBD_METER_21AF_CHANGED, "obd_meter_21AF", (CombineMeterData) message.obj);
                 break;
             case MESSAGE_OBD_COMBINE_METER_21BC:
                 App.obd.can.meter.setServiceReminderDistance(((CombineMeterData) message.obj).getServiceReminderDistance());
                 App.obd.can.meter.setServiceReminderPeriod(((CombineMeterData) message.obj).getServiceReminderPeriod());
                 SendBroadcastAction(ACTION_OBD_METER_21BC_CHANGED, "obd_meter_21BC", (CombineMeterData) message.obj);
                 break;
             case MESSAGE_OBD_PARKING_SENSORS:
                 if (Objects.nonNull(message.obj)) {
                     ArrayList<Integer> buffer = (ArrayList<Integer>) message.obj;

                     Intent intent = new Intent();
                     intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                     intent.setAction(ACTION_OBD_PARKING_2101_CHANGED);
                     intent.putExtra(KEY_OBD_PARKING_2101, buffer);
                     App.getInstance().sendBroadcast(intent);
                 }
                 break;

             default:
                 break;
         }
    }

    public void processObdCommandResult(String pid, String blockId, ArrayList<Integer> buffer) {
        if ( buffer == null ) return;

        switch (blockId) {
            case BLOCK_7E0: ObdEngine.processResult(mHandler, pid, buffer); break;
            case BLOCK_7E1: ObdCvt.processResult(mHandler, pid, buffer); break;
            case BLOCK_688: ObdClimate.processResult(mHandler, pid, buffer); break;
            case BLOCK_6A0: ObdCombineMeter.processResult(mHandler, pid, buffer); break;
            case BLOCK_763: OdbParking.processResult(mHandler, pid, buffer); break;
            case BLOCK_7B6: ObdAwc.processObdAwcResult(mHandler, pid, buffer); break;
        }
    }

    public void process_parking_sensors() {
        requestMmcParkingSensors();
    }

    public void distanceCalculation(float tripA) {
        if (App.obd.newDistanceCalc) {
            App.obd.oneTrip.updateDistance(tripA);
            App.obd.todayTrip.updateDistance(tripA);
            App.obd.totalTrip.updateDistance(tripA);
        }
    }
 }
