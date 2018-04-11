package com.v2.surfaces.shaders;

import com.v2.primitives.ColoredVertex;

public class ColoredVertexShader implements VertexShader<ColoredVertex, Object> {
    @Override
    public Object getUniforms() {
        return null;
    }

    @Override
    public void setUniforms(Object data) {

    }

    @Override
    public ColoredVertex getVertex(ColoredVertex input) {
        return input;
    }
}
