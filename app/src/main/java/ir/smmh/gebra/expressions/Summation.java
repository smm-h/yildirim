package ir.smmh.gebra.expressions;

import ir.smmh.gebra.EvaluationContext;
import ir.smmh.gebra.EvaluationError;
import ir.smmh.gebra.Expression;
import ir.smmh.gebra.VisualizationContext;

public class Summation extends Expression {

    @Override
    public double evaluate(EvaluationContext ectx) throws EvaluationError {
        double sum = 0;
        for (Expression e : children) {
            sum += e.evaluate(ectx);
        }
        return sum;
    }

    public void addTerm(Expression e) {
        children.add(e);
    }

    @Override
    public ExpressionView visualize(final VisualizationContext vctx) {
        LinearExpressionView v = new LinearExpressionView(vctx);
        boolean firstTime = true;
        for (final Expression e : children) {
            final boolean n = e instanceof Negation;
            if (firstTime) {
                firstTime = false;
            } else {
                v.addView(new Symbol(vctx, n ? "-" : "+"));
            }
            v.addView((n ? ((Negation) e).operand : e).visualize(vctx).getView());
        }
        return v;
    }
}
