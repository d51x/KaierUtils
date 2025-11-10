package ru.d51x.kaierutils.coding.datatypes.variant;

public enum SeatBeltReminderLogic {
    seatBeltReminderLogic_0(0, "DOM / EU"),
    seatBeltReminderLogic_1(1, "NAS (Except EU)"),
    ;
    private final int idx;
    private final String title;

    SeatBeltReminderLogic(int idx, String title) {
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
