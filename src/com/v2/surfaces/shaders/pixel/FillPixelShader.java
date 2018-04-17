package com.v2.surfaces.shaders.pixel;

import com.v2.primitives.Vertex;
import com.v2.vectormath.Vector;

public class FillPixelShader<T, V extends Vertex> implements PixelShader<T, V> {
    private T color;

    public FillPixelShader(T color) {
        this.color = color;
    }

    public T getColor() {
        return color;
    }

    public void setColor(T color) {
        this.color = color;
    }

    @Override
    public T getColor(float x, float y, V va, V vb, V vc, Vector barycentric) {
        return color;
    }
}
