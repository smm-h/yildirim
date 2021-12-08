package ir.smmh.jile.math.abstractalgebra;

import ir.smmh.jile.common.Random;
import ir.smmh.jile.common.Singleton;
import ir.smmh.gebra.values.Complex;
import ir.smmh.jile.math.operations.Addition;
import ir.smmh.jile.math.operations.AdditiveInversion;
import ir.smmh.jile.math.operations.Multiplication;
import ir.smmh.jile.math.operations.MultiplicativeInversion;
import ir.smmh.jile.math.settheory.InfiniteUniversalSet;

public class ComplexField extends BaseField<Complex> implements InfiniteUniversalSet<Complex>, Singleton {

    private static ComplexField singleton;

    public static ComplexField singleton() {
        if (singleton == null) {
            singleton = new ComplexField();
        }
        return singleton;
    }

    private ComplexField() {
        super(new Complex(0), new AdditiveInversion<Complex>() {
            @Override
            public Complex operate(Complex x) {
                return x.negate();
            }
        }, new Addition<Complex>() {
            @Override
            public Complex operate(Complex x, Complex y) {
                return x.add(y);
            }
        }, new Complex(1), new MultiplicativeInversion<Complex>() {
            @Override
            public Complex operate(Complex x) {
                return x.reciprocal();
            }
        }, new Multiplication<Complex>() {
            @Override
            public Complex operate(Complex x, Complex y) {
                return x.multiply(y);
            }
        });
    }

    @Override
    public Complex choose() {
        return new Complex(Random.singleton().nextInt(10000), Random.singleton().nextInt(10000));
    }
}
