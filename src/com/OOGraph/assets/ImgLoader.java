package com.OOGraph.assets;

import com.OOGraph.math.MathHelper;
import com.OOGraph.raster.SurfaceRGB24;
import com.OOGraph.raster.colors.ColorRGB24;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class ImgLoader implements AssetLoader<SurfaceRGB24> {
    private static ImgLoader _instance = null;
    public static ImgLoader getInstance() {
        if (_instance == null) _instance = new ImgLoader();
        return _instance;
    }

    private ImgLoader() { }

    @Override
    public SurfaceRGB24 loadAsset(InputStream stream) throws IOException {
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
