package ir.smmh.jile.math.relationalalgebra;

import ir.smmh.jile.math.settheory.Set;
import ir.smmh.jile.math.settheory.StoredSet;
import ir.smmh.jile.math.tuples.FiniteTuple;

/**
 * A {@link StoredRelation} is a {@link Set} of {@link FiniteTuple}s.
 *
 * @see https://en.wikipedia.org/wiki/Relation_(database)
 */

public class StoredRelation extends StoredSet<FiniteTuple> {

    // private final TupleHeader header;

    public StoredRelation(Iterable<FiniteTuple> tuples) {
        super(tuples);
    }
}
