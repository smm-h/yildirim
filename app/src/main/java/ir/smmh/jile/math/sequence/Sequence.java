package ir.smmh.jile.math.sequence;

import ir.smmh.jile.math.settheory.OrderedSet;

/**
 * A {@link Sequence} is an {@link OrderedSet}.
 */
public interface Sequence extends OrderedSet {

    Object getElementAt(int index);

    int chooseElement();

    @Override
    default Object choose() {
        return getElementAt(chooseElement());
    }

}
