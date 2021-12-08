package ir.smmh.jile.math.operations;

public interface Modulo<T> extends ClosedBinaryOperation<T> {

    @Override
    default String getSymbol() {
        return "%";
    }

    @Override
    default String getLaTeX() {
        return "\\mod";
    }

    @Override
    default BinaryOperation.BinarySymbolPlacement getPlacement() {
        return BinaryOperation.BinarySymbolPlacement.INFIX;
    }

}
