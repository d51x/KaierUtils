package ru.d51x.kaierutils.coding.datatypes.variant;

public enum CoolingFanControl {
    coolingFanControl_0(0, "Relay control"),
    coolingFanControl_1(1, "PWM control"),
    ;
    private final int idx;
    private final String title;

    CoolingFanControl(int idx, String title) {
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
