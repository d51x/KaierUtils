package ru.d51x.kaierutils.coding.datatypes.variant;

public enum FuelConsumptionScale {
    fuelConsumptionScale_0(0, "km/L"),
    fuelConsumptionScale_1(1, "L/100km"),
    fuelConsumptionScale_2(2, "MPG (US)"),
    fuelConsumptionScale_3(3, "MPG (UK)"),
    ;
    private final int idx;
    private final String title;

    FuelConsumptionScale(int idx, String title) {
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
