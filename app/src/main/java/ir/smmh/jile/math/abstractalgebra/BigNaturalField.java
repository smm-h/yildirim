package ir.smmh.jile.math.abstractalgebra;

import ir.smmh.jile.common.Common;
import ir.smmh.jile.common.Random;
import ir.smmh.jile.common.Singleton;
import ir.smmh.jile.math.numbers.BigNatural;
import ir.smmh.jile.math.operations.Addition;
import ir.smmh.jile.math.operations.AdditiveInversion;
import ir.smmh.jile.math.operations.Multiplication;
import ir.smmh.jile.math.operations.MultiplicativeInversion;
import ir.smmh.jile.math.settheory.InfiniteUniversalSet;

public class BigNaturalField extends BaseField<BigNatural> implements InfiniteUniversalSet<BigNatural>, Singleton {

    private static BigNaturalField singleton;

    public static BigNaturalField singleton() {
        if (singleton == null) {
            singleton = new BigNaturalField(Common.RADIX_BIN);
        }
        return singleton;
    }

    private final byte radix;

    private BigNaturalField(byte radix) {
        super(new BigNatural("0", radix), new AdditiveInversion<BigNatural>() {
            @Override
            public BigNatural operate(BigNatural x) {
                // return x.additiveInverse();
                throw new UnsupportedOperationException("cannot subtract BigNatural");
            }
        }, new Addition<BigNatural>() {
            @Override
            public BigNatural operate(BigNatural x, BigNatural y) {
                return x.add(y);
            }
        }, new BigNatural("1", radix), new MultiplicativeInversion<BigNatural>() {
            @Override
            public BigNatural operate(BigNatural x) {
                // return x.multiplicativeInverse();
                throw new UnsupportedOperationException("cannot divide BigNatural");
            }
        }, new Multiplication<BigNatural>() {
            @Override
            public BigNatural operate(BigNatural x, BigNatural y) {
                return x.multiply(y);
            }
        });
        this.radix = radix;
    }

    @Override
    public BigNatural choose() {
        return new BigNatural(Random.singleton().nextLong(), radix);
    }
}
