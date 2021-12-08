package ir.smmh.gebra.expressions;

import androidx.annotation.NonNull;

import ir.smmh.gebra.Expression;
import ir.smmh.gebra.Namespace;
import ir.smmh.gebra.Type;
import ir.smmh.gebra.VisualizationContext;
import ir.smmh.gebra.errors.EvaluationError;
import ir.smmh.gebra.errors.InferenceError;
import ir.smmh.gebra.expressionviews.SequentialView;
import ir.smmh.gebra.expressionviews.Symbol;

public class Negation extends Expression {

    public Negation(final Expression core) {
        super(new Expression[] {core});
    }

    public Expression getCore() {
        return subexpressions[0];
    }

    @Override
    public Expression negate() {
        return getCore();
    }

    @NonNull
    @Override
    public Type inferType(@NonNull final Namespace ns) throws InferenceError {
        return getCore().inferType(ns); // TODO
    }

    @NonNull
    @Override
    public Expression evaluate(@NonNull final Namespace ns) throws EvaluationError {
        return -getCore().evaluate(ns);
    }

    @Override
    public ExpressionView visualize(final VisualizationContext vctx) {
        return new SequentialView(vctx, new Symbol(vctx, "-"), getCore().visualize(vctx).getView());
    }
}
