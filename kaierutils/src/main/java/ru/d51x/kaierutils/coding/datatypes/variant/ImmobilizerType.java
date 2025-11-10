package ru.d51x.kaierutils.coding.datatypes.variant;

public enum ImmobilizerType {
    immobilizerType_0(0, "Not Present"),
    immobilizerType_1(1, "Present (Type A)"),
    immobilizerType_2(2, "Present (Type B)"),
    ;
    private final int idx;
    private final String title;

    ImmobilizerType(int idx, String title) {
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
