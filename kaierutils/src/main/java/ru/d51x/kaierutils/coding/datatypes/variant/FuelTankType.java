package ru.d51x.kaierutils.coding.datatypes.variant;

public enum FuelTankType {
    fuelTankType_0(0, "Small"),
    fuelTankType_1(1, "Normal"),
    ;
    private final int idx;
    private final String title;

    FuelTankType(int idx, String title) {
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
