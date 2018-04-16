package com.v2.surfaces.shaders.pixel;

import com.v2.primitives.Vertex;
import com.v2.vectormath.Vector;

public class FillPixelShader<T, V extends Vertex> implements PixelShader<T, T, V> {
    private T color;

    public FillPixelShader(T color) {
        this.color = color;
    }

    @Override
    public T getUniforms() {
        return color;
    }

    @Override
    public void setUniforms(T data) {
        color = data;
    }

    @Override
    public T getColor(float x, float y, V va, V vb, V vc, Vector barycentric) {
        return color;
    }
}
