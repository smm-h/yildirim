package ir.smmh.jile.math.settheory;

/**
 * A {@link FiniteSet} is a {@link Set} whose cardinality is less than
 * aleph-naught.
 */
public interface FiniteSet extends Set {
    default boolean isFinite() {
        return true;
    }
}
