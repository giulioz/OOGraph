package com.OOGraph.raster.surfaces;

import com.OOGraph.raster.colors.ColorFactory;
import com.OOGraph.raster.colors.ColorGrayscale8;

public class SurfaceGrayscale8 extends ByteArraySurface<ColorGrayscale8> {
    public SurfaceGrayscale8(byte[] data, int width, int height) {
        super(data, width, height, 1);
    }

    public SurfaceGrayscale8(int width, int height) {
        super(width, height, 1);
    }

    @Override
    protected ColorGrayscale8 create(byte[] data, int startIndex) {
        return new ColorGrayscale8(data[startIndex]);
    }

    @Override
    protected byte[] getByteData(ColorGrayscale8 value) {
        return new byte[] {
                value.getVal(),
        };
    }

    @Override
    public ColorFactory<ColorGrayscale8> getColorFactory() {
        return vector -> new ColorGrayscale8(
                (byte)(vector.get(0) * 255.0f)
        );
    }
}
