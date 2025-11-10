package ru.d51x.kaierutils.coding.datatypes.variant;

public enum Etacs_Enable_Disable {
    etacs_variant_data_0(0, "Enable"),
    etacs_variant_data_1(1, "Disable"),
    ;
    private final int idx;
    private final String title;

    Etacs_Enable_Disable(int idx, String title) {
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
