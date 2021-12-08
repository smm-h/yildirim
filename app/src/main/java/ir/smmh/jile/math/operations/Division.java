package ir.smmh.jile.math.operations;

public interface Division<T> extends ClosedBinaryOperation<T> {

    @Override
    default String getSymbol() {
        return "÷";
    }

    @Override
    default BinaryOperation.BinarySymbolPlacement getPlacement() {
        return BinaryOperation.BinarySymbolPlacement.INFIX;
    }

}
