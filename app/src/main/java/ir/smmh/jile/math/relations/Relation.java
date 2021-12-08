package ir.smmh.jile.math.relations;

import ir.smmh.jile.math.settheory.Set;
import ir.smmh.jile.math.settheory.SpecificSet;
import ir.smmh.jile.math.tuples.FiniteTuple;

/**
 * A {@link Relation} is a {@link Set} of {@link FiniteTuple}s. A relation over
 * N sets is a subset of the Cartesian product of all of them. In other words,
 * it is a set of tuples in each of which the i'th value is an element of the
 * i'th set.
 *
 * @see https://en.wikipedia.org/wiki/Finitary_relation
 * @see https://en.wikipedia.org/wiki/Relation_(database)
 */
public interface Relation extends SpecificSet<FiniteTuple> {

    /**
     * Returns the arity of the relation, which is the length of each tuple in it.
     * Arity is a non-negative integer, or null if the relation is infinitary.
     *
     * @see #isInfinitary
     */
    Integer getArity();

    /**
     * Returns whether this relation has a finite arity.
     */
    boolean isFinitary();

    /**
     * Returns whether this relation has an infinite arity.
     *
     * @implNote returns the opposite of {@link #isFinitary} unless overridden.
     */
    default boolean isInfinitary() {
        return !isFinitary();
    }

    /**
     * Returns whether or not this relation is homogeneous, meaning all the values
     * in each of its tuples are objects of the same type.
     * <p>
     * It is recommended that you use this method rather than {@code instanceof}
     * {@link HomogeneousRelation} because a relation may be homogeneous and not be
     * an instance of that interface, e.g. {@link NullaryRelation}.
     */
    default boolean isHomogeneous() {
        return false;
    }

    /**
     * Returns whether or not this relation is heterogeneous, meaning the values in
     * its tuples may be objects of various types.
     *
     * @implNote returns the opposite of {@link #isHomogeneous} unless overridden.
     */
    default boolean isHeterogeneous() {
        return !isHomogeneous();
    }

}
