package ru.d51x.kaierutils.coding.datatypes.variant;

public enum EtacsHandleSide {
    handleSide_1(0, "LHD (Левый руль)"),
    handleSide_2(1, "RHD (Правый руль)"),
    ;
    private final int idx;
    private final String title;

    EtacsHandleSide(int idx, String title) {
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
