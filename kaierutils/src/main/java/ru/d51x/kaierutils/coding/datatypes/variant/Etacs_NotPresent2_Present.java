package ru.d51x.kaierutils.coding.datatypes.variant;

public enum Etacs_NotPresent2_Present {
    etacs_variant_data_0(0, "Not present (cannot change)"),
    etacs_variant_data_1(1, "Not present (can change)"),
    etacs_variant_data_2(2, "Present (can change))"),
    ;
    private final int idx;
    private final String title;

    Etacs_NotPresent2_Present(int idx, String title) {
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
