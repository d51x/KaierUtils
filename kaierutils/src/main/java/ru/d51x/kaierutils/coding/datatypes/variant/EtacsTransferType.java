package ru.d51x.kaierutils.coding.datatypes.variant;

public enum EtacsTransferType {
    transferType_1(1, "2WD"),
    transferType_2(2, "ECC - электронная муфтв"),
    transferType_3(3, "Center Diff + VCU"),
    transferType_4(4, "ACD"),
    transferType_5(5, "SS4-3"),
    ;
    private final int idx;
    private final String title;

    EtacsTransferType(int idx, String title) {
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
