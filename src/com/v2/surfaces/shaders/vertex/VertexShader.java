package com.v2.surfaces.shaders.vertex;

import com.v2.primitives.Vertex;

public interface VertexShader<Tin extends Vertex, Tout extends Vertex, U> {
    U getUniforms();
    void setUniforms(U data);

    Tout getVertex(Tin input);
}
