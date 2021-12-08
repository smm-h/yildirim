package ir.smmh.jile.math.operations;

public interface MultiplicativeInversion<T> extends ClosedUnaryOperation<T> {

    @Override
    default UnaryOperation.UnarySymbolPlacement getPlacement() {
        return UnaryOperation.UnarySymbolPlacement.BEFORE;
    }

    @Override
    default String getSymbol() {
        return "1/";
    }

}
