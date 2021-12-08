package ir.smmh.jile.math.series;

import ir.smmh.jile.common.Common;
import ir.smmh.jile.math.numbers.Real;

/**
 * https://en.wikipedia.org/wiki/Geometric_series
 */
public interface GeometricSeries extends Series<Real> {

    Real getCoefficient();

    Real getRatio();

    @Override
    default boolean contains(Real n) {
        return Common.isPowerOf(n.divide(getCoefficient()).approximate(), getRatio().approximate());
    }

    @Override
    default Real getElementAt(int index) {
        return Real.fromContents(getCoefficient().approximate() * Common.power(getRatio().approximate(), (double) index));
    }

    @Override
    default int fairLimit() {
        return 100;
    }
}
