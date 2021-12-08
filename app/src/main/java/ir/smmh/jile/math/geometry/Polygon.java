package ir.smmh.jile.math.geometry;

import ir.smmh.jile.math.numbers.Real;

/**
 * A {@link Polygon} is a closed {@link PolygonalChain}.
 */
public interface Polygon extends PolygonalChain {

    Real getPerimeter();
}
