package ru.d51x.kaierutils.Data;

import java.io.Serializable;

public class EtacsData implements Serializable {
    private int diagVersion;
    private String partNumber;
    private String partNumberSw;
    private String currentVIN;
    private String originalVIN;
    private String customCoding;
    private String variantCoding;

    public EtacsData() {
        this.diagVersion = -1;
        this.partNumber = "unknown";
        this.partNumberSw = "unknown";
        this.currentVIN = "unknown";
        this.originalVIN = "unknown";
        this.customCoding = "unknown";
        this.variantCoding = "unknown";
    }

    public int getDiagVersion() {
        return diagVersion;
    }

    public void setDiagVersion(int diagVersion) {
        this.diagVersion = diagVersion;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getPartNumberSw() {
        return partNumberSw;
    }

    public void setPartNumberSw(String partNumberSw) {
        this.partNumberSw = partNumberSw;
    }

    public String getCurrentVIN() {
        return currentVIN;
    }

    public void setCurrentVIN(String currentVIN) {
        this.currentVIN = currentVIN;
    }

    public String getOriginalVIN() {
        return originalVIN;
    }

    public void setOriginalVIN(String originalVIN) {
        this.originalVIN = originalVIN;
    }

    public String getCustomCoding() {
        return customCoding;
    }

    public void setCustomCoding(String customCoding) {
        this.customCoding = customCoding;
    }

    public String getVariantCoding() {
        return variantCoding;
    }

    public void setVariantCoding(String variantCoding) {
        this.variantCoding = variantCoding;
    }
}
