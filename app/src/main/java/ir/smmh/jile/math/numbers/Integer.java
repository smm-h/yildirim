package ir.smmh.jile.math.numbers;

import ir.smmh.jile.math.operations.Addition;
import ir.smmh.jile.math.operations.Multiplication;

/**
 * An {@link Integer} is any {@link Real} number that can be written without a
 * {@link Fraction}.
 */
public interface Integer extends Real {
    Addition<Integer> addition = (a, b) -> null;
    Multiplication<Integer> multiplication = (a, b) -> null;

    static Integer fromContents(long contents) {
        return (Integer) Real.fromContents((double) contents);
    }

    static Integer fromContents(int contents) {
        return (Integer) Real.fromContents((double) contents);
    }

    default Integer add(int other) {
        return add(fromContents(other));
    }

    default Integer multiply(int other) {
        return multiply(fromContents(other));
    }

    default Integer add(Integer other) {
        return addition.operate(this, other);
    }

    default Integer multiply(Integer other) {
        return multiplication.operate(this, other);
    }
}
