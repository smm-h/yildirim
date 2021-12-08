package ir.smmh.gebra.expressionviews;

import android.graphics.Canvas;
import android.view.View;

import ir.smmh.fy.Util;
import ir.smmh.gebra.Expression;
import ir.smmh.gebra.Gebra;
import ir.smmh.gebra.VisualizationContext;

public class UnderVinculumView extends FreeView {

    private static final int ADDITIONAL_W, ADDITIONAL_H;

    static {
        ADDITIONAL_W = Util.dipToPixel(8);
        ADDITIONAL_H = Util.dipToPixel(8);
    }

    private final Expression.ExpressionView coreView;

    public UnderVinculumView(final VisualizationContext vctx, final Expression core) {
        super(vctx);
        addView((coreView = core.visualize(vctx)).getView());
    }

    @Override
    public float getRestingY() {
        return coreView.getRestingY() + ADDITIONAL_H;
    }

    @Override
    protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {
        final float w, h;
        final View v = coreView.getView();
        v.measure(-2, -2);
        w = v.getMeasuredWidth() + ADDITIONAL_W;
        h = v.getMeasuredHeight() + ADDITIONAL_H;
        setMeasuredDimension((int) w, (int) h);
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        final float x = ADDITIONAL_W / 2f;
        final float y = ADDITIONAL_H / 2f;
        canvas.drawLine(x, y, getWidth() - x, y, Gebra.PAINT);
    }
}
