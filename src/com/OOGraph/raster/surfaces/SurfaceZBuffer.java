package com.OOGraph.raster.surfaces;

import com.OOGraph.math.Point;
import com.OOGraph.raster.ZBuffer;
import com.OOGraph.raster.colors.ColorFactory;

public class SurfaceZBuffer implements Surface<Float>, ZBuffer {
    private float[] buffer;
    private int width, height;

    public SurfaceZBuffer(int width, int height) {
        this.width = width;
        this.height = height;
        buffer = new float[width * height];
        clear();
    }

    @Override
    public boolean zTest(Point position, float z) {
        return z >= getXY(position.x, position.y);
    }

    @Override
    public boolean zWrite(Point position, float z) {
        if (zTest(position, z)) {
            setXY(position.x, position.y, z);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ColorFactory<Float> getColorFactory() {
        return vector -> vector.get(0);
    }

    public void clear() {
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = Float.NEGATIVE_INFINITY;
        }
    }

    @Override
    public byte[] getSurfaceData() {
        float max = buffer[0];
        for (float v : buffer) {
            max = v > max ? v : max;
        }
        byte[] bb = new byte[buffer.length];
        for (int i = 0; i < bb.length; i++) {
            bb[i] = (byte) ((buffer[i] / max) * 255);
        }
        return bb;
    }

    @Override
    public Float getLinear(int index) {
        return buffer[index];
    }

    @Override
    public void setLinear(int index, Float value) {
        buffer[index] = value;
    }

    @Override
    public Float getXY(int x, int y) {
        return getLinear(x + y * width);
    }

    @Override
    public void setXY(int x, int y, Float value) {
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
        return buffer.length;
    }
}
