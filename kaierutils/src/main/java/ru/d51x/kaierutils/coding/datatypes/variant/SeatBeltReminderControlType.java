package ru.d51x.kaierutils.coding.datatypes.variant;

public enum SeatBeltReminderControlType {
    seatBeltReminderControlType_0(0, "Type 0"),
    seatBeltReminderControlType_1(1, "Type 1"),
    ;
    private final int idx;
    private final String title;

    SeatBeltReminderControlType(int idx, String title) {
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
