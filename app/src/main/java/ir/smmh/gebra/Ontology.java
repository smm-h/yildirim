package ir.smmh.gebra;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Ontology {

    public final GebraDocument doc;
    private final Map<Type, Set<Type>> map = new HashMap<>();

    public Ontology(final GebraDocument doc) {
        this.doc = doc;
    }

    @Nullable
    public Set<Type> getIntensionSet(final Type type) {
        return map.get(type);
    }

    @NonNull
    public Set<Type> getIntensionSetNonNull(final Type type) {
        return map.computeIfAbsent(type, k -> new HashSet<>());
    }
}
