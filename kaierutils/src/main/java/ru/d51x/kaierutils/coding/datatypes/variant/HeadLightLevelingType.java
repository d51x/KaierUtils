package ru.d51x.kaierutils.coding.datatypes.variant;

public enum HeadLightLevelingType { //Not present or 2 height sensor
    headLightLevelingType_0(0, "Not present"),
    headLightLevelingType_1(1, "Communication less and static type"),
    headLightLevelingType_2(2, "Communication less and dynamic type"),
    headLightLevelingType_3(3, "CAN communication and static type"),
    headLightLevelingType_4(4, "CAN communication and dynamic type"),
    headLightLevelingType_7(7, "SNA"),
    ;
    private final int idx;
    private final String title;

    HeadLightLevelingType(int idx, String title) {
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
