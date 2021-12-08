package ir.smmh.jile.math.series;

public interface FibonacciSeries extends HigherOrderFibonacciSeries {
    @Override
    default int getOrder() {
        return 2;
    }
}
