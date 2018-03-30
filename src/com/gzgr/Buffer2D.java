package com.gzgr;

import java.util.ArrayList;
import java.util.List;

public interface Buffer2D<T> {
    int getLength();
    int getWidth();
    int getHeight();

    T getLinear(int i);
    void setLinear(int i, T val);

    default T getXY(int x, int y) {
        return getLinear(x + y * getWidth());
    }
    default void setXY(int x, int y, T val) {
        setLinear(x + y * getWidth(), val);
    }

    default List<T> toList() {
        List<T> out = new ArrayList<>(getLength());
        for (int i = 0; i < getLength(); i++) {
            out.add(getLinear(i));
        }
        return out;
    }
}
