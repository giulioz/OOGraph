package com.OOGraph.raster.colors;

import com.OOGraph.math.Vector;

public interface ColorFactory<T> {
    T fromRGBVector(Vector vector);
}
