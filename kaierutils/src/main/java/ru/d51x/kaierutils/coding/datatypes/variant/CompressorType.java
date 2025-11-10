package ru.d51x.kaierutils.coding.datatypes.variant;

public enum CompressorType {
    compressorType_0(0, "No Compressor"),
    compressorType_1(1, "Scroll type 60cc/rev"),
    compressorType_2(2, "Scroll type 90cc/rev"),
    ;
    private final int idx;
    private final String title;

    CompressorType(int idx, String title) {
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
