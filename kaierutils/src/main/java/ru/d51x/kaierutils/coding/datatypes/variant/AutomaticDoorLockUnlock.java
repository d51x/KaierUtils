package ru.d51x.kaierutils.coding.datatypes.variant;

public enum AutomaticDoorLockUnlock {
    automaticDoorLockUnlock_0(0, "Disable"),
    automaticDoorLockUnlock_1(1, "crash unlock"),
    automaticDoorLockUnlock_2(2, "crash unlock + speed lock"),
    automaticDoorLockUnlock_3(3, "Speed lock"),
    ;
    private final int idx;
    private final String title;

    AutomaticDoorLockUnlock(int idx, String title) {
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
