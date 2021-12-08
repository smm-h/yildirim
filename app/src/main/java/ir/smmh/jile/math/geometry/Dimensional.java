package ir.smmh.jile.math.geometry;

import ir.smmh.jile.math.geometry.d2.TwoDimensional;
import ir.smmh.jile.math.geometry.d3.ThreeDimensional;

/**
 * Anything that is {@link Dimensional} is either a {@link GeometricalObject}
 * that resides somewhere within a space, or is a {@link EuclideanSpace} that
 * holds some geometrical objects. This type is meant to be the super-type for
 * almost every other type in this package.
 *
 * @see TwoDimensional
 * @see ThreeDimensional
 */
public interface Dimensional {
    int getDimensions();
}
