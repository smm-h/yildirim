package ir.smmh.jile.math.logic.propositional;

/**
 * A deductive {@link Argument} is a series of {@link Proposition}s called the
 * premises, intended to determine the truth of another proposition, called the
 * conclusion.
 */
public interface Argument {

    Iterable<Proposition> getPremises();

    Proposition getConclusion();

    /**
     * A valid argument is one in which the premises necessitate the conclusion.
     */
    boolean getValidity();

    /**
     * A sound argument is a valid argument whose premises are true.
     */
    default boolean getSoundness() {
        if (getValidity()) {
            for (Proposition premise : getPremises()) {
                if (!premise.determineTruth()) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }
}
