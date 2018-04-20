package com.OOGraph.surfaces.shaders.vertex;

import com.OOGraph.primitives.Vertex;

public interface VertexShader<T extends Vertex> {

    T getVertex(T input);
}
