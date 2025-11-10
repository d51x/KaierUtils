package ru.d51x.kaierutils.coding.datatypes.custom;

public enum SecuritySensorSensivity {
    custom_41_0(0, "100% sensitivity"), // "Level 1"),
    custom_41_1(1, "90% sensitivity"), // "Level 2"),
    custom_41_2(2, "80% sensitivity"), // "Level 3"),
    custom_41_3(3, "70% sensitivity"), // "Level 4"),
    cannot_select(0x0F, "Cannot change");
    private final int idx;
    private final String title;

    SecuritySensorSensivity(int idx, String title) {
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
