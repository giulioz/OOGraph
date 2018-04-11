package com.v2.primitives;

import com.v2.MathHelper;
import com.v2.Rectangle;
import com.v2.surfaces.shaders.VertexShader;

public class Triangle<T extends Vertex> {
    private T a, b, c;

    public Triangle(T a, T b, T c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public T getA() {
        return a;
    }

    public T getB() {
        return b;
    }

    public T getC() {
        return c;
    }

    public Rectangle getBoundingBox(Rectangle clamp) {
        int bboxminx = clamp.getWidth();
        int bboxmaxx = 0;
        int bboxminy = clamp.getHeight();
        int bboxmaxy = 0;

        bboxminx = MathHelper.max(0, MathHelper.min(bboxminx, a.getPosition().x));
        bboxmaxx = MathHelper.min(clamp.getWidth(), MathHelper.max(bboxmaxx, a.getPosition().x));
        bboxminy = MathHelper.max(0, MathHelper.min(bboxminy, a.getPosition().y));
        bboxmaxy = MathHelper.min(clamp.getHeight(), MathHelper.max(bboxmaxy, a.getPosition().y));

        bboxminx = MathHelper.max(0, MathHelper.min(bboxminx, b.getPosition().x));
        bboxmaxx = MathHelper.min(clamp.getWidth(), MathHelper.max(bboxmaxx, b.getPosition().x));
        bboxminy = MathHelper.max(0, MathHelper.min(bboxminy, b.getPosition().y));
        bboxmaxy = MathHelper.min(clamp.getHeight(), MathHelper.max(bboxmaxy, b.getPosition().y));

        bboxminx = MathHelper.max(0, MathHelper.min(bboxminx, c.getPosition().x));
        bboxmaxx = MathHelper.min(clamp.getWidth(), MathHelper.max(bboxmaxx, c.getPosition().x));
        bboxminy = MathHelper.max(0, MathHelper.min(bboxminy, c.getPosition().y));
        bboxmaxy = MathHelper.min(clamp.getHeight(), MathHelper.max(bboxmaxy, c.getPosition().y));

        return new Rectangle(bboxminx, bboxminy, bboxmaxx - bboxminx, bboxmaxy - bboxminy);
    }

    public <U> Triangle<T> transformShader(VertexShader<T, U> vertexShader) {
        return new Triangle<>(
                vertexShader.getVertex(this.a),
                vertexShader.getVertex(this.b),
                vertexShader.getVertex(this.c)
        );
    }
}
