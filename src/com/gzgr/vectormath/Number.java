package com.gzgr.vectormath;

public interface Number {
    void setInt(int val);
    void setFloat(float val);
    void setByte(byte val);
    void setDouble(double val);

    int getInt();
    float getFloat();
    byte getByte();
    double getDouble();
    Number copy();

    Number sum(Number b);
    Number subtract(Number b);
    Number negate();
    Number multiply(Number b);
    Number divide(Number b);
    Number abs();
    Number mod(Number b);
    Number squareRoot();
    Number sin();
    Number cos();
    Number tan();
    Number asin();
    Number acos();
    Number atan();
    Number log();
    Number pow(Number exponent);

    boolean equals(Number b);
    boolean greaterThan(Number b);
    boolean lessThan(Number b);
}
