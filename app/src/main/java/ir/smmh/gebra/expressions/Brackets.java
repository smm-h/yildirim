package ir.smmh.gebra.expressions;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

import ir.smmh.fy.R;
import ir.smmh.fy.Util;
import ir.smmh.gebra.Expression;
import ir.smmh.gebra.VisualizationContext;

public abstract class Brackets extends Expression {

    private static final float THICKNESS = Util.dipToPixel(1);
    private static final float PADDING = Util.dipToPixel(3);

    public enum Type {
        ANGLE, CURLY, CURVED, SQUARE
    }

    private final Expression core;
    private final Type type;
    private final float ratio;

    public Brackets(final Expression core, final Type type) {
        this.core = core;
        this.type = type;
        children.add(core);
        ratio = getDefaultRatio();
    }

    private float getDefaultRatio() {
        switch (type) {
            case ANGLE:
                return 0.25f;
            case CURLY:
                return 0.3f;
            case CURVED:
                return 0.4f;
            case SQUARE:
                return 0.2f;
            default:
                return 0.31f;
        }
    }

    private static final Paint PAINT;

    static {
        PAINT = new Paint();
        PAINT.setStrokeWidth(THICKNESS);
        PAINT.setStyle(Paint.Style.STROKE);
        PAINT.setColor(Util.colorAddAlpha(Util.getColor(Util.getMainActivity(), R.color.passive_fore), 127));
        PAINT.setAntiAlias(true);
        // PAINT.setShadowLayer(PADDING / 2, 0, 0, Color.BLACK);
    }

    private class BracketsView extends LinearExpressionView {

        private final View coreView;

        BracketsView(final VisualizationContext vctx, final Expression core) {
            super(vctx);
            coreView = core.visualize(vctx).getView();
            addView(new Bracket(true));
            addView(coreView);
            addView(new Bracket(false));
        }

        private class Bracket extends View {
            private final boolean open;
            private final Path path;
            private float prev_w, prev_h;

            public Bracket(final boolean open) {
                super(coreView.getContext());
                this.open = open;
                setWillNotDraw(false);
                path = new Path();
            }

            @Override
            protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {
                coreView.measure(-2, -2);
                final float w, h;
                h = coreView.getMeasuredHeight() * 1.1f;
                w = ((h - PADDING * 2) * ratio) + PADDING * 2; // RATIO[type.ordinal()])
                setMeasuredDimension((int) w, (int) h);
            }

            @Override
            protected void onDraw(final Canvas canvas) {
                super.onDraw(canvas);
                update(getWidth(), getHeight());
                canvas.drawPath(path, PAINT);
            }

            private void update(final float curr_w, final float curr_h) {
                if (curr_w != prev_w || curr_h != prev_h) {
                    prev_w = curr_w;
                    prev_h = curr_h;
                    final float x = PADDING;
                    final float y = PADDING;
                    final float w = curr_w - PADDING * 2;
                    final float h = curr_h - PADDING * 2;
                    path.reset();
                    switch (type) {
                        case ANGLE: {
                            if (open) {
                                path.moveTo(x + w, y);
                                path.lineTo(x, y + h / 2);
                                path.lineTo(x + w, y + h);
                            } else {
                                path.moveTo(x, y);
                                path.lineTo(x + w, y + h / 2);
                                path.lineTo(x, y + h);
                            }
                            break;
                        }
                        case CURLY: {
                            final float q = w / 2;
                            if (open) {
                                path.moveTo(x + w, y);
                                path.quadTo(x + q, y, x + q, y + q);
                                path.lineTo(x + q, y + h / 2 - q);
                                path.quadTo(x + q, y + h / 2, x, y + h / 2);
                                path.quadTo(x + q, y + h / 2, x + q, y + h / 2 + q);
                                path.lineTo(x + q, y + h - q);
                                path.quadTo(x + q, y + h, x + w, y + h);
                            } else {
                                path.moveTo(x, y);
                                path.quadTo(x + q, y, x + q, y + q);
                                path.lineTo(x + q, y + h / 2 - q);
                                path.quadTo(x + q, y + h / 2, x + w, y + h / 2);
                                path.quadTo(x + q, y + h / 2, x + q, y + h / 2 + q);
                                path.lineTo(x + q, y + h - q);
                                path.quadTo(x + q, y + h, x, y + h);
                            }
                        }
                        break;
                        case CURVED: {
                            if (open) {
                                path.moveTo(x + w, y);
                                path.quadTo(x, y + h / 2, x + w, y + h);
                            } else {
                                path.moveTo(x, y);
                                path.quadTo(x + w, y + h / 2, x, y + h);
                            }
                        }
                        break;
                        case SQUARE: {
                            final float x1 = x; // + w * 1 / 3;
                            final float x2 = x + w; // * 2 / 3;
                            if (open) {
                                path.moveTo(x2, y);
                                path.lineTo(x1, y);
                                path.lineTo(x1, y + h);
                                path.lineTo(x2, y + h);
                            } else {
                                path.moveTo(x1, y);
                                path.lineTo(x2, y);
                                path.lineTo(x2, y + h);
                                path.lineTo(x1, y + h);
                            }
                        }
                        break;
                    }
                }
            }
        }
    }

    @Override
    public ExpressionView visualize(final VisualizationContext vctx) {
        return new BracketsView(vctx, core);
    }

    public Expression getCore() {
        return core;
    }
}
