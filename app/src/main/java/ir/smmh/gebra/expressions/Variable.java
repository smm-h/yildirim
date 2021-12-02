package ir.smmh.gebra.expressions;

import ir.smmh.gebra.EvaluationContext;
import ir.smmh.gebra.EvaluationError;
import ir.smmh.gebra.Expression;
import ir.smmh.gebra.VisualizationContext;

public class Variable extends Expression {
    private final String label;

    public Variable(final String label) {
        this.label = label;
    }

    @Override
    public double evaluate(final EvaluationContext ectx) throws EvaluationError {
        return ectx.getValue(label);
    }

    @Override
    public ExpressionView visualize(final VisualizationContext vctx) {
        return new Symbol(vctx, label);
    }
}
