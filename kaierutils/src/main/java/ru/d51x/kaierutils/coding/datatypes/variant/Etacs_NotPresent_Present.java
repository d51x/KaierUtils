package ru.d51x.kaierutils.coding.datatypes.variant;

public enum Etacs_NotPresent_Present {
    not_present(0, "Not Present"),
    present(1, "Present");
    private final int idx;
    private final String title;

    Etacs_NotPresent_Present(int idx, String title) {
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
