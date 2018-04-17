package com.v2.surfaces.shaders.vertex;

import com.v2.primitives.Vertex;

public class IdentityVertexShader<T extends Vertex> implements VertexShader<T, T> {
    @Override
    public T getVertex(T input) {
        return input;
    }
}
