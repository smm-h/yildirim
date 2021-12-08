package ir.smmh.jile.math.numbers;

import androidx.annotation.NonNull;

public abstract class BaseNumber extends Number implements Real {

    @Override
    public int intValue() {
        return (int) doubleValue();
    }

    @Override
    public long longValue() {
        return (long) doubleValue();
    }

    @Override
    public float floatValue() {
        return (float) doubleValue();
    }

    @Override
    public double doubleValue() {
        return approximate();
    }

    @Override
    public boolean isPrecise() {
        return true;
    }

    @NonNull
    @Override
    public String toString() {
        return Double.toString(approximate());
    }

    @Override
    public int hashCode() {
        return Double.hashCode(approximate());
    }

    public boolean equals(Object other) {
        if (other instanceof Number) {
            return approximate() == ((Number) other).doubleValue();
        } else if (other instanceof Real) {
            return approximate() == ((Real) other).approximate();
        } else {
            return false;
        }
    }

    @Override
    public int compareTo(Real other) {
        return Double.compare(approximate(), other.approximate());
    }
}