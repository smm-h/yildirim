package ir.smmh.gebra.expressions;

import android.graphics.Canvas;
import android.graphics.Path;
import android.view.View;

import ir.smmh.fy.Util;
import ir.smmh.gebra.EvaluationContext;
import ir.smmh.gebra.EvaluationError;
import ir.smmh.gebra.Expression;
import ir.smmh.gebra.Gebra;
import ir.smmh.gebra.VisualizationContext;

public abstract class Wrapped extends Expression {

    private final Expression core;
    private final Gebra.Effect effect;

    public Wrapped(final Expression core, final Gebra.Effect effect) {
        this.core = core;
        children.add(core);
        this.effect = effect;
    }

    public Expression getCore() {
        return core;
    }

    @Override
    public double evaluate(final EvaluationContext ectx) throws EvaluationError {
        return effect.affect(getCore().evaluate(ectx));
    }

    public static class InBrackets extends Wrapped {

        private final Bracket bracket;

        public InBrackets(final Expression core, final Gebra.Effect effect, final Bracket bracket) {
            super(core, effect);
            this.bracket = bracket;
        }

        @Override
        public ExpressionView visualize(final VisualizationContext vctx) {
            return new Brackets(vctx, getCore(), bracket);
        }

        private static class Brackets extends Layout {

            private static final float PADDING, ADDITIONAL_H;

            static {
                PADDING = Util.dipToPixel(3);
                ADDITIONAL_H = Util.dipToPixel(8);
            }

            private final ExpressionView coreView;

            Brackets(final VisualizationContext vctx, final Expression core, final Bracket open) {
                this(vctx, core, open, open.flipX());
            }

            Brackets(final VisualizationContext vctx, final Expression core, final Bracket open, final Bracket close) {
                super(vctx);
                coreView = core.visualize(vctx);
                addView(new BracketView(open));
                addView(coreView.getView());
                addView(new BracketView(close));
            }

            @Override
            public float getRestingY() {
                return coreView.getRestingY() + ADDITIONAL_H / 2;
            }

            private class BracketView extends View {
                private final Bracket pattern;
                private final Path path;
                private float prev_w, prev_h;

                public BracketView(final Bracket pattern) {
                    super(coreView.getVisualizationContext().androidContext);
                    this.pattern = pattern;
                    setWillNotDraw(false);
                    path = new Path();
                }

                @Override
                protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {
                    final float w, h;
                    final View v = coreView.getView();
                    v.measure(-2, -2);
                    h = v.getMeasuredHeight() + ADDITIONAL_H;
                    w = ((h - PADDING * 2) * pattern.ratio) + PADDING * 2;
                    setMeasuredDimension((int) w, (int) h);
                }

                @Override
                protected void onDraw(final Canvas canvas) {
                    super.onDraw(canvas);
                    final int w = getWidth();
                    final int h = getHeight();
                    if (prev_w != w || prev_h != h) {
                        prev_w = w;
                        prev_h = h;
                        pattern.shapePath(path, PADDING, PADDING, w - PADDING * 2, h - PADDING * 2);
                    }
                    canvas.drawPath(path, Gebra.PAINT);
                }
            }
        }
    }

    public static class UnderVinculum extends Wrapped {

        public UnderVinculum(final Expression core, final Gebra.Effect effect) {
            super(core, effect);
        }

        @Override
        public ExpressionView visualize(final VisualizationContext vctx) {
            return new Vinculum(vctx, getCore());
        }

        private static class Vinculum extends Layout {

            private static final int ADDITIONAL_W, ADDITIONAL_H;

            static {
                ADDITIONAL_W = Util.dipToPixel(8);
                ADDITIONAL_H = Util.dipToPixel(8);
            }

            private final ExpressionView coreView;

            Vinculum(final VisualizationContext vctx, final Expression core) {
                super(vctx);
                addView((coreView = core.visualize(vctx)).getView());
            }

            @Override
            public float getRestingY() {
                return coreView.getRestingY() + ADDITIONAL_H;
            }

            @Override
            protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {
                final float w, h;
                final View v = coreView.getView();
                v.measure(-2, -2);
                w = v.getMeasuredWidth() + ADDITIONAL_W;
                h = v.getMeasuredHeight() + ADDITIONAL_H;
                setMeasuredDimension((int) w, (int) h);
            }

            @Override
            protected void onDraw(final Canvas canvas) {
                super.onDraw(canvas);
                final float x = ADDITIONAL_W / 2f;
                final float y = ADDITIONAL_H / 2f;
                canvas.drawLine(x, y, getWidth() - x, y, Gebra.PAINT);
            }
        }
    }
}
