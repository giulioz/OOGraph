package com.v2.surfaces.shaders;

import com.v2.primitives.Vertex;

public interface VertexShader<T extends Vertex, U> {
    U getUniforms();
    void setUniforms(U data);

    T getVertex(T input);
}
