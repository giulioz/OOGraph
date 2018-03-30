package com.gzgr;

public class FrameBuffer extends ArrayBuffer2D<DrawableColor> {
    public FrameBuffer(int width, int height, DrawableColor defaultValue) {
        super(width, height, defaultValue);
    }

    public void clear(DrawableColor clearColor) {
        for (int i = 0; i < this.getLength(); i++) {
            this.set(i, clearColor);
        }
    }
}
