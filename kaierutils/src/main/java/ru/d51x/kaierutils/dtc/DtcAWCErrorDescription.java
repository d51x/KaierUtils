package ru.d51x.kaierutils.dtc;

public enum DtcAWCErrorDescription {
    // SRS
    C1078("C1078", "Tyre with incorrect diameter equipped"),
    C1456("C1456", "Wiring harness or coupling coil overcurrent failure"),
    C145A("C145A", "Wiring harness or coupling coil short circuit failure"),
    C145D("C145D", "Wiring harness or coupling open circuit failure"),
    C145F("C145F", "Coupling overload"),
    C1460("C1460", "Drive mode selector circuit abnormality"),
    C2100("C2100", "Battery voltage abnormality (Low voltage)"),
    C2101("C2101", "Battery voltage abnormality (High voltage)"),
    C211C("C211C", "IG1 power supply low voltage abnormality"),
    C211D("C211D", "IG1 power supply high voltage abnormality"),
    C211E("C211E", "Power supply voltage abnormality (Low voltage)"),
    C211F("C211F", "Power supply voltage abnormality (High voltage)"),
    C2208("C2208", "4WD-ECU internal abnormality"),
    U0001("U0001", "Bus off abnormality"),
    U0100("U0100", "Engine CAN time out"),
    U0121("U0121", "ABS CAN time out"),
    U0141("U0141", "ETACS CAN time out"),
    U0401("U0401", "Engine CAN data abnormality"),
    U0415("U0415", "ABS CAN data abnormality"),
    U113C("U113C", "Wheel speed sensor abnormality"),
    U1415("U1415", "Variant coding not implemented"),


    ;
    private String code;
    private String description;

    DtcAWCErrorDescription(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static DtcAWCErrorDescription getByCode(String code) {
        for (DtcAWCErrorDescription dtc : values()) {
            if (dtc.code.equals(code)) {
                return dtc;
            }
        }
        return null;
    }
}
