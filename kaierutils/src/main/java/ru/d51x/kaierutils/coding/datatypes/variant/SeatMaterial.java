package ru.d51x.kaierutils.coding.datatypes.variant;

public enum SeatMaterial {
    seatMaterial_0(0, "Fabric"),
    seatMaterial_1(1, "Leather"),
    ;
    private final int idx;
    private final String title;

    SeatMaterial(int idx, String title) {
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
