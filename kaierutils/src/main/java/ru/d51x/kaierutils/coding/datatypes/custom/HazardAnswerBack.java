package ru.d51x.kaierutils.coding.datatypes.custom;

public enum HazardAnswerBack {
    lock_1_unlock_2(0, "LOCK: Flashes once, \nUNLOCK: Flashes twice (initial condition)"),  //Lock:1, Unlock:2
    lock_1_unlock_0(1, "LOCK: Flashes once, \nUNLOCK: No flash"), //Lock:1, Unlock:0
    lock_0_unlock_2(2, "LOCK: No flash, \nUNLOCK: Flash twice"), // Lock:0, Unlock:2
    lock_2_unlock_1(3, "LOCK: Flash twice, \nUNLOCK: Flash once"), // Lock:2, Unlock:1

    // в мануале поменяны местами
    lock_0_unlock_1(4, "LOCK: No flash, \nUNLOCK: Flash once"),  // Lock:0, Unlock:1
    lock_2_unlock_0(5, "LOCK: Flash twice, \nUNLOCK: No flash"),  // Lock:2, Unlock:0
    lock_0_unlock_0(6, "No function"), // Lock:0, Unlock:0
    cannot_select(0x0F, "Cannot change"); // в мануале нет
    private final int idx;
    private final String title;

    HazardAnswerBack(int idx, String title) {
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
