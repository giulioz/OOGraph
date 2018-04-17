package com.v2.surfaces.shaders.pixel;

import com.v2.primitives.Vertex;
import com.v2.vectormath.Vector;

public interface PixelShader<T,V extends Vertex> {
    T getColor(float x, float y, V va, V vb, V vc, Vector barycentric);
}
