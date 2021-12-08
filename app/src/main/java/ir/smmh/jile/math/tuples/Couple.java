package ir.smmh.jile.math.tuples;

/**
 * A {@link Couple} is a {@link FiniteTuple} with a length of 2.
 * <p>
 * <b>Alternative names:</b> double / ordered pair / two-ple / twin / dual /
 * duad / dyad / twosome
 */
public interface Couple<First, Second> extends FiniteTuple {
    @Override
    default Integer getCardinality() {
        return 2;
    }

    @Override
    default Object getElementAt(int index) {
        switch (index) {
            case 0:
                return getFirst();
            case 1:
                return getSecond();
            default:
                throw new IndexOutOfBoundsException("trying to access index: " + index + " in a 2-tuple");
        }
    }

    First getFirst();

    Second getSecond();

}