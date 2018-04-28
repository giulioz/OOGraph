package com.OOGraph.raster.colors;

import com.OOGraph.math.Vector;

// CRTP?
public interface ColorFactory<T> {
    T fromRGBVector(Vector vector);
}
