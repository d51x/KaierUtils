package ru.d51x.kaierutils.coding.datatypes.variant;

// XXXXXX
public enum EtacsFinalDrive {
    finalDrive_1(1, "Front Drive"),
    finalDrive_2(2, "Rear Drive"),
    finalDrive_3(3, "4WD FF Base"),
    finalDrive_4(4, "4WD FR Base"),
    ;
    private final int idx;
    private final String title;

    EtacsFinalDrive(int idx, String title) {
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
