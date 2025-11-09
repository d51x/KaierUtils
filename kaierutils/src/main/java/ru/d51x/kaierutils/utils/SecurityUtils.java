package ru.d51x.kaierutils.utils;

import static ru.d51x.kaierutils.utils.StringUtils.toByteArray;

import java.util.ArrayList;

public class SecurityUtils {
    private static int calculateSkey(int seed, int a, int b) {
        int hidh_bytes = (seed >> 24) * 256 | (seed >> 16);
        int low_bytes = ((seed >> 8) * 256 | (seed & 0xFF)) & 0xFFFF;

        hidh_bytes = (hidh_bytes * a + b) & 0xFFFF;
        low_bytes = (low_bytes * a + b) & 0xFFFF;

        return hidh_bytes * 0x10000 + low_bytes;
    }

    public static int calculateSkeyCVT(int seed) {
        return calculateSkey(seed, 0x88, 0x1209);
    }

    public static int calculateSkeyMeter(int seed) {
        return calculateSkey(seed, 0x85, 0x2706);
    }

    public static int calculateSkeyImmo(int seed) {
        return calculateSkey(seed, 0x67, 0x4206);
    }

    public static int calculateSkeyEngine(int seed) {
        return calculateSkey(seed, 0x67, 0x4206); // TODO find correct a and b
    }

    public static int calculateSkeyEtacs(int seed, int ecuType) {
        int a = 0x89;
        int b = 0x2012;

        if (ecuType == 0) {
            a = 0x85; b = 0x2706;
        } else if (ecuType == 1) {
            a = 0x89; b = 0x1213;
        } else if (ecuType == 2) {
            a = 0xF7; b = 0x3C8A;
        } else if (ecuType == 3) {
            a = 0x21; b = 0x5261;
        } else if (ecuType == 5) {
            a = 0x28; b = 0x326;
        }
        return calculateSkey(seed, a, b);
    }

    public enum Vendor {
        Lear,
        Omron,
        Denso,
        Melco, //Mitsubishi Electric
        Continental,
        Teves,
        Bosch,
        Nippon,
        Mitsubishi_Heavy_Industry,
        Unknown
    };


    // Vendors:
    // 0 - Lear
    // 2 - other, except continental
    // 3 - Omron
    // 4 - denso
    // 5 - melco
    //
    // Vendor ETACS определяется по PartNumber блока
    // omron - 8637B2 8637A99 8637A84 8637B73 8637B051 8637B054 8637B074 8637A910
    // если такиое не нашлось, то все остальное это ....
    // lear - 8637A
    // continental - 8637B
    //
    // Vendor Engine
    // melco - 1860D 1860B551 1860C295 1860C440 1860C445 1860C901 1860C902 1860C903
    // остальное 1860 - denso

    private static Vendor getEtacsVendor(String partNumber) {
        if (partNumber.contains("8637B2") || partNumber.contains("8637A99") || partNumber.contains("8637A84")
                || partNumber.contains("8637B73") || partNumber.contains("8637B051") || partNumber.contains("8637B054")
                || partNumber.contains("8637B074") || partNumber.contains("8637A910")) {
            return Vendor.Omron;
        } else if (partNumber.contains("8637A")) {
            return Vendor.Lear;
        } else if (partNumber.contains("8637B")) {
            return Vendor.Continental;
        }
        return Vendor.Unknown;
    }

    private static Vendor getEngineVendor(String partNumber) {
        // 1860B481
        if (partNumber.contains("1860D") || partNumber.contains("1860B551") || partNumber.contains("1860C295")
                || partNumber.contains("1860C440") || partNumber.contains("1860C445") || partNumber.contains("1860C901")
                || partNumber.contains("1860C902") || partNumber.contains("1860C903")) {
            return Vendor.Melco;
        } else if (partNumber.contains("1860")) {
            return Vendor.Denso;
        }
        return Vendor.Unknown;
    }

    private static Vendor getAbsVendor(String partNumber) {
        if (partNumber.contains("4670A") ) {
            return Vendor.Teves;
        }
        return Vendor.Unknown;
    }
    private static Vendor getSasVendor(String partNumber) {
        if (partNumber.contains("8651A") ) {
            return Vendor.Bosch;
        }
        return Vendor.Unknown;
    }
    private static Vendor getMeterVendor(String partNumber) {
        if (partNumber.contains("8100B") ) {
            return Vendor.Nippon;
        }
        return Vendor.Unknown;
    }
    private static Vendor getClimateVendor(String partNumber) {
        if (partNumber.contains("7820A") ) {
            return Vendor.Mitsubishi_Heavy_Industry;
        }
        return Vendor.Unknown;
    }
    private static Vendor getCvtVendor(String partNumber) {
        if (partNumber.contains("8631A") ) {
            return Vendor.Unknown;
        }
        return Vendor.Unknown;
    }
    private static Vendor getImmoVendor(String partNumber) {
        if (partNumber.contains("8670A") ) {
            return Vendor.Omron;
        }
        return Vendor.Unknown;
    }

    public static Vendor getVendor(String partNumber) {
        // Etacs vendors
        if (partNumber.startsWith("8637")) {
            return getEtacsVendor(partNumber);
        }
        // engine vendors
        else if (partNumber.startsWith("1860")) {
            return getEngineVendor(partNumber);
        }
        // abs vendors
        else if (partNumber.startsWith("4670")) {
            return getAbsVendor(partNumber);
        }
        // sas vendors
        else if (partNumber.startsWith("8651")) {
            return getSasVendor(partNumber);
        }
        // meter vendors
        else if (partNumber.startsWith("8100")) {
            return getMeterVendor(partNumber);
        }
        // climate vendors
        else if (partNumber.startsWith("7820")) {
            return getClimateVendor(partNumber);
        }
        // cvt vendors
        else if (partNumber.startsWith("8631")) {
            return getCvtVendor(partNumber);
        }
        // immo vendors
        else if (partNumber.startsWith("8670")) {
            return getImmoVendor(partNumber);
        }
        return Vendor.Unknown;
    }

    public static String getVIN(ArrayList<Integer> buffer) {
        return buffer.isEmpty() || (buffer.size() < 19) ? "" : new String(toByteArray(buffer, 2)).trim();
    }

    public static String getPartNumber(ArrayList<Integer> buffer) {
        return buffer.isEmpty() || (buffer.size() < 22) ? "" : new String(toByteArray(buffer, 12)).trim();
    }

    public static int getDiagVersion(ArrayList<Integer> buffer) {
        return buffer.isEmpty() || (buffer.size() < 5) ? -1 : buffer.get(4) * 256 + buffer.get(5);
    }

}
