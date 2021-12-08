package ir.smmh.jile.math.settheory;

public interface InfiniteSet extends Set {

    default Integer getCardinality() {
        return null;
    }

    default boolean isFinite() {
        return false;
    }
}
