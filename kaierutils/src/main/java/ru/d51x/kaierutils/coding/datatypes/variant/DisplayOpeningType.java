package ru.d51x.kaierutils.coding.datatypes.variant;

public enum DisplayOpeningType {
    displayOpeningType_0(0, "Not opening display" ),
    displayOpeningType_1(1, "Opening for MMC" ),
    displayOpeningType_2(2, "Opening for OEM1" ),
    displayOpeningType_3(3, "Opening for OEM2" ),
    displayOpeningType_4(4, "Opening for Reserved" ),        ;
    private final int idx;
    private final String title;
    DisplayOpeningType(int idx, String title) {
        this.idx = idx;
        this.title = title;
    }
    public int getIdx() { return idx; }
    public String getTitle() { return title; }
}
