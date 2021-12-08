package ir.smmh.jile.math.tuples;

/**
 * An {@link Quintuple} is a {@link FiniteTuple} with a length of 5.
 * <p>
 * <b>Alternative names:</b> pentuple / quint / pentad
 */
public interface Quintuple<First, Second, Third, Fourth, Fifth> extends FiniteTuple {
    @Override
    default Integer getCardinality() {
        return 5;
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
            default:
                throw new IndexOutOfBoundsException("trying to access index: " + index + " in a 5-tuple");
        }
    }

    First getFirst();

    Second getSecond();

    Third getThird();

    Fourth getFourth();

    Fifth getFifth();

}