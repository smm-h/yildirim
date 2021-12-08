package ir.smmh.jile.math.tuples;

/**
 * A {@link Nonuple} is a {@link FiniteTuple} with a length of 9.
 * <p>
 * <b>Alternative names:</b> nonad / ennead
 */
public interface Nonuple<First, Second, Third, Fourth, Fifth, Sixth, Seventh, Eighth, Ninth> extends FiniteTuple {
    @Override
    default Integer getCardinality() {
        return 9;
    }

    @Override
    default Object getElementAt(int index) {
        switch (index) {
            case 0:
                return getFirst();
            case 1:
                return getSecond();
            case 2:
                return getThird();
            case 3:
                return getFourth();
            case 4:
                return getFifth();
            case 5:
                return getSixth();
            case 6:
                return getSeventh();
            case 7:
                return getEighth();
            case 8:
                return getNinth();
            default:
                throw new IndexOutOfBoundsException("trying to access index: " + index + " in a 9-tuple");
        }
    }

    First getFirst();

    Second getSecond();

    Third getThird();

    Fourth getFourth();

    Fifth getFifth();

    Sixth getSixth();

    Seventh getSeventh();

    Eighth getEighth();

    Ninth getNinth();

}