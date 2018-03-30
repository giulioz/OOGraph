package com.v2;

import com.v2.surfaces.Surface;

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
