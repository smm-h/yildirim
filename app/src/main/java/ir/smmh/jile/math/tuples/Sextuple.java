package ir.smmh.jile.math.tuples;

/**
 * A {@link Sextuple} is a {@link FiniteTuple} with a length of 6.
 * <p>
 * <b>Alternative names:</b> hextuple / hexad
 */
public interface Sextuple<First, Second, Third, Fourth, Fifth, Sixth> extends FiniteTuple {

    @Override
    default Integer getCardinality() {
        return 6;
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
            default:
                throw new IndexOutOfBoundsException("trying to access index: " + index + " in a 6-tuple");
        }
    }

    First getFirst();

    Second getSecond();

    Third getThird();

    Fourth getFourth();

    Fifth getFifth();

    Sixth getSixth();

}