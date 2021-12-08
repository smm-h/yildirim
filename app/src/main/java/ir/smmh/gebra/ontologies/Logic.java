package ir.smmh.gebra.ontologies;

import ir.smmh.gebra.Ontology;

public final class Logic extends Ontology {

    private Logic() {
        super(doc);
    }

    private class Type extends ir.smmh.gebra.Type {
        private Type(final String name) {
            super(name);
        }
    }

    public static final Ontology ont = new Logic();

    public static final ir.smmh.gebra.Type BOOLEAN = new ir.smmh.gebra.Type(ont, "BOOLEAN");
    public static final ir.smmh.gebra.Type FUZZY = new ir.smmh.gebra.Type(ont, "FUZZY");

    static {
        BOOLEAN.is(FUZZY);
    }
}
