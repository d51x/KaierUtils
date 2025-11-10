package ru.d51x.kaierutils.coding.datatypes.variant;

public enum KeyReminderUnlock {
    keyReminderUnlock_0(0, "Disable"),
    keyReminderUnlock_1(1, "A-spec. (Dr only)"),
    keyReminderUnlock_2(2, "B-spec. (Dr and As)"),
    ;
    private final int idx;
    private final String title;

    KeyReminderUnlock(int idx, String title) {
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
