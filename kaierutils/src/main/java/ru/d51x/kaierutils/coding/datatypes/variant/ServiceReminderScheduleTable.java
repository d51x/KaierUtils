package ru.d51x.kaierutils.coding.datatypes.variant;

public enum ServiceReminderScheduleTable {
    serviceReminderScheduleTable_0(0, "The function is not available"),
    serviceReminderScheduleTable_1(1, "Japan 10"),
    serviceReminderScheduleTable_2(2, "Japan 11"),
    serviceReminderScheduleTable_3(3, "Japan 20"),
    serviceReminderScheduleTable_4(4, "Japan 30"),
    serviceReminderScheduleTable_5(5, "Japan 31"),
    serviceReminderScheduleTable_6(6, "Japan 40"),
    serviceReminderScheduleTable_7(7, "Reserved 1"),
    serviceReminderScheduleTable_8(8, "GCC_EXP 10"),
    serviceReminderScheduleTable_9(9, "AUS"),
    serviceReminderScheduleTable_10(10, "Reserved 2"),
    serviceReminderScheduleTable_11(11, "NAS 10 - 12.000 km"),
    serviceReminderScheduleTable_12(12, "NAS 11 - 6.000 km"),
    serviceReminderScheduleTable_13(13, "NAS 20 - 8.000 km"),
    serviceReminderScheduleTable_14(14, "NAS 21 - 6.000 km"),
    serviceReminderScheduleTable_15(15, "Reserved 3"),
    serviceReminderScheduleTable_16(16, "EU 10"),
    serviceReminderScheduleTable_17(17, "EU 11"),
    serviceReminderScheduleTable_18(18, "Optional Schedule"),
    serviceReminderScheduleTable_19(19, "EU 20"),
    serviceReminderScheduleTable_20(20, "GCC_EXP 20"),
    serviceReminderScheduleTable_21(0x1F, "Function Off"),
    ;
    private final int idx;
    private final String title;

    ServiceReminderScheduleTable(int idx, String title) {
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
