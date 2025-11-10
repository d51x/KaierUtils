package ru.d51x.kaierutils.coding.datatypes.variant;

public enum RearWiperMode {
    rearWiperMode_0(0, "Conventional or Not Present"),
    rearWiperMode_1(1, "With Lo control - с управлением на низкой скорости"),
    ;
    private final int idx;
    private final String title;

    RearWiperMode(int idx, String title) {
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
