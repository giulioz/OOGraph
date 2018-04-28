package com.OOGraph.io.files;

import com.OOGraph.math.MathHelper;
import com.OOGraph.raster.colors.ColorRGB24;
import com.OOGraph.raster.surfaces.Surface;
import com.OOGraph.raster.surfaces.SurfaceRGB24;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IOColorRGB24 implements FileIO<ColorRGB24> {
    @Override
    public void saveFile(OutputStream stream, Surface<ColorRGB24> surface) throws IOException {
        BufferedImage bufferedImage = new BufferedImage(surface.getWidth(), surface.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
        byte[] bytes = surface.getSurfaceData();
        for (int i = 0; i < surface.getLength(); i++) {
            byte temp = bytes[i * 3];
            bytes[i * 3] = bytes[i * 3 + 2];
            bytes[i * 3 + 2] = temp;
        }
        bufferedImage.setData(Raster.createRaster(bufferedImage.getSampleModel(), new DataBufferByte(bytes, bytes.length), new Point()));
        ImageIO.write(bufferedImage, "jpg", stream);
    }

    @Override
    public Surface<ColorRGB24> loadFile(InputStream stream) throws IOException {
        BufferedImage jImg = ImageIO.read(stream);
        SurfaceRGB24 surface = new SurfaceRGB24(jImg.getWidth(), jImg.getHeight());
        for (int yPixel = 0; yPixel < jImg.getHeight(); yPixel++) {
            for (int xPixel = 0; xPixel < jImg.getWidth(); xPixel++) {
                long color = MathHelper.getUnsignedInt(jImg.getRGB(xPixel, yPixel));
                int r = (int) ((color & 0xFF0000) >> 16);
                int g = (int) ((color & 0xFF00) >> 8);
                int b = (int) ((color & 0xFF));
                surface.setXY(xPixel, yPixel, new ColorRGB24(r, g, b));
            }
        }
        return surface;
    }
}
