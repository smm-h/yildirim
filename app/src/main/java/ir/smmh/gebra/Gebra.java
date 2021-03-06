package ir.smmh.gebra;

import android.graphics.Paint;
import android.widget.LinearLayout;

import ir.smmh.fy.R;
import ir.smmh.fy.Util;
import ir.smmh.gebra.expressions.DoubleValue;

/**
 * @see Expression +
 * @see Statement +
 * @see Symbols
 * @see Namespace
 * @see GebraTab
 * @see GebraTabView
 */
public class Gebra {
    private Gebra() {
    }

    public static final LinearLayout.LayoutParams WRAP_BOTH;

    // public static final Paint DEBUG_PAINT;

    public static final Paint PAINT;

    private static final float THICKNESS;

    static {
        WRAP_BOTH = new LinearLayout.LayoutParams(-2, -2);
        // DEBUG_PAINT = new Paint();
        // DEBUG_PAINT.setColor(Color.RED);

        THICKNESS = Util.dipToPixel(1);

        PAINT = new Paint();
        PAINT.setStrokeWidth(THICKNESS);
        PAINT.setStyle(Paint.Style.STROKE);
        // PAINT.setColor(Util.getColor(Util.getMainActivity(), R.color.passive_fore));
        PAINT.setColor(Util.colorAddAlpha(Util.getColor(Util.getMainActivity(), R.color.passive_fore), 127));
        PAINT.setAntiAlias(true);
    }

    public static final DoubleValue _n2 = new DoubleValue(-2);
    public static final DoubleValue _n1 = new DoubleValue(-1);
    public static final DoubleValue _0 = new DoubleValue(0);
    public static final DoubleValue _1 = new DoubleValue(1);
    public static final DoubleValue _2 = new DoubleValue(2);
    public static final DoubleValue _3 = new DoubleValue(3);
    public static final DoubleValue _4 = new DoubleValue(4);
    public static final DoubleValue _5 = new DoubleValue(5);
    public static final DoubleValue _6 = new DoubleValue(6);
    public static final DoubleValue _7 = new DoubleValue(7);
    public static final DoubleValue _8 = new DoubleValue(8);
    public static final DoubleValue _9 = new DoubleValue(9);
    public static final DoubleValue _10 = new DoubleValue(10);
    public static final DoubleValue _11 = new DoubleValue(11);
    public static final DoubleValue _12 = new DoubleValue(12);

    public interface Effect {
        double affect(double x);
    }
}
