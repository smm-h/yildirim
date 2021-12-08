package ir.smmh.jile.math.series;

import static ir.smmh.jile.common.q.Quality.is;

import ir.smmh.jile.math.numbers.Integer;
import ir.smmh.jile.math.numbers.Real;

/**
 * @see https://en.wikipedia.org/wiki/Arithmetic_progression
 */
public interface ArithmeticSeries extends Series<Integer> {

    Integer getInitialTerm();

    Integer getDifference();

    @Override
    default boolean contains(Integer n) {
        return is(n.subtract(getInitialTerm()), Real.Qualities.Multiple.of(getDifference()));
    }

    @Override
    default Integer getElementAt(int index) {
        return getInitialTerm().add(getDifference().multiply(index));
    }

    @Override
    default int fairLimit() {
        return 1000;
    }

}
