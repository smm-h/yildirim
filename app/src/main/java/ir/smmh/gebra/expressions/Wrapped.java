package ir.smmh.gebra.expressions;

import ir.smmh.gebra.EvaluationContext;
import ir.smmh.gebra.EvaluationError;
import ir.smmh.gebra.Expression;
import ir.smmh.gebra.Gebra;
import ir.smmh.gebra.VisualizationContext;

public class Wrapped extends Expression {

    private final Expression core;
    private final Bracket shape;
    private final Gebra.Effect effect;

    public Wrapped(final Expression core, final Bracket shape, final Gebra.Effect effect) {
        this.core = core;
        children.add(core);
        this.shape = shape;
        this.effect = effect;
    }

    public Expression getCore() {
        return core;
    }

    @Override
    public double evaluate(final EvaluationContext ectx) throws EvaluationError {
        return effect.affect(getCore().evaluate(ectx));
    }

    @Override
    public ExpressionView visualize(final VisualizationContext vctx) {
        return new Brackets(vctx, getCore(), shape);
    }
}
