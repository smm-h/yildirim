package ir.smmh.jile.math.operations;

/**
 * An {@link AssociativeClosedBinaryOperation} is a
 * {@link ClosedBinaryOperation} that is "associative", meaning a*b*c is
 * unambiguous.
 *
 * @see https://en.wikipedia.org/wiki/Associative_property
 */
public interface AssociativeClosedBinaryOperation<T> extends ClosedBinaryOperation<T> {
    @Override
    default boolean isAssociative() {
        return true;
    }
}
