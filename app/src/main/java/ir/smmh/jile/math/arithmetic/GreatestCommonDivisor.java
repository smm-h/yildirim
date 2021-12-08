package ir.smmh.jile.math.arithmetic;

import ir.smmh.jile.math.operations.AssociativeClosedBinaryOperation;
import ir.smmh.jile.math.operations.BinaryOperation;

/**
 * The {@link GreatestCommonDivisor} of at least two non-zero elements from a
 * {@link CommutativeRing}, like that of integers or polynomials, is another
 * element from that set, which is defined as "the largest such element that
 * divides all others", or is a {@link Divisor} of them.
 */
public interface GreatestCommonDivisor<T> extends AssociativeClosedBinaryOperation<T> {

    @Override
    default BinaryOperation.BinarySymbolPlacement getPlacement() {
        return BinaryOperation.BinarySymbolPlacement.INFIX;
    }

    @Override
    default String getSymbol() {
        return "|";
    }

    @Override
    default T operate(T a, T b) {
        return null;
    }

}
