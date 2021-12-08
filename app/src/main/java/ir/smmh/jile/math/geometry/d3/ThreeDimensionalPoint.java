package ir.smmh.jile.math.geometry.d3;

import ir.smmh.jile.common.Cache;
import ir.smmh.jile.common.CacheCollisionException;
import ir.smmh.jile.common.HashMapCache;
import ir.smmh.jile.math.geometry.EuclideanSpace;
import ir.smmh.jile.math.geometry.Point;
import ir.smmh.jile.math.numbers.Real;
import ir.smmh.jile.math.tuples.HomogeneousTriple;
import ir.smmh.jile.math.tuples.Triple;

/**
 * A {@link ThreeDimensionalPoint} is a {@link Point} in the 3-dimensional
 * {@link EuclideanSpace} that is defined as a {@link Triple} of {@link Real}
 * numbers.
 */
public abstract class ThreeDimensionalPoint implements ThreeDimensional, Point, HomogeneousTriple<Real> {

    private static final Cache<Point, Real[]> cache = new HashMapCache<Point, Real[]>();

    public static Point fromContents(Real[] contents) {
        assert contents.length == 3;
        Point wrapper;
        wrapper = cache.get(contents);
        if (wrapper != null) {
            return wrapper;
        } else {
            wrapper = new DefaultImplementation(contents[0], contents[1], contents[2]);
            try {
                cache.add(contents, wrapper);
            } catch (CacheCollisionException e) {
                // TODO should hash collision be silenced?
                e.printStackTrace();
            }
            return wrapper;
        }
    }

    private static class DefaultImplementation extends ThreeDimensionalPoint {

        private final Real x, y, z;

        public DefaultImplementation(Real x, Real y, Real z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        @Override
        public Real getFirst() {
            return x;
        }

        @Override
        public Real getSecond() {
            return y;
        }

        @Override
        public Real getThird() {
            return z;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder(24); // 1+6+2+6+2+6+1
            builder.append("("); // 1
            builder.append(x.toString()); // 6
            builder.append(", "); // 2
            builder.append(y.toString()); // 6
            builder.append(", "); // 2
            builder.append(z.toString()); // 6
            builder.append(")"); // 1
            return builder.toString();
        }

        @Override
        public int hashCode() {
            return x.hashCode() ^ y.hashCode() ^ z.hashCode();
        }

        @Override
        public boolean equals(Object other) {
            return Point.equals(this, other);
        }
    }
}