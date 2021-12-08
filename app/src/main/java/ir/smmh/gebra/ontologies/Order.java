package ir.smmh.gebra.ontologies;

import ir.smmh.gebra.Ontology;

public final class Order extends Ontology {

    private Order() {
        super(doc);
    }

    private class Type extends ir.smmh.gebra.Type {
        private Type(final String name) {
            super(name);
        }
    }

    public static final Ontology ont = new Order();

    public static final ir.smmh.gebra.Type MULTITUDE = new ir.smmh.gebra.Type(ont, "MULTITUDE");
    public static final ir.smmh.gebra.Type UNORDERED = new ir.smmh.gebra.Type(ont, "UNORDERED");
    public static final ir.smmh.gebra.Type ORDERED = new ir.smmh.gebra.Type(ont, "ORDERED");

    static {
        UNORDERED.is(MULTITUDE);
        ORDERED.is(MULTITUDE);
    }
}
