package com.OOGraph.io;

import com.OOGraph.raster.Surface;

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
