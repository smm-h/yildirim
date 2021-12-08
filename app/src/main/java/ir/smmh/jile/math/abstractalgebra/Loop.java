package ir.smmh.jile.math.abstractalgebra;

import ir.smmh.jile.math.annotations.Convenient;
import ir.smmh.jile.math.annotations.QualityTest;
import ir.smmh.jile.math.operations.ClosedUnaryOperation;

/**
 * A {@link Loop} is an invertible {@link UnitalMagma}.
 * <ul>
 * <li><b>Closure</b>: ∀ a, b ∈ S, a ⋆ b ∈ S</li>
 * <li><b>Associativity</b>: ∀ a, b, c ∈ S, (a ⋆ b) ⋆ c = a ⋆ (b ⋆ c)</li>
 * <li><b>Identity element</b>: ∀ g ∈ S ∃ e that g ⋆ e = e ⋆ g = g ∈ S</li>
 * <li><b>Inverse element</b>: ∀ g ∈ S ∃ h that g ⋆ h = h ⋆ g = e ∈ S</li>
 * </ul>
 */

public interface Loop<T> extends UnitalMagma<T> {

    public ClosedUnaryOperation<T> getInversion();

    /**
     * Tests unitalness and invertibility.
     */
    @Override
    default boolean test() {
        if (!UnitalMagma.super.test())
            return false;
        if (!testInvertibility())
            return false;
        return true;
    }

    @Convenient
    default T inverse(T x) {
        return getInversion().operate(x);
    }

    @QualityTest
    @Override
    default boolean testInvertibility() {
        return testInvertibility(getSet().choose());
    }

    default boolean testInvertibility(T x) {
        return operate(x, inverse(x)) == getIdentity() && operate(inverse(x), x) == getIdentity();
    }
}
