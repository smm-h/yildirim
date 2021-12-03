package ir.smmh.gebra.expressions;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import android.widget.LinearLayout;

import ir.smmh.fy.R;
import ir.smmh.fy.Util;
import ir.smmh.gebra.EvaluationContext;
import ir.smmh.gebra.EvaluationError;
import ir.smmh.gebra.Expression;
import ir.smmh.gebra.Gebra;
import ir.smmh.gebra.VisualizationContext;

public class Fraction extends Expression {

    private final Expression p, q;

    public Fraction(final Expression p, final Expression q) {
        this.p = p;
        this.q = q;
        children.add(p);
        children.add(q);
    }

    @Override
    public double evaluate(final EvaluationContext ectx) throws EvaluationError {
        return p.evaluate(ectx) / q.evaluate(ectx);
        // double qv = q.evaluate(ectx);
        // if (qv == 0) {
        //     throw new EvaluationError("Cannot divide by zero");
        // } else {
        //     return p.evaluate(ectx) / qv;
        // }
    }

    private static final int HR_HEIGHT, HR_ADDITIONAL_WIDTH;
    private static final Paint HR_PAINT;
    // private static final LinearLayout.LayoutParams HR_LP;

    static {
        HR_ADDITIONAL_WIDTH = Util.dipToPixel(12);
        HR_HEIGHT = Util.dipToPixel(4);
        HR_PAINT = new Paint();
        HR_PAINT.setColor(Util.getColor(Util.getMainActivity(), R.color.passive_fore));
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
            canvas.drawLine(x, y, w - x, y, HR_PAINT);
        }
    }

    @Override
    public ExpressionView visualize(final VisualizationContext vctx) {

        View pv = p.visualize(vctx).getView();
        View qv = q.visualize(vctx).getView();

        pv.measure(-2, -2);
        qv.measure(-2, -2);
        int mw = Math.max(pv.getMeasuredWidth(), qv.getMeasuredWidth());
        HorizontalRule hr = new HorizontalRule(vctx.androidContext, mw);

        Layout v = new Layout(vctx);
        v.setOrientation(LinearLayout.VERTICAL);
        v.addView(pv);
        v.addView(hr);
        v.addView(qv);

        return v;
    }
}
