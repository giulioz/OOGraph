package com.gzgr;

public interface BufferConverter<S,D> {
    Buffer2D<D> convert(Buffer2D<S> source);
}
