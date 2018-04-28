package com.OOGraph.raster.colors;

import com.OOGraph.math.MathHelper;
import com.OOGraph.math.Vector;

public class ColorARGB32 extends ColorRGB24 {
    protected byte a;

    public ColorARGB32(int r, int g, int b, byte a) {
        super(r, g, b);
        this.a = a;
    }

    @Override
    public Vector getVector() {
        return new Vector(
                MathHelper.getUnsignedByte(r) / 255.0f,
                MathHelper.getUnsignedByte(g) / 255.0f,
                MathHelper.getUnsignedByte(b) / 255.0f,
                MathHelper.getUnsignedByte(a) / 255.0f
        );
    }
}
