package ir.smmh.gebra.expressions;

import androidx.annotation.NonNull;

import ir.smmh.gebra.Namespace;
import ir.smmh.gebra.errors.EvaluationError;
import ir.smmh.gebra.Expression;
import ir.smmh.gebra.VisualizationContext;
import ir.smmh.gebra.expressionviews.RootView;

public class Root extends Expression {
    public Root(final Expression radicand, final Expression root) {
        super(new Expression[] {radicand, root});
    }

    public Expression getRadicand() {
        return subexpressions[0];
    }

    public Expression getRoot() {
        return subexpressions[1];
    }

    @Override
    public Expression evaluate(@NonNull final Namespace ns) throws EvaluationError {
        return Math.pow(getRadicand().evaluate(ns), 1 / getRoot().evaluate(ns));
    }

    @Override
    public ExpressionView visualize(final VisualizationContext vctx) {
        return new RootView(vctx, this);
    }
}
