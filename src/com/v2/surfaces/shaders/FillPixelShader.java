package com.v2.surfaces.shaders;

public class FillPixelShader<T> implements PixelShader<T, T> {
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
    public T getColor(int x, int y) {
        return color;
    }
}
