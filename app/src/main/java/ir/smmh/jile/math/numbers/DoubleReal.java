package ir.smmh.jile.math.numbers;

import androidx.annotation.NonNull;

import ir.smmh.jile.common.Cache;
import ir.smmh.jile.common.CacheCollisionException;
import ir.smmh.jile.common.Common;
import ir.smmh.jile.common.HashMapCache;

/**
 * A {@link DoubleReal} is a {@link Real} number implemented using Java's
 * {@link Double}.
 */
public class DoubleReal extends BaseNumber {

    private static final Cache<Real, Double> cache = new HashMapCache<Real, Double>();
    private final double value;

    public DoubleReal(double value) {
        this.value = value;
    }

    public DoubleReal(String string) {
        this(Double.parseDouble(string));
    }

    public static Real fromContents(Double contents) {
        if (contents == null)
            throw new NullPointerException();
        else if (contents == 0)
            return ZERO;
        else if (contents == 1)
            return ONE;
        else if (contents == 2)
            return TWO;
        else if (contents == -1)
            return M_ONE;
        else if (contents == 3)
            return THREE;
        else if (contents == -2)
            return M_TWO;
        else {
            Real wrapper;
            wrapper = cache.get(contents);
            if (wrapper == null) {
                wrapper = Common.is_int(contents) ? new IntegerInteger(contents.intValue()) : new DoubleReal(contents);
                try {
                    cache.add(contents, wrapper);
                } catch (CacheCollisionException e) {
                    // TODO should hash collision be silenced?
                    e.printStackTrace();
                }
            }
            return wrapper;
        }
    }

    @Override
    public double approximate() {
        return value;
    }

    @Override
    public boolean isPrecise() {
        return true;
    }

    @NonNull
    public String toString() {
        return Double.toString(value);
    }

    @Override
    public int hashCode() {
        return Double.hashCode(value);
    }

    public boolean equals(Object other) {
        if (other instanceof Double) {
            return value == ((Double) other);
        } else if (other instanceof Real) {
            return value == ((Real) other).approximate();
        } else {
            return false;
        }
    }

    @Override
    public int compareTo(Real other) {
        return Double.compare(value, other.approximate());
    }

}