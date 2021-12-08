package ir.smmh.jile.math.sequence;

import ir.smmh.jile.common.Random;

/**
 * A {@link FiniteSequence} is a {@link Sequence} whose length is finite, and
 * thus can be converted to an array.
 */
public interface FiniteSequence extends Sequence {

    @Override
    default boolean isFinite() {
        return true;
    }

    default Object[] toArray() {
        int length = getCardinality();
        Object[] array = new Object[length];
        for (int i = 0; i < length; i++)
            array[i] = getElementAt(i);
        return array;
    }

    @Override
    default int chooseElement() {
        return Random.singleton().nextInt(getCardinality());
    }

}
