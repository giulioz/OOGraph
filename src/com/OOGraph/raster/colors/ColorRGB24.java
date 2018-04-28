package com.OOGraph.raster.colors;

import com.OOGraph.math.MathHelper;
import com.OOGraph.math.Vector;

public class ColorRGB24 implements Color {
    protected byte r, g, b;

    public ColorRGB24(int r, int g, int b) {
        this.r = (byte) r;
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

    @Override
    public Vector getVector() {
        return new Vector(
                MathHelper.getUnsignedByte(r) / 255.0f,
                MathHelper.getUnsignedByte(g) / 255.0f,
                MathHelper.getUnsignedByte(b) / 255.0f
        );
    }
}
