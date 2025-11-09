package ru.d51x.kaierutils.etacs;

import static ru.d51x.kaierutils.utils.StringUtils.bufferToHex;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import ru.d51x.kaierutils.App;
import ru.d51x.kaierutils.R;
import ru.d51x.kaierutils.utils.SecurityUtils;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EngineCodingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EngineCodingFragment extends Fragment   implements View.OnClickListener {
    public static final String TAG = "EngineCoding";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EditText edtEngineCoding;

    private EtacsActivity parentActivity;
    public EngineCodingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EngineCodingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EngineCodingFragment newInstance(String param1, String param2) {
        EngineCodingFragment fragment = new EngineCodingFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_engine_coding, container, false);
        parentActivity = (EtacsActivity) getActivity();

        Button btnEngineCodingRead = v.findViewById(R.id.btnEngineCodingRead);
        btnEngineCodingRead.setOnClickListener(this);

        Button btnEngineCodingWrite = v.findViewById(R.id.btnEngineCodingWrite);
        btnEngineCodingWrite.setOnClickListener(this);

        edtEngineCoding = v.findViewById(R.id.edtEngineCoding);
        return v;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
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
                    writeEngineCoding(engineCoding, parentActivity.engineVendor);
                }
                break;
        }
    }

    private void readEngineCoding() {
        if (App.obd.isConnected) {
            parentActivity.runOnUiThread(() -> {
                ArrayList<Integer> buffer = App.obd.readEngineCoding(parentActivity.engineVendor);
                if (buffer != null) {
                    String s = bufferToHex(buffer, 2, false);
                    edtEngineCoding.setText(s);
                    edtEngineCoding.setEnabled(true);
                }
            });
        }
    }

        private void writeEngineCoding(String coding, SecurityUtils.Vendor vendor) {
        if (App.obd.isConnected) {
            parentActivity.runOnUiThread(() -> App.obd.writeEngineCoding(coding, vendor));
        }
    }
}