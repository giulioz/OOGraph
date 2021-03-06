package com.OOGraph.raster.surfaces;

import com.OOGraph.raster.colors.ColorFactory;
import com.OOGraph.raster.colors.ColorRGB24;

public class SurfaceRGB24 extends ByteArraySurface<ColorRGB24> {
    public SurfaceRGB24(byte[] data, int width, int height) {
        super(data, width, height, 3);
    }

    public SurfaceRGB24(int width, int height) {
        super(width, height, 3);
    }

    @Override
    protected ColorRGB24 create(byte[] data, int startIndex) {
        return new ColorRGB24(data[startIndex], data[1 + startIndex], data[2 + startIndex]);
    }

    @Override
    protected byte[] getByteData(ColorRGB24 value) {
        return new byte[] {
                value.getR(),
                value.getG(),
                value.getB()
        };
    }

    @Override
    public ColorFactory<ColorRGB24> getColorFactory() {
        return vector -> new ColorRGB24(
                (byte)(vector.get(0) * 255.0f),
                (byte)(vector.get(1) * 255.0f),
                (byte)(vector.get(2) * 255.0f)
        );
    }
}
