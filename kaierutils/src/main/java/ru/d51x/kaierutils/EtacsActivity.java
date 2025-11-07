package ru.d51x.kaierutils;

import static ru.d51x.kaierutils.Data.EtacsCustomCoding.getAvailableOptions;
import static ru.d51x.kaierutils.Data.EtacsCustomCoding.getAvailableValues;
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
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import ru.d51x.kaierutils.Data.EtacsCustomCoding;
import ru.d51x.kaierutils.dialog.RadioListDialog;

public class EtacsActivity  extends Activity implements View.OnClickListener {
    public static final String TAG = "Etacs";
    private EditText edtEtacsCustom;
    private EditText edtEtacsCustom2;
    private EditText edtEtacsVariant;
    private EditText edtEngineCoding;
    private Button btnEtacsReadCustom;
    private Button btnEtacsWriteCustom;
    private Button btnEtacsReadVariant;
    private Button btnEtacsWriteVariant;
    private Button btnEngineCodingRead;
    private Button btnEngineCodingWrite;
    private Button btnTestCustomCoding;
    private ListView lvEtacsCustom;
    private ArrayList<EtacsCustomCoding> customCodings = new ArrayList<>();
    private ArrayList<Integer> customCodingBuffer = new ArrayList<>();

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
        edtEtacsCustom.setText("11302210F222120FF03F0F113F0FFFFF00000F1F2FF1FFFFFF");

        edtEtacsCustom2 = findViewById(R.id.edtEtacsCustom2);
        edtEtacsCustom2.setText("11302210F222120FF03F0F113F0FFFFF00000F1F2FF1FFFFFF");

        edtEtacsVariant = findViewById(R.id.edtEtacsVariant);
        edtEngineCoding = findViewById(R.id.edtEngineCoding);

        btnTestCustomCoding = findViewById(R.id.btnTestCustomCoding);
        btnTestCustomCoding.setOnClickListener(this);

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

        lvEtacsCustom = findViewById(R.id.lvEtacsCustom);
        lvEtacsCustom.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position,
                                    long id) {

                EtacsCustomCoding e = (EtacsCustomCoding) itemClicked.getTag(R.id.ITEM_OBJECT_TAG);
                int byteValue = (int) itemClicked.getTag(R.id.ITEM_BYTE_VALUE_TAG);
                String currentValue = (String) itemClicked.getTag(R.id.ITEM_CURRENT_VALUE_TAG);
                if (customCodings.isEmpty()) return;

                if (e == null) return;
                int idx = e.getByteIdx();

                ArrayList<String> options = getAvailableOptions(e.getIdx());
                CharSequence[] radioValues = options.toArray(new CharSequence[0]);
                final int[] selectedValue = {-1};
                RadioListDialog dialog = new RadioListDialog(EtacsActivity.this, e.getTitle(), e.getName());
                dialog.setValuesList(radioValues, options.indexOf(currentValue));
                dialog.setListener(new RadioListDialog.RadioListDialogListener() {
                    @Override
                    public void onDialogResult(int selected) {
                        if (selected == -1) {
                            Log.d(TAG, "option cancelled");
                            return;
                        }
                        selectedValue[0] = selected;
                        Log.d(TAG, "selected idx = " + selectedValue[0]);

                        int selValue = getAvailableValues(e.getIdx()).get(selected);
                        int oldValue = customCodingBuffer.get(e.getByteIdx());
                        int shiftedValue = selValue << e.getStartBit();
                        int maskedValue = shiftedValue & e.getMask();
                        //int newValue = oldValue | maskedValue;
                        int newValue = oldValue | selValue;
                        Log.d(TAG, String.format("Old value = 0x%02X", oldValue));
                        Log.d(TAG, "selected value = " + selValue);
                        Log.d(TAG, "start bit = " + e.getStartBit());
                        Log.d(TAG, String.format("mask = 0x%02X", e.getMask()));
                        Log.d(TAG, String.format("shifted value = 0x%02X", shiftedValue));
                        Log.d(TAG, String.format("masked value = 0x%02X", maskedValue));
                        Log.d(TAG, String.format("new value = 0x%02X", newValue));
                        // TODO: нужно изменить значение у опции на основе выбранного результата и выставить флаг, что есть активные изменения
                        // TODO: обновить список новым выбором

                    }
                });
                dialog.show();

//                Toast.makeText(getApplicationContext(),
//                        String.format("%s = %s", e.getTitle(), currentValue),
//                        Toast.LENGTH_SHORT).show();
            }
        });

        registerReceiver(receiver, new IntentFilter(ACTION_OBD_ETACS_CUSTOM_CODING_CHANGED));
        registerReceiver(receiver, new IntentFilter(ACTION_OBD_ETACS_VARIANT_CODING_CHANGED));
        registerReceiver(receiver, new IntentFilter(ACTION_OBD_ENGINE_CODING_CHANGED));
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnTestCustomCoding: {
                    String str = edtEtacsCustom.getText().toString().trim();
                    if (str.isEmpty()) return;
                    edtEtacsCustom2.setText(str);
                    customCodingBuffer = hexStringToBuffer(str, 0);
                    customCodings.clear();
                    customCodings.addAll(Arrays.stream(EtacsCustomCoding.values())
                            //.filter(i -> i.getIdx() % 2 == 0)
                            .filter(i -> i.getIdx() != 999)
                            .collect(Collectors.toList()));
                    CodingAdapter etacsCustomCodingAdapter = new CodingAdapter(this, customCodingBuffer, R.layout.list_item_coding,
                            customCodings);
                    lvEtacsCustom.setAdapter(etacsCustomCodingAdapter);

                    if (etacsCustomCodingAdapter.getCount() > 10) {
                        View item = etacsCustomCodingAdapter.getView(0, null, lvEtacsCustom);
                        item.measure(0, 0);
                        ViewGroup.LayoutParams params = lvEtacsCustom.getLayoutParams();
                        params.height = (int) (10.5 * item.getMeasuredHeight());
                        lvEtacsCustom.setLayoutParams(params);
                    }
                }
                break;
            case R.id.btnEtacsReadCustom: {
                    Log.d(TAG, "Read Etacs Custom coding...");
                    ArrayList<Integer> buffer = readEtacsCodingCustom();
                    CodingAdapter etacsCustomCodingAdapter = new CodingAdapter(this, buffer, R.layout.list_item_coding, Arrays.asList(EtacsCustomCoding.values()));
                    lvEtacsCustom.setAdapter(etacsCustomCodingAdapter);
                }
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

    private ArrayList<Integer> readEtacsCodingCustom() {
        AtomicReference<ArrayList<Integer>> buffer = new AtomicReference<>();
        if (App.obd.isConnected) {
            runOnUiThread(() -> {
                buffer.set(App.obd.readEtacsCodingCustom());
                if (buffer.get() != null) {
                    String s = bufferToHex(buffer.get(), 2, false);
                    edtEtacsCustom.setText(s);
                    edtEtacsCustom.setEnabled(true);
                }
            });
        }
        return buffer.get();
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
