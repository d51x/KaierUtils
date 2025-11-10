package ru.d51x.kaierutils.coding.datatypes.variant;

public enum SeatBeltReminderIndicator {
    seatbeltReminderIndicator_0(0, "DRV and PSG indicator independent"),
    seatbeltReminderIndicator_1(1, "DRV and PSG indicator integrate"),
    seatbeltReminderIndicator_2(2, "Indicator not available"),
    ;
    private final int idx;
    private final String title;

    SeatBeltReminderIndicator(int idx, String title) {
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
