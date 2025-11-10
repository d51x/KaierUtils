package ru.d51x.kaierutils.coding.datatypes.variant;

public enum TemperatureUnits {
    temperatureUnits_0(0, "Celecious"),
    temperatureUnits_1(1, "Fahrenheit"),
    ;
    private final int idx;
    private final String title;

    TemperatureUnits(int idx, String title) {
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
