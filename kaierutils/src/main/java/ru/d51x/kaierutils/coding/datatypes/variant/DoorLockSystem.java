package ru.d51x.kaierutils.coding.datatypes.variant;

public enum DoorLockSystem {
    doorLockSystem_0(0, "Not Present"),
    doorLockSystem_1(1, "A-spec. (for NAS)"),
    doorLockSystem_2(2, "B-spec. (for except NAS high line)"),
    doorLockSystem_3(3, "C-spec. (for except NAS low line)"),
    doorLockSystem_4(4, "D-spec. (for dead lock)"),
    doorLockSystem_5(5, "E-spec. (for dead lock high line)"),
    ;
    private final int idx;
    private final String title;

    DoorLockSystem(int idx, String title) {
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
