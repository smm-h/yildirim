package ir.smmh.jile.math.series;

import java.util.ArrayList;
import java.util.List;

import ir.smmh.jile.common.Random;
import ir.smmh.jile.math.annotations.BadDesign;
import ir.smmh.jile.math.annotations.Inefficient;
import ir.smmh.jile.math.annotations.Suboptimal;
import ir.smmh.jile.math.numbers.Integer;
import ir.smmh.jile.math.numbers.Real;
import ir.smmh.jile.math.sequence.InfiniteSequence;
import ir.smmh.jile.math.sequence.SpecificSequence;

/**
 * A {@link Series} is an {@link InfiniteSequence} of {@link T} values.
 *
 * @see ParasiteSeries
 * @see OffsetSeries
 * @see SumSeries
 * @see ProductSeries
 * @see ArithmeticSeries
 * @see GeometricSeries
 */
public interface Series<T extends Real> extends InfiniteSequence, SpecificSequence<T> {

    @Override
    default List<T> excerpt() {
        int n = fairLimit();
        List<T> list = new ArrayList<T>(n);
        for (int i = 0; i < n; i++)
            list.add(i, getElementAt(i));
        return list;
    }

    @BadDesign
    default List<Integer> excerptWhole() {
        int n = fairLimit();
        List<Integer> list = new ArrayList<Integer>(n);
        for (int i = 0; i < n; i++) {
            T e = getElementAt(i);
            list.add(i, e.toInteger());
        }
        return list;
    }

    @Inefficient
    @Suboptimal
    @Override
    default boolean contains(T n) {
        int k = 0;
        T e;
        while (true) {
            e = getElementAt(k++);
            if (e.compareTo(n) >= 0)
                return e.equals(n);
            if (k > fairLimit())
                break;
        }
        return false;
    }

    @Override
    default int chooseElement() {
        return Random.singleton().nextInt(fairLimit());
    }

    int fairLimit();

}
