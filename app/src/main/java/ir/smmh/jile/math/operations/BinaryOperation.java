package ir.smmh.jile.math.operations;

public interface BinaryOperation<A, B, Z> extends Operation {

    public enum BinarySymbolPlacement implements Operation.SymbolPlacement {
        PREFIX, INFIX, POSTFIX
    }

    @Override
    default int getArity() {
        return 2;
    }

    public Z operate(A a, B b);

    @SuppressWarnings("unchecked")
    default Z operateUnchecked(Object a, Object b) throws ClassCastException {
        return operate((A) a, (B) b);
    }

    /**
     * A binary operation may be associative. By default though, it is not.
     *
     * @see AssociativeClosedBinaryOperation
     */
    default boolean isAssociative() {
        return false;
    }
}
