package com.OOGraph.primitives;

public interface VertexTransformer<T extends Vertex> {
    T transform(T in);
}
