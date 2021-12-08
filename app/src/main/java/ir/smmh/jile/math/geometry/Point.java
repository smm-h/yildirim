package ir.smmh.jile.math.geometry;

import ir.smmh.jile.math.geometry.d2.TwoDimensionalPoint;
import ir.smmh.jile.math.geometry.d3.ThreeDimensionalPoint;
import ir.smmh.jile.math.numbers.Real;
import ir.smmh.jile.math.tuples.HomogeneousFiniteTuple;
import ir.smmh.jile.math.tuples.HomogeneousTuple;
import ir.smmh.jile.math.tuples.Tuple;

/**
 * A {@link Point} is an n-dimensional {@link GeometricalObject}, defined as an
 * n-{@link Tuple} of {@link Real} numbers, that denotes an exact location in an
 * n-dimensional {@link EuclideanSpace}.
 *
 * @see HomogeneousTuple
 * @see TwoDimensionalPoint
 * @see ThreeDimensionalPoint
 */
public interface Point extends Dimensional, HomogeneousFiniteTuple<Real> {

    static Point fromContents(Real[] contents) {
        switch (contents.length) {
            case 2:
                return TwoDimensionalPoint.fromContents(contents);
            case 3:
                return ThreeDimensionalPoint.fromContents(contents);
            default:
                return GeneralPoint.fromContents(contents);
        }
    }

    static boolean equals(Point self, Object other) {
        if (self == null || other == null) {
            return false;
        } else if (other instanceof Point) {
            return equals(self, (Point) other);
        } else {
            return false;
        }
    }

    static boolean equals(Point self, Point other) {
        int n = self.getDimensions();
        if (n == other.getDimensions()) {
            for (int i = 0; i < n; i++) {
                if (!self.getElementAt(i).equals(other.getElementAt(i))) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }
}