package com.OOGraph.scenegraph.nodes;

import com.OOGraph.math.Matrix;
import com.OOGraph.math.Vector;
import com.OOGraph.primitives.vertices.Vertex;
import com.OOGraph.scenegraph.MeshRenderer;
import com.OOGraph.scenegraph.PositionableSceneNode;
import com.OOGraph.scenegraph.SceneGraphFactory;
import com.OOGraph.scenegraph.SceneNode;

import java.util.Collection;

public class GroupNode<Tvertex extends Vertex> implements PositionableSceneNode<Tvertex> {
    protected Vector position, rotation, scale;
    protected boolean enabled;
    protected Collection<SceneNode> children;

    public GroupNode(Vector position, Vector rotation, Vector scale) {
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
        this.enabled = true;
        this.children = SceneGraphFactory.getFactory().createSceneNodeCollection();
    }

    public GroupNode() {
        this.position = new Vector(0, 0, 0);
        this.rotation = new Vector(0, 0, 0);
        this.scale = new Vector(1, 1, 1);
        this.enabled = true;
        this.children = SceneGraphFactory.getFactory().createSceneNodeCollection();
    }

    @Override
    public MeshRenderer<Tvertex> getMeshRenderer() {
        return null;
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
    public Collection<SceneNode> getChildren() {
        return children;
    }

    @Override
    public void addChildren(SceneNode children) {
        this.children.add(children);
    }

    @Override
    public void removeChildren(SceneNode children) {
        this.children.remove(children);
    }

    @Override
    public Matrix getTransform() {
        return Matrix.createTranslation(4, 4, position)
                .multiply(Matrix.createScale(4, 4, scale))
                .multiply(Matrix.createRotationX_4x4(rotation.get(0)))
                .multiply(Matrix.createRotationY_4x4(rotation.get(1)))
                .multiply(Matrix.createRotationZ_4x4(rotation.get(2)));
    }
}
