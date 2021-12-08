package ir.smmh.jile.math.series;

import ir.smmh.jile.math.numbers.Real;

/**
 * The {@link IterativeProductSeries} is an implementation of the
 * {@link ProductSeries} that is calculated without the use of recursion.
 *
 * @see RecursiveProductSeries
 */
public interface IterativeProductSeries<H extends Real> extends IterativeSeries, ProductSeries<H> {
    @Override
    default Real getElementAt(int k) {
        Real result = identity;
        for (int i = 0; i < k; i++)
            result = result.multiply(getHostSeries().getElementAt(i));
        return result;
    }
}