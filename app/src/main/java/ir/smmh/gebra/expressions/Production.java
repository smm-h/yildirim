package ir.smmh.gebra.expressions;

import ir.smmh.gebra.EvaluationContext;
import ir.smmh.gebra.EvaluationError;
import ir.smmh.gebra.Expression;
import ir.smmh.gebra.VisualizationContext;

public class Production extends Expression {

    @Override
    public double evaluate(EvaluationContext ectx) throws EvaluationError {
        double product = 1;
        for (Expression e : children) {
            product *= e.evaluate(ectx);
        }
        return product;
    }

    public void addTerm(Expression e) {
        children.add(e);
    }

    @Override
    public ExpressionView visualize(final VisualizationContext vctx) {
        LinearExpressionView v = new LinearExpressionView(vctx);
        for (Expression e : children) {
            v.addView(e.visualize(vctx).getView());
        }
        return v;
    }
}
