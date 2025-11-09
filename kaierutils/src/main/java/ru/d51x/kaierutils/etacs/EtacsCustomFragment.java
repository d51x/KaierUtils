package ru.d51x.kaierutils.etacs;

import static ru.d51x.kaierutils.etacs.EtacsCustomCoding.getAvailableOptions;
import static ru.d51x.kaierutils.etacs.EtacsCustomCoding.getAvailableValues;
import static ru.d51x.kaierutils.utils.StringUtils.bufferToHex;
import static ru.d51x.kaierutils.utils.StringUtils.hexStringToBuffer;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import ru.d51x.kaierutils.App;
import ru.d51x.kaierutils.CodingAdapter;
import ru.d51x.kaierutils.R;
import ru.d51x.kaierutils.dialog.FileSelector;
import ru.d51x.kaierutils.dialog.FileSelectorDialog;
import ru.d51x.kaierutils.dialog.RadioListDialog;
import ru.d51x.kaierutils.utils.SecurityUtils;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EtacsCustomFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class EtacsCustomFragment extends Fragment  implements View.OnClickListener {

    public static final String TAG = "EtacsCustom";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private EditText edtEtacsCustom;
    private EditText edtEtacsCustom2;


    private ListView lvEtacsCustom;

    private CodingAdapter etacsCustomCodingAdapter;
    private final ArrayList<EtacsCustomCoding> customCoding = new ArrayList<>();
    private ArrayList<Integer> customCodingBuffer = new ArrayList<>();
    private final ArrayList<Integer> prevCustomCodingBuffer = new ArrayList<>();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EtacsActivity parentActivity;

    public EtacsCustomFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EtacsCustomFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EtacsCustomFragment newInstance(String param1, String param2) {
        EtacsCustomFragment fragment = new EtacsCustomFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_etacs_custom, container, false);

        parentActivity = (EtacsActivity) getActivity();
        edtEtacsCustom = v.findViewById(R.id.edtEtacsCustom);
        edtEtacsCustom.setText("11302210F222120FF03F0F113F0FFFFF00000F1F2FF1FFFFFF");

        edtEtacsCustom2 = v.findViewById(R.id.edtEtacsCustom2);
        edtEtacsCustom2.setText("11302210F222120FF03F0F113F0FFFFF00000F1F2FF1FFFFFF");


        Button btnToFileCustomCoding = v.findViewById(R.id.btnToFileCustomCoding);
        btnToFileCustomCoding.setOnClickListener(this);

        Button btnFromFileCustomCoding = v.findViewById(R.id.btnFromFileCustomCoding);
        btnFromFileCustomCoding.setOnClickListener(this);

        Button btnTestCustomCoding = v.findViewById(R.id.btnTestCustomCoding);
        btnTestCustomCoding.setOnClickListener(this);

        Button btnEtacsReadCustom = v.findViewById(R.id.btnEtacsReadCustom);
        btnEtacsReadCustom.setOnClickListener(this);

        Button btnEtacsWriteCustom = v.findViewById(R.id.btnEtacsWriteCustom);
        btnEtacsWriteCustom.setOnClickListener(this);

        lvEtacsCustom = v.findViewById(R.id.lvEtacsCustom);
        lvEtacsCustom.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position,
                                    long id) {

                EtacsCustomCoding e = (EtacsCustomCoding) itemClicked.getTag(R.id.ITEM_OBJECT_TAG);
//                int byteValue = (int) itemClicked.getTag(R.id.ITEM_BYTE_VALUE_TAG);
                String currentValue = (String) itemClicked.getTag(R.id.ITEM_CURRENT_VALUE_TAG);
                if (customCoding.isEmpty()) return;

                if (e == null) return;
//                int idx = e.getByteIdx();

                ArrayList<String> options = getAvailableOptions(e.getIdx());
                CharSequence[] radioValues = options.toArray(new CharSequence[0]);
                final int[] selectedValue = {-1};
                RadioListDialog dialog = new RadioListDialog(getActivity(), e.getTitle(), e.getName());
                dialog.setValuesList(radioValues, options.indexOf(currentValue));
                dialog.setListener(selected -> {
                    if (selected == -1) {
                        Log.d(TAG, "option cancelled");
                        return;
                    }
                    selectedValue[0] = selected;
                    Log.d(TAG, "selected idx = " + selectedValue[0]);

                    int selValue = getAvailableValues(e.getIdx()).get(selected);
                    int oldValue = customCodingBuffer.get(e.getByteIdx());
                    int shiftedValue = selValue << e.getStartBit();
                    int maskedValue = oldValue & ~e.getMask();
                    //int newValue = oldValue | maskedValue;
                    int newValue = maskedValue ^ shiftedValue;
                    Log.d(TAG, String.format("Old value = 0x%02X", oldValue));
                    Log.d(TAG, "selected value = " + selValue);
                    Log.d(TAG, "start bit = " + e.getStartBit());
                    Log.d(TAG, String.format("mask = 0x%02X", e.getMask()));
                    Log.d(TAG, String.format("shifted value = 0x%02X", shiftedValue));
                    //Log.d(TAG, String.format("masked value = 0x%02X", maskedValue));
                    Log.d(TAG, String.format("new value = 0x%02X", newValue));
                    // TODO: нужно изменить значение у опции на основе выбранного результата и выставить флаг, что есть активные изменения
                    // TODO: обновить список новым выбором
                    customCodingBuffer.set(e.getByteIdx(), newValue);
                    String newCodingStr = bufferToHex(customCodingBuffer, 0, false);
                    edtEtacsCustom.setText(newCodingStr);

                    int index = lvEtacsCustom.getFirstVisiblePosition();
                    etacsCustomCodingAdapter = updateListView(lvEtacsCustom, customCodingBuffer,
                            prevCustomCodingBuffer, customCoding);
                    lvEtacsCustom.setSelection(index);
                });
                dialog.show();

//                Toast.makeText(getApplicationContext(),
//                        String.format("%s = %s", e.getTitle(), currentValue),
//                        Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }

    private CodingAdapter updateListView(ListView lv, ArrayList<Integer> buffer,
                                         ArrayList<Integer> prevBuffer,
                                         List<EtacsCustomCoding> list) {
        CodingAdapter adapter = new CodingAdapter(getActivity(), buffer, prevBuffer,
                R.layout.list_item_coding, list);
        lv.setAdapter(adapter);

        if (adapter.getCount() > 10) {
            View item = adapter.getView(0, null, lv);
            item.measure(0, 0);
            ViewGroup.LayoutParams params = lv.getLayoutParams();
            params.height = (int) (10.5 * item.getMeasuredHeight());
            lv.setLayoutParams(params);
        }

        return adapter;
    }

    private void prepareAndFillCustomCodingList(boolean showExtended) {
        prevCustomCodingBuffer.clear();
        prevCustomCodingBuffer.addAll(customCodingBuffer);
        customCoding.clear();
        customCoding.addAll(Arrays.stream(EtacsCustomCoding.values())
                //.filter(i -> i.getIdx() % 2 == 0)
                .filter(i -> i.getIdx() != 999)
                .collect(Collectors.toList()));
        etacsCustomCodingAdapter = updateListView(lvEtacsCustom, customCodingBuffer,
                prevCustomCodingBuffer, customCoding);
    }

    private void saveCustomToFile() {
        String sValue = bufferToHex(customCodingBuffer, 0, false);
        File path = parentActivity.getFilesDir();
        SimpleDateFormat formater = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
        Date date = new Date();
        String fName = String.format("etacs_custom_%s.cuf", formater.format(date));
        File f = new File(path, fName);

        try {
            FileWriter out = new FileWriter(f);
            out.write(sValue);
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnFromFileCustomCoding: {
                // TODO: диалог выбора файлов
//                    if (checkPermission()) {
                this.doSearchFile("cuf");

//                    } else {
//                        Log.e(TAG, "Permissions not granted");
//
//                    }
            }
            break;
            case R.id.btnToFileCustomCoding:
                saveCustomToFile();
                break;
            case R.id.btnTestCustomCoding: {
                String str = edtEtacsCustom.getText().toString().trim();
                if (str.isEmpty()) return;
                edtEtacsCustom2.setText(str);
                customCodingBuffer = hexStringToBuffer(str, 0);
                prepareAndFillCustomCodingList(true);
            }
            break;
            case R.id.btnEtacsReadCustom: {
                Log.d(TAG, "Read Etacs Custom coding... Vendor is " + parentActivity.etacsVendor);
                if (parentActivity.etacsVendor == SecurityUtils.Vendor.Unknown) {
                    // read block first
                    Log.e(TAG, "Error read etacs custom. Read block  first...");
                    return;
                }
                customCodingBuffer = readEtacsCodingCustom();
                prepareAndFillCustomCodingList(true);
            }
            break;
//            case R.id.btnEtacsReadVariant:
//                Log.d(TAG, "Read Etacs Variant coding...");
//                readEtacsCodingVariant();
//                break;
            case R.id.btnEtacsWriteCustom:
                Log.d(TAG, "Write Etacs Custom coding... Vendor is " + parentActivity.etacsVendor);
                if (parentActivity.etacsVendor == SecurityUtils.Vendor.Unknown) {
                    // read block first
                    Log.e(TAG, "Error read etacs custom. Read block  first...");
                    return;
                }
                String customCoding = edtEtacsCustom.getText().toString().trim();
                if (customCoding.isEmpty()) {
                    Log.w(TAG, "Custom coding is empty");
                } else {
                    Log.d(TAG, "Custom: " + customCoding);
                    writeEtacsCodingCustom(customCoding, parentActivity.etacsVendor);
                    prevCustomCodingBuffer.clear();
                    prevCustomCodingBuffer.addAll(customCodingBuffer);

                    // TODO: read custom after write
                    //customCodingBuffer = readEtacsCodingCustom();
                }
                break;
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

    private ArrayList<Integer> readEtacsCodingCustom() {
        AtomicReference<ArrayList<Integer>> buffer = new AtomicReference<>();
        if (App.obd.isConnected) {
            parentActivity.runOnUiThread(() -> {
                buffer.set(App.obd.readEtacsCodingCustom(parentActivity.etacsVendor));
                if (buffer.get() != null) {
                    String s = bufferToHex(buffer.get(), 2, false);
                    edtEtacsCustom.setText(s);
                    edtEtacsCustom.setEnabled(true);
                    edtEtacsCustom2.setText(s);
                }
            });
        }
        return buffer.get();
    }

    private void writeEtacsCodingCustom(String coding, SecurityUtils.Vendor vendor) {
        if (App.obd.isConnected) {
            parentActivity.runOnUiThread(() -> App.obd.writeEtacsCodingCustom(coding, vendor));
        }
    }



    private void loadFile(String fName) {
        BufferedReader in;
        try {
            File path = parentActivity.getFilesDir();
            File f = new File(path, fName);
            in = new BufferedReader(new FileReader(f));
            String sCoding = in.readLine();
            customCodingBuffer = hexStringToBuffer(sCoding, 0);
            edtEtacsCustom.setText(sCoding);
            edtEtacsCustom2.setText(sCoding);
            prepareAndFillCustomCodingList(true);
        } catch (FileNotFoundException e) {
            Log.e(TAG, "File " + fName + " not found");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void doSearchFile(String fileType) {
        String path = parentActivity.getFilesDir().getAbsolutePath();
        FileSelector fileSelector = new FileSelector(path, fileType);
        List<File> resultList = fileSelector.getFiles(path, fileType);

        FileSelectorDialog fileSelectorDialog = new FileSelectorDialog(parentActivity,
                resultList, file -> {
            Log.d(TAG, "File is " + file.getName());
            loadFile(file.getName());
        });
        fileSelectorDialog.show();
    }

    public CodingAdapter getEtacsCustomCodingAdapter() {
        return etacsCustomCodingAdapter;
    }

    public void setEtacsCustomCodingAdapter(CodingAdapter etacsCustomCodingAdapter) {
        this.etacsCustomCodingAdapter = etacsCustomCodingAdapter;
    }
}