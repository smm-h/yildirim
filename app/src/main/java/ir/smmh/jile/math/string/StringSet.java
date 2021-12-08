package ir.smmh.jile.math.string;

import ir.smmh.jile.common.Singleton;
import ir.smmh.jile.math.abstractalgebra.FreeMonoid;
import ir.smmh.jile.math.settheory.Set;
import ir.smmh.jile.math.settheory.SpecificSet;

/**
 * The {@link StringSet} is the {@link Set} of all strings. It is a
 * {@link FreeMonoid} over the set of all characters ({@link CharacterSet}).
 */
public class StringSet implements FreeMonoid<Character>, Singleton {

    private static StringSet singleton;

    private StringSet() {
    }

    public static StringSet singleton() {
        if (singleton == null) {
            singleton = new StringSet();
        }
        return singleton;
    }

    @Override
    public SpecificSet<Character> getFreeSet() {
        return CharacterSet.singleton();
    }

}
