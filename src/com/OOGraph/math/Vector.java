package com.OOGraph.math;

public class Vector {
    private int dimensions;
    private float[] vect;

    public Vector(Vector copy) {
        this.dimensions = copy.dimensions;
        this.vect = new float[copy.dimensions];
        for (int i = 0; i < copy.dimensions; i++) {
            this.vect[i] = copy.vect[i];
        }
    }

    public Vector(int dimensions) {
        this.dimensions = dimensions;
        this.vect = new float[dimensions];
    }

    public Vector(int dimensions, float[] vect) {
        this.dimensions = dimensions;
        this.vect = vect;
    }

    public Vector(float x) {
        this.dimensions = 1;
        this.vect = new float[dimensions];
        vect[0] = x;
    }

    public Vector(float x, float y) {
        this.dimensions = 2;
        this.vect = new float[dimensions];
        vect[0] = x;
        vect[1] = y;
    }

    public Vector(float x, float y, float z) {
        this.dimensions = 3;
        this.vect = new float[dimensions];
        vect[0] = x;
        vect[1] = y;
        vect[2] = z;
    }

    public Vector(float x, float y, float z, float w) {
        this.dimensions = 5;
        this.vect = new float[dimensions];
        vect[0] = x;
        vect[1] = y;
        vect[2] = z;
        vect[3] = w;
    }

    public int getDimensions() { return dimensions; }
    public float get(int i) {
        if (i < vect.length)
            return vect[i];
        else
            return 0.0f;
    }
    private void set(int i, float value) { vect[i] = value; }

    public float dot(Vector b) {
        float sum = 0;
        for (int i = 0; i < getDimensions(); i++) {
            sum += get(i) * b.get(i);
        }
        return sum;
    }

    public Vector cross(Vector vector2) {
        return new Vector(
                this.get(1) * vector2.get(2) - this.get(2) * vector2.get(1),
                this.get(2) * vector2.get(0) - this.get(0) * vector2.get(2),
                this.get(0) * vector2.get(1) - this.get(1) * vector2.get(0)
        );
    }

    public Vector min(Vector b) {
        Vector out = new Vector(this);
        for (int i = 0; i < getDimensions(); i++) {
            if (get(i) < (this.get(i)))
                out.set(i, get(i));
        }
        return out;
    }

    public Vector max(Vector b) {
        Vector out = new Vector(this);
        for (int i = 0; i < getDimensions(); i++) {
            if (get(i) > (this.get(i)))
                this.set(i, get(i));
        }
        return this;
    }

    public Vector clamp(Vector min, Vector max) {
        for (int i = 0; i < getDimensions(); i++) {
            if (get(i) < (min.get(i)))
                this.set(i, min.get(i));
            else if (get(i) > (max.get(i)))
                this.set(i, max.get(i));
        }
        return this;
    }

    public Vector wrap(Vector max) {
        for (int i = 0; i < getDimensions(); i++) {
            this.set(i, this.get(i) % max.get(i));
        }
        return this;
    }


    public Vector normalize() {
        return this.divide(this.length());
    }

    public float distance(Vector b) {
        return (this.subtract(b)).length();
    }

    public float lengthSquared() {
        return dot(this);
    }

    public float length() {
        return (float)Math.sqrt(lengthSquared());
    }

    public Vector sum(float scalar) {
        Vector out = new Vector(this);
        for (int i = 0; i < getDimensions(); i++) {
            out.set(i, get(i) + scalar);
        }
        return out;
    }

    public Vector sum(Vector vector) {
        Vector out = new Vector(this);
        for (int i = 0; i < getDimensions(); i++) {
            out.set(i, get(i) + vector.get(i));
        }
        return out;
    }

    public Vector subtract(float scalar) {
        Vector out = new Vector(this);
        for (int i = 0; i < getDimensions(); i++) {
            out.set(i, get(i) - scalar);
        }
        return out;
    }

    public Vector subtract(Vector vector) {
        Vector out = new Vector(this);
        for (int i = 0; i < getDimensions(); i++) {
            out.set(i, get(i) - vector.get(i));
        }
        return out;
    }

    public Vector negate() {
        Vector out = new Vector(this);
        for (int i = 0; i < getDimensions(); i++) {
            out.set(i, -get(i));
        }
        return out;
    }

    public Vector multiply(float scalar) {
        Vector out = new Vector(this);
        for (int i = 0; i < getDimensions(); i++) {
            out.set(i, get(i) * scalar);
        }
        return out;
    }

    public Vector multiply(Vector vector) {
        Vector out = new Vector(this);
        for (int i = 0; i < getDimensions(); i++) {
            out.set(i, get(i) * vector.get(i));
        }
        return out;
    }

    public Vector divide(float scalar) {
        Vector out = new Vector(this);
        for (int i = 0; i < getDimensions(); i++) {
            out.set(i, get(i) / scalar);
        }
        return out;
    }

    public Vector divide(Vector vector) {
        Vector out = new Vector(this);
        for (int i = 0; i < getDimensions(); i++) {
            out.set(i, get(i) / vector.get(i));
        }
        return out;
    }

    public boolean equals(Vector vector) {
        boolean flag = true;
        for (int i = 0; i < getDimensions(); i++) {
            flag &= get(i) == vector.get(i);
        }
        return flag;
    }

    public boolean greaterThan(Vector vector) {
        boolean flag = true;
        for (int i = 0; i < getDimensions(); i++) {
            flag &= get(i) > vector.get(i);
        }
        return flag;
    }

    public boolean lessThan(Vector vector) {
        boolean flag = true;
        for (int i = 0; i < getDimensions(); i++) {
            flag &= get(i) < vector.get(i);
        }
        return flag;
    }

    public Vector multiply(Matrix matrix) {
        return multiplyFourth(matrix, 1.0f);
    }

    private Vector multiplyFourth(Matrix matrix, float v) {
        int m = matrix.getRows();
        int n = matrix.getCols();
        Vector out = new Vector(m);
        for (int i = 0; i < m; i++) {
            float val = 0.0f;
            for (int j = 0; j < n; j++) {
                if (j < dimensions) {
                    val += matrix.get(i, j) * get(j);
                } else {
                    val += matrix.get(i, j) * v;
                }
            }
            out.vect[i] = val;
        }
        return out;
    }

    public Vector multiplyNormal(Matrix matrix) {
        return multiplyFourth(matrix, 0.0f);
    }
}
