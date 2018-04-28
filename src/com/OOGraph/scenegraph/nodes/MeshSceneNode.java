package com.OOGraph.scenegraph.nodes;

import com.OOGraph.math.Matrix;
import com.OOGraph.math.Vector;
import com.OOGraph.primitives.meshes.Mesh;
import com.OOGraph.primitives.vertices.Vertex;
import com.OOGraph.scenegraph.MeshRenderable;
import com.OOGraph.scenegraph.MeshRenderer;

public class MeshSceneNode<Tvertex extends Vertex> extends GroupNode implements MeshRenderable<Tvertex> {
    protected Mesh<Tvertex> mesh;
    private MeshRenderer<Tvertex> meshRenderer;

    public MeshSceneNode(Mesh<Tvertex> mesh, MeshRenderer<Tvertex> meshRenderer, Vector position, Vector rotation, Vector scale) {
        super(position, rotation, scale);
        this.mesh = mesh;
        this.meshRenderer = meshRenderer;
    }

    protected MeshSceneNode(MeshRenderer<Tvertex> meshRenderer, Vector position, Vector rotation, Vector scale) {
        super();
        this.meshRenderer = meshRenderer;
    }

    public MeshSceneNode(Mesh<Tvertex> mesh, MeshRenderer<Tvertex> meshRenderer) {
        super();
        this.mesh = mesh;
        this.meshRenderer = meshRenderer;
    }

    protected MeshSceneNode(MeshRenderer<Tvertex> meshRenderer) {
        super();
        this.meshRenderer = meshRenderer;
    }

    public Mesh<Tvertex> getMesh() {
        return mesh;
    }

    @Override
    public MeshRenderer<Tvertex> getMeshRenderer() {
        return meshRenderer;
    }

    @Override
    public void draw(Matrix parentTransform) {
        if (enabled) {
            meshRenderer.drawMesh(getMesh().transformMatrix(parentTransform.multiply(getTransform())));
        }
        super.draw(parentTransform);
    }
}
