package ir.smmh.gebra.expressions;

import androidx.annotation.NonNull;

import ir.smmh.gebra.Expression;
import ir.smmh.gebra.Namespace;
import ir.smmh.gebra.Type;
import ir.smmh.gebra.VisualizationContext;
import ir.smmh.gebra.errors.EvaluationError;
import ir.smmh.gebra.errors.InferenceError;
import ir.smmh.gebra.expressionviews.FractionView;

public class Fraction extends Expression {

    private Fraction(final Expression numerator, final Expression denominator) {
        super(new Expression[] {numerator, denominator});
    }

    public Expression getNumerator() {
        return subexpressions[0];
    }

    public Expression getDenominator() {
        return subexpressions[1];
    }

    public static Fraction of(@NonNull final Expression numerator, @NonNull final Expression denominator) {
        return new Fraction(numerator, denominator);
    }

    @Override
    public Expression negate() {
        return of(getNumerator().negate(), getDenominator());
    }

    @Override
    public Expression reciprocal() {
        return of(getDenominator(), getNumerator());
    }

    @NonNull
    @Override
    public Type inferType(@NonNull final Namespace ns) throws InferenceError {
        final Type n = getNumerator().inferType(ns);
        final Type d = getDenominator().inferType(ns);
    }

    @NonNull
    @Override
    public Expression evaluate(@NonNull final Namespace ns) throws EvaluationError {
        final Expression n = getNumerator().evaluate(ns);
        final Expression d = getDenominator().evaluate(ns);
        if (d.equals(DoubleValue.ZERO)) {
            throw new EvaluationError("Cannot divide by zero");
        } else {
            return DoubleValue.of(n / d);
        }
    }

    @Override
    public ExpressionView visualize(final VisualizationContext vctx) {
        return new FractionView(vctx, this);
    }

    public Fraction add(@NonNull final Fraction other) {
        final Expression q2 = other.getDenominator();
        return of(getNumerator().multiply(q2).add(other.getNumerator().multiply(getDenominator())), getDenominator().multiply(q2));
    }

    public Fraction multiply(@NonNull final Fraction other) {
        return of(getNumerator().multiply(other.getNumerator()), getDenominator().multiply(other.getDenominator()));
    }
}