package ir.smmh.jile.math.abstractalgebra;

import ir.smmh.jile.math.operations.Addition;
import ir.smmh.jile.math.operations.AdditiveInversion;
import ir.smmh.jile.math.operations.ClosedBinaryOperation;
import ir.smmh.jile.math.operations.Multiplication;
import ir.smmh.jile.math.operations.Operation;
import ir.smmh.jile.math.operations.Subtraction;
import ir.smmh.jile.math.settheory.Set;

/**
 * A {@link NearRing} is an {@link AlgebraicStructure} that is a {@link Group}
 * under {@link Addition}, a {@link SemiGroup} under {@link Multiplication}, and
 * whose multiplication distributes on the right over addition.
 *
 * @see Ring
 */

public interface NearRing<T> extends AlgebraicStructure {

    @Override
    default Set getSet() {
        return getGroup().getSet();
    }

    @Override
    default Operation[] getOperations() {
        Operation[] operations = {getAddition(), getMultiplication()};
        return operations;
    }

    public AdditiveGroup<T> getGroup();

    default Addition<T> getAddition() {
        return getGroup().getOperation();
    }

    public ClosedBinaryOperation<T> getMultiplication();

    default AdditiveInversion<T> getAdditiveInvertion() {
        return getGroup().getInversion();
    }

    default Subtraction<T> makeSubtraction() {
        return new Subtraction<T>() {

            private Addition<T> addition = getAddition();
            private AdditiveInversion<T> additiveInversion = getAdditiveInvertion();

            @Override
            public T operate(T a, T b) {
                return addition.operate(a, additiveInversion.operate(b));
            }
        };
    }

    @Override
    default boolean test() {
        if (!getGroup().test())
            return false;
        if (!getMultiplication().isAssociative(getGroup().getSet()))
            return false;
        if (!getMultiplication().isRightDistributive(getAddition(), getGroup().getSet()))
            return false;
        return true;
    }
}
