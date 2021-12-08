package ir.smmh.jile.math.operations;

import ir.smmh.jile.math.settheory.SpecificSet;

public interface ClosedBinaryOperation<T> extends ClosedOperation<T>, BinaryOperation<T, T, T> {

    @Override
    public BinaryOperation.BinarySymbolPlacement getPlacement();

    default boolean isAssociative(SpecificSet<T> set) {
        return isAssociative(set.choose(), set.choose(), set.choose());
    }

    /**
     * "the order [in which chained operations are done] doesn't matter"
     */
    default boolean isAssociative(T x, T y, T z) {
        return operate(x, operate(y, z)) == operate(operate(x, y), z);
    }

    default boolean isCommutative(SpecificSet<T> set) {
        return isCommutative(set.choose(), set.choose());
    }

    default boolean isCommutative(T x, T y) {
        return operate(x, y) == operate(y, x);
    }

    /**
     * Checks the distributivity of this operation over another, using random
     * elements from the set.
     */
    default boolean isDistributive(ClosedBinaryOperation<T> other, SpecificSet<T> set) {
        return isLeftDistributive(other, set) && isRightDistributive(other, set);
    }

    /**
     * Checks the distributivity of this operation over another, using three
     * specific elements.
     */
    default boolean isDistributive(ClosedBinaryOperation<T> other, T x, T y, T z) {
        return isLeftDistributive(other, x, y, z) && isRightDistributive(other, x, y, z);
    }

    /**
     * Checks the left-distributivity of this operation over another, using random
     * elements from the set.
     */
    default boolean isLeftDistributive(ClosedBinaryOperation<T> other, SpecificSet<T> set) {
        return isLeftDistributive(other, set.choose(), set.choose(), set.choose());
    }

    /**
     * Checks the left-distributivity of this operation over another, using three
     * specific elements.
     */
    default boolean isLeftDistributive(ClosedBinaryOperation<T> other, T x, T y, T z) {
        return operate(other.operate(x, y), z) == other.operate(operate(x, y), operate(x, z));
    }

    /**
     * Checks the right-distributivity of this operation over another, using random
     * elements from the set.
     */
    default boolean isRightDistributive(ClosedBinaryOperation<T> other, SpecificSet<T> set) {
        return isRightDistributive(other, set.choose(), set.choose(), set.choose());
    }

    /**
     * Checks the right-distributivity of this operation over another, using three
     * specific elements.
     */
    default boolean isRightDistributive(ClosedBinaryOperation<T> other, T x, T y, T z) {
        return operate(x, other.operate(y, z)) == other.operate(operate(x, y), operate(x, z));
    }
}
