package com.gzgr.vectormath;

public class Byte implements Number {
    private byte value;

    public Byte(byte value) {
        this.value = value;
    }

    @Override
    public void setInt(int val) {
        value = (byte)val;
    }

    @Override
    public void setFloat(float val) {
        value = (byte)val;
    }

    @Override
    public void setByte(byte val) {
        value = val;
    }

    @Override
    public void setDouble(double val) {
        value = (byte)val;
    }

    @Override
    public int getInt() {
        return value;
    }

    @Override
    public float getFloat() {
        return (float)value;
    }

    @Override
    public byte getByte() {
        return value;
    }

    @Override
    public double getDouble() {
        return value;
    }

    @Override
    public Number copy() {
        return new Byte(value);
    }

    @Override
    public Number sum(Number b) {
        value = (byte)(value + b.getInt());
        return this;
    }

    @Override
    public Number subtract(Number b) {
        value = (byte)(value - b.getInt());
        return this;
    }

    @Override
    public Number negate() {
        value = (byte)(-value);
        return this;
    }

    @Override
    public Number multiply(Number b) {
        value = (byte)(value * b.getInt());
        return this;
    }

    @Override
    public Number divide(Number b) {
        value = (byte)(value / b.getInt());
        return this;
    }

    @Override
    public Number abs() {
        value = (byte)(Math.abs(value));
        return this;
    }

    @Override
    public Number mod(Number b) {
        value = (byte)(value % b.getDouble());
        return this;
    }

    @Override
    public Number squareRoot() {
        value = (byte)(Math.sqrt(value));
        return this;
    }

    @Override
    public Number sin() {
        value = (byte)(Math.sin(value));
        return this;
    }

    @Override
    public Number cos() {
        value = (byte)(Math.cos(value));
        return this;
    }

    @Override
    public Number tan() {
        value = (byte)(Math.tan(value));
        return this;
    }

    @Override
    public Number asin() {
        value = (byte)(Math.asin(value));
        return this;
    }

    @Override
    public Number acos() {
        value = (byte)(Math.acos(value));
        return this;
    }

    @Override
    public Number atan() {
        value = (byte)(Math.atan(value));
        return this;
    }

    @Override
    public Number log() {
        value = (byte)(Math.log(value));
        return this;
    }

    @Override
    public Number pow(Number exponent) {
        value = (byte)(Math.pow(value, exponent.getDouble()));
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
