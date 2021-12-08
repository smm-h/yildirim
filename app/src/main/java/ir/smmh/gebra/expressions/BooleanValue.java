package ir.smmh.gebra.expressions;

import androidx.annotation.NonNull;

import ir.smmh.gebra.errors.EvaluationError;
import ir.smmh.gebra.Expression;
import ir.smmh.gebra.Namespace;
import ir.smmh.gebra.Type;
import ir.smmh.gebra.VisualizationContext;
import ir.smmh.gebra.expressionviews.Symbol;
import ir.smmh.gebra.ontologies.Logic;

public final class BooleanValue extends Expression {
    private final boolean value;

    private BooleanValue(final boolean value) {
        super(new Expression[0]);
        this.value = value;
    }

    @Override
    public Type inferType(@NonNull final Namespace ns) {
        return Logic.BOOLEAN;
    }

    @Override
    public Expression evaluate(@NonNull final Namespace ns) throws EvaluationError {
        return this;
    }

    @Override
    public ExpressionView visualize(final VisualizationContext vctx) {
        return new Symbol(vctx, value ? "T" : "F");
    }
}
