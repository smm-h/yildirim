package ir.smmh.jile.math.abstractalgebra;

import ir.smmh.jile.math.operations.Multiplication;
import ir.smmh.jile.math.operations.MultiplicativeInversion;

/**
 * A "multiplicative" group is a {@link Group} where the binary operation is an
 * {@link Multiplication}
 */

public interface MultiplicativeGroup<T> extends Group<T> {

    @Override
    public Multiplication<T> getOperation();

    @Override
    public MultiplicativeInversion<T> getInversion();

}
