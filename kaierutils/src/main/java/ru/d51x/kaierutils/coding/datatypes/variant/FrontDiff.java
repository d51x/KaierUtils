package ru.d51x.kaierutils.coding.datatypes.variant;

public enum FrontDiff {
    frontDiff_0(0, "Undefined"),
    frontDiff_1(1, "Open - открытый дифференциал (обычный)"),
    frontDiff_2(2, "Helical - самоблокирующийся дифференциал"),
    frontDiff_3(3, "ELSD - электронный дифференциал повышенного трения"),
    ;
    private final int idx;
    private final String title;

    FrontDiff(int idx, String title) {
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
