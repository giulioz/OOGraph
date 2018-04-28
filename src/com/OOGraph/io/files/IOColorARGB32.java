package com.OOGraph.io.files;

import com.OOGraph.raster.colors.ColorARGB32;
import com.OOGraph.raster.surfaces.Surface;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IOColorARGB32 implements FileIO<ColorARGB32> {
    @Override
    public void saveFile(OutputStream stream, Surface<ColorARGB32> surface) throws IOException {
        BufferedImage bufferedImage = new BufferedImage(surface.getWidth(), surface.getHeight(), BufferedImage.TYPE_INT_ARGB);
        byte[] bytes = surface.getSurfaceData();
        bufferedImage.setData(Raster.createRaster(bufferedImage.getSampleModel(), new DataBufferByte(bytes, bytes.length), new Point() ) );
    }

    @Override
    public Surface<ColorARGB32> loadFile(InputStream stream) throws IOException {
        return null;
    }
}
