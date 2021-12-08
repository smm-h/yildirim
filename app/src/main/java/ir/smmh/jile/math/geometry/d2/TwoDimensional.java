package ir.smmh.jile.math.geometry.d2;

import ir.smmh.jile.math.geometry.Dimensional;
import ir.smmh.jile.math.geometry.d3.ThreeDimensional;

/**
 * Anything {@link TwoDimensional} is 2-{@link Dimensional}. This type is meant
 * to be the super-type for almost every other type in this package.
 *
 * @see ThreeDimensional
 */
public interface TwoDimensional extends Dimensional {
    @Override
    default int getDimensions() {
        return 2;
    }
}
