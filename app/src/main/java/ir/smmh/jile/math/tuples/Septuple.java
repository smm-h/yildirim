package ir.smmh.jile.math.tuples;

/**
 * A {@link Septuple} is a {@link FiniteTuple} with a length of 7.
 * <p>
 * <b>Alternative names:</b> heptuple / heptad
 */
public interface Septuple<First, Second, Third, Fourth, Fifth, Sixth, Seventh> extends FiniteTuple {
    @Override
    default Integer getCardinality() {
        return 7;
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
            default:
                throw new IndexOutOfBoundsException("trying to access index: " + index + " in a 7-tuple");
        }
    }

    First getFirst();

    Second getSecond();

    Third getThird();

    Fourth getFourth();

    Fifth getFifth();

    Sixth getSixth();

    Seventh getSeventh();

}