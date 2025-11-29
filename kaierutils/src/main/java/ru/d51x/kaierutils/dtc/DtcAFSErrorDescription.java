package ru.d51x.kaierutils.dtc;

public enum DtcAFSErrorDescription {
    // AFS
    B2358("B2358", "AFS OFF SW short circuit"),
    B2507("B2507", "Steering wheel sensor error"),
    B2509("B2509", "Steering wheel sensor not initialized"),
    B2510("B2510", "Auto levelling initialization not completed"),
    B2511("B2511", "ECU internal error (ROM)"),
    B2512("B2512", "ECU internal error (EEPROM)"),
    B2513("B2513", "Levelling actuator output error"),
    B2514("B2514", "Height sensor power supply error"),
    B2515("B2515", "Front height sensor signal error"),
    B2516("B2516", "Rear height sensor signal error"),
    U0001("U0001", "Bus-off (CAN-C)"),
    U0100("U0100", "Engine CAN time-out"),
    U0121("U0121", "ASC/ABS CAN time-out"),
    U0126("U0126", "Steering wheel sensor CAN time-out"),
    U0141("U0141", "ETACS CAN time-out"),
    U0145("U0145", "Coding not completed/failed"),
    U0147("U0147", "Abnormality in coding data"),


    ;
    private String code;
    private String description;

    DtcAFSErrorDescription(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static DtcAFSErrorDescription getByCode(String code) {
        for (DtcAFSErrorDescription dtc : values()) {
            if (dtc.code.equals(code)) {
                return dtc;
            }
        }
        return null;
    }
}
