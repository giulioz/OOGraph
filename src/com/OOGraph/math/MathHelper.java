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
}
