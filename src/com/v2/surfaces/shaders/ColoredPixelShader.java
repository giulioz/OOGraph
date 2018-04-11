package com.v2.surfaces.shaders;

import com.v2.surfaces.colors.ColorRGB24;

public class ColoredPixelShader implements PixelShader<ColorRGB24, Object> {
    @Override
    public Object getUniforms() {
        return null;
    }

    @Override
    public void setUniforms(Object data) {

    }

    @Override
    public ColorRGB24 getColor(int x, int y) {
        return null;
    }
}
