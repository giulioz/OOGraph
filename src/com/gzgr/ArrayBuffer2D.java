package com.gzgr;

import java.util.ArrayList;
import java.util.List;

public class ArrayBuffer2D<T> extends ArrayList<T> implements Buffer2D<T> {
    private int width, height;

    public ArrayBuffer2D(int width, int height, T defaultValue) {
        super(width * height);
        for (int i = 0; i < width * height; i++) super.add(defaultValue);
        this.width = width;
        this.height = height;
    }

    @Override
    public int getLength() {
        return this.size();
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
    public T getLinear(int i) {
        return this.get(i);
    }

    @Override
    public void setLinear(int i, T val) {
        this.set(i, val);
    }

    @Override
    public List<T> toList() {
        return this;
    }
}
