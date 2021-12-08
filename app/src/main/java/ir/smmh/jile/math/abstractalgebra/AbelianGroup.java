package ir.smmh.jile.math.abstractalgebra;

/**
 * An {@link AbelianGroup} is a {@link Group} whose operation is commutative,
 * i.e. x⋆y = y⋆x.
 *
 * @see {@linkir.smmh.jile.math.operations.ClosedBinaryOperation#isCommutative(T x, T y)}
 */

public interface AbelianGroup<T> extends Group<T> {

    @Override
    default boolean test() {
        if (!Group.super.test())
            return false;
        if (!getOperation().isCommutative(getSet()))
            return false;
        return true;
    }

}
