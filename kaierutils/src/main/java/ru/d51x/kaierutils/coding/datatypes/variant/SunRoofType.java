package ru.d51x.kaierutils.coding.datatypes.variant;

public enum SunRoofType {
    sunRoofType_0(0, "Not Present"),
    sunRoofType_1(1, "Type S1"),
    sunRoofType_2(2, "Type S2"),
    sunRoofType_3(3, "Type S3"),
    sunRoofType_4(4, "Type S4"),
    sunRoofType_5(5, "Type S3 MMAL"),
    ;
    private final int idx;
    private final String title;

    SunRoofType(int idx, String title) {
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
