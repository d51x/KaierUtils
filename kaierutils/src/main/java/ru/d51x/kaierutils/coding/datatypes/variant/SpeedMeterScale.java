package ru.d51x.kaierutils.coding.datatypes.variant;

public enum SpeedMeterScale {
    speedMeterScale_0(0, "km/h"),
    speedMeterScale_1(1, "mph"),
    ;
    private final int idx;
    private final String title;

    SpeedMeterScale(int idx, String title) {
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
