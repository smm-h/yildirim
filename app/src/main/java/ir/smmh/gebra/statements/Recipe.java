package ir.smmh.gebra.statements;

import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;

import ir.smmh.fy.Util;
import ir.smmh.gebra.errors.EvaluationError;
import ir.smmh.gebra.Expression;
import ir.smmh.gebra.Namespace;
import ir.smmh.gebra.Statement;
import ir.smmh.gebra.VisualizationContext;
import ir.smmh.gebra.expressionviews.Symbol;

public class Recipe extends Statement {

    public final Expression expression;

    public Recipe(final Namespace ns, final Expression expression) {
        super(ns);
        this.expression = expression;
    }

    @Override
    public Status execute(final StatementView sv) {
        Status status;
        Expression answer = null;
        try {
            answer = expression.evaluate(ns);
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
            v.setAnswer(answer);
            // v.relationSymbol.setText("=");
        }
        return status;
    }

    @Override
    public StatementView visualize(final VisualizationContext vctx) {
        return new RecipeView(vctx, this);
    }

    public static class RecipeView extends StatementView {

        private final LinearLayout answer;

        public RecipeView(final VisualizationContext vctx, final Recipe core) {
            super(vctx, core);

            final Space space = new Space(vctx.androidContext);
            space.setMinimumWidth(STATUS_BAR_WIDTH + STATUS_BAR_MARGIN);

            final View expressionVisualized = core.expression.visualize(vctx).getView();

            addView(space);
            addView(expressionVisualized);
            addView(answer = new LinearLayout(vctx.androidContext));
        }

        public void setAnswer(Expression expression) {

            final View output;
            final TextView relationSymbol = new Symbol(visualizationContext, "=");

            if (expression == null) {
                final TextView questionMark = new Symbol(visualizationContext, "?");
                questionMark.setTextColor(Color.GRAY);
                relationSymbol.setTextColor(Color.GRAY);
                output = questionMark;
            } else {
                output = expression.visualize(visualizationContext).getView();
            }

            answer.removeAllViews();
            answer.addView(relationSymbol);
            answer.addView(output);
        }
    }
}
