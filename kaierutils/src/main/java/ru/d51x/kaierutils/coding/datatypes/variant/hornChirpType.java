package ru.d51x.kaierutils.coding.datatypes.variant;

public enum hornChirpType {
    hornChirp_0(0, "Not present (cannot change)"),
    hornChirp_1(1, "Not present (can change)"),
    hornChirp_2(2, "Present (can change)"),
    ;
    private final int idx;
    private final String title;

    hornChirpType(int idx, String title) {
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
