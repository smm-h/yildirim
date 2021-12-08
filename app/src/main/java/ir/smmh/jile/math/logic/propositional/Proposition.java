package ir.smmh.jile.math.logic.propositional;

/**
 * The meaning behind any of number of statements that have the same meaning.
 */
public interface Proposition extends Comparable<Proposition> {

    Boolean determineTruth();

}
