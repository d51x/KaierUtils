package ru.d51x.kaierutils.dtc;

import android.util.Pair;

import java.util.ArrayList;

public class DtcUtils {

    public static String getDtcErrorType(int type) {
        if (type >=0 && type < 4) {
            return "P" + type;
        }
        else if (type < 8) {
            return "C" + type % 4;
        }
        else if (type < 0xC) { // 12
            return "B" + type % 8;
        }
        else if (type <= 0xF) { // 15
            return "U" + type % 12;
        }
        return "";
    }

    public static boolean isActiveError(int type) {
        int res = type & 0b01000000;
        res = res >> 6;
        return res == 0;
    }

    public static ArrayList<DtcError> extractErrorsFromBuffer(ArrayList<Integer> buffer) {
        ArrayList<DtcError> dtcErrors = new ArrayList<>();
        if (buffer != null && !buffer.isEmpty() && buffer.get(0) == (0x18 + 0x40)) {
            //int dtcCount = buffer.get(1);
            for (int i = 0; i < buffer.get(1); i++) {
                int idx = 2 + i * 3;

                int codeGroup = buffer.get(idx) >> 4 & 0xF;
                String sCodeGroup = getDtcErrorType(codeGroup);

                int errNumber = buffer.get(idx) & 0xF;
                boolean isActive = isActiveError(buffer.get(idx + 2));
                String err = String.format("%s%X%2X", sCodeGroup, errNumber, buffer.get(idx + 1));

                DtcError dtcError = new DtcError(err, isActive);
                dtcErrors.add(dtcError);
            }
        }
        return dtcErrors;
    }

    public static Pair<Integer, ArrayList<DtcHistoryError>> extractHistoryErrorsFromBuffer(ArrayList<Integer> buffer) {
        ArrayList<DtcHistoryError> dtcErrors = new ArrayList<>();
        int odoLastDtc = 0;
        if (buffer != null && !buffer.isEmpty() && buffer.get(0) == (0x33 + 0x40) && buffer.get(1) == 0xE2) {
            // int dtcReadCount = buffer.get(2); //  how many times dtc read from block
            // buffer.size always 37 bytes or 8 saved errors

            odoLastDtc = (buffer.get(3) << 8 | buffer.get(4)) * 0x40 / 10;

            for (int i = 0; i < 8; i++) {
                int idx = 5 + i * 4;

                if (buffer.get(idx) == 0xFF && buffer.get(idx+1) == 0xFF) break;

                int codeGroup = buffer.get(idx) >> 4 & 0xF;
                String sCodeGroup = getDtcErrorType(codeGroup);

                int errNumber = buffer.get(idx) & 0xF;
                String err = String.format("%s%X%2X", sCodeGroup, errNumber, buffer.get(idx + 1));

                int odoDtcError = buffer.get(idx + 2) << 8 | buffer.get(idx + 3);
                odoDtcError = odoDtcError * 0x40 / 10;

                DtcHistoryError dtcError = new DtcHistoryError(err, odoDtcError);
                dtcErrors.add(dtcError);
            }
        }
        return new Pair<>(odoLastDtc, dtcErrors);
    }
}
