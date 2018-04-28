package com.OOGraph.io.files;

import com.OOGraph.raster.colors.Color;
import com.OOGraph.raster.surfaces.Surface;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface FileIO<Tpixel extends Color> {
    void saveFile(OutputStream stream, Surface<Tpixel> surface) throws IOException;
    Surface<Tpixel> loadFile(InputStream stream) throws IOException;
}
