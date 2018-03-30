package com.gzgr.vectormath;

public interface Vector {
    int getDimensions();
    void setComponent(int i, Number value);
    Number getComponent(int i);
    Vector clone();

    default Number dot(Vector b) {
        if (getDimensions() > 0) {
            Number sum = getComponent(0);
            sum = sum.multiply(b.getComponent(0));
            for (int i = 1; i < getDimensions(); i++) {
                sum = sum.sum(getComponent(i).multiply(b.getComponent(i)));
            }
            return sum;
        } else {
            throw new IllegalArgumentException();
        }
    }

    default Vector min(Vector b) {
        for (int i = 0; i < getDimensions(); i++) {
            if (getComponent(i).lessThan(this.getComponent(i)))
                this.setComponent(i, getComponent(i));
        }
        return this;
    }

    default Vector max(Vector b) {
        for (int i = 0; i < getDimensions(); i++) {
            if (getComponent(i).greaterThan(this.getComponent(i)))
                this.setComponent(i, getComponent(i));
        }
        return this;
    }

    default Vector clamp(Vector min, Vector max) {
        for (int i = 0; i < getDimensions(); i++) {
            if (getComponent(i).lessThan(min.getComponent(i)))
                this.setComponent(i, min.getComponent(i));
            else if (getComponent(i).greaterThan(max.getComponent(i)))
                this.setComponent(i, max.getComponent(i));
        }
        return this;
    }

    default Vector normalize() {
        return this.divide(this.length());
    }

    default Number distance(Vector b) {
        return (this.subtract(b)).length();
    }

    default Number lengthSquared() {
        return dot(this);
    }

    default Number length() {
        return lengthSquared().squareRoot();
    }

    default Vector sum(Number scalar) {
        Vector out = clone();
        for (int i = 0; i < getDimensions(); i++) {
            getComponent(i).sum(scalar);
        }
        return this;
    }

    default Vector sum(Vector vector) {
        for (int i = 0; i < getDimensions(); i++) {
            getComponent(i).sum(vector.getComponent(i));
        }
        return this;
    }

    default Vector subtract(Number scalar) {
        for (int i = 0; i < getDimensions(); i++) {
            getComponent(i).sum(scalar);
        }
        return this;
    }

    default Vector subtract(Vector vector) {
        for (int i = 0; i < getDimensions(); i++) {
            getComponent(i).subtract(vector.getComponent(i));
        }
        return this;
    }

    default Vector negate() {
        for (int i = 0; i < getDimensions(); i++) {
            getComponent(i).negate();
        }
        return this;
    }

    default Vector multiply(Number scalar) {
        for (int i = 0; i < getDimensions(); i++) {
            getComponent(i).multiply(scalar);
        }
        return this;
    }

    default Vector multiply(Vector vector) {
        for (int i = 0; i < getDimensions(); i++) {
            getComponent(i).multiply(vector.getComponent(i));
        }
        return this;
    }

    default Vector divide(Number scalar) {
        for (int i = 0; i < getDimensions(); i++) {
            getComponent(i).divide(scalar);
        }
        return this;
    }

    default Vector divide(Vector vector) {
        for (int i = 0; i < getDimensions(); i++) {
            getComponent(i).divide(vector.getComponent(i));
        }
        return this;
    }

    default boolean equals(Vector vector) {
        boolean flag = true;
        for (int i = 0; i < getDimensions(); i++) {
            flag &= getComponent(i).equals(vector.getComponent(i));
        }
        return flag;
    }

    default boolean greaterThan(Vector vector) {
        boolean flag = true;
        for (int i = 0; i < getDimensions(); i++) {
            flag &= getComponent(i).greaterThan(vector.getComponent(i));
        }
        return flag;
    }

    default boolean lessThan(Vector vector) {
        boolean flag = true;
        for (int i = 0; i < getDimensions(); i++) {
            flag &= getComponent(i).lessThan(vector.getComponent(i));
        }
        return flag;
    }
}
