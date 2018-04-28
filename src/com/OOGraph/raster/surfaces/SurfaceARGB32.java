package com.OOGraph.raster.surfaces;

import com.OOGraph.raster.colors.ColorARGB32;
import com.OOGraph.raster.colors.ColorFactory;

public class SurfaceARGB32 extends ByteArraySurface<ColorARGB32> {
    public SurfaceARGB32(byte[] data, int width, int height) {
        super(data, width, height, 4);
    }

    public SurfaceARGB32(int width, int height) {
        super(width, height, 4);
    }

    @Override
    protected ColorARGB32 create(byte[] data, int startIndex) {
        return new ColorARGB32(data[startIndex], data[1 + startIndex], data[2 + startIndex], data[3 + startIndex]);
    }

    @Override
    protected byte[] getByteData(ColorARGB32 value) {
        return new byte[] {
                value.getR(),
                value.getG(),
                value.getB(),
                value.getA()
        };
    }

    @Override
    public ColorFactory<ColorARGB32> getColorFactory() {
        return vector -> new ColorARGB32(
                (byte)(vector.get(0) * 255.0f),
                (byte)(vector.get(1) * 255.0f),
                (byte)(vector.get(2) * 255.0f),
                (byte)(vector.get(3) * 255.0f)
        );
    }
}
