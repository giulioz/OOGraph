package com.OOGraph.surfaces.shaders.pixel;

import com.OOGraph.primitives.Vertex;
import com.OOGraph.math.Vector;

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
