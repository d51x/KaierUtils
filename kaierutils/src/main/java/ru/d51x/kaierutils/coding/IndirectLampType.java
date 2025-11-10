package ru.d51x.kaierutils.coding;

public enum IndirectLampType {
    indirectLampType_0(0, "Not Present or Type1"),
    indirectLampType_1(1, "Type2"),
    ;
    private final int idx;
    private final String title;

    IndirectLampType(int idx, String title) {
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
