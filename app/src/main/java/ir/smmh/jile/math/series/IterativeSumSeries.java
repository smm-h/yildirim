package ir.smmh.jile.math.series;

import ir.smmh.jile.math.numbers.Real;

/**
 * An {@link IterativeSumSeries} is a {@link SumSeries} that is calculated
 * iteratively ({@link IterativeSeries}).
 *
 * @see RecursiveSumSeries
 */
public interface IterativeSumSeries<H extends Real> extends IterativeSeries, SumSeries<H> {
    @Override
    default Real getElementAt(int k) {
        Real result = identity;
        for (int i = 0; i < k; i++)
            result = result.add(getHostSeries().getElementAt(i));
        return result;
    }
}