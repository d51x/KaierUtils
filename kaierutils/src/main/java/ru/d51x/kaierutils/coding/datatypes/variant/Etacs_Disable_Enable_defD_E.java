package ru.d51x.kaierutils.coding.datatypes.variant;

public enum Etacs_Disable_Enable_defD_E {
    etacs_variant_data_0(0, "Disable"),
    etacs_variant_data_1(1, "Enable (default D)"),
    etacs_variant_data_2(2, "Enable (default E)"),
    ;
    private final int idx;
    private final String title;

    Etacs_Disable_Enable_defD_E(int idx, String title) {
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
