package ir.smmh.jile.math.tuples;

/**
 * A {@link Quadruple} is a {@link FiniteTuple} with a length of 4.
 * <p>
 * <b>Alternative names:</b> quad / tetrad / quartet / quadruplet
 */
public interface Quadruple<First, Second, Third, Fourth> extends FiniteTuple {
    @Override
    default Integer getCardinality() {
        return 4;
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
            default:
                throw new IndexOutOfBoundsException("trying to access index: " + index + " in a 4-tuple");
        }
    }

    First getFirst();

    Second getSecond();

    Third getThird();

    Fourth getFourth();
}