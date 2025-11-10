package ru.d51x.kaierutils.coding.datatypes.variant;

public enum SecurityAlarmFunction {
    securityAlarmFunction_0(0, "Not present (can change)"),
    securityAlarmFunction_1(1, "Not present (cannot change)"),
    securityAlarmFunction_2(2, "Present (can change)"),
    securityAlarmFunction_3(3, "Present (cannot change)"),
    ;
    private final int idx;
    private final String title;

    SecurityAlarmFunction(int idx, String title) {
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
