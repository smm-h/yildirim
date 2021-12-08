package ir.smmh.gebra.expressions;

import androidx.annotation.NonNull;

import ir.smmh.gebra.errors.InferenceError;
import ir.smmh.gebra.Namespace;
import ir.smmh.gebra.errors.EvaluationError;
import ir.smmh.gebra.Expression;

import ir.smmh.gebra.Type;
import ir.smmh.gebra.VisualizationContext;
import ir.smmh.gebra.expressionviews.Symbol;

public class Variable extends Expression {
    private final String label;

    public Variable(final String label) {
        super(new Expression[0]);
        this.label = label;
    }

    @NonNull
    @Override
    public Type inferType(@NonNull final Namespace ns) throws InferenceError {
        final Type t = ns.get(label).getSpecificType();
        return t != null ? t : ns.get(label).getGeneralType();
    }

    @NonNull
    @Override
    public Expression evaluate(@NonNull final Namespace ns) throws EvaluationError {
        Expression v = ns.get(label).getValue();
        return v == null ? this : v;
    }

    @Override
    public ExpressionView visualize(final VisualizationContext vctx) {
        return new Symbol(vctx, label);
    }
}
