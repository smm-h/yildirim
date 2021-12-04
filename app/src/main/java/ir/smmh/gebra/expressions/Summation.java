package ir.smmh.gebra.expressions;

import android.view.View;

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
        final ListLayout layout = new ListLayout(vctx);
        boolean firstTime = true;
        for (final Expression expression : children) {
            final boolean n = expression instanceof Negation;
            View view = (n ? ((Negation) expression).operand : expression).visualize(vctx).getView();
            if (firstTime) {
                firstTime = false;
            } else {
                layout.addView(new Symbol(vctx, n ? "-" : "+"));
            }
            layout.addView(view);
        }
        return layout;
    }
}
