package com.gzgr.vectormath;

import com.gzgr.DrawableColor;

public class ColorRGBA implements Vector, DrawableColor {
    private Number r, g, b, a;

    public ColorRGBA(byte r, byte g, byte b, byte a) {
        this.r = new Byte(r);
        this.g = new Byte(g);
        this.b = new Byte(b);
        this.a = new Byte(a);
    }

    public ColorRGBA(Number r, Number g, Number b, Number a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    public Number getR() {
        return r;
    }

    public void setR(Number r) {
        this.r = r;
    }

    public Number getG() {
        return g;
    }

    public void setG(Number g) {
        this.g = g;
    }

    public Number getB() {
        return b;
    }

    public void setB(Number b) {
        this.b = b;
    }

    public Number getA() {
        return a;
    }

    public void setA(Number a) {
        this.a = a;
    }

    @Override
    public int getDimensions() {
        return 4;
    }

    @Override
    public void setComponent(int i, Number value) {
        switch (i) {
            case 0:
                r = value;
                break;
            case 1:
                g = value;
                break;
            case 2:
                b = value;
                break;
            case 3:
                a = value;
                break;
            default:
                throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public Number getComponent(int i) {
        switch (i) {
            case 0:
                return r;
            case 1:
                return g;
            case 2:
                return b;
            case 3:
                return a;
            default:
                throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public Vector clone() {
        return new ColorRGBA(r, g, b, a);
    }

    @Override
    public int getArgb32() {
        return (a.getByte()) << 24 + (r.getByte()) << 16 + (g.getByte()) << 8 + (b.getByte());
    }
}
