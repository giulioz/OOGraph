package com.v2.assets;

import java.io.IOException;
import java.io.InputStream;

public interface AssetLoader<T> {
    T loadAsset(InputStream stream) throws IOException;
}
