package ir.smmh.jile.math.numbers;

/**
 * A {@link Rational} number is a {@link Real} number that can be represented as
 * a {@link Fraction} of two {@link Number}s of the same type, one called the
 * "numerator" and the other "denominator", the latter of which cannot be zero.
 *
 * @see LongRational
 */
public interface Rational extends Real {

    Integer getNumerator();

    Integer getDenominator();

    Rational reciprocal();

    Rational negate();

    Rational add(Rational other);

    Rational multiply(Rational other);

    // public boolean isProper();

    @Override
    default boolean isPrecise() {
        // TODO
        return false;
    }
}
