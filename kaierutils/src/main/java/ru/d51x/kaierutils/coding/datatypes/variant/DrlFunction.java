package ru.d51x.kaierutils.coding.datatypes.variant;

public enum DrlFunction {
    drlFunction_0(0, "Present (cannot change)"),
    drlFunction_1(1, "Not present (cannot change)"),
    drlFunction_2(2, "Present (can change)"),
    drlFunction_3(3, "Not present (can change)"),
    ;
    private final int idx;
    private final String title;

    DrlFunction(int idx, String title) {
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
