package ir.smmh.gebra.expressions;

import ir.smmh.gebra.EvaluationContext;
import ir.smmh.gebra.Expression;
import ir.smmh.gebra.VisualizationContext;

public final class DoubleValue extends Expression {
    private final double value;

    public DoubleValue(final double value) {
        this.value = value;
    }

    @Override
    public double evaluate(EvaluationContext ectx) {
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
}
