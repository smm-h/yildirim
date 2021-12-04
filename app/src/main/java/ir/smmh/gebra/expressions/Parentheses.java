package ir.smmh.gebra.expressions;

import ir.smmh.gebra.EvaluationContext;
import ir.smmh.gebra.EvaluationError;
import ir.smmh.gebra.Expression;

public class Parentheses extends Brackets {
    public Parentheses(final Expression core) {
        super(core, Brackets.Type.CURVED);
    }

    @Override
    public double evaluate(final EvaluationContext ectx) throws EvaluationError {
        return getCore().evaluate(ectx);
    }
}
