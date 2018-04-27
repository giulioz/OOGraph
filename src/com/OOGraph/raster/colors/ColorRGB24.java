package com.OOGraph.raster.colors;

public class ColorRGB24 {
    private byte r, g, b;

    public ColorRGB24(int r, int g, int b) {
        this.r = (byte)r;
        this.g = (byte) g;
        this.b = (byte) b;
    }

    public byte getR() {
        return r;
    }

    public byte getG() {
        return g;
    }

    public byte getB() {
        return b;
    }
}
