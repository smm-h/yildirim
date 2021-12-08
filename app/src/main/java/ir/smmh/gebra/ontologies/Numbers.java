package ir.smmh.gebra.ontologies;

import ir.smmh.gebra.Expression;
import ir.smmh.gebra.Ontology;

public final class Numbers extends Ontology {

    private Numbers() {
        super(doc);
    }

    private class Type extends ir.smmh.gebra.Type {
        private Type(final String name) {
            super(name);
        }
    }

    public static final Ontology ont = new Numbers();

    public static final ir.smmh.gebra.Type NATURAL = new ir.smmh.gebra.Type(ont, "NATURAL");
    public static final ir.smmh.gebra.Type INTEGER = new ir.smmh.gebra.Type(ont, "INTEGER");
    public static final ir.smmh.gebra.Type RATIONAL = new ir.smmh.gebra.Type(ont, "RATIONAL");
    public static final ir.smmh.gebra.Type IRRATIONAL = new ir.smmh.gebra.Type(ont, "IRRATIONAL");
    public static final ir.smmh.gebra.Type REAL = new ir.smmh.gebra.Type(ont, "REAL");
    public static final ir.smmh.gebra.Type COMPLEX = new ir.smmh.gebra.Type(ont, "COMPLEX");
    public static final ir.smmh.gebra.Type i = new ir.smmh.gebra.Type(ont, "i");
    public static final ir.smmh.gebra.Type NUMBER = new ir.smmh.gebra.Type(ont, "NUMBER");

    static {
        // NUMBER.is(Dimensions.SCALAR);
        COMPLEX.is(NUMBER);
        i.is(COMPLEX);
        REAL.is(COMPLEX);
        IRRATIONAL.is(REAL);
        RATIONAL.is(REAL);
        INTEGER.is(RATIONAL);
        NATURAL.is(INTEGER);
    }
}