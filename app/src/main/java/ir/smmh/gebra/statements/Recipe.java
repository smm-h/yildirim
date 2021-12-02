package ir.smmh.gebra.statements;

import android.graphics.Color;
import android.view.View;
import android.widget.Space;
import android.widget.TextView;

import ir.smmh.fy.Util;
import ir.smmh.gebra.EvaluationContext;
import ir.smmh.gebra.EvaluationError;
import ir.smmh.gebra.Expression;
import ir.smmh.gebra.Statement;
import ir.smmh.gebra.VisualizationContext;
import ir.smmh.gebra.expressions.DoubleValue;
import ir.smmh.gebra.expressions.Symbol;

public class Recipe extends Statement {

    public final Expression expression;

    public Recipe(final EvaluationContext ectx, final Expression expression) {
        super(ectx);
        this.expression = expression;
    }

    @Override
    public Status execute(final StatementView sv) {
        Status status;
        String answer = "?";
        try {
            answer = DoubleValue.toText(expression.evaluate(ectx));
            status = Status.NO_ERROR;
        } catch (EvaluationError ee) {
            status = Status.EVAL_ERROR;
            Util.toast(Util.getMainActivity(), ee.getMessage());
        } catch (Exception e) {
            status = Status.JAVA_ERROR;
            e.printStackTrace();
        }
        if (sv != null) {
            final RecipeView v = (RecipeView) sv;
            v.answer.setText(answer);
            // v.relationSymbol.setText("=");
        }
        return status;
    }

    @Override
    public StatementView visualize(final VisualizationContext vctx) {
        return new RecipeView(vctx, this);
    }

    public static class RecipeView extends StatementView {

        private final TextView relationSymbol;
        private final TextView answer;

        public RecipeView(final VisualizationContext vctx, final Recipe core) {
            super(vctx, core);

            final Space space = new Space(vctx.androidContext);
            space.setMinimumWidth(STATUS_BAR_WIDTH + STATUS_BAR_MARGIN);

            final View expressionVisualized = core.expression.visualize(vctx).getView();

            relationSymbol = new Symbol(vctx, "=");
            relationSymbol.setTextColor(Color.GRAY);

            answer = new Symbol(vctx, "?");
            answer.setTextColor(Color.GRAY);

            addView(space);
            addView(expressionVisualized);
            addView(relationSymbol);
            addView(answer);
        }
    }
}
