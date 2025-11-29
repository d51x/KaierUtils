package ru.d51x.kaierutils.dtc;

public enum DtcCVTErrorDescription {

    // CVT
    P0703("P0703", "Malfunction of stop lamp switch"),
    P0705("P0705", "Malfunction of inhibitor switch"),
    P0711("P0711", "Malfunction of the CVT fluid temperature sensor (Abnormality in CVT fluid temperature sensor function)"),
    P0712("P0712", "Malfunction of the CVT fluid temperature sensor (short)"),
    P0713("P0713", "Malfunction of the CVT fluid temperature sensor (open)"),
    P0715("P0715", "Malfunction of primary pulley speed sensor"),
    P0720("P0720", "Malfunction of secondary pulley speed sensor"),
    P0725("P0725", "Malfunction of engine speed"),
    P0740("P0740", "Malfunction of lockup solenoid valve"),
    P0741("P0741", "Abnormality in lockup function"),
    P0745("P0745", "Malfunction of line pressure solenoid valve"),
    P0746("P0746", "Abnormality in hydraulic control system function"),
    P0776("P0776", "Abnormality in secondary pressure solenoid function"),
    P0778("P0778", "Malfunction of secondary pressure solenoid"),
    P0815("P0815", "Malfunction of paddle shift up switch <Vehicles with paddle shift>"),
    P0816("P0816", "Malfunction of paddle shift down switch <Vehicles with paddle shift>"),
    P0826("P0826", "Malfunction of shift switch assembly"),
    P0840("P0840", "Malfunction of secondary pressure sensor"),
    P0841("P0841", "Abnormality in line pressure sensor function"),
    P0868("P0868", "Secondary pressure drop"),
    P0882("P0882", "Malfunction of system power supply (Low)"),
    P0883("P0883", "Malfunction of system power supply (High)"),
    P1637("P1637", "Malfunction of memory backup"),
    P1706("P1706", "Malfunction of throttle signal"),
    P1710("P1710", "Malfunction of vehicle speed signal"),
    P1723("P1723", "Abnormality in speed sensor system function"),
    P1740("P1740", "Malfunction of lockup/select switching solenoid valve"),
    P1745("P1745", "Monitoring of percentage change in gear ratio"),
    P1773("P1773", "Malfunction of ABS"),
    P1777("P1777", "Malfunction of stepper motor"),
    P1778("P1778", "Malfunction of stepper motor"),
    P1902("P1902", "Malfunction of engine system"),
    U0001("U0001", "Malfunction of CAN communication circuit"),
    U0100("U0100", "CAN time-out error (Engine)"),
    U0121("U0121", "CAN time-out error (ASC)"),
    U0141("U0141", "CAN time-out error (ETACS)"),
    U1415("U1415", "Variant coding not implemented"),
    U1417("U1417", "Wrong coding data received"),
    ;
    private String code;
    private String description;

    DtcCVTErrorDescription(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static DtcCVTErrorDescription getByCode(String code) {
        for (DtcCVTErrorDescription dtc : values()) {
            if (dtc.code.equals(code)) {
                return dtc;
            }
        }
        return null;
    }
}
