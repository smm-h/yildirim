package ir.smmh.jile.math.tuples;

/**
 * A {@link Decuple} is a {@link FiniteTuple} with a length of 10.
 * <p>
 * <b>Alternative names:</b> decad / decade (antiquated)
 */
public interface Decuple<First, Second, Third, Fourth, Fifth, Sixth, Seventh, Eighth, Ninth, Tenth> extends FiniteTuple {
    @Override
    default Integer getCardinality() {
        return 10;
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
            case 9:
                return getTenth();
            default:
                throw new IndexOutOfBoundsException("trying to access index: " + index + " in a 10-tuple");
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

    Tenth getTenth();

}