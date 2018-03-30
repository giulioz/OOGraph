package com.v2;

public interface Shader<T,U> {
    U getUniforms();
    void setUniforms(U data);

    T getColor(Point coord);
}
