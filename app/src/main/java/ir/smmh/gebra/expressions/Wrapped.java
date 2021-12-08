package ir.smmh.gebra.expressions;

import androidx.annotation.NonNull;

import ir.smmh.gebra.Namespace;
import ir.smmh.gebra.Type;
import ir.smmh.gebra.errors.EvaluationError;
import ir.smmh.gebra.Expression;
import ir.smmh.gebra.Gebra;
import ir.smmh.gebra.VisualizationContext;
import ir.smmh.gebra.errors.InferenceError;
import ir.smmh.gebra.expressionviews.Bracket;
import ir.smmh.gebra.expressionviews.InBracketsView;
import ir.smmh.gebra.expressionviews.UnderVinculumView;

public abstract class Wrapped extends Expression {

    private final Gebra.Effect effect;

    public Wrapped(final Expression core, final Gebra.Effect effect) {
        super(new Expression[] {core});
        this.effect = effect;
    }

    public Expression getCore() {
        return subexpressions[0];
    }

    @NonNull
    @Override
    public Type inferType(@NonNull final Namespace ns) throws InferenceError {
        return getCore().inferType(ns);
    }

    @Override
    public Expression evaluate(@NonNull final Namespace ns) throws EvaluationError {
        return effect.affect(getCore().evaluate(ns));
    }

    }
