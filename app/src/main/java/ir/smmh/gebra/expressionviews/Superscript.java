package ir.smmh.gebra.expressionviews;

import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Space;

import androidx.annotation.NonNull;

import ir.smmh.gebra.Expression;
import ir.smmh.gebra.VisualizationContext;
import ir.smmh.gebra.expressions.Exponentiation;

public class Superscript extends FreeView implements Expression.ExpressionView {

    private static final float SCALE_START, SCALE_STEP, SCALE_END;

    static {
        SCALE_START = 0.60f;
        SCALE_STEP = 0.05f;
        SCALE_END = 0.40f;
    }

    private final float restingY;

    public Superscript(final VisualizationContext vctx, @NonNull final Exponentiation exponentiation) {
        this(vctx, exponentiation.getBase(), exponentiation.getExponent());
    }
    public Superscript(final VisualizationContext vctx, @NonNull final Expression base, @NonNull final Expression sup) {
        super(vctx);
        // start with a nice and big de-scaling factor
        float f = SCALE_START;

        // visualize base and measure its height
        final Expression.ExpressionView bev = base.visualize(vctx);
        final View b = bev.getView();
        b.measure(-2, -2);
        final int bh = b.getMeasuredHeight();

        // visualize power and measure its height until its height is lower than base's
        View p;
        int ph;
        do {
            p = sup.visualize(vctx.multiplyScaleBy(f)).getView();
            p.measure(-2, -2);
            ph = p.getMeasuredHeight();
            f -= SCALE_STEP;
        } while (bh < ph && f >= SCALE_END); // or we reach too small a de-scaling

        // determine space height
        final int sh = bh > ph ? bh - ph : (int) (bh * SCALE_END);

        // determine resting Y
        restingY = bev.getRestingY() + sh + ph - bh;

        // create the space below power
        final Space s = new Space(vctx.androidContext);
        s.setMinimumHeight(sh);

        // create the right side layout with power and its space
        final LinearLayout r = new LinearLayout(vctx.androidContext);
        r.setOrientation(LinearLayout.VERTICAL);
        r.addView(p);
        r.addView(s);

        // create the total layout with base and the right side
        setGravity(Gravity.BOTTOM);
        addView(b);
        addView(r);
    }

    @Override
    public float getRestingY() {
        return restingY;
    }
}
