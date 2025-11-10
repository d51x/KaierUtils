package ru.d51x.kaierutils.coding.datatypes.custom;

public enum FrontRearWiper {
    only_washer(0, "Only Washer"),
    washer_wiper(1, "Without delayed finishing wipe function"), // Washer & Wiper
    with_after_wipe(2, "With delayed finishing wipe function"), // With after wipe
    cannot_select(0x0F, "Cannot change"); // нет в мануале
    private final int idx;
    private final String title;

    FrontRearWiper(int idx, String title) {
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
