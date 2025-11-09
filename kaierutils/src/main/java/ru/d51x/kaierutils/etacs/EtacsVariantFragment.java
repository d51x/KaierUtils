package ru.d51x.kaierutils.etacs;

import static ru.d51x.kaierutils.etacs.EtacsVariantCoding.getAvailableOptions;
import static ru.d51x.kaierutils.etacs.EtacsVariantCoding.getAvailableValues;
import static ru.d51x.kaierutils.utils.StringUtils.bufferToHex;
import static ru.d51x.kaierutils.utils.StringUtils.hexStringToBuffer;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
import ru.d51x.kaierutils.R;
import ru.d51x.kaierutils.dialog.FileSelector;
import ru.d51x.kaierutils.dialog.FileSelectorDialog;
import ru.d51x.kaierutils.dialog.RadioListDialog;
import ru.d51x.kaierutils.utils.SecurityUtils;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EtacsVariantFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EtacsVariantFragment extends Fragment   implements View.OnClickListener {

    public static final String TAG = "EtacsVariant";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private CheckBox cbShowExtend;
    private EditText edtEtacsVariant;
//    private EditText edtEtacsVariant2;

    private ListView lvEtacsVariant;

    private VariantCodingAdapter etacsVariantCodingAdapter;
    private final ArrayList<EtacsVariantCoding> variantCoding = new ArrayList<>();
    private ArrayList<Integer> variantCodingBuffer = new ArrayList<>();
    private final ArrayList<Integer> prevVariantCodingBuffer = new ArrayList<>();

    private EtacsActivity parentActivity;

    public EtacsVariantFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EtacsVariantFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EtacsVariantFragment newInstance(String param1, String param2) {
        EtacsVariantFragment fragment = new EtacsVariantFragment();
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
        View v =  inflater.inflate(R.layout.fragment_etacs_variant, container, false);
        parentActivity = (EtacsActivity) getActivity();

        edtEtacsVariant = v.findViewById(R.id.edtEtacsVariant);
        edtEtacsVariant.setText("A0183305181390086B00477950B611010D0402BA514F72B7B2B2C9216B03824BE5672143A032A20900000000000000000000A002C019");

        //edtEtacsVariant2 = v.findViewById(R.id.edtEtacsVariant2);
//        edtEtacsVariant2.setText("A0183305181390086B00477950B611010D0402BA514F72B7B2B2C9216B03824BE5672143A032A20900000000000000000000A002C019");

        cbShowExtend = v.findViewById(R.id.cbShowExtend);
        cbShowExtend.setChecked(false);
        cbShowExtend.setOnCheckedChangeListener((compoundButton, isChecked) -> prepareAndFillVariantCodingList(isChecked));

        Button btnEtacsReadVariant = v.findViewById(R.id.btnEtacsReadVariant);
        btnEtacsReadVariant.setOnClickListener(this);

        Button btnEtacsWriteVariant = v.findViewById(R.id.btnEtacsWriteVariant);
        btnEtacsWriteVariant.setOnClickListener(this);

        Button btnToFileVariantCoding = v.findViewById(R.id.btnToFileVariantCoding);
        btnToFileVariantCoding.setOnClickListener(this);

        Button btnFromFileVariantCoding = v.findViewById(R.id.btnFromFileVariantCoding);
        btnFromFileVariantCoding.setOnClickListener(this);

        Button btnTestVariantCoding = v.findViewById(R.id.btnTestVariantCoding);
        btnTestVariantCoding.setOnClickListener(this);



        lvEtacsVariant = v.findViewById(R.id.lvEtacsVariant);
        lvEtacsVariant.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position,
                                    long id) {

                EtacsVariantCoding e = (EtacsVariantCoding) itemClicked.getTag(R.id.ITEM_OBJECT_TAG);
//                int byteValue = (int) itemClicked.getTag(R.id.ITEM_BYTE_VALUE_TAG);
                String currentValue = (String) itemClicked.getTag(R.id.ITEM_CURRENT_VALUE_TAG);
                if (variantCoding.isEmpty()) return;

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
                    int oldValue = variantCodingBuffer.get(e.getByteIdx());
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
                    variantCodingBuffer.set(e.getByteIdx(), newValue);
                    String newCodingStr = bufferToHex(variantCodingBuffer, 0, false);
                    edtEtacsVariant.setText(newCodingStr);

                    int index = lvEtacsVariant.getFirstVisiblePosition();
                    etacsVariantCodingAdapter = updateListView(lvEtacsVariant, variantCodingBuffer,
                            prevVariantCodingBuffer, variantCoding);
                    lvEtacsVariant.setSelection(index);
                });
                dialog.show();

//                Toast.makeText(getApplicationContext(),
//                        String.format("%s = %s", e.getTitle(), currentValue),
//                        Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }

    public VariantCodingAdapter updateListView(ListView lv, ArrayList<Integer> buffer,
                                               ArrayList<Integer> prevBuffer,
                                               List<EtacsVariantCoding> list) {
        VariantCodingAdapter adapter = new VariantCodingAdapter(parentActivity, buffer, prevBuffer,
                R.layout.list_item_coding, list);
        lv.setAdapter(adapter);

//        if (adapter.getCount() > 7) {
//            View item = adapter.getView(0, null, lv);
//            item.measure(0, 0);
//            ViewGroup.LayoutParams params = lv.getLayoutParams();
//            params.height = (int) (7.5 * item.getMeasuredHeight());
//            lv.setLayoutParams(params);
//        }

        return adapter;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnFromFileVariantCoding: {
                // TODO: диалог выбора файлов
//                    if (checkPermission()) {
                this.doSearchFile("kon");

//                    } else {
//                        Log.e(TAG, "Permissions not granted");
//
//                    }
            }
            break;
            case R.id.btnToFileVariantCoding:
                saveVariantToFile();
                break;
            case R.id.btnTestVariantCoding: {
                String str = edtEtacsVariant.getText().toString().trim();
                if (str.isEmpty()) return;
                //edtEtacsVariant2.setText(str);
                variantCodingBuffer = hexStringToBuffer(str, 0);
                prepareAndFillVariantCodingList(cbShowExtend.isChecked());
            }
            break;
            case R.id.btnEtacsReadVariant: {
                    Log.d(TAG, "Read Etacs Variant coding... Vendor is " + parentActivity.etacsVendor);
                    if (parentActivity.etacsVendor == SecurityUtils.Vendor.Unknown) {
                        // read block first
                        Log.e(TAG, "Error read etacs variant. Read block  first...");
                        return;
                    }
                    variantCodingBuffer = readEtacsCodingVariant();
                    prepareAndFillVariantCodingList(cbShowExtend.isChecked());
            }
            break;
            case R.id.btnEtacsWriteVariant:
                Log.d(TAG, "Write Etacs Variant coding... Vendor is " + parentActivity.etacsVendor);
                if (parentActivity.etacsVendor == SecurityUtils.Vendor.Unknown) {
                    // read block first
                    Log.e(TAG, "Error read etacs variant. Read block  first...");
                    return;
                }
                String variantCoding = edtEtacsVariant.getText().toString().trim();
                if (variantCoding.isEmpty()) {
                    Log.w(TAG, "Variant coding is empty");
                } else {
                    Log.d(TAG, "Variant: " + variantCoding);
                    writeEtacsCodingVariant(variantCoding, parentActivity.etacsVendor);
                    prevVariantCodingBuffer.clear();
                    prevVariantCodingBuffer.addAll(variantCodingBuffer);

                    // TODO: read variant after write
                    //variantCodingBuffer = readEtacsCodingVariant();
                }
                break;
        }
    }

    private void prepareAndFillVariantCodingList(boolean showExtended) {
        if (variantCodingBuffer.isEmpty()) return;

        prevVariantCodingBuffer.clear();
        prevVariantCodingBuffer.addAll(variantCodingBuffer);
        variantCoding.clear();
        variantCoding.addAll(Arrays.stream(EtacsVariantCoding.values())
                //.filter(i -> i.getIdx() % 2 == 0)
                .filter(i -> i.getIdx() != 999 &&
                        (
                                (showExtended && (i.isExtended() || !i.isExtended()))
                                        || (!showExtended && !i.isExtended())
                        )
                )
                .collect(Collectors.toList()));
        etacsVariantCodingAdapter = updateListView(lvEtacsVariant, variantCodingBuffer,
                prevVariantCodingBuffer, variantCoding);
    }

    private void saveVariantToFile() {
        String sValue = bufferToHex(variantCodingBuffer, 0, false);
        File path = parentActivity.getFilesDir();
        SimpleDateFormat formater = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
        Date date = new Date();
        String fName = String.format("etacs_variant_%s.kon", formater.format(date));
        File f = new File(path, fName);

        try {
            FileWriter out = new FileWriter(f);
            out.write(sValue);
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeEtacsCodingVariant(String coding, SecurityUtils.Vendor vendor) {
        if (App.obd.isConnected) {
            parentActivity.runOnUiThread(() -> {
                App.obd.writeEtacsCodingVariant(coding, vendor);
            });
        }
    }

    private ArrayList<Integer> readEtacsCodingVariant() {
        AtomicReference<ArrayList<Integer>> buffer = new AtomicReference<>();
        if (App.obd.isConnected) {
            parentActivity.runOnUiThread(() -> {
                buffer.set(App.obd.readEtacsCodingVariant(parentActivity.etacsVendor));
                if (buffer.get() != null) {
                    String s = bufferToHex(buffer.get(), 2, false);
                    edtEtacsVariant.setText(s);
                    edtEtacsVariant.setEnabled(true);
                    //edtEtacsVariant2.setText(s);
                }
            });
        }
        return buffer.get();
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

    private void loadFile(String fName) {
        BufferedReader in;
        try {
            File path = parentActivity.getFilesDir();
            File f = new File(path, fName);
            in = new BufferedReader(new FileReader(f));
            String sCoding = in.readLine();
            if (sCoding == null || sCoding.isEmpty()) {
                Log.e(TAG, "Error reading coding file " + fName);
                return;
            }
            variantCodingBuffer = hexStringToBuffer(sCoding, 0);
            edtEtacsVariant.setText(sCoding);
            //edtEtacsVariant2.setText(sCoding);
            prepareAndFillVariantCodingList(cbShowExtend.isChecked());
        } catch (FileNotFoundException e) {
            Log.e(TAG, "File " + fName + " not found");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}