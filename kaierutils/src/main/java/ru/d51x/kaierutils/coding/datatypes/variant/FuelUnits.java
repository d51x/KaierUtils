package ru.d51x.kaierutils.coding.datatypes.variant;

public enum FuelUnits {
    fuelUnits_0(0, "Litter"),
    fuelUnits_1(1, "US gallon"),
    fuelUnits_2(2, "UK gallon"),
    ;
    private final int idx;
    private final String title;

    FuelUnits(int idx, String title) {
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
