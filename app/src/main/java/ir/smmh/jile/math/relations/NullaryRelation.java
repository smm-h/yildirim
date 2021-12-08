package ir.smmh.jile.math.relations;

import ir.smmh.jile.math.annotations.Controversial;
import ir.smmh.jile.math.tuples.EmptyTuple;
import ir.smmh.jile.math.tuples.FiniteTuple;

/**
 * @implNote A "homogeneous nullary relation" type does not exist all nullary
 * relations are implicitly homogeneous, on any set.
 * @see https://math.stackexchange.com/questions/4178618/is-a-nullary-relation-homogeneous
 */
public interface NullaryRelation extends FinitaryRelation {

    @Override
    default Integer getArity() {
        return 0;
    }

    /**
     * A nullary relation is always homogeneous.
     */
    @Override
    @Controversial
    default boolean isHomogeneous() {
        return true;
    }

    boolean holdsUnchecked();

    @Override
    default Integer getCardinality() {
        return holdsUnchecked() ? 1 : 0;
    }

    @Override
    default FiniteTuple choose() {
        return holdsUnchecked() ? EmptyTuple.singleton() : null;
    }
}
