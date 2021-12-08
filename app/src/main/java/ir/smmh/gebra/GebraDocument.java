package ir.smmh.gebra;

import androidx.annotation.NonNull;

import ir.smmh.gebra.errors.ExecutionError;
import ir.smmh.gebra.ontologies.Numbers;

public class GebraDocument {
    @NonNull
    public final Namespace ns;
    @NonNull
    public final Ontology ont;

    public GebraDocument() {
        ns = new Namespace(this, null);
        ont = new Ontology(this);
        try {
            // TODO truly IRRATIONAL constants
            ns.new Namepoint("pi", Numbers.REAL).setValue(Expression.of(3.1415));
            ns.new Namepoint("e", Numbers.REAL).setValue(Expression.of(2.78));
            ns.new Namepoint("phi", Numbers.REAL).setValue(Expression.of(1.618));
        } catch (ExecutionError executionError) {
            executionError.printStackTrace();
        }
    }
}
