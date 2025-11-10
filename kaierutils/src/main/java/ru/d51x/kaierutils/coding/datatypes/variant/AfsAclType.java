package ru.d51x.kaierutils.coding.datatypes.variant;

public enum AfsAclType {
    afsAclType_0(0, "Not present"),
    afsAclType_1(1, "Swivel RHD type"),
    afsAclType_2(2, "Swivel LHD type"),
    afsAclType_3(3, "Fixed bending lamp type"),
    afsAclType_4(4, "Cornering lamp type"),
    ;
    private final int idx;
    private final String title;

    AfsAclType(int idx, String title) {
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
