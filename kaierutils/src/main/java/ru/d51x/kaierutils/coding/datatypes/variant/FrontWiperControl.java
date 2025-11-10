package ru.d51x.kaierutils.coding.datatypes.variant;

public enum FrontWiperControl {
    frontWiperControl_0(0, "Normal INT"),
    frontWiperControl_1(1, "Variable INT"),
    frontWiperControl_2(2, "Speed Sensitive"),
    frontWiperControl_3(3, "Rain Sensitive"),
    ;
    private final int idx;
    private final String title;

    FrontWiperControl(int idx, String title) {
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
