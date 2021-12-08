package ir.smmh.jile.math.topology;

import ir.smmh.jile.math.geometry.EuclideanSpace;
import ir.smmh.jile.math.geometry.Metric;
import ir.smmh.jile.math.geometry.Point;
import ir.smmh.jile.math.settheory.Set;

/**
 * A {@link TopologicalSpace} is a {@link EuclideanSpace} in which "closeness"
 * is defined but not neccesarily via a {@link Metric}. It is defined as a
 * {@link Set} of {@link Point}s, along with a set of {@link Neighborhood}s for
 * each point.
 * <p>
 * A topological space is the most general type of a mathematical space that
 * allows for the definition of limits, continuity, and connectedness.[1] Other
 * spaces, such as Euclidean spaces, metric spaces and manifolds, are
 * topological spaces with extra structures, properties or constraints.
 *
 * @see https://en.wikipedia.org/wiki/Topological_space
 */
public interface TopologicalSpace {
}
