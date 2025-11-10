package ru.d51x.kaierutils.coding;

import static ru.d51x.kaierutils.OBD2.ObdConstants.ACTION_OBD_ETACS_DIAG_AND_PART_NUMBER_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.ACTION_OBD_ENGINE_CODING_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.ACTION_OBD_ETACS_CUSTOM_CODING_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.ACTION_OBD_ETACS_VARIANT_CODING_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.OBD_BROADCAST_ACTION_STATUS_CHANGED;
import static ru.d51x.kaierutils.utils.SecurityUtils.getVendor;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.Arrays;
import java.util.List;

import ru.d51x.kaierutils.App;
import ru.d51x.kaierutils.R;
import ru.d51x.kaierutils.utils.SecurityUtils;

public class CodingActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = "Coding";
    private static final int REQUEST_CODE_PERMISSION = 1000;

    private TextView tvObdStatus;
    private TextView tvEtacsVersion;



    public SecurityUtils.Vendor etacsVendor;
    public SecurityUtils.Vendor engineVendor;
    private TabLayout tabLayout;
    private ViewPager2 viewPager;

    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
//            if (ACTION_OBD_ETACS_CUSTOM_CODING_CHANGED.equals(action)) {
//
//            }
//            else if (ACTION_OBD_ETACS_VARIANT_CODING_CHANGED.equals(action)) {
//
//            }
//            else if (ACTION_OBD_ENGINE_CODING_CHANGED.equals(action)) {
//
//            } else
            if (OBD_BROADCAST_ACTION_STATUS_CHANGED.equals(action)) {
                boolean res = intent.getBooleanExtra("Status", false);
                tvObdStatus.setText(String.format(getString(R.string.odbii_device_status), res ? "Подключен" : "Не подключен"));
            }
        }

    };
    @SuppressLint({"MissingInflatedId", "UnspecifiedRegisterReceiverFlag"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etacs);
        etacsVendor = SecurityUtils.Vendor.Unknown;

        tvObdStatus = findViewById(R.id.tvObdStatus);
        tvObdStatus.setText(App.obd.isConnected ? "Подключн" : "Не подключен");
        tvEtacsVersion = findViewById(R.id.tvEtacsVersion);
        tvEtacsVersion.setText("");

        Button btnReadBlocks = findViewById(R.id.btnReadBlocks);
        btnReadBlocks.setOnClickListener(this);


//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        tabLayout = findViewById(R.id.codingTabLayout);
        viewPager = findViewById(R.id.codingViewPager);
        List<String> tabTitles = Arrays.asList("Etacs Custom", "Etacs Variant", "Engine Coding");
        int[] tabIcons = {R.drawable.menu_settings, R.drawable.menu_settings, R.drawable.menu_settings};
        viewPager.setAdapter(new CodingPagerAdapter(this, tabTitles));
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            tab.setText(tabTitles.get(position));
            tab.setIcon(ContextCompat.getDrawable(this, tabIcons[position]));
        }).attach();



        registerReceiver(receiver, new IntentFilter(ACTION_OBD_ETACS_DIAG_AND_PART_NUMBER_CHANGED));
        registerReceiver(receiver, new IntentFilter(ACTION_OBD_ETACS_CUSTOM_CODING_CHANGED));
        registerReceiver(receiver, new IntentFilter(ACTION_OBD_ETACS_VARIANT_CODING_CHANGED));
        registerReceiver(receiver, new IntentFilter(ACTION_OBD_ENGINE_CODING_CHANGED));
        registerReceiver(receiver, new IntentFilter(OBD_BROADCAST_ACTION_STATUS_CHANGED));
    }




    private void readEtacsVersion() {
        if (App.obd.isConnected) {
            this.runOnUiThread(() -> {
                String sVersion = App.obd.readEtacsPartNumber();
                etacsVendor = getVendor(sVersion);
                tvEtacsVersion.setText(sVersion);
            });
        }
    }



    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnReadBlocks -> {
                readEtacsVersion();
            }
//            case R.id.btnFromFileCustomCoding: {
//                    // TODO: диалог выбора файлов
////                    if (checkPermission()) {
//                        this.doSearchFile("cuf");
//
////                    } else {
////                        Log.e(TAG, "Permissions not granted");
////
////                    }
//                }
//                break;
//            case R.id.btnToFileCustomCoding:
//                saveCustomToFile();
//                break;
//            case R.id.btnTestCustomCoding: {
//                    String str = edtEtacsCustom.getText().toString().trim();
//                    if (str.isEmpty()) return;
//                    edtEtacsCustom2.setText(str);
//                    customCodingBuffer = hexStringToBuffer(str, 0);
//                    prepareAndFillCustomCodingList(true);
//                }
//                break;
//            case R.id.btnEtacsReadCustom: {
//                    Log.d(TAG, "Read Etacs Custom coding...");
//                    customCodingBuffer = readEtacsCodingCustom();
//                    prepareAndFillCustomCodingList(true);
//                }
//                break;
//            case R.id.btnEtacsReadVariant:
//                Log.d(TAG, "Read Etacs Variant coding...");
//                readEtacsCodingVariant();
//                break;
//            case R.id.btnEtacsWriteCustom:
//                Log.d(TAG, "Write Etacs Custom coding...");
//                String customCoding = edtEtacsCustom.getText().toString().trim();
//                if (customCoding.isEmpty()) {
//                    Log.w(TAG, "Custom coding is empty");
//                } else {
//                    Log.d(TAG, "Custom: " + customCoding);
//                    writeEtacsCodingCustom(customCoding);
//                    prevCustomCodingBuffer.clear();
//                    prevCustomCodingBuffer.addAll(customCodingBuffer);
//
//                    // TODO: read custom after write
//                    //customCodingBuffer = readEtacsCodingCustom();
//                }
//                break;
//            case R.id.btnEtacsWriteVariant:
//                Log.d(TAG, "Write Etacs Variant coding...");
//                String variantCoding = edtEtacsVariant.getText().toString().trim();
//                if (variantCoding.isEmpty()) {
//                    Log.w(TAG, "Variant coding is empty");
//                } else {
//                    Log.d(TAG, "Variant: " + variantCoding);
//                    writeEtacsCodingVariant(variantCoding);
//                }
//                break;
//            case R.id.btnEngineCodingRead:
//                Log.d(TAG, "Read Engine coding...");
//                readEngineCoding();
//                break;
//            case R.id.btnEngineCodingWrite:
//                Log.d(TAG, "Write Engine coding...");
//                String engineCoding = edtEngineCoding.getText().toString().trim();
//                if (engineCoding.isEmpty()) {
//                    Log.w(TAG, "Engine coding is empty");
//                } else {
//                    Log.d(TAG, "Engine coding: " + engineCoding);
//                    writeEngineCoding(engineCoding);
//                }
//                break;
        }
    }

    protected void onDestroy() {
        unregisterReceiver(receiver);
        super.onDestroy();
    }








//    private boolean checkPermission() {
//        // Check if we have Call permission
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
//            Log.d(TAG, "Request file storage permissions");
//
//            String[] permissions = {
//                    Manifest.permission.READ_EXTERNAL_STORAGE,
//                    Manifest.permission.WRITE_EXTERNAL_STORAGE
//            };
//
//            List<String> permissionsToRequest = new ArrayList<>();
//
//            for (String permission : permissions) {
//                if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
//                    Log.e("Permissions", String.format("Permission %s is not granted", permission));
//                    permissionsToRequest.add(permission);
//                }
//            }
//
//            if (!permissionsToRequest.isEmpty()) {
//                ActivityCompat.requestPermissions(
//                        this,
//                        permissionsToRequest.toArray(new String[0]), // Convert list to array
//                        REQUEST_CODE_PERMISSION // Pass the request code
//                );
//            } else {
//                Log.i("Permissions", "All permissions already granted");
//                return true;
//            }
//            return false;
//        } else {
//            return true;
//        }
//    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == REQUEST_CODE_PERMISSION) {// Note: If request is cancelled, the result arrays are empty.
//            // Permissions granted (CALL_PHONE).
//            if (grantResults.length > 0
//                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//
//                Log.i(TAG, "Permission granted!");
//
//                this.doSearchFile("kon");
//            }
//            // Cancelled or denied.
//            else {
//                Log.i(TAG, "Permission denied!");
//                //Toast.makeText(this, "Permission denied!", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }






}
