package com.OOGraph.raster;

public abstract class ByteArraySurface<T> implements Surface<T> {
    protected byte[] data;
    protected int width, height, length;
    protected int bytesPerSample;

    protected ByteArraySurface(byte[] data, int width, int height, int bytesPerSample) {
        this.data = data;
        this.width = width;
        this.height = height;
        this.length = width * height;
        this.bytesPerSample = bytesPerSample;
    }

    protected ByteArraySurface(int width, int height, int bytesPerSample) {
        this.width = width;
        this.height = height;
        this.length = width * height;
        this.data = new byte[width *  height * bytesPerSample];
        this.bytesPerSample = bytesPerSample;
    }

    protected abstract T create(byte[] data, int startIndex);
    protected abstract byte[] getByteData(T value);

    @Override
    public byte[] getSurfaceData() {
        return data;
    }

    @Override
    public T getLinear(int index) {
        int i = index * bytesPerSample;
        return create(data, i);
    }

    @Override
    public void setLinear(int index, T value) {
        if (value == null) return;
        int i = index * bytesPerSample;
        byte[] byteData = getByteData(value);
        System.arraycopy(byteData, 0, data, i, byteData.length);
    }

    @Override
    public T getXY(int x, int y) {
        return getLinear(x + y * width);
    }

    @Override
    public void setXY(int x, int y, T value) {
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
