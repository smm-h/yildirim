package ir.smmh.jile.math.settheory;

import ir.smmh.jile.math.booleanalgebra.BooleanSet;
import ir.smmh.jile.math.string.CharacterSet;

/**
 * Not all {@link UniversalSet}s are infinite; for example {@link CharacterSet}
 * and {@link BooleanSet} contain a finite number of elements.
 * {@link FiniteUniversalSet} is the base interface for those sets.
 *
 * @see InfiniteUniversalSet
 */
public interface FiniteUniversalSet<T> extends UniversalSet<T>, FiniteSpecificSet<T> {
}
