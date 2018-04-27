package com.OOGraph.scenegraph.nodes;

import com.OOGraph.math.Vector;
import com.OOGraph.primitives.meshes.ArrayMesh;
import com.OOGraph.primitives.vertices.TexturedNormalVertex;
import com.OOGraph.primitives.vertices.Triangle;
import com.OOGraph.scenegraph.MeshRenderer;

public class TexturedQuadNode extends MeshSceneNode<TexturedNormalVertex> {
    public TexturedQuadNode(MeshRenderer<TexturedNormalVertex> meshRenderer) {
        super(null, meshRenderer);
        this.mesh = new ArrayMesh<>();
        this.mesh.add(new Triangle<>(
                new TexturedNormalVertex(new Vector(-1, -1, 0), new Vector(0, 0, -1), new Vector(0, 0)),
                new TexturedNormalVertex(new Vector(-1, 1, 0), new Vector(0, 0, -1), new Vector(0, 1)),
                new TexturedNormalVertex(new Vector(1, -1, 0), new Vector(0, 0, -1), new Vector(1, 0))
        ));
        this.mesh.add(new Triangle<>(
                new TexturedNormalVertex(new Vector(-1, 1, 0), new Vector(0, 0, -1), new Vector(0, 1)),
                new TexturedNormalVertex(new Vector(1, 1, 0), new Vector(0, 0, -1), new Vector(1, 1)),
                new TexturedNormalVertex(new Vector(1, -1, 0), new Vector(0, 0, -1), new Vector(1, 0))
        ));
    }
}
