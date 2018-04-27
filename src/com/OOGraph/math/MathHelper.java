package com.OOGraph.math;

public class MathHelper {
    // metodi statici per funzioni matematiche utili

    private MathHelper() { }

    public static int min(int a, int b) {
        return a > b ? b : a;
    }

    public static int max(int a, int b) {
        return a < b ? b : a;
    }

    public static float clamp(float val, float min, float max) {
        return val > min ? (val < max ? val : max) : min;
    }

    public static long getUnsignedInt(int x) {
        return x & 0x00000000ffffffffL;
    }

    public static int getUnsignedByte(byte x) {
        return x & 0xff;
    }
}
