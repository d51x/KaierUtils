package ru.d51x.kaierutils;

import static ru.d51x.kaierutils.OBD2.ObdConstants.ACTION_OBD_ENGINE_CODING_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.ACTION_OBD_ETACS_CUSTOM_CODING_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.ACTION_OBD_ETACS_VARIANT_CODING_CHANGED;
import static ru.d51x.kaierutils.utils.StringUtils.bufferToHex;
import static ru.d51x.kaierutils.utils.StringUtils.hexStringToBuffer;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class EtacsActivity  extends Activity implements View.OnClickListener {
    public static final String TAG = "Etacs";
    private EditText edtEtacsCustom;
    private EditText edtEtacsVariant;
    private EditText edtEngineCoding;
    private Button btnEtacsReadCustom;
    private Button btnEtacsWriteCustom;
    private Button btnEtacsReadVariant;
    private Button btnEtacsWriteVariant;
    private Button btnEngineCodingRead;
    private Button btnEngineCodingWrite;

    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (ACTION_OBD_ETACS_CUSTOM_CODING_CHANGED.equals(action)) {

            }
            else if (ACTION_OBD_ETACS_VARIANT_CODING_CHANGED.equals(action)) {

            }
            else if (ACTION_OBD_ENGINE_CODING_CHANGED.equals(action)) {

            }
        }

    };
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etacs);

        edtEtacsCustom = findViewById(R.id.edtEtacsCustom);
        edtEtacsVariant = findViewById(R.id.edtEtacsVariant);
        edtEngineCoding = findViewById(R.id.edtEngineCoding);

        btnEtacsReadCustom = findViewById(R.id.btnEtacsReadCustom);
        btnEtacsReadCustom.setOnClickListener(this);

        btnEtacsWriteCustom = findViewById(R.id.btnEtacsWriteCustom);
        btnEtacsWriteCustom.setOnClickListener(this);

        btnEtacsReadVariant = findViewById(R.id.btnEtacsReadVariant);
        btnEtacsReadVariant.setOnClickListener(this);

        btnEtacsWriteVariant = findViewById(R.id.btnEtacsWriteVariant);
        btnEtacsWriteVariant.setOnClickListener(this);

        btnEngineCodingRead = findViewById(R.id.btnEngineCodingRead);
        btnEngineCodingRead.setOnClickListener(this);

        btnEngineCodingWrite = findViewById(R.id.btnEngineCodingWrite);
        btnEngineCodingWrite.setOnClickListener(this);

        registerReceiver(receiver, new IntentFilter(ACTION_OBD_ETACS_CUSTOM_CODING_CHANGED));
        registerReceiver(receiver, new IntentFilter(ACTION_OBD_ETACS_VARIANT_CODING_CHANGED));
        registerReceiver(receiver, new IntentFilter(ACTION_OBD_ENGINE_CODING_CHANGED));
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnEtacsReadCustom:
                Log.d(TAG, "Read Etacs Custom coding...");
                readEtacsCodingCustom();
                break;
            case R.id.btnEtacsReadVariant:
                Log.d(TAG, "Read Etacs Variant coding...");
                readEtacsCodingVariant();
                break;
            case R.id.btnEtacsWriteCustom:
                Log.d(TAG, "Write Etacs Custom coding...");
                String customCoding = edtEtacsCustom.getText().toString().trim();
                if (customCoding.isEmpty()) {
                    Log.w(TAG, "Custom coding is empty");
                } else {
                    Log.d(TAG, "Custom: " + customCoding);
                    writeEtacsCodingCustom(customCoding);
                }
                break;
            case R.id.btnEtacsWriteVariant:
                Log.d(TAG, "Write Etacs Variant coding...");
                String variantCoding = edtEtacsVariant.getText().toString().trim();
                if (variantCoding.isEmpty()) {
                    Log.w(TAG, "Variant coding is empty");
                } else {
                    Log.d(TAG, "Variant: " + variantCoding);
                    writeEtacsCodingVariant(variantCoding);
                }
                break;
            case R.id.btnEngineCodingRead:
                Log.d(TAG, "Read Engine coding...");
                readEngineCoding();
                break;
            case R.id.btnEngineCodingWrite:
                Log.d(TAG, "Write Engine coding...");
                String engineCoding = edtEngineCoding.getText().toString().trim();
                if (engineCoding.isEmpty()) {
                    Log.w(TAG, "Engine coding is empty");
                } else {
                    Log.d(TAG, "Engine coding: " + engineCoding);
                    writeEngineCoding(engineCoding);
                }
                break;
        }
    }

    protected void onDestroy() {
        unregisterReceiver(receiver);
        super.onDestroy();
    }

    private void readEtacsCodingCustom() {
        if (App.obd.isConnected) {
            runOnUiThread(() -> {
                ArrayList<Integer> buffer = App.obd.readEtacsCodingCustom();
                if (buffer != null) {
                    String s = bufferToHex(buffer, 2, false);
                    edtEtacsCustom.setText(s);
                    edtEtacsCustom.setEnabled(true);
                }
            });
        }
    }

    private void writeEtacsCodingCustom(String coding) {
        if (App.obd.isConnected) {
            runOnUiThread(() -> {
                App.obd.writeEtacsCodingCustom(coding);

            });
        }
    }

    private void readEtacsCodingVariant() {
        if (App.obd.isConnected) {
            runOnUiThread(() -> {
                ArrayList<Integer> buffer = App.obd.readEtacsCodingVariant();
                if (buffer != null) {
                    String s = bufferToHex(buffer, 2, false);
                    edtEtacsVariant.setText(s);
                    edtEtacsVariant.setEnabled(true);
                }
            });
        }
    }

    private void writeEtacsCodingVariant(String coding) {
        if (App.obd.isConnected) {
            runOnUiThread(() -> {
                App.obd.writeEtacsCodingVariant(coding);
                // String s = bufferToHex(buffer, 0, false);
            });
        }
    }

    private void readEngineCoding() {
        if (App.obd.isConnected) {
            runOnUiThread(() -> {
                ArrayList<Integer> buffer = App.obd.readEngineCoding();
                if (buffer != null) {
                    String s = bufferToHex(buffer, 2, false);
                    edtEngineCoding.setText(s);
                    edtEngineCoding.setEnabled(true);
                }
            });
        }
    }

    private void writeEngineCoding(String coding) {
        if (App.obd.isConnected) {
            runOnUiThread(() -> {
                App.obd.writeEngineCoding(coding);

            });
        }
    }
}
