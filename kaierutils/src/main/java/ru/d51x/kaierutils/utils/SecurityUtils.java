package ru.d51x.kaierutils.utils;

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

}
