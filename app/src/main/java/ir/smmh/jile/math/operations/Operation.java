package ir.smmh.jile.math.operations;

/**
 * An operation is some deterministic transformation that happens to <i>n</i>
 * (arity) objects which may or may not be numbers.
 */
public interface Operation {

    String getSymbol();

    default String getLaTeX() {
        return getSymbol();
    }

    SymbolPlacement getPlacement();

    /**
     * the number of operands it takes
     */
    int getArity();

    /**
     * whether its operands and result are all elements of the same set
     */
    default boolean isClosed() {
        return false;
    }

    /**
     * operate director
     */
    default Object operateUnchecked(Object[] objects) throws ClassCastException {
        if (getArity() == objects.length) {
            switch (getArity()) {
                case 0:
                    return ((NullaryOperation<?>) this).operateUnchecked();
                case 1:
                    return ((UnaryOperation<?, ?>) this).operateUnchecked(objects[0]);
                case 2:
                    return ((BinaryOperation<?, ?, ?>) this).operateUnchecked(objects[0], objects[1]);
                default:
                    throw new UnsupportedOperationException("undefined arity");
            }
        } else {
            throw new UnsupportedOperationException("wrong number of arguments provided");
        }
    }

    /**
     * Extend this interface with enums and return those values from overridden
     * {@link #getPlacement()}s to be used during visualizations.
     */
    interface SymbolPlacement {
    }
}
