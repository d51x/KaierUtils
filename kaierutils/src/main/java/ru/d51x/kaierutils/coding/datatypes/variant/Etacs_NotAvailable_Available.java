package ru.d51x.kaierutils.coding.datatypes.variant;

public enum Etacs_NotAvailable_Available {
    etacs_variant_data_0(0, "Not available"),
    etacs_variant_data_1(1, "Available"),
    ;
    private final int idx;
    private final String title;

    Etacs_NotAvailable_Available(int idx, String title) {
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
