package ir.smmh.gebra.expressionviews;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

import ir.smmh.fy.Util;
import ir.smmh.gebra.Expression;
import ir.smmh.gebra.Gebra;
import ir.smmh.gebra.VisualizationContext;

@SuppressLint("AppCompatCustomView")
public class Text extends TextView implements Expression.ExpressionView {

    private final VisualizationContext vctx;

    public Text(final VisualizationContext vctx, final String text) {
        super(vctx.androidContext);
        this.vctx = vctx;
        setTypeface(Typeface.SANS_SERIF);
        setTextSize(Util.dipToPixel(5 * vctx.scale));
        setLayoutParams(Gebra.WRAP_BOTH);
        setText(text);
    }

    @Override
    public View getView() {
        return this;
    }

    @Override
    public VisualizationContext getVisualizationContext() {
        return vctx;
    }

    @Override
    public float getRestingY() {
        return getMeasuredHeight() * 0.527f;
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
