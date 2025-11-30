package ru.d51x.kaierutils.dtc;

import static ru.d51x.kaierutils.dtc.DtcUtils.extractErrorsFromBuffer;
import static ru.d51x.kaierutils.utils.StringUtils.hexStringToBuffer;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import ru.d51x.kaierutils.App;
import ru.d51x.kaierutils.R;
import ru.d51x.kaierutils.coding.VariantCodingAdapter;


public class DtcActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = "DTC";
    private List<DtcError> dtcErrorList;

    private CheckBox cbDtcEngine;
    private CheckBox cbDtcCvt;
    private CheckBox cbDtcEtacs;
    private CheckBox cbDtcAfs;
    private CheckBox cbDtcClimate;
    private CheckBox cbDtcMeter;
    private CheckBox cbDtcParking;
    private CheckBox cbDtcAwc;
    private CheckBox cbDtcAbs;
    private CheckBox cbDtcSas;
    private CheckBox cbDtcSrs;
    private CheckBox cbDtcImmo;

    private RecyclerView lvDtcErrors;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dtc);

        Button btnDtcRead = findViewById(R.id.btnDtcRead);
        btnDtcRead.setOnClickListener(this);

        Button btnDtcReset = findViewById(R.id.btnDtcReset);
        btnDtcReset.setOnClickListener(this);

        Button btnDtcTest = findViewById(R.id.btnDtcTest);
        btnDtcTest.setOnClickListener(this);

        cbDtcEngine = findViewById(R.id.cbDtcEngine);
        cbDtcCvt = findViewById(R.id.cbDtcCvt);
        cbDtcEtacs = findViewById(R.id.cbDtcEtacs);
        cbDtcAfs = findViewById(R.id.cbDtcAfs);
        cbDtcClimate = findViewById(R.id.cbDtcClimate);
        cbDtcMeter = findViewById(R.id.cbDtcMeter);
        cbDtcParking = findViewById(R.id.cbDtcParking);
        cbDtcAwc = findViewById(R.id.cbDtcAwc);
        cbDtcAbs = findViewById(R.id.cbDtcAbs);
        cbDtcSas = findViewById(R.id.cbDtcSas);
        cbDtcSrs = findViewById(R.id.cbDtcSrs);
        cbDtcImmo = findViewById(R.id.cbDtcImmo);

        lvDtcErrors = findViewById(R.id.lvDtcErrors);
        lvDtcErrors.setLayoutManager(new LinearLayoutManager(this));
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDtcRead -> readDtcErrors();
            case R.id.btnDtcReset -> dtcErrorsReset();
            case R.id.btnDtcTest -> readDtcErrorsTest();
        }
    }


    private void readDtcErrorsTest() {
        ArrayList<DtcError> dtcErrors = new ArrayList<>();

        String dtcString1 = "5801A51520";
        ArrayList<Integer> buffer = hexStringToBuffer(dtcString1, 0);
        ArrayList<DtcError> dtcErrors1 = extractErrorsFromBuffer(buffer);
        for (DtcError e: dtcErrors1) {
            if (DtcEngineErrorDescription.getByCode(e.getCode()) != null) {
                String description = Objects.requireNonNull(DtcEngineErrorDescription.getByCode(e.getCode())).getDescription() ;
                e.setDescription(description);
            }
            e.setBlock("Engine");
        }
        dtcErrors.addAll(dtcErrors1);

        dtcString1 = "5802C18460C16960";
        buffer = hexStringToBuffer(dtcString1, 0);
        ArrayList<DtcError> dtcErrors2 = extractErrorsFromBuffer(buffer);
        for (DtcError e: dtcErrors2) {
            if (DtcEtacsErrorDescription.getByCode(e.getCode()) != null) {
                String description = Objects.requireNonNull(DtcEtacsErrorDescription.getByCode(e.getCode())).getDescription() ;
                e.setDescription(description);
            }
            e.setBlock("Engine");
        }
        dtcErrors.addAll(dtcErrors2);

        DtcErrorAdapter adapter = new DtcErrorAdapter(DtcActivity.this, R.layout.list_item_dtc, dtcErrors);
        lvDtcErrors.setAdapter(adapter);
    }

    private void readDtcHistory() {

    }

    private void readDtcErrors() {
        App.obd.isServiceCommand = true;
        ArrayList<DtcError> dtcErrors = new ArrayList<>();
        // engine
        if (cbDtcEngine.isChecked()) {
            ArrayList<DtcError> dtcErrorsEngine = App.obd.readDtcEngine();
            for (DtcError e: dtcErrorsEngine) {
                if (DtcEngineErrorDescription.getByCode(e.getCode()) != null) {
                    String description = Objects.requireNonNull(DtcEngineErrorDescription.getByCode(e.getCode())).getDescription() ;
                    e.setDescription(description);
                }
                e.setBlock("Engine");
            }
            dtcErrors.addAll(dtcErrorsEngine);
        }

        if (cbDtcCvt.isChecked()) {
            ArrayList<DtcError> dtcErrorsCvt = App.obd.readDtcCVT();
            for (DtcError e: dtcErrorsCvt) {
                if (DtcCVTErrorDescription.getByCode(e.getCode()) != null) {
                    String description = Objects.requireNonNull(DtcCVTErrorDescription.getByCode(e.getCode())).getDescription() ;
                    e.setDescription(description);
                }
                e.setBlock("CVT");
            }
            dtcErrors.addAll(dtcErrorsCvt);
        }

        if (cbDtcEtacs.isChecked()) {
            ArrayList<DtcError> dtcErrorsEtacs = App.obd.readDtcEtacs();
            for (DtcError e: dtcErrorsEtacs) {
                if (DtcEtacsErrorDescription.getByCode(e.getCode()) != null) {
                    String description = Objects.requireNonNull(DtcEtacsErrorDescription.getByCode(e.getCode())).getDescription() ;
                    e.setDescription(description);
                }
                e.setBlock("ETACS");
            }
            dtcErrors.addAll(dtcErrorsEtacs);
        }

        if (cbDtcAfs.isChecked()) {
            ArrayList<DtcError> dtcErrorsAfs = App.obd.readDtcAFS();
            for (DtcError e: dtcErrorsAfs) {
                if (DtcAFSErrorDescription.getByCode(e.getCode()) != null) {
                    String description = Objects.requireNonNull(DtcAFSErrorDescription.getByCode(e.getCode())).getDescription() ;
                    e.setDescription(description);
                }
                e.setBlock("AFS");
            }
            dtcErrors.addAll(dtcErrorsAfs);
        }

        if (cbDtcAwc.isChecked()) {
            ArrayList<DtcError> dtcErrorsAwc = App.obd.readDtcAWC();
            for (DtcError e: dtcErrorsAwc) {
                if (DtcAWCErrorDescription.getByCode(e.getCode()) != null) {
                    String description = Objects.requireNonNull(DtcAWCErrorDescription.getByCode(e.getCode())).getDescription() ;
                    e.setDescription(description);
                }
                e.setBlock("4WD/AWC");
            }
            dtcErrors.addAll(dtcErrorsAwc);
        }

        if (cbDtcAbs.isChecked()) {
            ArrayList<DtcError> dtcErrorsAbs = App.obd.readDtcABS();
            for (DtcError e: dtcErrorsAbs) {
                if (DtcAbsAscErrorDescription.getByCode(e.getCode()) != null) {
                    String description = Objects.requireNonNull(DtcAbsAscErrorDescription.getByCode(e.getCode())).getDescription() ;
                    e.setDescription(description);
                }
                e.setBlock("ABS/ASC");
            }
            dtcErrors.addAll(dtcErrorsAbs);
        }

//        if (cbDtcSas.isChecked()) {
//            ArrayList<DtcError> dtcErrorsSas = App.obd.readDtcSAS();
//            for (DtcError e: dtcErrorsSas) {
//                String description = Objects.requireNonNull(DtcS.getByCode(e.getCode())).getDescription();
//                e.setDescription(description);
//            }
//            dtcErrors.add(new Pair<>("SAS", dtcErrorsSas));
//        }

        if (cbDtcMeter.isChecked()) {
            ArrayList<DtcError> dtcErrorsMeter = App.obd.readDtcMeter();
            for (DtcError e: dtcErrorsMeter) {
                if (DtcMeterErrorDescription.getByCode(e.getCode()) != null) {
                    String description = Objects.requireNonNull(DtcMeterErrorDescription.getByCode(e.getCode())).getDescription() ;
                    e.setDescription(description);
                }
                e.setBlock("Combine Meter");
            }
            dtcErrors.addAll(dtcErrorsMeter);
        }

        if (cbDtcParking.isChecked()) {
            ArrayList<DtcError> dtcErrorsParking = App.obd.readDtcParking();
            for (DtcError e: dtcErrorsParking) {
                if (DtcParkingErrorDescription.getByCode(e.getCode()) != null) {
                    String description = Objects.requireNonNull(DtcParkingErrorDescription.getByCode(e.getCode())).getDescription() ;
                    e.setDescription(description);
                }
                e.setBlock("Parking");
            }
            dtcErrors.addAll(dtcErrorsParking);
        }

        if (cbDtcClimate.isChecked()) {
            ArrayList<DtcError> dtcErrorsClimate = App.obd.readDtcClimate();
            for (DtcError e: dtcErrorsClimate) {
                if (DtcClimateErrorDescription.getByCode(e.getCode()) != null) {
                    String description = Objects.requireNonNull(DtcClimateErrorDescription.getByCode(e.getCode())).getDescription() ;
                    e.setDescription(description);
                }
                e.setBlock("Climate");
            }
            dtcErrors.addAll(dtcErrorsClimate);
        }

        if (cbDtcImmo.isChecked()) {
            ArrayList<DtcError> dtcErrorsImmo = App.obd.readDtcImmo();
            for (DtcError e: dtcErrorsImmo) {
                if (DtcKosWcmErrorDescription.getByCode(e.getCode()) != null) {
                    String description = Objects.requireNonNull(DtcKosWcmErrorDescription.getByCode(e.getCode())).getDescription() ;
                    e.setDescription(description);
                }
                e.setBlock("KOS/WCM/Immo");
            }
            dtcErrors.addAll(dtcErrorsImmo);
        }

        if (cbDtcSrs.isChecked()) {
            ArrayList<DtcError> dtcErrorsSrs = App.obd.readDtcSRS();
            for (DtcError e: dtcErrorsSrs) {
                if (DtcSRSErrorDescription.getByCode(e.getCode()) != null) {
                    String description = Objects.requireNonNull(DtcSRSErrorDescription.getByCode(e.getCode())).getDescription() ;
                    e.setDescription(description);
                }
                e.setBlock("SRS");
            }
            dtcErrors.addAll(dtcErrorsSrs);
        }
        App.obd.isServiceCommand = false;

        DtcErrorAdapter adapter = new DtcErrorAdapter(DtcActivity.this, R.layout.list_item_dtc, dtcErrors);
        lvDtcErrors.setAdapter(adapter);
    }

    private void dtcErrorsReset() {

    }
}

