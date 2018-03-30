package com.gzgr.vectormath;

public class Vector2 implements Vector {
    private Number x, y;

    public Vector2(double x, double y) {
        this.x = new Double(x);
        this.y = new Double(y);
    }

    public Vector2(Number x, Number y) {
        this.x = x;
        this.y = y;
    }

    public Number getX() {
        return x;
    }

    public void setX(Number x) {
        this.x = x;
    }

    public Number getY() {
        return y;
    }

    public void setY(Number y) {
        this.y = y;
    }

    @Override
    public int getDimensions() {
        return 2;
    }

    @Override
    public void setComponent(int i, Number value) {
        switch (i) {
            case 0:
                x = value;
                break;
            case 1:
                y = value;
                break;
            default:
                throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public Number getComponent(int i) {
        switch (i) {
            case 0:
                return x;
            case 1:
                return y;
            default:
                throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public Vector clone() {
        return new Vector2(x, y);
    }
}
