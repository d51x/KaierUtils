package ru.d51x.kaierutils.dtc;

public enum DtcAbsAscErrorDescription {
    // ABS / ASC
    C100A("C100A", "Abnormality in FL wheel speed sensor circuit"),
    C1015("C1015", "Abnormality in FR wheel speed sensor circuit"),
    C1020("C1020", "Abnormality in RL wheel speed sensor circuit"),
    C102B("C102B", "Abnormality in RR wheel speed sensor circuit"),
    C1011("C1011", "Abnormality in FL wheel speed sensor signal"),
    C101C("C101C", "Abnormality in FR wheel speed sensor signal"),
    C1027("C1027", "Abnormality in RL wheel speed sensor signal"),
    C1032("C1032", "Abnormality in RR wheel speed sensor signal"),
    C1014("C1014", "Mutual monitoring of FL wheel speed sensor"),
    C101F("C101F", "Mutual monitoring of FR wheel speed sensor"),
    C102A("C102A", "Mutual monitoring of RL wheel speed sensor"),
    C1035("C1035", "Mutual monitoring of RR wheel speed sensor"),
    C1041("C1041", "Abnormality in periodical signal for FL wheel speed sensor"),
    C1042("C1042", "Abnormality in periodical signal for FR wheel speed sensor"),
    C1043("C1043", "Abnormality in periodical signal for RL wheel speed sensor"),
    C1044("C1044", "Abnormality in periodical signal for RR wheel speed sensor"),
    C1046("C1046", "FL wheel speed sensor control phase time exceeded"),
    C1047("C1047", "FR wheel speed sensor control phase time exceeded"),
    C1048("C1048", "RL wheel speed sensor control phase time exceeded"),
    C1049("C1049", "RR wheel speed sensor control phase time exceeded"),
    C104B("C104B", "Abnormality in FL wheel inlet valve system"),
    C104F("C104F", "Abnormality in FR wheel inlet valve system"),
    C1053("C1053", "Abnormality in RL wheel inlet valve system"),
    C1057("C1057", "Abnormality in RR wheel inlet valve system"),
    C105F("C105F", "Abnormality in FL wheel outlet valve system"),
    C1063("C1063", "Abnormality in FR wheel outlet valve system"),
    C1067("C1067", "Abnormality in RL wheel outlet valve system"),
    C105B("C105B", "Abnormality in RR wheel outlet valve system"),
    C1200("C1200", "Abnormality in FL/RR wheel cut valve system"),
    C1204("C1204", "Abnormality in FR/RL wheel cut valve system"),
    C1208("C1208", "Abnormality in FL/RR wheel suction valve system"),
    C120C("C120C", "Abnormality in FR/RL wheel suction valve system"),
    C2104("C2104", "Faulty valve power supply circuit"),
    C1073("C1073", "Faulty motor drive circuit"),
    C2116("C2116", "Abnormality in power supply voltage in pump motor"),
    C1000("C1000", "Abnormality in stop lamp switch circuit"),
    C2200("C2200", "Abnormality in ABS-ECU"),
    C2204("C2204", "Internal abnormality in G and yaw rate sensor: Communication error"),
    C2206("C2206", "Re-execution of variant coding"),
    C2100("C2100", "Abnormality in battery voltage (low voltage)"),
    C2101("C2101", "Abnormality in battery voltage (high voltage)"),
    C2111("C2111", "Brake fluid pressure sensor power supply circuit: Low input"),
    C2112("C2112", "Brake fluid pressure sensor power supply circuit: High input"),
    C2114("C2114", "Abnormality in G and yaw rate sensor operation voltage: Low voltage (below 6.5 ± 0.5 V)"),
    C2115("C2115", "Abnormality in G and yaw rate sensor operation voltage: High voltage (18.0 ± 1.0 V or more)"),
    C1395("C1395", "Brake fluid filling not complete"),
    C2203("C2203", "VIN not recorded"),
    C1210("C1210", "Abnormality in G and yaw rate sensor: Abnormality in longitudinal G-sensor output voltage"),
    C1219("C1219", "Abnormality in steering wheel sensor signal"),
    C121A("C121A", "Abnormality in steering wheel sensor calibration: Steering wheel sensor neutral point not learned"),
    C1242("C1242", "Abnormality in G and yaw rate sensor: Abnormality in longitudinal G-sensor output signal"),
    C123A("C123A", "Abnormality in sensor calibration"),
    C123C("C123C", "Abnormality in G and yaw rate sensor: Abnormality in lateral G and yaw rate output value (incorrect installation)"),
    C1608("C1608", "Implausible diagnosis data"),
    C1290("C1290", "CAN time-out error"),
    C121C("C121C", "Torque request signal rejection"),
    C121D("C121D", "Abnormality in brake fluid pressure sensor circuit"),
    C121E("C121E", "Abnormality in brake fluid pressure sensor output signal"),
    C123B("C123B", "Prolonged operation of ASC"),
    C2002("C2002", "Valve calibration not completed"),
    C2003("C2003", "Control parameter not implement"),
    C2205("C2205", "Internal abnormality in steering wheel sensor"),
    U0001("U0001", "Bus off"),
    U0100("U0100", "Engine time-out error"),
    U0101("U0101", "A/T or CVT or TC-SST time-out error"),
    U0114("U0114", "4WD-ECU time-out error"),
    U0125("U0125", "G and yaw rate sensor message time-out error/message error"),
    U0126("U0126", "Steering wheel sensor time-out error"),
    U0141("U0141", "ETACS time-out error"),
    U0401("U0401", "Engine signal malfunction detected"),
    U0428("U0428", "Communication error in steering wheel sensor"),
    U1003("U1003", "G and yaw rate sensor bus-off"),
    U1415("U1415", "Variant coding not completed"),
    U1417("U1417", "Variant coding value invalid (includes faulty installation)"),


    ;
    private String code;
    private String description;

    DtcAbsAscErrorDescription(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static DtcAbsAscErrorDescription getByCode(String code) {
        for (DtcAbsAscErrorDescription dtc : values()) {
            if (dtc.code.equals(code)) {
                return dtc;
            }
        }
        return null;
    }
}
