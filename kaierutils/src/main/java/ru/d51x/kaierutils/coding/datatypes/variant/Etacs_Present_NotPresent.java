package ru.d51x.kaierutils.coding.datatypes.variant;

public enum Etacs_Present_NotPresent {
    etacs_variant_data_0(0, "Present"),
    etacs_variant_data_1(1, "Not present"),
    ;
    private final int idx;
    private final String title;

    Etacs_Present_NotPresent(int idx, String title) {
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
