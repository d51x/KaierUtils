package ru.d51x.kaierutils.coding.datatypes.variant;

public enum AutomaticLightThreshold {
    automaticLightThreshold_0(0, "Not present (low threshold) (can change)"),
    automaticLightThreshold_1(1, "Not present (high threshold) (cannot change)"),
    automaticLightThreshold_2(2, "Low threshold (analog) (can change)"),
    automaticLightThreshold_3(3, "Low threshold (RLS) (cannot change)"),
    automaticLightThreshold_4(4, "High threshold (analog) (cannot change)"),
    automaticLightThreshold_54(5, "High threshold (RLS) (cannot change)"),
    ;
    private final int idx;
    private final String title;

    AutomaticLightThreshold(int idx, String title) {
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
