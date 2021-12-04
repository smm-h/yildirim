package ir.smmh.gebra;

import android.view.View;

import java.util.LinkedList;
import java.util.List;

import ir.smmh.gebra.expressions.Brackets;
import ir.smmh.gebra.expressions.DoubleValue;
import ir.smmh.gebra.expressions.Power;
import ir.smmh.gebra.expressions.Production;
import ir.smmh.gebra.expressions.Summation;
import ir.smmh.gebra.expressions.Variable;
import ir.smmh.gebra.statements.Recipe;

/**
 * @see Gebra <=
 * @see Summation
 * @see Production
 * @see Variable
 * @see DoubleValue
 * @see Brackets
 * @see Power
 */
public abstract class Expression {

    protected final List<Expression> children = new LinkedList<>();

    public abstract double evaluate(final EvaluationContext ectx) throws EvaluationError;

    public List<Expression> simplify(final EvaluationContext ectx) {
        return simplify(this, ectx);
    }

    public boolean isSimple() {
        return false;
    }

    public static List<Expression> simplify(final Expression expression, final EvaluationContext ectx) {
        List<Expression> s = new LinkedList<>();
        simplifyRecursively(s, expression, ectx);
        return s;
    }

    private static void simplifyRecursively(final List<Expression> l, Expression e, final EvaluationContext ectx) {
        while (!e.isSimple()) {
            l.add(e);
            List<Expression> children = e.children;
            for (Expression child : children) {
                simplifyRecursively(l, child, ectx);
            }
            try {
                e = new DoubleValue(e.evaluate(ectx));
            } catch (EvaluationError evaluationError) {
                evaluationError.printStackTrace();
                break;
            }
        }
    }

    public Expression wrap() {
        return new Brackets(this, Brackets.Type.CURVED) {
            @Override
            public double evaluate(final EvaluationContext ectx) throws EvaluationError {
                return getCore().evaluate(ectx);
            }
        };
    }

    public abstract ExpressionView visualize(final VisualizationContext vctx);

    public Recipe getEvaluation(EvaluationContext ectx) {
        return new Recipe(ectx, this);
    }

    public interface ExpressionView {
        float getRestingY();

        VisualizationContext getVisualizationContext();

        default View getView() {
            return (View) this;
        }
    }
}
