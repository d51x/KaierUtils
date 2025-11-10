package ru.d51x.kaierutils.coding.datatypes.variant;

public enum EtacsDestinationRegion {
    destinationRegion_0(0, "DOM"),
    destinationRegion_1(1, "EXP - Экспорт"),
    destinationRegion_2(2, "NAS - Австралия"),
    destinationRegion_3(3, "EU - Европа"),
    destinationRegion_4(4, "MMAL - Америка"),
    destinationRegion_5(5, "GCC - Арабские Эмираты"),
    ;
    private final int idx;
    private final String title;

    EtacsDestinationRegion(int idx, String title) {
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
