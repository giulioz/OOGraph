package com.v2.surfaces.shaders;

import com.v2.Point;
import com.v2.Shader;
import com.v2.surfaces.colors.ColorRGB24;

public class ColorShader<T> implements Shader<T, T> {
    private T color;

    public ColorShader(T color) {
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
    public T getColor(Point coord) {
        return color;
    }
}
