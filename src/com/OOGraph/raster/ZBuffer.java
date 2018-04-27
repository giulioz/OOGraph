package com.OOGraph.raster;

import com.OOGraph.math.Point;

public interface ZBuffer {
    boolean zTest(Point position, float z);

    boolean zWrite(Point position, float z);

    void clear();
}
