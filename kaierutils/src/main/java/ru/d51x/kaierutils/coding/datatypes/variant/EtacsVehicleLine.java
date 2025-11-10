package ru.d51x.kaierutils.coding.datatypes.variant;

public enum EtacsVehicleLine {
    vehicleLine_0(0, "3H41"),
    vehicleLine_1(1, "3H41EVO"),
    vehicleLine_2(2, "3H44Z"),
    vehicleLine_3(3, "3H44SX"),
    vehicleLine_4(4, "3H45X"),
    vehicleLine_5(5, "3H45W"),
    vehicleLine_6(6, "3X45"),
    vehicleLine_7(0x20, "3R00"),
    vehicleLine_8(0x40, "3M00"),
    vehicleLine_9(0x41, "4A00");

    private final int idx;
    private final String title;

    EtacsVehicleLine(int idx, String title) {
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
