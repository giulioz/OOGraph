package com.OOGraph.rastersurfaces.shaders;

import com.OOGraph.primitives.Vertex;
import com.OOGraph.math.Vector;

public interface PixelShader<T,V extends Vertex> {
    T getColor(float x, float y, V va, V vb, V vc, Vector barycentric);
}
