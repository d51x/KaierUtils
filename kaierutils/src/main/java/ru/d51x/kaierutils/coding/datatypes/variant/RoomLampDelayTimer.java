package ru.d51x.kaierutils.coding.datatypes.variant;

public enum RoomLampDelayTimer {
    roomLampDelayTimer_0(0, "Short"),
    roomLampDelayTimer_1(1, "Long"),
    ;
    private final int idx;
    private final String title;

    RoomLampDelayTimer(int idx, String title) {
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
