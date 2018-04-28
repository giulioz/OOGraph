package com.OOGraph.scenegraph;

import com.OOGraph.primitives.vertices.Vertex;

public interface MeshRenderable<Tvertex extends Vertex>  {
    MeshRenderer<Tvertex> getMeshRenderer();
}
