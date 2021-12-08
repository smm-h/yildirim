package ir.smmh.jile.math.abstractalgebra;

import ir.smmh.jile.math.operations.Multiplication;

/**
 * A {@link CommutativeRing} is a {@link Ring} whose {@link Multiplication} is
 * commutative.
 */
public interface CommutativeRing<T> extends Ring<T> {

    /**
     * Checks the commutativity of the secondary binary operation.
     */
    @Override
    default boolean test() {
        if (!Ring.super.test())
            return false;
        if (!isCommutative())
            return false;
        return true;
    }

    default boolean isCommutative() {
        return true;
    }

}
