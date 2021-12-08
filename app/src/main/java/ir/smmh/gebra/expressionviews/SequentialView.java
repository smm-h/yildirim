package ir.smmh.gebra.expressionviews;

import android.view.Gravity;
import android.view.View;

import ir.smmh.gebra.Expression;
import ir.smmh.gebra.VisualizationContext;

public class SequentialView extends FreeView {

    private float restingY = 0;

    public SequentialView(final VisualizationContext vctx) {
        super(vctx);
        setGravity(Gravity.NO_GRAVITY);
    }

    public SequentialView(final VisualizationContext vctx, final Expression... expressions) {
        this(vctx);
        for (Expression e : expressions) {
            addView(e.visualize(vctx).getView());
        }
    }

    public SequentialView(final VisualizationContext vctx, final View... views) {
        this(vctx);
        for (View v : views) {
            addView(v);
        }
    }

    @Override
    public float getRestingY() {
        final int n = getChildCount();
        restingY = 0;
        for (int i = 0; i < n; i++) {
            final float y = ((Expression.ExpressionView) getChildAt(i)).getRestingY();
            if (restingY < y) {
                restingY = y;
            }
        }
        return restingY;
    }

    @Override
    protected void onLayout(final boolean changed, final int l, final int t, final int r, final int b) {
        if (changed) {
            getRestingY();
            final int n = getChildCount();
            for (int i = 0; i < n; i++) {
                final View child = getChildAt(i);
                child.setTranslationY(restingY - ((Expression.ExpressionView) child).getRestingY());
            }
        }
        super.onLayout(changed, l, t, r, b);
    }
}
