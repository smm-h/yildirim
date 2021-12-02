package ir.smmh.gebra;

import java.util.HashMap;
import java.util.Map;

/**
 * For values that we want to have available at every step of a
 * recursive process of evaluation.
 * For example {@link ir.smmh.gebra.expressions.Variable}s cannot
 * be evaluated without a ready storage against which they can be
 * queried.
 */
public class EvaluationContext {

    private final Map<String, Double> values = new HashMap<>();

    public double getValue(String name) throws EvaluationError {
        Double v = values.get(name);
        if (v == null) {
            throw new EvaluationError("Undefined variable: " + name);
        } else {
            return v;
        }
    }

    public void setValue(String name, Double value) {
        if (value == null) {
            values.remove(name);
        } else {
            values.put(name, value);
        }
    }

    public static class Default extends EvaluationContext {
        public Default() {
            setValue("pi", 3.1415);
            setValue("e", 2.78);
            setValue("phi", 1.618);
        }
    }
}
