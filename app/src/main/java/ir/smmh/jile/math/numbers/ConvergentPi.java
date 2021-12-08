package ir.smmh.jile.math.numbers;

import ir.smmh.jile.math.series.ChudnovskySeries;
// import ir.smmh.jile.math.series.GregoryLeibnizSeries;

/**
 * A {@link ConvergentPi} is a {@link Convergent} that uses the
 * {@link ChudnovskySeries} to converge to {@link Pi}.
 */
public class ConvergentPi extends BaseIrrationalConvergent implements Pi {

    private ConvergentPi() {
        super(20);
    }

    @Override
    public double converge(int depth) {
        return Real.ONE.divide(new ChudnovskySeries().getElementAt(depth)).approximate();
        // return new GregoryLeibnizSeries().getElementAt(depth);
    }
}
