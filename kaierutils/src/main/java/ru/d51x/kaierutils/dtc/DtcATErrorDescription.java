package ru.d51x.kaierutils.dtc;

public enum DtcATErrorDescription {

    // AT
    P0703("P0703", "Malfunction of stop lamp switch"),
    P0705("P0705", "Malfunction of inhibitor switch"),
    P0712("P0712", "A/T fluid temperature sensor system - Short circuit"),
    P0713("P0713", "A/T fluid temperature sensor system - Open circuit"),
    P0715("P0715", "Input shaft speed sensor system"),
    P0720("P0720", "Output shaft speed sensor system"),
    P0729("P0729", "6th gear incorrect ratio"),
    P0731("P0731", "1st gear incorrect ratio"),
    P0732("P0732", "2nd gear incorrect ratio"),
    P0733("P0733", "3rd gear incorrect ratio"),
    P0734("P0734", "4th gear incorrect ratio"),
    P0735("P0735", "5th gear incorrect ratio"),
    P0736("P0736", "Reverse gear incorrect ratio"),
    P0741("P0741", "Damper clutch system: Stuck off"),
    P0742("P0742", "Damper clutch system: Stuck on"),
    P0743("P0743", "Lock-up and Low-reverse brake linear solenoid valve system"),
    P0748("P0748", "Line pressure linear solenoid valve system"),
    P0753("P0753", "Low clutch linear solenoid valve system"),
    P0758("P0758", "2-6 brake linear solenoid valve system"),
    P0763("P0763", "3-5 reverse clutch linear solenoid valve system"),
    P0768("P0768", "High clutch linear solenoid valve system"),
    P0815("P0815", "Paddle shift switch (up) system"),
    P0816("P0816", "Paddle shift switch (down) system"),
    P0826("P0826", "Shift switch assembly system"),
    P0846("P0846", "2-6 brake pressure switch system"),
    P0876("P0876", "High clutch pressure switch system"),
    P0893("P0893", "Interlock detection"),
    P0988("P0988", "Low-reverse brake pressure switch system"),
    P1705("P1705", "Throttle position sensor information (engine)"),
    P1706("P1706", "Accelerator pedal position information"),
    P1731("P1731", "1st engine brake detection"),
    P1753("P1753", "Low clutch shift solenoid valve system"),
    P1758("P1758", "Low-reverse brake shift solenoid valve system"),
    P1773("P1773", "ABS information (ASC)"),
    P1794("P1794", "Earth return"),
    U0001("U0001", "Malfunction of CAN communication circuit"),
    U0100("U0100", "CAN time-out error (Engine)"),
    U0121("U0121", "CAN time-out error (ASC)"),
    U0141("U0141", "CAN time-out error (ETACS)"),
    ;
    private String code;
    private String description;

    DtcATErrorDescription(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static DtcATErrorDescription getByCode(String code) {
        for (DtcATErrorDescription dtc : values()) {
            if (dtc.code.equals(code)) {
                return dtc;
            }
        }
        return null;
    }
}
