package ru.d51x.kaierutils.dtc;

public enum DtcMeterErrorDescription {

    // Combination Meter
    B1200("B1200", "Malfunction of odometer"),
    B1201("B1201", "Fuel information error"),
    B1209("B1209", "Test mode"),
    B2203("B2203", "Chassis No. not programmed"),
    B2463("B2463", "Rheostat switch seizure"),
    B2464("B2464", "Meter information switch seizure"),
    B2465("B2465", "Ignition switch signal error"),
    U0019("U0019", "Bus off (CAN-B)"),
    U0100("U0100", "Engine-ECU CAN timeout"),
    U0141("U0141", "ETACS-ECU CAN timeout"),
    U0151("U0151", "SRS-ECU CAN time-out"),
    U0164("U0164", "A/C-ECU CAN timeout"),
    U0168("U0168", "OS/WCM CAN timeout"),
    U0184("U0184", "Audio CAN timeout"),
    U1415("U1415", "Coding not completed/Data fail")
    ;
    private String code;
    private String description;

    DtcMeterErrorDescription(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static DtcMeterErrorDescription getByCode(String code) {
        for (DtcMeterErrorDescription dtc : values()) {
            if (dtc.code.equals(code)) {
                return dtc;
            }
        }
        return null;
    }
}
