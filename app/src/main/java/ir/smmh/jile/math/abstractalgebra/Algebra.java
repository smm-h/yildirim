package ir.smmh.jile.math.abstractalgebra;

import ir.smmh.jile.math.operations.Addition;
import ir.smmh.jile.math.operations.Multiplication;
import ir.smmh.jile.math.operations.Operation;
import ir.smmh.jile.math.operations.ScalarMultiplication;
import ir.smmh.jile.math.settheory.Set;

/**
 * An {@link Algebra}, or "an algebra over a field", is a {@link VectorSpace}
 * equipped with a bilinear product. Thus, it is an {@link AlgebraicStructure}
 * consisting of a {@link Set} together with {@link Operation}s of
 * {@link Addition} and {@link Multiplication}, and {@link ScalarMultiplication}
 * by elements of a field and satisfying the axioms implied by "vector space"
 * and "bilinear".
 */
public interface Algebra {
}
