package ir.smmh.gebra.ontologies;

import ir.smmh.gebra.Ontology;
import ir.smmh.gebra.Type;

public class Dimensions {
    public void fill(final Ontology ont) {
        final Type SCALAR = new Type(ont, "SCALAR");
        final Type VECTOR = new Type(ont, "VECTOR");
        final Type MATRIX = new Type(ont, "MATRIX");
        final Type TENSOR = new Type(ont, "TENSOR");
        TENSOR.is(Order.ORDERED);
        MATRIX.is(TENSOR);
        VECTOR.is(MATRIX);
        SCALAR.is(VECTOR);
    }
}
