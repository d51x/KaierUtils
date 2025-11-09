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

    private EditText edtEtacsVariant;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_etacs_variant, container, false);
        parentActivity = (EtacsActivity) getActivity();

        Button btnEtacsReadVariant = v.findViewById(R.id.btnEtacsReadVariant);
        btnEtacsReadVariant.setOnClickListener(this);

        Button btnEtacsWriteVariant = v.findViewById(R.id.btnEtacsWriteVariant);
        btnEtacsWriteVariant.setOnClickListener(this);



        edtEtacsVariant = v.findViewById(R.id.edtEtacsVariant);
        return v;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnEtacsReadVariant:
                Log.d(TAG, "Read Etacs Variant coding...");
                readEtacsCodingVariant();
                break;
            case R.id.btnEtacsWriteVariant:
                Log.d(TAG, "Write Etacs Variant coding...");
                String variantCoding = edtEtacsVariant.getText().toString().trim();
                if (variantCoding.isEmpty()) {
                    Log.w(TAG, "Variant coding is empty");
                } else {
                    Log.d(TAG, "Variant: " + variantCoding);
                    writeEtacsCodingVariant(variantCoding, parentActivity.etacsVendor);
                }
                break;
        }
    }


    private void writeEtacsCodingVariant(String coding, SecurityUtils.Vendor vendor) {
        if (App.obd.isConnected) {
            parentActivity.runOnUiThread(() -> {
                App.obd.writeEtacsCodingVariant(coding, vendor);
                // String s = bufferToHex(buffer, 0, false);
            });
        }
    }

    private void readEtacsCodingVariant() {
        if (App.obd.isConnected) {
            parentActivity.runOnUiThread(() -> {
                ArrayList<Integer> buffer = App.obd.readEtacsCodingVariant(parentActivity.etacsVendor);
                if (buffer != null) {
                    String s = bufferToHex(buffer, 2, false);
                    edtEtacsVariant.setText(s);
                    edtEtacsVariant.setEnabled(true);
                }
            });
        }
    }
}