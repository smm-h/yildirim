package ir.smmh.jile.math.operations;

public interface NullaryOperation<Z> extends ClosedOperation<Z> {

    public enum NullarySymbolPlacement implements Operation.SymbolPlacement {
        THERE
    }

    @Override
    default NullarySymbolPlacement getPlacement() {
        return NullarySymbolPlacement.THERE;
    }

    @Override
    default int getArity() {
        return 0;
    }

    public Z operate();

    default Z operateUnchecked() {
        return operate();
    }

}
