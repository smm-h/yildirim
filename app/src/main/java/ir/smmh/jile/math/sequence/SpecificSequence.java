package ir.smmh.jile.math.sequence;

import ir.smmh.jile.math.settheory.OrderedSet;
import ir.smmh.jile.math.settheory.SpecificSet;

/**
 * An {@link SpecificSequence} is a {@link Sequence} whose values are of a
 * certain type; in this sense it is also an ordered ({@link OrderedSet})
 * specific ({@link SpecificSet}) set.
 *
 * @see FiniteSpecificSequence
 */
public interface SpecificSequence<T> extends Sequence, SpecificSet<T> {
    @Override
    T getElementAt(int index);

    @Override
    default T choose() {
        return getElementAt(chooseElement());
    }
}
