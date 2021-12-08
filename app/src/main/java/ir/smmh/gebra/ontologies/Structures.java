package ir.smmh.gebra.ontologies;

import ir.smmh.gebra.Ontology;

public final class Structures extends Ontology {

    private Structures() {
        super(doc);
    }

    private class Type extends ir.smmh.gebra.Type {
        private Type(final String name) {
            super(name);
        }
    }

    public static final Ontology ont = new Structures();

    public static final ir.smmh.gebra.Type STRUCTURE = new ir.smmh.gebra.Type(ont, "STRUCTURE");
    public static final ir.smmh.gebra.Type TUPLE = new ir.smmh.gebra.Type(ont, "TUPLE");
    public static final ir.smmh.gebra.Type LIST = new ir.smmh.gebra.Type(ont, "LIST");
    public static final ir.smmh.gebra.Type SEQUENCE = new ir.smmh.gebra.Type(ont, "SEQUENCE");
    public static final ir.smmh.gebra.Type SET = new ir.smmh.gebra.Type(ont, "SET");

    static {
        STRUCTURE.is(Order.MULTITUDE);
        TUPLE.is(STRUCTURE);
        TUPLE.is(Order.ORDERED);
        LIST.is(STRUCTURE);
        LIST.is(Order.ORDERED);
        SEQUENCE.is(STRUCTURE);
        SEQUENCE.is(Order.ORDERED);
        SET.is(STRUCTURE);
        SET.is(Order.UNORDERED);
    }
}
