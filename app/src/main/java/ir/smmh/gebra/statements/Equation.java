package ir.smmh.gebra.statements;

import ir.smmh.gebra.EvaluationContext;
import ir.smmh.gebra.Expression;
import ir.smmh.gebra.Statement;
import ir.smmh.gebra.VisualizationContext;

public class Equation extends Statement {

    private final Expression left, right;

    public Equation(final EvaluationContext ectx, final Expression left, final Expression right) {
        super(ectx);
        this.left = left;
        this.right = right;
    }

    @Override
    public int execute(final StatementView sv) {
        return 0;
    }

    @Override
    public StatementView visualize(final VisualizationContext vctx) {
        return null;
    }
}
