package com.OOGraph.io.files;

import com.OOGraph.math.MathHelper;
import com.OOGraph.raster.colors.ColorARGB32;
import com.OOGraph.raster.surfaces.Surface;
import com.OOGraph.raster.surfaces.SurfaceARGB32;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IOColorARGB32 implements FileIO<ColorARGB32> {
    private static IOColorARGB32 _instance = null;
    public static IOColorARGB32 getInstance() {
        if (_instance == null) _instance = new IOColorARGB32();
        return _instance;
    }

    private IOColorARGB32() { }

    @Override
    public void saveFile(OutputStream stream, Surface<ColorARGB32> surface) throws IOException {
        BufferedImage bufferedImage = new BufferedImage(surface.getWidth(), surface.getHeight(), BufferedImage.TYPE_INT_ARGB);
        byte[] bytes = surface.getSurfaceData();
        bufferedImage.setData(Raster.createRaster(bufferedImage.getSampleModel(), new DataBufferByte(bytes, bytes.length), new Point() ) );
    }

    @Override
    public Surface<ColorARGB32> loadFile(InputStream stream) throws IOException {
        BufferedImage jImg = ImageIO.read(stream);
        SurfaceARGB32 surface = new SurfaceARGB32(jImg.getWidth(), jImg.getHeight());
        for (int yPixel = 0; yPixel < jImg.getHeight(); yPixel++) {
            for (int xPixel = 0; xPixel < jImg.getWidth(); xPixel++) {
                long color = MathHelper.getUnsignedInt(jImg.getRGB(xPixel, yPixel));
                int a = (int) ((color & 0xFF000000) >> 24);
                int r = (int) ((color & 0xFF0000) >> 16);
                int g = (int) ((color & 0xFF00) >> 8);
                int b = (int) ((color & 0xFF));
                surface.setXY(xPixel, yPixel, new ColorARGB32(r, g, b, a));
            }
        }
        return surface;
    }
}
