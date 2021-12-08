package ir.smmh.gebra.expressions;

import androidx.annotation.NonNull;

import ir.smmh.gebra.errors.EvaluationError;
import ir.smmh.gebra.Expression;
import ir.smmh.gebra.errors.InferenceError;
import ir.smmh.gebra.Namespace;
import ir.smmh.gebra.Type;
import ir.smmh.gebra.Value;
import ir.smmh.gebra.VisualizationContext;
import ir.smmh.gebra.expressionviews.Symbol;
import ir.smmh.gebra.ontologies.Numbers;

public final class DoubleValue extends Expression implements Value<Double> {

    private final double value;
    private final @NonNull
    Type type;

    private DoubleValue(final double value, @NonNull final Type type) {
        super(new Expression[0]);
        this.value = value;
        this.type = type;
    }

    @Override
    public Double getValue() {
        return value;
    }

    public static DoubleValue of(final double value)  {
        return new DoubleValue(value, DoubleValue.inferType(value));
    }

    public static DoubleValue of(final int value) {
        return new DoubleValue(value, DoubleValue.inferType(value));
    }

    @NonNull
    @Override
    public Type inferType(@NonNull final Namespace ns) {
        return type;
    }
    @NonNull
    public static Type inferType(final double value) {
        if (Double.isNaN(value)) {
            throw new IllegalArgumentException("value cannot be NaN");
        } else if (Math.round(value) == value) {
            return inferType((int) value);
        } else {
            return Numbers.REAL;
        }
    }
    @NonNull
    public static Type inferType(final int value) {
        return value >= 0 ? Numbers.NATURAL : Numbers.INTEGER;
    }

    @NonNull
    @Override
    public Expression evaluate(@NonNull final Namespace ns) throws EvaluationError {
        return this;
    }

    @Override
    public ExpressionView visualize(final VisualizationContext vctx) {
        return new Symbol(vctx, Math.round(value) == value ? Long.toString((long) value) : Double.toString(value));
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        final DoubleValue that = (DoubleValue) o;
        return Double.compare(that.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(value);
    }
}
