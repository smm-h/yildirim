package ir.smmh.gebra;

import androidx.annotation.NonNull;

import java.util.Set;

public class Type {

    public final String name;
    private final Ontology ontology;

    public Type(final Ontology ontology, final String name) {
        this.ontology = ontology;
        this.name = name;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }

    private Set<Type> getIntensionSet() {
        return ontology.getIntensionSet(this);
    }

    /**
     * P is Q.
     */
    public void is(@NonNull Type that) {
        if (ontology != that.ontology)
            throw new IllegalArgumentException("ontology mismatch");
        final Set<Type> p = ontology.getIntensionSetNonNull(this);
        p.add(that);
        final Set<Type> q = ontology.getIntensionSet(that);
        if (q != null) {
            p.addAll(q);
            // for (Type t : q) {
            //      is(t);
            // }
        }
    }

    /**
     * is P Q?
     */
    public boolean isq(@NonNull Type that) {
        if (ontology != that.ontology)
            return false;
        final Set<Type> q = getIntensionSet();
        return q != null && q.contains(that);
    }

    public Type of(@NonNull Type genus) {
        if (ontology != genus.ontology)
            throw new IllegalArgumentException("ontology mismatch");
        return new HomogeneousType(this, genus);
    }

    public Type smallestCommonSupertype(final Type other) {
        if (ontology != other.ontology)
            throw new IllegalArgumentException("ontology mismatch");
        if (this == other) {
            return this;
        } else {
            final Set<Type> p = getIntensionSet();
            p.retainAll(other.getIntensionSet());
            int n = -1;
            Type s = null;
            for (Type t : p) {
                final Set<Type> q = t.getIntensionSet();
                q.retainAll(p);
                int m = q.size();
                if (n < m) {
                    n = m;
                    s = t;
                }
            }
            return s;
        }
    }

    private static class HomogeneousType extends Type {
        private final Type collection, genus;

        private HomogeneousType(final Type collection, final Type genus) {
            super(collection.ontology, collection.name + "-OF-[" + genus.name + "]");
            assert collection.ontology == genus.ontology;
            this.collection = collection;
            this.genus = genus;
        }
    }
}
