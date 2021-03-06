package ir.smmh.gebra.expressionviews;

import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import ir.smmh.gebra.Expression;
import ir.smmh.gebra.Gebra;
import ir.smmh.gebra.VisualizationContext;

public abstract class FreeView extends LinearLayout implements Expression.ExpressionView {

    private final VisualizationContext vctx;

    public FreeView(final VisualizationContext vctx) {
        super(vctx.androidContext);
        this.vctx = vctx;
        setGravity(Gravity.CENTER);
        setLayoutParams(Gebra.WRAP_BOTH);
        setWillNotDraw(false);
    }

    @Override
    public View getView() {
        return this;
    }

    @Override
    public VisualizationContext getVisualizationContext() {
        return vctx;
    }

    // @Override
    // protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {
    //     super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    //     // float mh = getMeasuredHeight();
    //     // setTranslationY(-getRestingY());
    // }

    // @Override
    // protected void onDraw(final Canvas canvas) {
    //     super.onDraw(canvas);
    //     final float y = getRestingY();
    //     canvas.drawLine(0, y, getWidth(), y, Gebra.DEBUG_PAINT);
    // }
}
