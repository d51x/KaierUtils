package ru.d51x.kaierutils.dtc;

public enum DtcEtacsErrorDescription {
    // ETACS
    B1034("B1034", "Ambient air temperature sensor system (short circuit)"),
    B1035("B1035", "Ambient air temperature sensor system (open circuit)"),
    B120A("B120A", "Security alarm siren error"),
    B120B("B120B", "Security alarm sensor error"),
    B120C("B120C", "Security alarm siren flat battery"),
    B1610("B1610", "Auto-lamp sensor (low input) <vehicles without lighting control sensor>"),
    B1611("B1611", "Auto-lamp sensor (high input) <vehicles without lighting control sensor>"),
    B16A0("B16A0", "Tail lamp (RH) circuit open <Open circuit in the position lamp (RH) circuit and the tail lamp (RH) circuit>"),
    B16A1("B16A1", "Tail lamp (LH) circuit open <Open circuit in the position lamp (LH) circuit, tail lamp (LH) circuit, and the licence plate lamp circuit>"),
    B16A2("B16A2", "Blown turn-signal lamp (LH) bulb"),
    B16A3("B16A3", "Turn-signal lamp (LH) short circuit"),
    B16A4("B16A4", "Blown turn-signal lamp (RH) bulb"),
    B16A5("B16A5", "Turn-signal lamp (RH) short circuit"),
    B16A6("B16A6", "Hazard warning lamp blown fuse"),
    B16A7("B16A7", "Tail lamp (RH) circuit short <Short circuit in the position lamp (RH) circuit or the tail lamp (RH) circuit>"),
    B16A8("B16A8", "Tail lamp (LH) circuit short <Short circuit in the position lamp (LH) circuit, tail lamp (LH) circuit or the licence plate lamp circuit>"),
    B1761("B1761", "Chassis No. not programmed"),
    B210A("B210A", "+B power supply (low input)"),
    B210B("B210B", "+B power supply (high input)"),
    B2206("B2206", "Chassis No. mismatch"),
    B2215("B2215", "ECU internal error"),
    B222C("B222C", "Coding not completed"),
    B2350("B2350", "Lighting switch malfunction"),
    B2351("B2351", "Wiper/washer switch malfunction"),
    B2353("B2353", "Ignition power supply (low input)"),
    B2354("B2354", "Ignition power supply (high input)"),

    U0001("U0001", "Bus off (CAN-C)"),
    U0019("U0019", "Bus off (CAN-B)"),
    U0100("U0100", "Engine CAN timeout"),
    U0101("U0101", "CVT/AT/TC-SST CAN timeout"),
    U0103("U0103", "Shift lever CAN timeout"),
    U0114("U0114", "4WD CAN timeout"),
    U0118("U0118", "Fuel additive-ECU CAN timeout"),
    U0121("U0121", "ABS/ASC CAN timeout"),
    U0126("U0126", "Steering wheel sensor CAN timeout"),
    U0151("U0151", "SRS CAN timeout"),
    U0155("U0155", "Meter CAN timeout"),
    U0164("U0164", "A/C CAN timeout"),
    U0168("U0168", "KOS/WCM CAN timeout"),
    U0169("U0169", "Sunroof LIN timeout"),
    U0181("U0181", "Headlamp automatic levelling-ECU CAN timeout"),
    U0184("U0184", "Audio CAN timeout"),
    U0215("U0215", "P/W SW (DR) LIN timeout"),
    U0231("U0231", "Rain light sensor LIN timeout"),
    U0245("U0245", "Audio visual navigation unit CAN timeout"),
    U0331("U0331", "ECU internal error"),
    U1004("U1004", "AS&G CAN timeout"),
    U1005("U1005", "Corner sensor/back sensor-ECU CAN timeout"),
    U1006("U1006", "Security alarm siren LIN timeout"),
    U1007("U1007", "Security alarm sensor LIN timeout"),
    U1108("U1108", "Additional CAN B ECU detected"),
    U1109("U1109", "Column SW LIN timeout"),
    U1120("U1120", "Bus line (CAN-C) low input"),
    U1121("U1121", "Bus line (CAN-C) high input"),
    U150B("U150B", "Column SW checksum error"),
    U150C("U150C", "P/W SW (DR) checksum error"),
    U1511("U1511", "Sunroof checksum error"),
    U1512("U1512", "Rain light sensor checksum error"),
    U1514("U1514", "Bit error (LIN)"),
    U1515("U1515", "No-Bus activity error (LIN)"),
    U1539("U1539", "Security alarm siren LIN checksum error"),
    U1540("U1540", "Security alarm SNSR.LIN checksum error"),


    ;

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    private String code;
    private String description;

    DtcEtacsErrorDescription(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public static DtcEtacsErrorDescription getByCode(String code) {
        for (DtcEtacsErrorDescription dtc : values()) {
            if (dtc.code.equals(code)) {
                return dtc;
            }
        }
        return null;
    }
}
