package com.OOGraph.io.live;

import com.OOGraph.raster.surfaces.Surface;

public interface GraphicsFrame<T> {
    int getWidth();
    int getHeight();
    void setSize(int width, int height);
    Surface<T> getFramebuffer();
    void swapBuffers();
    boolean isOpen();

    void open();
    void close();
}
