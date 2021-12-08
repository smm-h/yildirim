package ir.smmh.jile.math.abstractalgebra;

import ir.smmh.jile.math.operations.ClosedOperation;
import ir.smmh.jile.math.settheory.SpecificSet;

/**
 * A {@link Field} embodies not only a {@link UnitaryRing}, but also the
 * {@link AbelianGroup} for that {@link Ring} and the {@link SpecificSet} for
 * that {@link Group}; this means the functionalities of a set, a group, and a
 * ring are all exposed by a field.
 *
 * @see https://math.stackexchange.com/questions/164114/is-there-any-difference-between-the-definition-of-a-commutative-ring-and-field#comment377820_164114
 */
public interface Field<T> extends SpecificSet<T>, AdditiveAbelianGroup<T>, UnitaryRing<T> {

    @Override
    default SpecificSet<T> getSet() {
        return this;
    }

    @Override
    default ClosedOperation<T>[] getOperations() {
        return AdditiveAbelianGroup.super.getOperations();
    }

    @Override
    default boolean test() {
        return AdditiveAbelianGroup.super.test() && UnitaryRing.super.test();
    }

}
