package ru.d51x.kaierutils.coding.datatypes.variant;

public enum RearDiff {
    rearDiff_0(0, "Undefined"),
    rearDiff_1(1, "Open - открытый дифференциал (обычный)"),
    rearDiff_2(2, "AYC – Активный"),
    rearDiff_3(3, "LSD LOM"),
    ;
    private final int idx;
    private final String title;

    RearDiff(int idx, String title) {
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
