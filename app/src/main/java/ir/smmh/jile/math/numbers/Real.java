package ir.smmh.jile.math.numbers;

import static ir.smmh.jile.common.q.Quality.is;

import ir.smmh.jile.common.Common;
import ir.smmh.jile.common.q.Quality;
import ir.smmh.jile.math.operations.IntegerModulo;
import ir.smmh.jile.math.operations.Modulo;

/**
 * Since real numbers cannot always be accurately stored in computers, this
 * interface will be implemented by objects that represent various real numbers
 * accurately, and they may be approximated using doubles. This approximation is
 * either accurate, close-enough, or infinity if the actual number is too
 * {@link Big} to fit in double.
 */
public interface Real extends Comparable<Real> {

    Integer ZERO = new IntegerInteger(0);
    Integer ONE = new IntegerInteger(1);
    Integer TWO = new IntegerInteger(2);
    Integer M_ONE = new IntegerInteger(-1);
    Integer THREE = new IntegerInteger(3);
    Integer M_TWO = new IntegerInteger(-2);
    Modulo<Integer> modulo = new IntegerModulo();

    static Real fromContents(Number contents) {
        return fromContents(contents.doubleValue());
    }

    static Real fromContents(double contents) {
        return DoubleReal.fromContents(contents);
    }

    static boolean equals(Real self, Object other) {
        if (self == null || other == null) {
            return false;
        } else if (other instanceof Real) {
            return equals(self, (Real) other);
        } else if (other instanceof Number) {
            return equals(self, (Number) other);
        } else {
            return false;
        }
    }

    static boolean equals(Real self, Real other) {
        return self.approximate() == other.approximate();
    }

    static boolean equals(Real self, Number other) {
        return self.approximate() == other.doubleValue();
    }

    double approximate();

    boolean isPrecise();

    @Override
    default int compareTo(Real other) {
        return Double.compare(approximate(), other.approximate());
    }

    default Real add(Real y) {
        if (isPrecise() && y.isPrecise()) {
            return Real.fromContents(approximate() + y.approximate());
        } else {
            throw new UnsupportedOperationException("non-precise operation");
        }
    }

    default Real subtract(Real other) {
        if (isPrecise() && other.isPrecise()) {
            return Real.fromContents(approximate() - other.approximate());
        } else {
            throw new UnsupportedOperationException("non-precise operation");
        }
    }

    default Real multiply(Real other) {
        if (isPrecise() && other.isPrecise()) {
            return Real.fromContents(approximate() * other.approximate());
        } else {
            throw new UnsupportedOperationException("non-precise operation");
        }
    }

    default Real divide(Number other) {
        return divide(fromContents(other));
    }

    default Real divide(Real other) {
        if (isPrecise() && other.isPrecise()) {
            double n = approximate();
            double d = other.approximate();
            long tens = Common.power(10, Math.max(Common.tens(n), Common.tens(d)));
            return new LongRational((long) (n * tens), (long) (d * tens));
        } else {
            throw new UnsupportedOperationException("non-precise operation");
        }
    }

    default Real sqr() {
        if (isPrecise()) {
            return Real.fromContents(Common.sqr(approximate()));
        } else {
            throw new UnsupportedOperationException("non-precise operation");
        }
    }

    default Real sqrt() {
        if (isPrecise()) {
            return Real.fromContents(Common.sqrt(approximate()));
        } else {
            throw new UnsupportedOperationException("non-precise operation");
        }
    }

    default boolean isInteger() {
        return isPrecise() && Common.is_int(approximate());
    }

    default Integer toInteger() {
        return (Integer) this;
    }

    final class Qualities {
        public static final Quality<Real> whole = n -> n.isInteger();
        public static final Quality<Real> even = n -> is(n, whole) && modulo.operate(n.toInteger(), TWO.toInteger()).approximate() == 0;
        public static final Quality<Real> odd = n -> is(n, whole) && modulo.operate(n.toInteger(), TWO.toInteger()).approximate() != 0;

        public static final Quality<Real> prime = n -> {
            // TODO
            return is(n, whole);
        };

        private Qualities() {
        }

        public interface Multiple {
            static Divisible of(Integer d) {
                return Divisible.by(d);
            }

            static Divisible of(int d) {
                return Divisible.by(d);
            }
        }

        public static class Divisible implements Quality<Real> {

            private final Integer d;

            private Divisible(Integer d) {
                this.d = d;
            }

            public static Divisible by(Integer d) {
                return new Divisible(d);
            }

            public static Divisible by(int d) {
                return new Divisible(Integer.fromContents(d));
            }

            @Override
            public boolean holdsFor(Real n) {
                return is(n, whole) && modulo.operate(n.toInteger(), d).approximate() == 0;
            }

        }

    }
}
