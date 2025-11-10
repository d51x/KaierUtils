package ru.d51x.kaierutils.coding.datatypes.custom;

public enum WelcomeLight {
    disable(0, "Disable"),
    lamp_small(1, "Small (tail) lamp"),
    lamp_head(2, "Head lamp"),
    cannot_select(0x0F, "Cannot change");
    private final int idx;
    private final String title;

    WelcomeLight(int idx, String title) {
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
