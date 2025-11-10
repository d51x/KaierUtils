package ru.d51x.kaierutils.coding.datatypes.variant;

// XXXXX
public enum EtacsTransmission {
    transmission_1(1, "5MT"),
    transmission_2(2, "6MT"),
    transmission_3(3, "CVT"),
    transmission_4(4, "6AT"),
    transmission_5(5, "TC-SST"),
    transmission_6(6, "4AT"),
    ;
    private final int idx;
    private final String title;

    EtacsTransmission(int idx, String title) {
        this.idx = idx;
        this.title = title;
    }

    public int getIdx() {
        return idx;
    }

    public String getTitle() {
        return title;
    }
}
