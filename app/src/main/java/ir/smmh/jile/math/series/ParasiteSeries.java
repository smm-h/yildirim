package ir.smmh.jile.math.series;

import ir.smmh.jile.math.numbers.Real;

/**
 * A {@link ParasiteSeries} is a {@link Series} that internally uses the
 * elements of another series, called the "host" series, to produce its own
 * elements.
 *
 * @see SumSeries
 * @see ProductSeries
 * @see FactorialSeries
 */
public interface ParasiteSeries<T extends Real, H extends T> extends Series<T> {
    Series<H> getHostSeries();
}
