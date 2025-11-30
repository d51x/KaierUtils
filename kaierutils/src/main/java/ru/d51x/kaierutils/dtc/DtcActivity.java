package ru.d51x.kaierutils.dtc;

import static ru.d51x.kaierutils.dtc.DtcUtils.extractErrorsFromBuffer;
import static ru.d51x.kaierutils.dtc.DtcUtils.extractHistoryErrorsFromBuffer;
import static ru.d51x.kaierutils.utils.StringUtils.hexStringToBuffer;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ConcatAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
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
    private CheckBox cbHistory;
    private ProgressBar dtcProgressBar;
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

        cbHistory = findViewById(R.id.cbHistory);

        dtcProgressBar = findViewById(R.id.dtcProgressBar);
        dtcProgressBar.setVisibility(View.GONE);

        lvDtcErrors = findViewById(R.id.lvDtcErrors);
        lvDtcErrors.setLayoutManager(new LinearLayoutManager(this));
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDtcRead -> {
                dtcProgressBar.setVisibility(View.VISIBLE);
                readDtcErrors();
                readDtcHistory();
                dtcProgressBar.setVisibility(View.GONE);
            }
            case R.id.btnDtcReset -> {
                dtcProgressBar.setVisibility(View.VISIBLE);
                dtcErrorsReset();
                dtcProgressBar.setVisibility(View.GONE);
            }
            case R.id.btnDtcTest -> {
                dtcProgressBar.setVisibility(View.VISIBLE);
                readDtcErrorsTest();
                dtcProgressBar.setVisibility(View.GONE);
            }
        }
    }


    private void readDtcErrorsTest() {
        List<Object> items = new ArrayList<>();

        String dtcString1 = "";

        if (cbHistory.isChecked()) {
            dtcString1 = "73E2136659C217023FC1840721C1691297C01914BE96A85011FFFFFFFFFFFFFFFFFFFFFFFF";

            ArrayList<Integer> buffer = hexStringToBuffer(dtcString1, 0);
            Pair<Integer, ArrayList<DtcHistoryError>> dtcErrors1 = extractHistoryErrorsFromBuffer(buffer);
            for (DtcHistoryError e : dtcErrors1.second) {
                if (DtcEtacsErrorDescription.getByCode(e.getCode()) != null) {
                    String description = Objects.requireNonNull(DtcEtacsErrorDescription.getByCode(e.getCode())).getDescription();
                    e.setDescription(description);
                }
            }

            items.add(new DtcHeader("ETACS", dtcErrors1.second.size(), dtcErrors1.first));
            items.addAll(dtcErrors1.second);

            // *******
            dtcString1 = "73E2000000D4150000C4156318FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF";

            buffer = hexStringToBuffer(dtcString1, 0);
            dtcErrors1 = extractHistoryErrorsFromBuffer(buffer);
            for (DtcHistoryError e : dtcErrors1.second) {
                if (DtcAWCErrorDescription.getByCode(e.getCode()) != null) {
                    String description = Objects.requireNonNull(DtcAWCErrorDescription.getByCode(e.getCode())).getDescription();
                    e.setDescription(description);
                }
            }

            items.add(new DtcHeader("4WD/AWC", dtcErrors1.second.size(), dtcErrors1.first));
            items.addAll(dtcErrors1.second);

            // *******
            dtcString1 = "73E223665917736318FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF";

            buffer = hexStringToBuffer(dtcString1, 0);
            dtcErrors1 = extractHistoryErrorsFromBuffer(buffer);
            for (DtcHistoryError e : dtcErrors1.second) {
                if (DtcCVTErrorDescription.getByCode(e.getCode()) != null) {
                    String description = Objects.requireNonNull(DtcCVTErrorDescription.getByCode(e.getCode())).getDescription();
                    e.setDescription(description);
                }
            }

            items.add(new DtcHeader("CVT/AT/TC-SST", dtcErrors1.second.size(), dtcErrors1.first));
            items.addAll(dtcErrors1.second);

            // *******
            dtcString1 = "73E2116659C1844AFCC184630AC184117BC18413EFC01914BEC1842B9BC18439D4C184420F";

            buffer = hexStringToBuffer(dtcString1, 0);
            dtcErrors1 = extractHistoryErrorsFromBuffer(buffer);
            for (DtcHistoryError e : dtcErrors1.second) {
                if (DtcClimateErrorDescription.getByCode(e.getCode()) != null) {
                    String description = Objects.requireNonNull(DtcClimateErrorDescription.getByCode(e.getCode())).getDescription();
                    e.setDescription(description);
                }
            }

            items.add(new DtcHeader("Climate", dtcErrors1.second.size(), dtcErrors1.first));
            items.addAll(dtcErrors1.second);

            // *******
            dtcString1 = "73E2116659C1846659C1846650C1846651C1846652C1846653C1846655C1846657C1846658";

            buffer = hexStringToBuffer(dtcString1, 0);
            dtcErrors1 = extractHistoryErrorsFromBuffer(buffer);
            for (DtcHistoryError e : dtcErrors1.second) {
                if (DtcSRSErrorDescription.getByCode(e.getCode()) != null) {
                    String description = Objects.requireNonNull(DtcSRSErrorDescription.getByCode(e.getCode())).getDescription();
                    e.setDescription(description);
                }
            }

            items.add(new DtcHeader("SRS", dtcErrors1.second.size(), dtcErrors1.first));
            items.addAll(dtcErrors1.second);

        } else {
            dtcString1 = "5801A51520";
            ArrayList<Integer> buffer = hexStringToBuffer(dtcString1, 0);
            ArrayList<DtcError> dtcErrors1 = extractErrorsFromBuffer(buffer);
            for (DtcError e : dtcErrors1) {
                if (DtcEngineErrorDescription.getByCode(e.getCode()) != null) {
                    String description = Objects.requireNonNull(DtcEngineErrorDescription.getByCode(e.getCode())).getDescription();
                    e.setDescription(description);
                }
            }

            items.add(new DtcHeader("Engine", dtcErrors1.size(), 0));
            items.addAll(dtcErrors1);

            dtcString1 = "5802C18460C16960";
            buffer = hexStringToBuffer(dtcString1, 0);
            ArrayList<DtcError> dtcErrors2 = extractErrorsFromBuffer(buffer);
            for (DtcError e : dtcErrors2) {
                if (DtcEtacsErrorDescription.getByCode(e.getCode()) != null) {
                    String description = Objects.requireNonNull(DtcEtacsErrorDescription.getByCode(e.getCode())).getDescription();
                    e.setDescription(description);
                }
            }
            items.add(new DtcHeader("Etacs", dtcErrors2.size(), 0));
            items.addAll(dtcErrors2);

            dtcString1 = "5800";
            buffer = hexStringToBuffer(dtcString1, 0);
            ArrayList<DtcError> dtcErrors3 = extractErrorsFromBuffer(buffer);
            for (DtcError e : dtcErrors3) {
                if (DtcCVTErrorDescription.getByCode(e.getCode()) != null) {
                    String description = Objects.requireNonNull(DtcCVTErrorDescription.getByCode(e.getCode())).getDescription();
                    e.setDescription(description);
                }
            }
            items.add(new DtcHeader("CVT", dtcErrors3.size(), 0));
            items.addAll(dtcErrors3);
        }

        DtcErrorAdapter adapter = new DtcErrorAdapter(DtcActivity.this, items);
        lvDtcErrors.setAdapter(adapter);
    }

    private void readDtcHistory() {
        if (!cbHistory.isChecked()) return;
        App.obd.isServiceCommand = true;
        List<Object> items = new ArrayList<>();

        // engine
        if (cbDtcEngine.isChecked()) {
            Pair<Integer, ArrayList<DtcHistoryError>> dtcErrorsEngine = App.obd.readDtcHistoryEngine();
            for (DtcHistoryError e: dtcErrorsEngine.second) {
                if (DtcEngineErrorDescription.getByCode(e.getCode()) != null) {
                    String description = Objects.requireNonNull(DtcEngineErrorDescription.getByCode(e.getCode())).getDescription() ;
                    e.setDescription(description);
                }
            }
            items.add(new DtcHeader("Engine", dtcErrorsEngine.second.size(), dtcErrorsEngine.first));
            items.addAll(dtcErrorsEngine.second);
        }

        if (cbDtcCvt.isChecked()) {
            Pair<Integer, ArrayList<DtcHistoryError>> dtcErrorsCvt = App.obd.readDtcHistoryCVT();
            for (DtcHistoryError e: dtcErrorsCvt.second) {
                if (DtcCVTErrorDescription.getByCode(e.getCode()) != null) {
                    String description = Objects.requireNonNull(DtcCVTErrorDescription.getByCode(e.getCode())).getDescription() ;
                    e.setDescription(description);
                }
            }
            items.add(new DtcHeader("CVT", dtcErrorsCvt.second.size(), dtcErrorsCvt.first));
            items.addAll(dtcErrorsCvt.second);
        }

        if (cbDtcEtacs.isChecked()) {
            Pair<Integer, ArrayList<DtcHistoryError>> dtcErrorsEtacs = App.obd.readDtcHistoryEtacs();
            for (DtcHistoryError e: dtcErrorsEtacs.second) {
                if (DtcEtacsErrorDescription.getByCode(e.getCode()) != null) {
                    String description = Objects.requireNonNull(DtcEtacsErrorDescription.getByCode(e.getCode())).getDescription() ;
                    e.setDescription(description);
                }
            }
            items.add(new DtcHeader("ETACS", dtcErrorsEtacs.second.size(), dtcErrorsEtacs.first));
            items.addAll(dtcErrorsEtacs.second);
        }

        if (cbDtcAfs.isChecked()) {
            Pair<Integer, ArrayList<DtcHistoryError>> dtcErrorsAfs = App.obd.readDtcHistoryAFS();
            for (DtcHistoryError e: dtcErrorsAfs.second) {
                if (DtcAFSErrorDescription.getByCode(e.getCode()) != null) {
                    String description = Objects.requireNonNull(DtcAFSErrorDescription.getByCode(e.getCode())).getDescription() ;
                    e.setDescription(description);
                }
            }
            items.add(new DtcHeader("AFS", dtcErrorsAfs.second.size(), dtcErrorsAfs.first));
            items.addAll(dtcErrorsAfs.second);
        }

        if (cbDtcAwc.isChecked()) {
            Pair<Integer, ArrayList<DtcHistoryError>> dtcErrorsAwc = App.obd.readDtcHistoryAWC();
            for (DtcHistoryError e: dtcErrorsAwc.second) {
                if (DtcAWCErrorDescription.getByCode(e.getCode()) != null) {
                    String description = Objects.requireNonNull(DtcAWCErrorDescription.getByCode(e.getCode())).getDescription() ;
                    e.setDescription(description);
                }
            }
            items.add(new DtcHeader("4WD/AWC", dtcErrorsAwc.second.size(), dtcErrorsAwc.first));
            items.addAll(dtcErrorsAwc.second);
        }

        if (cbDtcAbs.isChecked()) {
            Pair<Integer, ArrayList<DtcHistoryError>> dtcErrorsAbs = App.obd.readDtcHistoryABS();
            for (DtcHistoryError e: dtcErrorsAbs.second) {
                if (DtcAbsAscErrorDescription.getByCode(e.getCode()) != null) {
                    String description = Objects.requireNonNull(DtcAbsAscErrorDescription.getByCode(e.getCode())).getDescription() ;
                    e.setDescription(description);
                }
            }
            items.add(new DtcHeader("ABS/ASC", dtcErrorsAbs.second.size(), dtcErrorsAbs.first));
            items.addAll(dtcErrorsAbs.second);
        }

        if (cbDtcMeter.isChecked()) {
            Pair<Integer, ArrayList<DtcHistoryError>> dtcErrorsMeter = App.obd.readDtcHistoryMeter();
            for (DtcHistoryError e: dtcErrorsMeter.second) {
                if (DtcMeterErrorDescription.getByCode(e.getCode()) != null) {
                    String description = Objects.requireNonNull(DtcMeterErrorDescription.getByCode(e.getCode())).getDescription() ;
                    e.setDescription(description);
                }
            }
            items.add(new DtcHeader("Combine Meter", dtcErrorsMeter.second.size(), dtcErrorsMeter.first));
            items.addAll(dtcErrorsMeter.second);
        }

        if (cbDtcParking.isChecked()) {
            Pair<Integer, ArrayList<DtcHistoryError>> dtcErrorsParking = App.obd.readDtcHistoryParking();
            for (DtcHistoryError e: dtcErrorsParking.second) {
                if (DtcParkingErrorDescription.getByCode(e.getCode()) != null) {
                    String description = Objects.requireNonNull(DtcParkingErrorDescription.getByCode(e.getCode())).getDescription() ;
                    e.setDescription(description);
                }
            }
            items.add(new DtcHeader("Parking", dtcErrorsParking.second.size(), dtcErrorsParking.first));
            items.addAll(dtcErrorsParking.second);
        }

        if (cbDtcClimate.isChecked()) {
            Pair<Integer, ArrayList<DtcHistoryError>> dtcErrorsClimate = App.obd.readDtcHistoryClimate();
            for (DtcHistoryError e: dtcErrorsClimate.second) {
                if (DtcClimateErrorDescription.getByCode(e.getCode()) != null) {
                    String description = Objects.requireNonNull(DtcClimateErrorDescription.getByCode(e.getCode())).getDescription() ;
                    e.setDescription(description);
                }
            }
            items.add(new DtcHeader("Climate", dtcErrorsClimate.second.size(), dtcErrorsClimate.first));
            items.addAll(dtcErrorsClimate.second);
        }

        if (cbDtcImmo.isChecked()) {
            Pair<Integer, ArrayList<DtcHistoryError>> dtcErrorsImmo = App.obd.readDtcHistoryImmo();
            for (DtcHistoryError e: dtcErrorsImmo.second) {
                if (DtcKosWcmErrorDescription.getByCode(e.getCode()) != null) {
                    String description = Objects.requireNonNull(DtcKosWcmErrorDescription.getByCode(e.getCode())).getDescription() ;
                    e.setDescription(description);
                }
            }
            items.add(new DtcHeader("KOS/WCM/Immo", dtcErrorsImmo.second.size(), dtcErrorsImmo.first));
            items.addAll(dtcErrorsImmo.second);
        }

        if (cbDtcSrs.isChecked()) {
            Pair<Integer, ArrayList<DtcHistoryError>> dtcErrorsSrs = App.obd.readDtcHistorySRS();
            for (DtcHistoryError e: dtcErrorsSrs.second) {
                if (DtcSRSErrorDescription.getByCode(e.getCode()) != null) {
                    String description = Objects.requireNonNull(DtcSRSErrorDescription.getByCode(e.getCode())).getDescription() ;
                    e.setDescription(description);
                }
            }
            items.add(new DtcHeader("SRS", dtcErrorsSrs.second.size(), dtcErrorsSrs.first));
            items.addAll(dtcErrorsSrs.second);
        }

        App.obd.isServiceCommand = false;

        DtcErrorAdapter adapter = new DtcErrorAdapter(DtcActivity.this, items);
        lvDtcErrors.setAdapter(adapter);
    }

    private void readDtcErrors() {
        if (cbHistory.isChecked()) return;
        App.obd.isServiceCommand = true;
        List<Object> items = new ArrayList<>();

        // engine
        if (cbDtcEngine.isChecked()) {
            ArrayList<DtcError> dtcErrorsEngine = App.obd.readDtcEngine();
            for (DtcError e: dtcErrorsEngine) {
                if (DtcEngineErrorDescription.getByCode(e.getCode()) != null) {
                    String description = Objects.requireNonNull(DtcEngineErrorDescription.getByCode(e.getCode())).getDescription() ;
                    e.setDescription(description);
                }
            }
            items.add(new DtcHeader("Engine", dtcErrorsEngine.size(), 0));
            items.addAll(dtcErrorsEngine);
        }

        if (cbDtcCvt.isChecked()) {
            ArrayList<DtcError> dtcErrorsCvt = App.obd.readDtcCVT();
            for (DtcError e: dtcErrorsCvt) {
                if (DtcCVTErrorDescription.getByCode(e.getCode()) != null) {
                    String description = Objects.requireNonNull(DtcCVTErrorDescription.getByCode(e.getCode())).getDescription() ;
                    e.setDescription(description);
                }
            }
            items.add(new DtcHeader("CVT", dtcErrorsCvt.size(), 0));
            items.addAll(dtcErrorsCvt);
        }

        if (cbDtcEtacs.isChecked()) {
            ArrayList<DtcError> dtcErrorsEtacs = App.obd.readDtcEtacs();
            for (DtcError e: dtcErrorsEtacs) {
                if (DtcEtacsErrorDescription.getByCode(e.getCode()) != null) {
                    String description = Objects.requireNonNull(DtcEtacsErrorDescription.getByCode(e.getCode())).getDescription() ;
                    e.setDescription(description);
                }
            }
            items.add(new DtcHeader("ETACS", dtcErrorsEtacs.size(), 0));
            items.addAll(dtcErrorsEtacs);
        }

        if (cbDtcAfs.isChecked()) {
            ArrayList<DtcError> dtcErrorsAfs = App.obd.readDtcAFS();
            for (DtcError e: dtcErrorsAfs) {
                if (DtcAFSErrorDescription.getByCode(e.getCode()) != null) {
                    String description = Objects.requireNonNull(DtcAFSErrorDescription.getByCode(e.getCode())).getDescription() ;
                    e.setDescription(description);
                }
            }
            items.add(new DtcHeader("AFS", dtcErrorsAfs.size(), 0));
            items.addAll(dtcErrorsAfs);
        }

        if (cbDtcAwc.isChecked()) {
            ArrayList<DtcError> dtcErrorsAwc = App.obd.readDtcAWC();
            for (DtcError e: dtcErrorsAwc) {
                if (DtcAWCErrorDescription.getByCode(e.getCode()) != null) {
                    String description = Objects.requireNonNull(DtcAWCErrorDescription.getByCode(e.getCode())).getDescription() ;
                    e.setDescription(description);
                }
            }
            items.add(new DtcHeader("4WD/AWC", dtcErrorsAwc.size(), 0));
            items.addAll(dtcErrorsAwc);
        }

        if (cbDtcAbs.isChecked()) {
            ArrayList<DtcError> dtcErrorsAbs = App.obd.readDtcABS();
            for (DtcError e: dtcErrorsAbs) {
                if (DtcAbsAscErrorDescription.getByCode(e.getCode()) != null) {
                    String description = Objects.requireNonNull(DtcAbsAscErrorDescription.getByCode(e.getCode())).getDescription() ;
                    e.setDescription(description);
                }
            }
            items.add(new DtcHeader("ABS/ASC", dtcErrorsAbs.size(), 0));
            items.addAll(dtcErrorsAbs);
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
            }
            items.add(new DtcHeader("Combine Meter", dtcErrorsMeter.size(), 0));
            items.addAll(dtcErrorsMeter);
        }

        if (cbDtcParking.isChecked()) {
            ArrayList<DtcError> dtcErrorsParking = App.obd.readDtcParking();
            for (DtcError e: dtcErrorsParking) {
                if (DtcParkingErrorDescription.getByCode(e.getCode()) != null) {
                    String description = Objects.requireNonNull(DtcParkingErrorDescription.getByCode(e.getCode())).getDescription() ;
                    e.setDescription(description);
                }
            }
            items.add(new DtcHeader("Parking", dtcErrorsParking.size(), 0));
            items.addAll(dtcErrorsParking);
        }

        if (cbDtcClimate.isChecked()) {
            ArrayList<DtcError> dtcErrorsClimate = App.obd.readDtcClimate();
            for (DtcError e: dtcErrorsClimate) {
                if (DtcClimateErrorDescription.getByCode(e.getCode()) != null) {
                    String description = Objects.requireNonNull(DtcClimateErrorDescription.getByCode(e.getCode())).getDescription() ;
                    e.setDescription(description);
                }
            }
            items.add(new DtcHeader("Climate", dtcErrorsClimate.size(), 0));
            items.addAll(dtcErrorsClimate);
        }

        if (cbDtcImmo.isChecked()) {
            ArrayList<DtcError> dtcErrorsImmo = App.obd.readDtcImmo();
            for (DtcError e: dtcErrorsImmo) {
                if (DtcKosWcmErrorDescription.getByCode(e.getCode()) != null) {
                    String description = Objects.requireNonNull(DtcKosWcmErrorDescription.getByCode(e.getCode())).getDescription() ;
                    e.setDescription(description);
                }
            }
            items.add(new DtcHeader("KOS/WCM/Immo", dtcErrorsImmo.size(), 0));
            items.addAll(dtcErrorsImmo);
        }

        if (cbDtcSrs.isChecked()) {
            ArrayList<DtcError> dtcErrorsSrs = App.obd.readDtcSRS();
            for (DtcError e: dtcErrorsSrs) {
                if (DtcSRSErrorDescription.getByCode(e.getCode()) != null) {
                    String description = Objects.requireNonNull(DtcSRSErrorDescription.getByCode(e.getCode())).getDescription() ;
                    e.setDescription(description);
                }
            }
            items.add(new DtcHeader("SRS", dtcErrorsSrs.size(), 0));
            items.addAll(dtcErrorsSrs);
        }
        App.obd.isServiceCommand = false;

        DtcErrorAdapter adapter = new DtcErrorAdapter(DtcActivity.this, items);
        lvDtcErrors.setAdapter(adapter);
    }

    private void dtcErrorsReset() {
        App.obd.isServiceCommand = true;

        // engine
        if (cbDtcEngine.isChecked()) {
            App.obd.resetDtcEngine();
        }

        if (cbDtcCvt.isChecked()) {
            App.obd.resetDtcCVT();
        }

        if (cbDtcEtacs.isChecked()) {
            App.obd.readDtcEtacs();
        }

        if (cbDtcAfs.isChecked()) {
            App.obd.readDtcAFS();
        }

        if (cbDtcAwc.isChecked()) {
            App.obd.readDtcAWC();
        }

        if (cbDtcAbs.isChecked()) {
            App.obd.readDtcABS();
        }

        if (cbDtcMeter.isChecked()) {
            App.obd.readDtcMeter();
        }

        if (cbDtcParking.isChecked()) {
            App.obd.readDtcParking();
        }

        if (cbDtcClimate.isChecked()) {
            App.obd.readDtcClimate();
        }

        if (cbDtcImmo.isChecked()) {
            App.obd.readDtcImmo();
        }

        if (cbDtcSrs.isChecked()) {
            App.obd.readDtcSRS();
        }

        App.obd.isServiceCommand = false;
    }
}

