package ru.d51x.kaierutils.coding.datatypes.custom;

public enum HeadLightEvRemoteCarFinder {
    custom_45_0(0, "Disable"),
    custom_45_1(1, "15 sec"),
    custom_45_2(2, "30 sec"),
    custom_45_3(3, "45 sec"),
    cannot_select(0x0F, "Cannot change");
    private final int idx;
    private final String title;

    HeadLightEvRemoteCarFinder(int idx, String title) {
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
