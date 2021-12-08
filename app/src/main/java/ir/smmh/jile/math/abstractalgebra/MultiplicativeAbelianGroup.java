package ir.smmh.jile.math.abstractalgebra;

import ir.smmh.jile.math.operations.Multiplication;
import ir.smmh.jile.math.operations.MultiplicativeInversion;

/**
 * A "multiplicative" abelian group is an {@link AbelianGroup} where the binary
 * operation is an {@link Multiplication}
 */

public interface MultiplicativeAbelianGroup<T> extends AbelianGroup<T>, MultiplicativeGroup<T> {

    @Override
    public Multiplication<T> getOperation();

    @Override
    public MultiplicativeInversion<T> getInversion();

}
