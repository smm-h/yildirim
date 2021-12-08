package ir.smmh.gebra.expressions;

import androidx.annotation.NonNull;

import ir.smmh.gebra.Namespace;
import ir.smmh.gebra.errors.EvaluationError;
import ir.smmh.gebra.Expression;
import ir.smmh.gebra.VisualizationContext;
import ir.smmh.gebra.expressionviews.SequentialView;

public class Production extends Expression {

    public Production(Expression... terms) {
        super(terms);
    }

    @Override
    public Expression evaluate(@NonNull final Namespace ns) throws EvaluationError {
        double product = 1;
        for (Expression e : subexpressions) {
            product *= e.evaluate(ns);
        }
        return product;
    }

    @Override
    public ExpressionView visualize(final VisualizationContext vctx) {
        return new SequentialView(vctx, subexpressions);
    }
}
