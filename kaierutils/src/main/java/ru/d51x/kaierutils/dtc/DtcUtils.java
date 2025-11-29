package ru.d51x.kaierutils.dtc;

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
                int codeGroup = buffer.get(2 + i * 3) >> 4 & 0xF;
                String sCodeGroup = getDtcErrorType(codeGroup);

                int errNumber = buffer.get(2 + i * 3) & 0xF;
                boolean isActive = isActiveError(buffer.get(2 + i * 3 + 2));
                String err = String.format("%s%X%2X", sCodeGroup, errNumber, buffer.get(2 + i * 3 + 1));

                DtcError dtcError = new DtcError(err, isActive);
                dtcErrors.add(dtcError);
            }
        }
        return dtcErrors;
    }
}
