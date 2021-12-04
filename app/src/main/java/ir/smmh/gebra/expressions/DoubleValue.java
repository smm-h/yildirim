package ir.smmh.gebra.expressions;

import ir.smmh.gebra.EvaluationContext;
import ir.smmh.gebra.EvaluationError;
import ir.smmh.gebra.Expression;
import ir.smmh.gebra.VisualizationContext;

public final class DoubleValue extends Expression {
    private final double value;

    public DoubleValue(final double value) {
        this.value = value;
    }

    @Override
    public double evaluate(final EvaluationContext ectx) throws EvaluationError {
        return value;
    }

    @Override
    public boolean isSimple() {
        return true;
    }

    @Override
    public ExpressionView visualize(final VisualizationContext vctx) {
        return new Symbol(vctx, DoubleValue.toText(value));
    }

    public static String toText(double value) {
        return Math.round(value) == value ? Long.toString((long) value) : Double.toString(value);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        final DoubleValue that = (DoubleValue) o;
        return Double.compare(that.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(value);
    }
}
