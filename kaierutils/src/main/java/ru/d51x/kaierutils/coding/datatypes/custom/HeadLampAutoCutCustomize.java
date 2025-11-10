package ru.d51x.kaierutils.coding.datatypes.custom;

public enum HeadLampAutoCutCustomize {
    disable(0, "Disable"),
    enable_a_spec(1, "Enable (A-spec.)"),
    enable_b_spec(2, "Enable (B-spec.)"),
    enable_c_spec(3, "Enable (C-spec.)"),
    enable_d_spec(4, "Enable (D-spec.)"),
    enable_e_spec(5, "Enable (E-spec.)"),
    cannot_select(0x0F, "Cannot change");
    private final int idx;
    private final String title;

    HeadLampAutoCutCustomize(int idx, String title) {
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
