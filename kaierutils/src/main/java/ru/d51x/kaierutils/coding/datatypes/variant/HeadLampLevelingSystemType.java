package ru.d51x.kaierutils.coding.datatypes.variant;

public enum HeadLampLevelingSystemType {
    headLampLevelingSystemType_0(0, "Type1/No present"),
    headLampLevelingSystemType_1(1, "Type 2"),
    headLampLevelingSystemType_2(2, "Type 3"),
    headLampLevelingSystemType_3(2, "Type 4"),
    ;
    private final int idx;
    private final String title;

    HeadLampLevelingSystemType(int idx, String title) {
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
