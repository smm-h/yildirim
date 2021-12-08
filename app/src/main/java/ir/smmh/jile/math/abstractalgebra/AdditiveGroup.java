package ir.smmh.jile.math.abstractalgebra;

import ir.smmh.jile.math.operations.Addition;
import ir.smmh.jile.math.operations.AdditiveInversion;

/**
 * An "additive" group is a {@link Group} where the binary operation is an
 * {@link Addition}
 */

public interface AdditiveGroup<T> extends Group<T> {

    @Override
    public Addition<T> getOperation();

    @Override
    public AdditiveInversion<T> getInversion();

}
