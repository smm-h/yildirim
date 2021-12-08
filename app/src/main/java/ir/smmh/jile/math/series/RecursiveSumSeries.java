package ir.smmh.jile.math.series;

import ir.smmh.jile.math.numbers.Real;

/**
 * A {@link RecursiveSumSeries} is a {@link SumSeries} that is calculated
 * recursively ({@link RecursiveSeries}).
 *
 * @see IterativeSumSeries
 */
public interface RecursiveSumSeries<H extends Real> extends RecursiveSeries, SumSeries<H> {
    @Override
    default Real getElementAt(int k) {
        return k == 0 ? identity : getHostSeries().getElementAt(k - 1).add(getElementAt(k - 1));
    }
}