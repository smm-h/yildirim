package ir.smmh.jile.math.abstractalgebra;

import ir.smmh.jile.common.Random;
import ir.smmh.jile.common.Singleton;
import ir.smmh.jile.math.operations.Addition;
import ir.smmh.jile.math.operations.AdditiveInversion;
import ir.smmh.jile.math.operations.Multiplication;
import ir.smmh.jile.math.operations.MultiplicativeInversion;
import ir.smmh.jile.math.settheory.InfiniteUniversalSet;

public class RealField extends BaseField<Double> implements InfiniteUniversalSet<Double>, Singleton {

    private static RealField singleton;

    public static RealField singleton() {
        if (singleton == null) {
            singleton = new RealField();
        }
        return singleton;
    }

    private RealField() {
        super(0.0, new AdditiveInversion<Double>() {
            @Override
            public Double operate(Double x) {
                return -x;
            }
        }, new Addition<Double>() {
            @Override
            public Double operate(Double x, Double y) {
                return x + y;
            }
        }, 1.0, new MultiplicativeInversion<Double>() {
            @Override
            public Double operate(Double x) {
                return 1 / x;
            }
        }, new Multiplication<Double>() {
            @Override
            public Double operate(Double x, Double y) {
                return x * y;
            }
        });
    }

    @Override
    public Double choose() {
        return Math.sqrt(Random.singleton().nextInt() / 100.0);
    }
}
