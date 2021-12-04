package ir.smmh.gebra.expressions;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Space;

import ir.smmh.fy.R;
import ir.smmh.fy.Util;
import ir.smmh.gebra.EvaluationContext;
import ir.smmh.gebra.EvaluationError;
import ir.smmh.gebra.Expression;
import ir.smmh.gebra.Gebra;
import ir.smmh.gebra.VisualizationContext;

public class Root extends Expression {
    private final Expression base, power;

    public Root(final Expression base, final Expression power) {
        this.base = base;
        this.power = power;
        children.add(base);
        children.add(power);
    }

    @Override
    public double evaluate(final EvaluationContext ectx) throws EvaluationError {
        return Math.pow(base.evaluate(ectx), 1 / power.evaluate(ectx));
    }

    private static final Paint PAINT;

    static {
        PAINT = new Paint();
        PAINT.setStyle(Paint.Style.STROKE);
        PAINT.setColor(Util.getColor(Util.getMainActivity(), R.color.passive_fore));
    }

    private static final float SCALE_START = 0.6f;
    private static final float SCALE_STEP = 0.1f;
    private static final float SCALE_END = 0.4f;

    private static final int H_SPACING = Util.dipToPixel(16);

    @Override
    public ExpressionView visualize(final VisualizationContext vctx) {

        // start with a nice and big de-scaling factor
        float f = SCALE_START;

        // visualize base and measure its height
        final ExpressionView bev = base.visualize(vctx);
        final View bv = bev.getView();
        bv.measure(-2, -2);
        final int bh = bv.getMeasuredHeight();

        View pv;
        int ph;
        if (power.equals(Gebra._2)) {
            pv = new Space(vctx.androidContext);
            ph = 0;
        } else {
            // visualize power and measure its height until its height is lower than base's
            do {
                pv = power.visualize(vctx.multiplyScaleBy(f)).getView();
                pv.measure(-2, -2);
                ph = pv.getMeasuredHeight();
                f -= SCALE_STEP;
            } while (bh < ph && f >= SCALE_END); // or we reach too small a de-scaling
        }

        // determine space height
        final int sh = bh > ph ? bh - ph : (int) (bh * SCALE_END);

        // create the space below power
        final Space s = new Space(vctx.androidContext);
        s.setMinimumHeight(sh);

        // create the left side layout with power and its space
        final LinearLayout l = new LinearLayout(vctx.androidContext);
        l.setOrientation(LinearLayout.VERTICAL);
        l.addView(pv);
        l.addView(s);

        // create the space between children
        final Space m = new Space(vctx.androidContext);
        m.setMinimumWidth(H_SPACING);

        final Path path = new Path();
        final int xj = pv.getMeasuredWidth();
        final int yj = pv.getMeasuredHeight();
        final int xm = xj + (sh * H_SPACING) / (bv.getMeasuredHeight() + sh);
        final int xo = xj + H_SPACING;
        final int yoc = -bv.getMeasuredWidth();

        final float restingY = bev.getRestingY();

        // create the total layout with base and the right side
        final Layout v = new Layout(vctx) {

            @Override
            public float getRestingY() {
                return restingY;
            }

            @Override
            protected void onDraw(final Canvas canvas) {
                final int x0, y0;
                x0 = Math.max(0, xj - Util.dipToPixel(5));
                y0 = Util.dipToPixel(3);
                final int yo = getHeight() + yoc;
                path.reset();
                path.moveTo(x0, yj + y0);
                path.lineTo(xj, yj);
                path.lineTo(xm, getHeight());
                path.lineTo(xo, yo);
                path.lineTo(getWidth(), yo);
                canvas.drawPath(path, PAINT);
            }
        };
        v.setWillNotDraw(false);
        v.setGravity(Gravity.BOTTOM);
        v.addView(l);
        v.addView(m);
        v.addView(bv);

        // and return it
        return v;
    }
}
