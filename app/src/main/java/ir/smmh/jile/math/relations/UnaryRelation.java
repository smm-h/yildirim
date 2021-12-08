package ir.smmh.jile.math.relations;

import ir.smmh.jile.math.operations.ClosedUnaryOperation;

/**
 * @implNote A "homogeneous unary relation" type does not exist because unlike
 * in operations, a unary relation is already implicitly homogeneous.
 * @see ClosedUnaryOperation
 */
public interface UnaryRelation<A> extends HomogeneousRelation<A> {

    @Override
    default Integer getArity() {
        return 1;
    }

    boolean holds(A a);

    @SuppressWarnings("unchecked")
    default boolean holdsUnchecked(Object a) throws ClassCastException {
        return holds((A) a);
    }

    @Override
    default boolean isHomogeneous() {
        return true;
    }
}
