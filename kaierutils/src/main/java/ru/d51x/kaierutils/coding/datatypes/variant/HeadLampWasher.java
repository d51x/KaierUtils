package ru.d51x.kaierutils.coding.datatypes.variant;

public enum HeadLampWasher {
    headLampWasher_0(0, "Disable"),
    headLampWasher_1(1, "Pop up control 1"),
    headLampWasher_2(2, "Pop up control1 with washer"),
    headLampWasher_3(3, "Non pop up control 1"),
    headLampWasher_4(4, "Non pop up control 1 with washer"),
    headLampWasher_5(5, "Pop up control 2"),
    headLampWasher_6(6, "Pop up control 2 with washer"),
    headLampWasher_7(7, "Non pop up control 2"),
    headLampWasher_8(8, "Non pop up control 2 with washer"),
    ;
    private final int idx;
    private final String title;

    HeadLampWasher(int idx, String title) {
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
