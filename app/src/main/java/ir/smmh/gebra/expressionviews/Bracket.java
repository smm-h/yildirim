package ir.smmh.gebra.expressionviews;

import android.graphics.Path;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

public class Bracket {

    private final Shape shape;
    final float ratio;
    private final boolean xMirror, yMirror;

    private static final Map<Character, Bracket> predefined;

    public static void define(char symbol, Bracket pattern) {
        predefined.put(symbol, pattern);
    }

    static {
        predefined = new HashMap<>();

        define('(', new Bracket(Shape.CURVED, 0.40f));
        define('[', new Bracket(Shape.SQUARE, 0.20f));
        define('{', new Bracket(Shape.CURLY, 0.30f));
        define('<', new Bracket(Shape.ANGLE, 0.25f));
        define(')', of('(').flipX());
        define(']', of('[').flipX());
        define('}', of('{').flipX());
        define('>', of('<').flipX());

        define('L', new Bracket(Shape.ELL, 0.20f));
        define('|', new Bracket(Shape.BAR, 0.10f));
    }

    @NonNull
    public static Bracket of(char symbol) {
        final Bracket pattern;
        if ((pattern = predefined.get(symbol)) != null)
            return pattern;
        else
            throw new IllegalArgumentException("Undefined bracket pattern: " + symbol);
    }

    public Bracket(final Shape shape, final float ratio) {
        this(shape, ratio, false, false);
    }

    public Bracket(final Shape shape, final float ratio, final boolean xMirror, final boolean yMirror) {
        this.shape = shape;
        this.ratio = ratio;
        this.xMirror = xMirror;
        this.yMirror = yMirror;
    }

    public Bracket flipX() {
        return new Bracket(shape, ratio, !xMirror, yMirror);
    }

    public Bracket flipY() {
        return new Bracket(shape, ratio, xMirror, !yMirror);
    }

    public Bracket flipBoth() {
        return new Bracket(shape, ratio, !xMirror, !yMirror);
    }

    public enum Shape {
        ANGLE, CURLY, CURVED, SQUARE, ELL, BAR
    }

    public void shapePath(final Path path, final float x, final float y, final float w, final float h) {

        final float x1 = xMirror ? x + w : x;
        final float y1 = yMirror ? y + h : y;
        final float x2 = xMirror ? x : x + w;
        final float y2 = yMirror ? y : y + h;
        final float xm = x + w / 2;
        final float ym = y + h / 2;

        path.reset();

        switch (shape) {
            case ANGLE:
                path.moveTo(x2, y1);
                path.lineTo(x1, ym);
                path.lineTo(x2, y2);
                break;
            case CURLY:
                final float q = w / 2;
                path.moveTo(x2, y1);
                path.quadTo(xm, y1, xm, y1 + q);
                path.lineTo(xm, ym - q);
                path.quadTo(xm, ym, x1, ym);
                path.quadTo(xm, ym, xm, ym + q);
                path.lineTo(xm, y2 - q);
                path.quadTo(xm, y2, x2, y2);
                break;
            case CURVED:
                path.moveTo(x2, y1);
                path.quadTo(x1, ym, x2, y2);
                break;
            case SQUARE:
                path.moveTo(x2, y1);
                path.lineTo(x1, y1);
                path.lineTo(x1, y2);
                path.lineTo(x2, y2);
                break;
            case ELL:
                path.moveTo(x1, y1);
                path.lineTo(x1, y2);
                path.lineTo(x2, y2);
                break;
            case BAR:
                path.moveTo(xm, y1);
                path.lineTo(xm, y2);
                break;
        }
    }
}
