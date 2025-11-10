package ru.d51x.kaierutils.coding.datatypes.variant;

public enum RoomLampControl {
    roomLampControl_0(0, "Disabled (вручную)"),
    roomLampControl_1(1, "In dimming (плавно)"),
    roomLampControl_2(2, "Full (сразу)"),
    ;
    private final int idx;
    private final String title;

    RoomLampControl(int idx, String title) {
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
