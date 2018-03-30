package com.v2.surfaces;

import com.v2.surfaces.colors.ColorRGB24;

public class SurfaceRGB24 implements Surface<ColorRGB24> {
    private byte[] data;
    private int width, height, length;

    public SurfaceRGB24(byte[] data, int width, int height) {
        this.data = data;
        this.width = width;
        this.height = height;
        this.length = width * height;
    }

    public SurfaceRGB24(int width, int height) {
        this.width = width;
        this.height = height;
        this.length = width * height;
        this.data = new byte[width *  height * 3];
    }

    @Override
    public byte[] getSurfaceData() {
        return data;
    }

    @Override
    public ColorRGB24 getLinear(int index) {
        int i = index * 3;
        return new ColorRGB24(data[i], data[i + 1], data[i + 2]);
    }

    @Override
    public void setLinear(int index, ColorRGB24 value) {
        int i = index * 3;
        data[i] = value.getR();
        data[i + 1] = value.getG();
        data[i + 2] = value.getB();
    }

    @Override
    public ColorRGB24 getXY(int x, int y) {
        return getLinear(x + y * width);
    }

    @Override
    public void setXY(int x, int y, ColorRGB24 value) {
        setLinear(x + y * width, value);
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getLength() {
        return length;
    }
}
