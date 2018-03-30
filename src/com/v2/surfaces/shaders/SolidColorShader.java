package com.v2.surfaces.shaders;

import com.v2.Shader;

public class SolidColorShader<T> implements Shader<T, T> {
    private T color;

    public SolidColorShader(T color) {
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
    public T getColor(float x, float y) {
        return color;
    }
}
