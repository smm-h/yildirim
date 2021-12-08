package ir.smmh.jile.math.string;

import java.util.Iterator;

import ir.smmh.jile.common.Random;
import ir.smmh.jile.common.Singleton;
import ir.smmh.jile.math.settheory.FiniteUniversalSet;

/**
 * The {@link CharacterSet} is the non-nullible finite universal set of all
 * characters ({@link FiniteUniversalSet}).
 */
public class CharacterSet implements FiniteUniversalSet<Character>, Singleton {

    private static CharacterSet singleton;
    private final int cardianlity = 96;
    private final int offset = 32;

    private CharacterSet() {
    }

    public static CharacterSet singleton() {
        if (singleton == null) {
            singleton = new CharacterSet();
        }
        return singleton;
    }

    @Override
    public Integer getCardinality() {
        return cardianlity;
    }

    @Override
    public Character choose() {
        return (char) (Random.singleton().nextInt(cardianlity) + offset);
    }

    @Override
    public Iterator<Character> iterator() {
        return new Iterator<Character>() {

            int index = offset;

            @Override
            public boolean hasNext() {
                return index < cardianlity + offset;
            }

            @Override
            public Character next() {
                return (char) index++;
            }
        };
    }
}
