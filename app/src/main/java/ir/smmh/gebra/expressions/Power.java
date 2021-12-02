package ir.smmh.gebra.expressions;

import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Space;

import ir.smmh.gebra.EvaluationContext;
import ir.smmh.gebra.EvaluationError;
import ir.smmh.gebra.Expression;
import ir.smmh.gebra.VisualizationContext;

public class Power extends Expression {

    private final Expression base, power;

    public Power(final Expression base, final Expression power) {
        this.base = base;
        this.power = power;
        children.add(base);
        children.add(power);
    }

    @Override
    public double evaluate(final EvaluationContext ectx) throws EvaluationError {
        return Math.pow(base.evaluate(ectx), power.evaluate(ectx));
    }

    private static final float SCALE_START = 0.6f;
    private static final float SCALE_STEP = 0.1f;
    private static final float SCALE_END = 0.4f;

    @Override
    public ExpressionView visualize(final VisualizationContext vctx) {

        // start with a nice and big de-scaling factor
        float f = SCALE_START;

        // visualize base and measure its height
        final View b;
        b = base.visualize(vctx).getView();
        b.measure(-2, -2);
        final int bh = b.getMeasuredHeight();

        // visualize power and measure its height until its height is lower than base's
        View p;
        int ph;
        do {
            p = power.visualize(vctx.multiplyScaleBy(f)).getView();
            p.measure(-2, -2);
            ph = p.getMeasuredHeight();
            f -= SCALE_STEP;
        } while (bh < ph && f >= SCALE_END); // or we reach too small a de-scaling

        // determine space height
        final int sh = bh > ph ? bh - ph : (int) (bh * SCALE_END);

        // create the space below power
        final Space s;
        s = new Space(vctx.androidContext);
        s.setMinimumHeight(sh);

        // create the right side layout with power and its space
        final LinearLayout r;
        r = new LinearLayout(vctx.androidContext);
        r.setOrientation(LinearLayout.VERTICAL);
        r.addView(p);
        r.addView(s);

        // create the total layout with base and the right side
        final Layout v;
        v = new Layout(vctx);
        v.setGravity(Gravity.BOTTOM);
        v.addView(b);
        v.addView(r);

        // and return it
        return v;
    }
}
