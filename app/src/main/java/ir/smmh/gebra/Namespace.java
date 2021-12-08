package ir.smmh.gebra;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;

import ir.smmh.gebra.errors.ExecutionError;
import ir.smmh.gebra.errors.InferenceError;

public class Namespace {

    public final GebraDocument doc;
    private final Namespace parent;

    public Namespace(@NonNull final GebraDocument doc, @Nullable final Namespace parent) {
        this.doc = doc;
        this.parent = parent;
    }

    public Namespace getParent() {
        return parent;
    }

    private final Map<String, Namepoint> values = new HashMap<>();

    @NonNull
    public Namepoint getNonNull(@NonNull final String name) {
        Namepoint np = get(name);
        return np != null ? np : new Namepoint(name, Type);
    }

    @Nullable
    public Namepoint get(@NonNull final String name) {
        Namespace ns = this;
        Namepoint np = null;
        while (ns != null && np == null) {
            np = ns.getImmediate(name);
            ns = ns.getParent();
        }
        return np;
    }

    @Nullable
    public Namepoint getImmediate(@NonNull final String name) {
        return values.get(name);
        // if (v == null) throw new EvaluationError("Undefined variable: " + name);
    }

    public class Namepoint {

        private final String name;
        private Expression value;
        private @NonNull
        Type generalType;
        private Type specificType;

        public Namepoint(@NonNull final String name, @NonNull final Type type) {
            values.put(name, this);
            this.name = name;
            this.generalType = type;
        }

        @NonNull
        public String getName() {
            return name;
        }

        public void setValue(@NonNull final Expression v) throws ExecutionError {
            try {
                final Type type = v.inferType(Namespace.this);
                if (type.isq(generalType)) {
                    specificType = type;
                    value = v;
                } else {
                    throw new ExecutionError("cannot assign expression of type: " + type + " to namepoint of type: " + generalType);
                }
            } catch (InferenceError e) {
                throw new ExecutionError("could not infer type of assignment RHS");
            }
        }

        public Expression getValue() {
            return value;
        }

        @NonNull
        public Type getGeneralType() {
            return generalType;
        }

        public Type getSpecificType() {
            return specificType;
        }

        public void specifyType(@NonNull final Type type) throws ExecutionError {

            // If my general type generalizes this new general type
            if (type.isq(generalType)) {

                // If my value does not restrict my general type
                if (value == null) {

                    // just assign it
                    generalType = type;
                }

                // If my value may restrict my general type
                else {

                    // If my value does not restrict the new general type
                    if (specificType.isq(type)) {

                        // assign it
                        generalType = type;
                    }
                }
            }
            // If it does not,
            else {
                // throw an execution error
                throw new ExecutionError("cannot replace type: " + generalType + " with type: " + type);
            }
        }
    }
}
