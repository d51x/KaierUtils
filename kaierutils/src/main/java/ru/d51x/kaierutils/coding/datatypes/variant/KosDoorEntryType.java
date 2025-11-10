package ru.d51x.kaierutils.coding.datatypes.variant;

public enum KosDoorEntryType {
    kosDoorEntryType_0(0, "Touch sensor & request sw"),
    kosDoorEntryType_1(1, "Request sw"),
    ;
    private final int idx;
    private final String title;

    KosDoorEntryType(int idx, String title) {
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
