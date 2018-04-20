package com.OOGraph.surfaces.shaders.vertex;

import com.OOGraph.primitives.Vertex;

public class IdentityVertexShader<T extends Vertex> implements VertexShader<T> {
    @Override
    public T getVertex(T input) {
        return input;
    }
}
