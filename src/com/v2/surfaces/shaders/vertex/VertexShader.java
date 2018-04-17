package com.v2.surfaces.shaders.vertex;

import com.v2.primitives.Vertex;

public interface VertexShader<Tin extends Vertex, Tout extends Vertex> {

    Tout getVertex(Tin input);
}
