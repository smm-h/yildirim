package ir.smmh.jile.math.abstractalgebra;

import ir.smmh.jile.math.annotations.BadDesign;
import ir.smmh.jile.math.operations.Multiplication;

/**
 * A {@link Ring} is an {@link AbelianGroup} with an additional binary operation
 * called {@link Multiplication}, on which the focus is on.
 */
public interface Ring<T> extends NearRing<T> {

    @Override
    public AdditiveAbelianGroup<T> getGroup();

    public Multiplication<T> getMultiplication();

    default T add(T x, T y) {
        return getAddition().operate(x, y);
    }

    default T negate(T x) {
        return getAdditiveInvertion().operate(x);
    }

    default T subtract(T x, T y) {
        return getAddition().operate(x, negate(y));
    }

    public T multiply(T x, T y);

    public T divide(T x, T y);

    /**
     * Checks the associativity of the additional operation and its distributivity
     * over the abelian group operation.
     */
    @Override
    default boolean test() {
        if (!NearRing.super.test())
            return false;
        if (getMultiplication() == null)
            return false;
        if (!getMultiplication().isAssociative(getGroup().getSet()))
            return false;
        if (!getMultiplication().isDistributive(getAddition(), getGroup().getSet()))
            return false;
        return true;
    }

    @BadDesign
    default boolean isCommutative() {
        return getMultiplication().isCommutative(getGroup().getSet());
    }

}