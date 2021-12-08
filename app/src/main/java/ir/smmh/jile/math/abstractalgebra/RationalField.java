package ir.smmh.jile.math.abstractalgebra;

import ir.smmh.jile.common.Random;
import ir.smmh.jile.common.Singleton;
import ir.smmh.jile.math.numbers.LongRational;
import ir.smmh.jile.math.numbers.Rational;
import ir.smmh.jile.math.operations.Addition;
import ir.smmh.jile.math.operations.AdditiveInversion;
import ir.smmh.jile.math.operations.Multiplication;
import ir.smmh.jile.math.operations.MultiplicativeInversion;
import ir.smmh.jile.math.settheory.InfiniteUniversalSet;

public class RationalField extends BaseField<Rational> implements InfiniteUniversalSet<Rational>, Singleton {

    private static RationalField singleton;

    public static RationalField singleton() {
        if (singleton == null) {
            singleton = new RationalField();
        }
        return singleton;
    }

    private RationalField() {
        super(new LongRational(0), new AdditiveInversion<Rational>() {
            @Override
            public Rational operate(Rational x) {
                return x.negate();
            }
        }, new Addition<Rational>() {
            @Override
            public Rational operate(Rational x, Rational y) {
                return x.add(y);
            }
        }, new LongRational(1), new MultiplicativeInversion<Rational>() {
            @Override
            public Rational operate(Rational x) {
                return x.reciprocal();
            }
        }, new Multiplication<Rational>() {
            @Override
            public Rational operate(Rational x, Rational y) {
                return x.multiply(y);
            }
        });
    }

    @Override
    public Rational choose() {
        return new LongRational(Random.singleton().nextInt(10000), Random.singleton().nextInt(10000));
    }
}
