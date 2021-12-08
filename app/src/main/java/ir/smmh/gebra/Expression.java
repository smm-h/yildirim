package ir.smmh.gebra;

import android.view.View;

import androidx.annotation.NonNull;

import java.util.LinkedList;
import java.util.List;

import ir.smmh.gebra.errors.EvaluationError;
import ir.smmh.gebra.errors.InferenceError;
import ir.smmh.gebra.expressions.BooleanValue;
import ir.smmh.gebra.expressions.InBrackets;
import ir.smmh.gebra.expressionviews.Bracket;
import ir.smmh.gebra.expressions.DoubleValue;
import ir.smmh.gebra.expressions.Fraction;
import ir.smmh.gebra.expressions.Negation;
import ir.smmh.gebra.expressions.Production;
import ir.smmh.gebra.expressions.Summation;
import ir.smmh.gebra.statements.Recipe;

public abstract class Expression {

    protected final Expression[] subexpressions;

    protected Expression(final Expression[] subexpressions) {
        this.subexpressions = subexpressions;
    }

    @NonNull
    public abstract Type inferType(@NonNull final Namespace ns) throws InferenceError;

    @NonNull
    public abstract Expression evaluate(@NonNull final Namespace ns) throws EvaluationError;

    public List<Expression> simplify(@NonNull final Namespace ns) {
        return simplify(this, ns);
    }

    private static List<Expression> simplify(final Expression expression, final Namespace ns) {
        List<Expression> s = new LinkedList<>();
        simplifyRecursively(s, expression, ns);
        return s;
    }

    private static void simplifyRecursively(final List<Expression> l, Expression e, final Namespace ns) {
        while (true) {
            l.add(e);
            for (Expression subexpression : e.subexpressions) {
                simplifyRecursively(l, subexpression, ns);
            }
            try {
                Expression simplifiedE = e.evaluate(ns);
                if (e == simplifiedE) {
                    break;
                } else {
                    e = simplifiedE;
                }
            } catch (EvaluationError evaluationError) {
                evaluationError.printStackTrace();
                break;
            }
        }
    }

    public static final Expression ZERO = Expression.of(0);
    public static final Expression ONE = Expression.of(1);
    public static final Expression TWO = Expression.of(2);

    private static final Bracket PARENTHESES = Bracket.of('(');
    private static final Bracket ELL = Bracket.of('L');
    private static final Bracket UPSIDE_DOWN_ELL = Bracket.of('L').flipY();
    private static final Bracket BAR = Bracket.of('|');

    public Expression parenthesize() {
        return new InBrackets(this, x -> x, PARENTHESES);
    }

    public Expression floor() {
        return new InBrackets(this, Math::floor, ELL);
    }

    public Expression ceil() {
        return new InBrackets(this, Math::ceil, UPSIDE_DOWN_ELL);
    }

    public Expression abs() {
        return new InBrackets(this, Math::abs, BAR);
    }

    public Expression negate() {
        return new Negation(this);
    }

    public Expression add(Expression other) {
        return new Summation(this, other);
    }

    public Expression multiply(Expression other) {
        return new Production(this, other);
    }

    public Expression subtract(Expression other) {
        return add(other.negate());
    }

    public Expression divide(Expression other) {
        return multiply(other.reciprocal()); // TODO not the most ideal way to produce division
    }

    public Expression reciprocal() {
        return Fraction.of(ONE, this);
    }

    public static Expression of(double value){
        return DoubleValue.of(value);
    }

    public static Expression of(int value) {
        return DoubleValue.of(value);
    }

    public static Expression of(int p, int q) {
        return  Fraction.of(of(p), of(q));
    }

    public static Expression of(boolean value) {
        return BooleanValue.of(value);
    }

    public abstract ExpressionView visualize(final VisualizationContext vctx);

    public Recipe getEvaluation(Namespace ns) {
        return new Recipe(ns, this);
    }

    public interface ExpressionView {
        float getRestingY();

        VisualizationContext getVisualizationContext();

        default View getView() {
            return (View) this;
        }
    }
}
