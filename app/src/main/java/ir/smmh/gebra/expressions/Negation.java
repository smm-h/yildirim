package ir.smmh.gebra.expressions;

import ir.smmh.gebra.EvaluationContext;
import ir.smmh.gebra.EvaluationError;
import ir.smmh.gebra.Expression;
import ir.smmh.gebra.VisualizationContext;

public class Negation extends Expression {

    public final Expression operand;

    public Negation(final Expression operand) {
        this.operand = operand;
    }

    @Override
    public double evaluate(final EvaluationContext ectx) throws EvaluationError {
        return -operand.evaluate(ectx);
    }

    @Override
    public ExpressionView visualize(final VisualizationContext vctx) {
        Layout v = new Layout(vctx);
        v.addView(new Symbol(vctx, "-"));
        v.addView(operand.visualize(vctx).getView());
        return v;
    }
}
