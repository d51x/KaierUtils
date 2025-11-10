package ru.d51x.kaierutils.coding.datatypes.variant;

// XXXXX
public enum EtacsEngineType {
    engine_1(1, "3.0L S4 MIVEC"),
    engine_2(2, "DIESEL 2.0L [BSY or BWC]"),
    engine_3(3, "1.8L MPI D4/S4"),
    engine_4(4, "2.0L MPI D4/S4"),
    engine_5(5, "2.4L D4 MPI VVT"),
    engine_6(6, "1.5L D4 MPI VVT"),
    engine_7(7, "2.0L D4 VVT T/C"),
    engine_8(8, "2.2L DI I/C T/C"),
    engine_9(9, "1.8L DI-DC MIVEC"),
    engine_10(0x0A, "2.2L DI-DC MIVEC"),
    engine_11(0x0B, "1.6L D4 MPI VVT"),
    engine_12(0x0C, "-"),
    engine_13(0x0D, "1.6L DI I/C T/C"),
    engine_14(0xFF, "--"),
    ;
    private final int idx;
    private final String title;

    EtacsEngineType(int idx, String title) {
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
