package ru.d51x.kaierutils.coding.datatypes.variant;

public enum TurnSignalBulb {
    turnSignalBulb_0(0, "21W plus 21W plus 5W"),
    turnSignalBulb_1(1, "21W plus 16W plus 5W"),
    turnSignalBulb_2(2, "21W plus 21W or 21W plus 21W plus 0.36W"),
    turnSignalBulb_3(3, "21W plus 16W or 21W plus 16W plus 0.36W"),
    ;
    private final int idx;
    private final String title;

    TurnSignalBulb(int idx, String title) {
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
