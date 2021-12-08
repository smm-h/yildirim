package ir.smmh.gebra.expressionviews;

import android.graphics.Canvas;
import android.graphics.Path;
import android.view.View;

import ir.smmh.fy.Util;
import ir.smmh.gebra.Expression;
import ir.smmh.gebra.Gebra;
import ir.smmh.gebra.VisualizationContext;

public class InBracketsView extends FreeView {

    private static final float PADDING, ADDITIONAL_H;

    static {
        PADDING = Util.dipToPixel(3);
        ADDITIONAL_H = Util.dipToPixel(8);
    }

    private final Expression.ExpressionView coreView;

    public InBracketsView(final VisualizationContext vctx, final Expression core, final Bracket open) {
        this(vctx, core, open, open.flipX());
    }

    InBracketsView(final VisualizationContext vctx, final Expression core, final Bracket open, final Bracket close) {
        super(vctx);
        coreView = core.visualize(vctx);
        addView(new BracketView(open));
        addView(coreView.getView());
        addView(new BracketView(close));
    }

    @Override
    public float getRestingY() {
        return coreView.getRestingY() + ADDITIONAL_H / 2;
    }

    private class BracketView extends View {
        private final Bracket pattern;
        private final Path path;
        private float prev_w, prev_h;

        public BracketView(final Bracket pattern) {
            super(coreView.getVisualizationContext().androidContext);
            this.pattern = pattern;
            setWillNotDraw(false);
            path = new Path();
        }

        @Override
        protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {
            final float w, h;
            final View v = coreView.getView();
            v.measure(-2, -2);
            h = v.getMeasuredHeight() + ADDITIONAL_H;
            w = ((h - PADDING * 2) * pattern.ratio) + PADDING * 2;
            setMeasuredDimension((int) w, (int) h);
        }

        @Override
        protected void onDraw(final Canvas canvas) {
            super.onDraw(canvas);
            final int w = getWidth();
            final int h = getHeight();
            if (prev_w != w || prev_h != h) {
                prev_w = w;
                prev_h = h;
                pattern.shapePath(path, PADDING, PADDING, w - PADDING * 2, h - PADDING * 2);
            }
            canvas.drawPath(path, Gebra.PAINT);
        }
    }
}
