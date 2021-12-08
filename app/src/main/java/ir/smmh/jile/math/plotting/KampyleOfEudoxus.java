package ir.smmh.jile.math.plotting;

import ir.smmh.jile.common.Common;

/**
 * @see "https://en.wikipedia.org/wiki/Kampyle_of_Eudoxus"
 */
public class KampyleOfEudoxus implements CartesianEquation {

    private final double a;

    public KampyleOfEudoxus(double a) {
        this.a = a;
    }

    @Override
    public double y(double x) {
        // x4=a2(x2+y2)
        // x2((x/a)2-1)=y2
        // y = +- x sqrt((x/a)2-1)
        return x * Common.sqrt(Common.sqr(x / a) - 1);
    }
}
