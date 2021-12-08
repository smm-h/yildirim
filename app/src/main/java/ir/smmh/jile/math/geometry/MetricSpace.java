package ir.smmh.jile.math.geometry;

import ir.smmh.jile.math.settheory.Set;
import ir.smmh.jile.math.settheory.SpecificSet;

/**
 * A {@link MetricSpace} is a {@link Set} equipped with a {@link Metric} on it.
 *
 * @see https://en.wikipedia.org/wiki/Metric_space
 */
public interface MetricSpace<M> extends SpecificSet<M> {
    Metric<M> getMetric();
}
