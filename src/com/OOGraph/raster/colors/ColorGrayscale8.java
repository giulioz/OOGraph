package com.OOGraph.raster.colors;

import com.OOGraph.math.MathHelper;
import com.OOGraph.math.Vector;

public class ColorGrayscale8 implements Color {
    private byte val;

    public ColorGrayscale8(byte val) {
        this.val = val;
    }

    public byte getVal() {
        return val;
    }

    @Override
    public Vector getVector() {
        return new Vector(MathHelper.getUnsignedByte(val) / 255.0f);
    }
}
