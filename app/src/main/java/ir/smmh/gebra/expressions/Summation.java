package ir.smmh.gebra.expressions;

import android.view.View;

import androidx.annotation.NonNull;

import ir.smmh.gebra.errors.EvaluationError;
import ir.smmh.gebra.Expression;
import ir.smmh.gebra.errors.InferenceError;
import ir.smmh.gebra.Namespace;

import ir.smmh.gebra.Type;
import ir.smmh.gebra.VisualizationContext;
import ir.smmh.gebra.expressionviews.SequentialView;
import ir.smmh.gebra.expressionviews.Symbol;
import ir.smmh.gebra.operations.Addition;

public class Summation extends Expression {

    public Summation(Expression... terms) {
        super(terms);
    }

    @NonNull
    @Override
    public Type inferType(@NonNull final Namespace ns) throws InferenceError {
        Type t = subexpressions[0].inferType(ns);
        for (int i = 1; i < subexpressions.length; i++) {
            if (t == null) {
                return null;
            }
            t = Addition.infer(t, subexpressions[i].inferType(ns));
        }
        return t;
    }

    @Override
    public Expression evaluate(@NonNull final Namespace ns) throws EvaluationError {
        Expression sum = DoubleValue.ZERO;
        for (Expression e : subexpressions) {
            sum += e.evaluate(ns);
        }
        return sum;
    }

    @Override
    public ExpressionView visualize(final VisualizationContext vctx) {
        final SequentialView layout = new SequentialView(vctx);
        boolean firstTime = true;
        for (final Expression expression : subexpressions) {
            final boolean n = expression instanceof Negation;
            View view = (n ? ((Negation) expression).core : expression).visualize(vctx).getView();
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
