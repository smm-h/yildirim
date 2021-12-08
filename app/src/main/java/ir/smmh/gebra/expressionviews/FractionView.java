package ir.smmh.gebra.expressionviews;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import ir.smmh.fy.Util;
import ir.smmh.gebra.Expression;
import ir.smmh.gebra.Gebra;
import ir.smmh.gebra.VisualizationContext;
import ir.smmh.gebra.expressions.Fraction;

public class FractionView extends FreeView implements Expression.ExpressionView {
    private final float restingY;

    public FractionView(@NonNull final VisualizationContext vctx, @NonNull final Fraction fraction) {
        this(vctx, fraction.getNumerator(), fraction.getDenominator());
    }

    public FractionView(@NonNull final VisualizationContext vctx, @NonNull final Expression top, @NonNull final Expression bottom) {
        super(vctx);
        final View n = top.visualize(vctx).getView();
        final View d = bottom.visualize(vctx).getView();

        n.measure(-2, -2);
        d.measure(-2, -2);
        final int mw = Math.max(n.getMeasuredWidth(), d.getMeasuredWidth());
        final HorizontalRule hr = new HorizontalRule(vctx.androidContext, mw);

        restingY = n.getMeasuredHeight() + HR_HEIGHT / 2f;
        setOrientation(LinearLayout.VERTICAL);
        addView(n);
        addView(hr);
        addView(d);

    }

    @Override
    public float getRestingY() {
        return restingY;
    }

    private static final int HR_HEIGHT, HR_ADDITIONAL_WIDTH;
    // private static final LinearLayout.LayoutParams HR_LP;

    static {
        HR_ADDITIONAL_WIDTH = Util.dipToPixel(12);
        HR_HEIGHT = Util.dipToPixel(4);
        // HR_LP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, HR_HEIGHT);
    }

    private static class HorizontalRule extends View {

        private final int w;

        public HorizontalRule(final Context context, final int width) {
            super(context);
            // setLayoutParams(HR_LP);
            w = width + HR_ADDITIONAL_WIDTH;
            // setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
            // setLayoutParams(new LinearLayout.LayoutParams(w, HR_HEIGHT));
            setLayoutParams(Gebra.WRAP_BOTH);
            // setMinimumWidth(w);
            // setMinimumHeight(HR_HEIGHT);
        }

        @Override
        protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {
            setMeasuredDimension(w, HR_HEIGHT);
        }

        @Override
        protected void onDraw(final Canvas canvas) {
            final float x = HR_ADDITIONAL_WIDTH / 4f;
            final float y = HR_HEIGHT / 2f;
            canvas.drawLine(x, y, w - x, y, Gebra.PAINT);
        }
    }

}
