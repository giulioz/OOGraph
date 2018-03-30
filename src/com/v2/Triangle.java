package com.v2;

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

        bboxminx = MathHelper.max(0, MathHelper.min(bboxminx, a.getPosition().getX()));
        bboxmaxx = MathHelper.min(clamp.getWidth(), MathHelper.max(bboxmaxx, a.getPosition().getX()));
        bboxminy = MathHelper.max(0, MathHelper.min(bboxminy, a.getPosition().getY()));
        bboxmaxy = MathHelper.min(clamp.getHeight(), MathHelper.max(bboxmaxy, a.getPosition().getY()));

        bboxminx = MathHelper.max(0, MathHelper.min(bboxminx, b.getPosition().getX()));
        bboxmaxx = MathHelper.min(clamp.getWidth(), MathHelper.max(bboxmaxx, b.getPosition().getX()));
        bboxminy = MathHelper.max(0, MathHelper.min(bboxminy, b.getPosition().getY()));
        bboxmaxy = MathHelper.min(clamp.getHeight(), MathHelper.max(bboxmaxy, b.getPosition().getY()));

        bboxminx = MathHelper.max(0, MathHelper.min(bboxminx, c.getPosition().getX()));
        bboxmaxx = MathHelper.min(clamp.getWidth(), MathHelper.max(bboxmaxx, c.getPosition().getX()));
        bboxminy = MathHelper.max(0, MathHelper.min(bboxminy, c.getPosition().getY()));
        bboxmaxy = MathHelper.min(clamp.getHeight(), MathHelper.max(bboxmaxy, c.getPosition().getY()));

        return new Rectangle(bboxminx, bboxminy, bboxmaxx - bboxminx, bboxmaxy - bboxminy);
    }
}
