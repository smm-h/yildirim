package ir.smmh.jile.math.operations;

public interface Concatenation<T> extends AssociativeClosedBinaryOperation<T> {

    /**
     * {@code concatenate(a, b)} = {@code ab} = {@code a+b} = {@code a.b} =
     * {@code a||b} = {@code a⧺b} = {@code a⋅b} = {@code a⌢b},
     */
    @Override
    default String getSymbol() {
        return "⧺";
    }

    @Override
    default String getLaTeX() {
        return "\\​doubleplus";
    }

    @Override
    default BinaryOperation.BinarySymbolPlacement getPlacement() {
        return BinaryOperation.BinarySymbolPlacement.INFIX;
    }

}
