package ir.smmh.jile.math.operations;

public interface ScalarMultiplication<A, B, Z> extends BinaryOperation<A, B, Z> {

    @Override
    default String getSymbol() {
        return "×";
    }

    @Override
    default BinaryOperation.BinarySymbolPlacement getPlacement() {
        return BinaryOperation.BinarySymbolPlacement.INFIX;
    }
}
