package ru.d51x.kaierutils.coding.datatypes.variant;

public enum FuelTankSize {
    fuelTankSize_0(0, "Not used"),
    fuelTankSize_1(0x13, "55L"),
    fuelTankSize_2(0x15, "57L"),
    fuelTankSize_3(0x16, "58L"),
    fuelTankSize_4(0x17, "59L"),
    fuelTankSize_5(0x18, "60L"),
    fuelTankSize_6(0x1A, "62L"),
    fuelTankSize_7(0x1B, "63L"),
    fuelTankSize_8(0x1E, "66L"),
    fuelTankSize_9(0x22, "70L"),
    ;
    private final int idx;
    private final String title;

    FuelTankSize(int idx, String title) {
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
