package ru.d51x.kaierutils.dtc;

public enum DtcKosWcmErrorDescription {

    // KOS / WCM
    B1731("B1731", "Engine-ECU communication timeout"),
    B1761("B1761", "Chassis No.not programmed"),
    B1A08("B1A08", "Keyless/KOS key1 performance"),
    B1A09("B1A09", "Keyless/KOS key2 performance"),
    B1A0A("B1A0A", "Keyless/KOS key3 performance"),
    B1A0B("B1A0B", "Keyless/KOS key4 performance"),
    B1A0C("B1A0C", "Keyless key 5 performance"),
    B1A0D("B1A0D", "Keyless key 6 performance"),
    B1A0E("B1A0E", "Keyless key 7 performance"),
    B1A0F("B1A0F", "Keyless key 8 performance"),
    B1A10("B1A10", "Keyless/KOS key 1 low battery"),
    B1A11("B1A11", "Keyless/KOS key 2 low battery"),
    B1A12("B1A12", "Keyless/KOS key 3 low battery"),
    B1A13("B1A13", "Keyless/KOS key 4 low battery"),
    B1A14("B1A14", "Keyless key 5 low battery"),
    B1A15("B1A15", "Keyless key 6 low battery"),
    B1A16("B1A16", "Keyless key 7 low battery"),
    B1A17("B1A17", "Keyless key 8 low battery"),
    B1A24("B1A24", "Key ID not registered"),
    B1A25("B1A25", "Key ID unmatched"),
    B1A28("B1A28", "Engine-ECU authenticate error"),
    B1A35("B1A35", "Transponder read error"),
    B2101("B2101", "IG SW start POS.circuit low"),
    B2102("B2102", "IG SW start POS.circuit high"),
    B2204("B2204", "Coding data mismatch"),
    B2206("B2206", "Chassis No.mismatch"),
    B2352("B2352", "Antenna fail"),
    B2400("B2400", "KOS key registration fail"),
    B2401("B2401", "Keyless/KOS key ID not registered"),
    B2402("B2402", "STL unit*1 comm.(system ID)"),
    B2403("B2403", "STL unit*1 comm.(CRC)"),
    B2404("B2404", "STL unit*1 comm.(function code)"),
    B2405("B2405", "STL unit*1 comm.(rolling code)"),
    B2406("B2406", "STL unit*1 comm.(PTC operate)"),
    B2407("B2407", "STL unit*1 comm.(EEPROM)"),
    B2408("B2408", "STL unit*1 comm.(solenoid)"),
    B2409("B2409", "STL unit*1 comm.(No response)"),
    B240A("B240A", "DR side antenna(outdoor) open)"),
    B240B("B240B", "PS side antenna(outdoor) open"),
    B240C("B240C", "Tail gate antenna(outdoor) open"),
    B240D("B240D", "Front antenna(indoor) open"),
    B240E("B240E", "RR antenna(indoor) open"),
    B2412("B2412", "LF antenna power voltage"),
    B2413("B2413", "STL unit*1 power voltage"),
    B2415("B2415", "RA module*2 power voltage"),
    B2416("B2416", "ECU internal error"),
    U0019("U0019", "Bus off (CAN-B)"),
    U0141("U0141", "ETACS CAN timeout"),
    U0151("U0151", "SRS CAN timeout"),
    U0155("U0155", "Meter CAN timeout"),
    U0164("U0164", "A/C CAN timeout"),
    U0184("U0184", "AUDIO CAN timeout"),
    U0245("U0245", "Audio visual navigation unit CAN timeout"),
    U1415("U1415", "Coding not completed/Data fail"),
    U1417("U1417", "Implausible coding data"),
    ;
    private String code;
    private String description;

    DtcKosWcmErrorDescription(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static DtcKosWcmErrorDescription getByCode(String code) {
        for (DtcKosWcmErrorDescription dtc : values()) {
            if (dtc.code.equals(code)) {
                return dtc;
            }
        }
        return null;
    }
}
