package ir.smmh.jile.math.operations;

public interface UnaryOperation<A, Z> extends Operation {

    public enum UnarySymbolPlacement implements Operation.SymbolPlacement {
        BEFORE, AFTER, ABOVE, BELOW;
    }

    @Override
    public UnarySymbolPlacement getPlacement();

    @Override
    default int getArity() {
        return 1;
    }

    public Z operate(A a);

    @SuppressWarnings("unchecked")
    default Z operateUnchecked(Object a) throws ClassCastException {
        return operate((A) a);
    }
}
