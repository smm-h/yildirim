package ir.smmh.jile.math.series;

import ir.smmh.jile.math.numbers.Real;

/**
 * The {@link RecursiveProductSeries} is an implementation of the
 * {@link ProductSeries} that is calculated using recursion.
 *
 * @see IterativeProductSeries
 */
public interface RecursiveProductSeries<H extends Real> extends RecursiveSeries, ProductSeries<H> {
    @Override
    default Real getElementAt(int k) {
        return k == 0 ? identity : getHostSeries().getElementAt(k - 1).multiply(getElementAt(k - 1));
    }
}