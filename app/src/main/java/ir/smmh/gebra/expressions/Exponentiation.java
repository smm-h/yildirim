package ir.smmh.gebra.expressions;

import androidx.annotation.NonNull;

import ir.smmh.gebra.VisualizationContext;
import ir.smmh.gebra.errors.InferenceError;
import ir.smmh.gebra.Namespace;
import ir.smmh.gebra.errors.EvaluationError;
import ir.smmh.gebra.Expression;
import ir.smmh.gebra.Type;
import ir.smmh.gebra.expressionviews.Superscript;
import ir.smmh.gebra.ontologies.Numbers;

public class Exponentiation extends Expression {

    public Exponentiation(final Expression base, final Expression exponent) {
        super(new Expression[]{base, exponent});
    }

    public Expression getBase() {
        return subexpressions[0];
    }

    public Expression getExponent() {
        return subexpressions[1];
    }

    @NonNull
    @Override
    public Type inferType(@NonNull final Namespace ns) throws InferenceError {
        final Type b = getBase().inferType(ns);
        final Type e = getExponent().inferType(ns);
        if (b == Numbers.REAL && e == Numbers.REAL) {
            return Numbers.REAL;
        } else {
            throw new InferenceError(b + "^" + e);
        }
    }

    @NonNull
    @Override
    public Expression evaluate(@NonNull final Namespace ns) throws EvaluationError {
        final double b = ((DoubleValue) getBase().evaluate(ns)).getValue();
        final double e = ((DoubleValue) getExponent().evaluate(ns)).getValue();
        try {
            return DoubleValue.of(Math.pow(b, e));
        } catch (InferenceError error) {
            throw new EvaluationError(error.getMessage());
        }
    }

    @Override
    public ExpressionView visualize(final VisualizationContext vctx) {
        return new Superscript(vctx, this);
    }
}
