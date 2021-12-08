package ir.smmh.gebra.expressionviews;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Space;

import androidx.annotation.NonNull;

import ir.smmh.fy.R;
import ir.smmh.fy.Util;
import ir.smmh.gebra.Expression;
import ir.smmh.gebra.Gebra;
import ir.smmh.gebra.VisualizationContext;
import ir.smmh.gebra.expressions.Root;

public class RootView extends FreeView implements Expression.ExpressionView {

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

    private final float restingY;
    private final Path path;

    public RootView(final VisualizationContext vctx, @NonNull final Root root) {
        this(vctx, root.getRadicand(), root.getRoot());
    }

    public RootView(final VisualizationContext vctx, @NonNull final Expression radicand, @NonNull final Expression root) {
        super(vctx);
        // start with a nice and small de-scaling factor
        float f = SCALE_START;

        // visualize base and measure its height
        final Expression.ExpressionView bev = radicand.visualize(vctx);
        final View bv = bev.getView();
        bv.measure(-2, -2);
        final int bh = bv.getMeasuredHeight();

        View pv;
        int ph;
        if (root.equals(Gebra._2)) {
            pv = new Space(vctx.androidContext);
            ph = 0;
        } else {
            // visualize power and measure its height until its height is lower than base's
            do {
                pv = root.visualize(vctx.multiplyScaleBy(f)).getView();
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

        // create the space between radicand and root
        final Space m = new Space(vctx.androidContext);
        m.setMinimumWidth(H_SPACING);

        final int xj = pv.getMeasuredWidth();
        final int yj = pv.getMeasuredHeight();
        final int xm = xj + (sh * H_SPACING) / (bv.getMeasuredHeight() + sh);
        final int xo = xj + H_SPACING;
        final int yoc = -bv.getMeasuredWidth();

        final int x0 = Math.max(0, xj - Util.dipToPixel(5));
        final int y0 = Util.dipToPixel(3);
        final int yo = getHeight() + yoc;

        path = new Path();
        path.reset();
        path.moveTo(x0, yj + y0);
        path.lineTo(xj, yj);
        path.lineTo(xm, getHeight());
        path.lineTo(xo, yo);
        path.lineTo(getWidth(), yo);

        restingY = bev.getRestingY();

        setGravity(Gravity.BOTTOM);
        addView(l);
        addView(m);
        addView(bv);
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        canvas.drawPath(path, PAINT);
    }

    @Override
    public float getRestingY() {
        return restingY;
    }
}
