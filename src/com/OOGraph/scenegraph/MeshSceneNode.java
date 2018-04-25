package com.OOGraph.scenegraph;

import com.OOGraph.math.Matrix;
import com.OOGraph.math.Vector;
import com.OOGraph.primitives.ArrayMesh;
import com.OOGraph.primitives.Mesh;
import com.OOGraph.primitives.Triangle;
import com.OOGraph.primitives.Vertex;

import java.util.Collection;

public class MeshSceneNode<Tvertex extends Vertex> implements PositionableSceneNode<Tvertex> {
    private Mesh<Tvertex> mesh;
    private Vector position, rotation, scale;
    private boolean enabled;
    private Collection<SceneNode<Tvertex>> children;

    public MeshSceneNode(Mesh<Tvertex> mesh, Vector position, Vector rotation, Vector scale) {
        this.mesh = mesh;
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
        this.enabled = true;
        this.children = SceneGraphFactory.getFactory().createSceneNodeCollection();
    }

    public MeshSceneNode(Mesh<Tvertex> mesh) {
        this.mesh = mesh;
        this.position = new Vector(0,0,0);
        this.rotation = new Vector(0,0,0);
        this.scale = new Vector(1,1,1);
        this.enabled = true;
        this.children = SceneGraphFactory.getFactory().createSceneNodeCollection();
    }

    @Override
    public Vector getPosition() {
        return position;
    }

    @Override
    public void setPosition(Vector pos) {
        this.position = pos;
    }

    @Override
    public Vector getRotation() {
        return rotation;
    }

    @Override
    public void setRotation(Vector rot) {
        this.rotation = rot;
    }

    @Override
    public Vector getScale() {
        return scale;
    }

    @Override
    public void setScale(Vector scale) {
        this.scale = scale;
    }

    @Override
    public boolean getEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public Collection<SceneNode<Tvertex>> getChildren() {
        return children;
    }

    @Override
    public void addChildren(SceneNode<Tvertex> children) {
        this.children.add(children);
    }

    @Override
    public void removeChildren(SceneNode<Tvertex> children) {
        this.children.remove(children);
    }

    @Override
    public Matrix getTransform() {
        return Matrix.createTranslation(4, 4, position)
                .multiply(Matrix.createRotationX_4x4(rotation.get(0)))
                .multiply(Matrix.createRotationY_4x4(rotation.get(1)))
                .multiply(Matrix.createRotationZ_4x4(rotation.get(2)))
                .multiply(Matrix.createScale(4, 4, scale));
    }

    @Override
    public void draw(Matrix parentTransform, Renderer<Tvertex> renderer) {
        if (enabled) {
            renderer.drawMesh(mesh.transformMatrix(parentTransform.multiply(getTransform())));
        }
        PositionableSceneNode.super.draw(parentTransform, renderer);
    }
}
