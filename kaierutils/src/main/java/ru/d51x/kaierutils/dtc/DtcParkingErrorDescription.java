package ru.d51x.kaierutils.dtc;

public enum DtcParkingErrorDescription {
    // Parking
    B252E("B252E", "Rear corner sensor (RH) wire disconnection"),
    B2530("B2530", "Rear corner sensor (LH) wire disconnection"),
    B2532("B2532", "Back sensor (RH) wire disconnection"),
    B2534("B2534", "Back sensor (LH) wire disconnection"),
    B252F("B252F", "Rear corner sensor (RH) error"),
    B2531("B2531", "Rear corner sensor (LH) error"),
    B2533("B2533", "Back sensor (RH) error"),
    B2535("B2535", "Back sensor (LH) error"),
    U0001("U0001", "Bus off (CAN-C)"),
    U0100("U0100", "Engine-ECU CAN timeout"),
    U0121("U0121", "ASC/ABS-ECU CAN timeout"),
    U0141("U0141", "ETACS-ECU CAN timeout"),


    ;
    private String code;
    private String description;

    DtcParkingErrorDescription(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static DtcParkingErrorDescription getByCode(String code) {
        for (DtcParkingErrorDescription dtc : values()) {
            if (dtc.code.equals(code)) {
                return dtc;
            }
        }
        return null;
    }
}
