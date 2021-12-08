package ir.smmh.jile.math.operations;

import ir.smmh.jile.math.settheory.SpecificSet;

public interface Intersection<T, S extends SpecificSet<T>> extends ClosedBinaryOperation<S> {

    @Override
    default String getSymbol() {
        return "∩";
    }

    @Override
    default BinaryOperation.BinarySymbolPlacement getPlacement() {
        return BinaryOperation.BinarySymbolPlacement.INFIX;
    }

}
