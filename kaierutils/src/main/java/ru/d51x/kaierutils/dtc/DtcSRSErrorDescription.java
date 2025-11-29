package ru.d51x.kaierutils.dtc;

public enum DtcSRSErrorDescription {
    // SRS
    B1206("B1206", "Passenger’s air bag OFF indicator lamp (open circuit)"),
    B1207("B1207", "Passenger’s air bag OFF indicator lamp (short circuit)"),
    B1B00("B1B00", "Driver’s air bag module (1st squib) system (shorted to squib circuit earth)"),
    B1B01("B1B01", "Driver’s air bag module (1st squib) system (shorted to squib circuit power supply)"),
    B1B02("B1B02", "Driver’s air bag module (1st squib) system (open circuit of squib circuit)"),
    B1B03("B1B03", "Driver’s air bag module (1st squib) system (short circuit between squib circuit terminals)"),
    B1B04("B1B04", "Driver’s air bag module (2nd squib) system (shorted to squib circuit earth)"),
    B1B05("B1B05", "Driver’s air bag module (2nd squib) system (shorted to squib circuit power supply)"),
    B1B06("B1B06", "Driver’s air bag module (2nd squib) system (open circuit of squib circuit)"),
    B1B07("B1B07", "Driver’s air bag module (2nd squib) system (short circuit between squib circuit terminals)"),
    B1B08("B1B08", "Passenger’s (front) air bag module (1st squib) system (shorted to squib circuit earth)"),
    B1B09("B1B09", "Passenger’s (front) air bag module (1st squib) system (shorted to squib circuit power supply)"),
    B1B0A("B1B0A", "Passenger’s (front) air bag module (1st squib) system (open circuit of squib circuit)"),
    B1B0B("B1B0B", "Passenger’s (front) air bag module (1st squib) system (short circuit between squib circuit terminals)"),
    B1B0C("B1B0C", "Passenger’s (front) air bag module (2nd squib) system (shorted to squib circuit earth)"),
    B1B0D("B1B0D", "Passenger’s (front) air bag module (2nd squib) system (shorted to squib circuit power supply)"),
    B1B0E("B1B0E", "Passenger’s (front) air bag module (2nd squib) system (open circuit of squib circuit)"),
    B1B0F("B1B0F", "Passenger’s (front) air bag module (2nd squib) system (short circuit between squib circuit terminals)"),
    B1B18("B1B18", "Left curtain air bag module (squib) system (shorted to squib circuit earth)"),
    B1B19("B1B19", "Left curtain air bag module (squib) system (shorted to squib circuit power supply)"),
    B1B1A("B1B1A", "Left curtain air bag module (squib) system (open circuit of squib circuit)"),
    B1B1B("B1B1B", "Left curtain air bag module (squib) system (short circuit between squib terminals)"),
    B1B20("B1B20", "Right curtain air bag module (squib) system (shorted to squib circuit earth)"),
    B1B21("B1B21", "Right curtain air bag module (squib) system (shorted to squib circuit power supply)"),
    B1B22("B1B22", "Right curtain air bag module (squib) system (open circuit of squib circuit)"),
    B1B23("B1B23", "Right curtain air bag module (squib) system (short circuit between squib terminals)"),
    B1B70("B1B70", "Malfunction of G-sensor inside left front impact sensor"),
    B1B71("B1B71", "Malfunction of G-sensor inside right front impact sensor"),
    B1B72("B1B72", "Malfunction of G-sensor inside left side impact sensor (front)"),
    B1B73("B1B73", "Malfunction of G-sensor inside left side impact sensor (rear)"),
    B1B75("B1B75", "Malfunction of G-sensor inside right side impact sensor (front)"),
    B1B76("B1B76", "Malfunction of G-sensor inside right side impact sensor (rear)"),
    B1BA5("B1BA5", "SRS-ECU squib count mismatch"),
    B1BB3("B1BB3", "Passenger’s air bag cut off switch circuit (earth side) shorted"),
    B1BB4("B1BB4", "Passenger’s air bag cut off switch circuit (power supply side) shorted"),
    B1BB5("B1BB5", "Passenger’s air bag cut off switch circuit open"),
    B1BC7("B1BC7", "SRS-ECU (record data full) system"),
    B1C27("B1C27", "Left side-airbag module (squib) system (shorted to squib circuit earth)"),
    B1C28("B1C28", "Left side-airbag module (squib) system (shorted to squib circuit power supply)"),
    B1C29("B1C29", "Left side-airbag module (squib) system (open circuit of squib circuit)"),
    B1C2A("B1C2A", "Left side-airbag module (squib) system (short circuit between squib terminals)"),
    B1C2B("B1C2B", "Right side-airbag module (squib) system (shorted to squib circuit earth)"),
    B1C2C("B1C2C", "Right side-airbag module (squib) system (shorted to squib circuit power supply)"),
    B1C2D("B1C2D", "Right side-airbag module (squib) system (open circuit of squib circuit)"),
    B1C2E("B1C2E", "Right side-airbag module (squib) system (short circuit between squib terminals)"),
    B1C33("B1C33", "Driver’s lap pre-tensioner (squib) system (shorted to squib circuit earth)"),
    B1C34("B1C34", "Driver’s lap pre-tensioner (squib) system (shorted to squib circuit power supply)"),
    B1C35("B1C35", "Driver’s lap pre-tensioner (squib) system (open circuit of squib circuit)"),
    B1C36("B1C36", "Driver’s lap pre-tensioner (squib) system (short circuit between squib circuit terminals)"),
    B1C38("B1C38", "Driver’s seat belt pre-tensioner (squib) system (shorted to squib circuit earth)"),
    B1C39("B1C39", "Driver’s seat belt pre-tensioner (squib) system (shorted to squib circuit power supply)"),
    B1C3A("B1C3A", "Driver’s seat belt pre-tensioner (squib) system (open circuit of squib circuit)"),
    B1C3B("B1C3B", "Driver’s seat belt pre-tensioner (squib) system (short circuit between squib circuit terminals)"),
    B1C47("B1C47", "Front passenger’s seat belt pre-tensioner (squib) system (shorted to squib circuit earth)"),
    B1C48("B1C48", "Front passenger’s seat belt pre-tensioner (squib) system (shorted to squib circuit power supply)"),
    B1C49("B1C49", "Front passenger’s seat belt pre-tensioner (squib) system (open circuit of squib circuit)"),
    B1C4A("B1C4A", "Front passenger’s seat belt pre-tensioner (squib) system (short circuit between squib circuit terminals)"),
    B210D("B210D", "Battery abnormal low voltage"),
    B212C("B212C", "IG1 power supply open circuit (fuse No. 12 circuit)"),
    B212D("B212D", "IG1 power supply open circuit (fuse No. 18 circuit)"),
    B2207("B2207", "Occupant restraint controller internal 1"),
    B2208("B2208", "Occupant restraint controller internal 2"),
    B2209("B2209", "Occupant restraint controller internal 3"),
    B220A("B220A", "Occupant restraint controller internal 4"),
    B220B("B220B", "Occupant restraint controller firing stored energy"),
    B220C("B220C", "Occupant restraint controller accelerometer 1 internal"),
    B220D("B220D", "Occupant restraint controller accelerometer 2 internal"),


    U0019("U0019", "Bus off"),
    U0141("U0141", "ETACS CAN timeout"),
    U0155("U0155", "Combination meter CAN timeout"),
    U0164("U0164", "A/C -ECU CAN timeout"),
    U0168("U0168", "KOS/WCM CAN timeout"),
    U0170("U0170", "Left front impact sensor communication error"),
    U0171("U0171", "Right front impact sensor communication error"),
    U0172("U0172", "Left side impact sensor (front) communication error"),
    U0173("U0173", "Left side impact sensor (rear) communication error"),
    U0175("U0175", "Right side impact sensor (front) communication error"),
    U0176("U0176", "Right side impact sensor (rear) communication error"),
    U0184("U0184", "Audio CAN timeout"),
    U1414("U1414", "Defective coding data"),
    U1415("U1415", "Coding data not written"),

    ;
    private String code;
    private String description;

    DtcSRSErrorDescription(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static DtcSRSErrorDescription getByCode(String code) {
        for (DtcSRSErrorDescription dtc : values()) {
            if (dtc.code.equals(code)) {
                return dtc;
            }
        }
        return null;
    }
}
