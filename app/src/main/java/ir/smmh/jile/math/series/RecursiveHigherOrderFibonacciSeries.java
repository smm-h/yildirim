package ir.smmh.jile.math.series;

import ir.smmh.jile.math.numbers.Integer;

public interface RecursiveHigherOrderFibonacciSeries extends HigherOrderFibonacciSeries {

    @Override
    default int fairLimit() {
        return 60 / getOrder();
    }

    @Override
    default Integer getElementAt(int index) {
        int n = getOrder();
        if (index < n) {
            return index == (n - 1) ? Integer.ONE : Integer.ZERO;
        } else {
            Integer s = Integer.ZERO;
            for (int i = 1; i <= n; i++)
                s = s.add(getElementAt(index - i));
            return s;
        }
    }
}
