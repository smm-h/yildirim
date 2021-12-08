package ir.smmh.jile.math.numbers;

import ir.smmh.jile.common.Random;
import ir.smmh.jile.math.settheory.InfiniteUniversalSet;
import ir.smmh.jile.math.settheory.NamedSet;

public class RealSet implements NamedSet, InfiniteUniversalSet<Double> {

    private static RealSet singleton;

    private RealSet() {
    }

    public static RealSet singleton() {
        if (singleton == null) {
            singleton = new RealSet();
        }
        return singleton;
    }

    @Override
    public String getName() {
        return "R";
    }

    @Override
    public Double choose() {
        return Math.sqrt(Random.singleton().nextInt() / 100.0);
    }
}
