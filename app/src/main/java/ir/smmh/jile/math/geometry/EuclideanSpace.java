package ir.smmh.jile.math.geometry;

import ir.smmh.jile.math.numbers.DoubleReal;
import ir.smmh.jile.math.numbers.Real;
import ir.smmh.jile.math.numbers.RealSet;
import ir.smmh.jile.math.settheory.InfiniteUniversalSet;
import ir.smmh.jile.math.settheory.Set;

/**
 * A {@link EuclideanSpace} is an n-{@link Dimensional} container for
 * n-dimensional {@link GeometricalObject}s, defined as an infinite
 * non-countable {@link Set} of n-dimensional {@link Point}s.
 *
 * @implNote To implement this interface, you only need to override
 * {@link #getDimensions()} and {@link #getObjects()}.
 * @see https://en.wikipedia.org/wiki/Euclidean_space
 */
public interface EuclideanSpace extends Dimensional, InfiniteUniversalSet<Point>, MetricSpace<Point> {

    @Override
    default Point choose() {
        int n = getDimensions();
        Real[] array = new Real[n];
        for (int i = 0; i < n; i++) {
            array[i] = new DoubleReal(RealSet.singleton().choose());
        }
        return Point.fromContents(array);
    }

    Iterable<GeometricalObject> getObjects();

    @Override
    default Metric<Point> getMetric() {
        return new Metric<Point>() {
            @Override
            public Real distance(Point a, Point b) {
                return EuclideanDistance.singleton().distance(a, b);
            }
        };
    }
}
