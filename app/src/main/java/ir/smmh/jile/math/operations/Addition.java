package ir.smmh.jile.math.operations;

public interface Addition<T> extends AssociativeClosedBinaryOperation<T> {

    @Override
    default String getSymbol() {
        return "+";
    }

    @Override
    default BinaryOperation.BinarySymbolPlacement getPlacement() {
        return BinaryOperation.BinarySymbolPlacement.INFIX;
    }


}
