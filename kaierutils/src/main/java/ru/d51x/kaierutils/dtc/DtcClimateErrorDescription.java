package ru.d51x.kaierutils.dtc;

public enum DtcClimateErrorDescription {

    // Climate
    B1000("B1000", "Communication error with control panel"),
    B1003("B1003", "Mode dial SW error"),
    B1018("B1018", "Temperature control dial SW errore"),
    B1021("B1021", "Fan dial SW error"),
    B1031("B1031", "Fin thermo sensor system (short circuit)"),
    B1032("B1032", "Fin thermo sensor system (open circuit)"),
    B1079("B1079", "A/C refrigerant leaks"),
    B10C0("B10C0", "Interior temperature sensor system (short circuit)"),
    B10C1("B10C1", "Interior temperature sensor system (open circuit)"),
    B2214("B2214", "Control panel failure"),
    U0019("U0019", "Bus off (CAN-B)"),
    U0141("U0141", "ETACS-ECU CAN timeout"),
    U0151("U0151", "SRS-ECU CAN time-out"),
    U0155("U0155", "Combination meter CAN time-out"),
    U0168("U0168", "OS/WCM CAN timeout"),
    U0184("U0184", "Audio CAN timeout"),
    ;
    private String code;
    private String description;

    DtcClimateErrorDescription(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static DtcClimateErrorDescription getByCode(String code) {
        for (DtcClimateErrorDescription dtc : values()) {
            if (dtc.code.equals(code)) {
                return dtc;
            }
        }
        return null;
    }
}
