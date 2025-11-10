package ru.d51x.kaierutils.coding.datatypes.variant;

public enum Etacs_NotPresent_PresentABC {
    etacs_data_0(0, "Not Present"),
    etacs_data_1(1, "Present (Type A)"),
    etacs_data_2(2, "Present (Type B)"),
    etacs_data_3(3, "Present (Type C)"),
    ;
    private final int idx;
    private final String title;

    Etacs_NotPresent_PresentABC(int idx, String title) {
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
