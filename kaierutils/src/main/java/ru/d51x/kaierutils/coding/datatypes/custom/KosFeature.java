package ru.d51x.kaierutils.coding.datatypes.custom;

public enum KosFeature {
    both_enable(0, "All KOS functions are enabled"), // Both enable
    doorEntry_enable(1, "Only door entry function is enabled"), // DoorEntry enable
    eng_start_enable(2, "Only engine starting function is enabled"), // ENG start enable
    both_disable(3, "All KOS functions are disabled"), // Both disable
    cannot_select(0x0F, "Cannot change");
    private final int idx;
    private final String title;

    KosFeature(int idx, String title) {
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
