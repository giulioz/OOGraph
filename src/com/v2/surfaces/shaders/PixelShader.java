package com.v2.surfaces.shaders;

public interface PixelShader<T,U> {
    U getUniforms();
    void setUniforms(U data);

    T getColor(int x, int y);
}
