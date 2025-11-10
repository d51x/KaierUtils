package ru.d51x.kaierutils.coding.datatypes.custom;

public enum InteriorIlluminationControl {
    same_exterior_lights(0, "Same Exterior lights"),
    independent_normal(1, "Independent Normal"),
    independent_late(2, "Independent Late"),
    cannot_select(0x0F, "Cannot change");
    private final int idx;
    private final String title;

    InteriorIlluminationControl(int idx, String title) {
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
