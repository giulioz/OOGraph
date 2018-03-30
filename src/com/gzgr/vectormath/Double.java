package com.gzgr.vectormath;

public class Double implements Number {
    private double value;

    public Double(double value) {
        this.value = value;
    }

    @Override
    public void setInt(int val) {
        value = val;
    }

    @Override
    public void setFloat(float val) {
        value = val;
    }

    @Override
    public void setByte(byte val) {
        value = val;
    }

    @Override
    public void setDouble(double val) {
        value = val;
    }

    @Override
    public int getInt() {
        return (int)value;
    }

    @Override
    public float getFloat() {
        return (float)value;
    }

    @Override
    public byte getByte() {
        return (byte)value;
    }

    @Override
    public double getDouble() {
        return value;
    }

    @Override
    public Number copy() {
        return new Double(value);
    }

    @Override
    public Number sum(Number b) {
        value = (value + b.getDouble());
        return this;
    }

    @Override
    public Number subtract(Number b) {
        value = (value - b.getDouble());
        return this;
    }

    @Override
    public Number negate() {
        value = (-value);
        return this;
    }

    @Override
    public Number multiply(Number b) {
        value = (value * b.getDouble());
        return this;
    }

    @Override
    public Number divide(Number b) {
        value = (value / b.getDouble());
        return this;
    }

    @Override
    public Number abs() {
        value = (Math.abs(value));
        return this;
    }

    @Override
    public Number mod(Number b) {
        value = (value % b.getDouble());
        return this;
    }

    @Override
    public Number squareRoot() {
        value = (Math.sqrt(value));
        return this;
    }

    @Override
    public Number sin() {
        value = (Math.sin(value));
        return this;
    }

    @Override
    public Number cos() {
        value = (Math.cos(value));
        return this;
    }

    @Override
    public Number tan() {
        value = (Math.tan(value));
        return this;
    }

    @Override
    public Number asin() {
        value = (Math.asin(value));
        return this;
    }

    @Override
    public Number acos() {
        value = (Math.acos(value));
        return this;
    }

    @Override
    public Number atan() {
        value = (Math.atan(value));
        return this;
    }

    @Override
    public Number log() {
        value = (Math.log(value));
        return this;
    }

    @Override
    public Number pow(Number exponent) {
        value = (Math.pow(value, exponent.getDouble()));
        return this;
    }

    @Override
    public boolean equals(Number b) {
        return value == b.getDouble();
    }

    @Override
    public boolean greaterThan(Number b) {
        return value > b.getDouble();
    }

    @Override
    public boolean lessThan(Number b) {
        return value < b.getDouble();
    }
}
