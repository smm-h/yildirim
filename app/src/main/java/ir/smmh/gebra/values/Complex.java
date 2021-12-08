package ir.smmh.gebra.values;

import androidx.annotation.NonNull;

public class Complex {

    private final double real, imaginary;

    public Complex(int value) {
        this((double) value);
    }

    public Complex(long value) {
        this((double) value);
    }

    public Complex(double value) {
        real = value;
        imaginary = 0;
    }

    public Complex(int real, int imaginary) {
        this(real, (double) imaginary);
    }

    public Complex(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public Complex reciprocal() {
        double a = real;
        double b = imaginary;
        double u = a * a + b * b;
        return new Complex(a / u, -b / u);
    }

    public Complex negate() {
        return new Complex(-real, imaginary);
    }

    public Complex add(Complex other) {
        return new Complex(real + other.real, imaginary + other.imaginary);
    }

    public Complex multiply(Complex other) {
        double a = real;
        double b = imaginary;
        double c = other.real;
        double d = other.imaginary;
        return new Complex(a * c - b * d, b * c + a * d);
    }

    @NonNull
    @Override
    public String toString() {
        return "" + real + "+" + imaginary + "i";
    }
}
