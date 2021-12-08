package ir.smmh.jile.math;

/**
 * The base interface for almost everything in this package.
 */
public interface Testable {
    default boolean test() {
        return true;
    }

    ;
}