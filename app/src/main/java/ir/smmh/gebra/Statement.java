package ir.smmh.gebra;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.Gravity;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import ir.smmh.fy.Util;
import ir.smmh.gebra.statements.Equation;
import ir.smmh.gebra.statements.Recipe;

/**
 * @see Gebra <=
 * @see StatementView
 * @see Recipe
 * @see Equation
 */
public abstract class Statement {

    public enum Status {
        NOT_EXECUTED_YET, NO_ERROR, EVAL_ERROR, JAVA_ERROR;

        private int getColor() {
            switch (this) {
                case NOT_EXECUTED_YET:
                    return Color.YELLOW;
                case NO_ERROR:
                    return Color.GREEN;
                case JAVA_ERROR:
                    return Color.BLUE;
                default:
                    return Color.RED;
            }
        }
    }

    private Status status = Status.NOT_EXECUTED_YET;
    public final Namespace ns;

    public Statement(final Namespace ns) {
        this.ns = ns;
    }

    public abstract Status execute(@Nullable StatementView sv);

    public abstract StatementView visualize(final VisualizationContext vctx);

    public abstract static class StatementView extends LinearLayout {

        protected static final int STATUS_BAR_WIDTH = Util.dipToPixel(2);
        protected static final int STATUS_BAR_MARGIN = Util.dipToPixel(8);

        private final Paint paint;
        public final Statement core;
        protected final VisualizationContext visualizationContext;

        public StatementView(final VisualizationContext vctx, final Statement core) {
            super(vctx.androidContext);
            this.visualizationContext = vctx;
            this.core = core;
            setWillNotDraw(false);
            setLayoutParams(Gebra.WRAP_BOTH);
            setGravity(Gravity.CENTER_VERTICAL);
            setOnClickListener(v -> {
                core.status = core.execute(this);
                invalidate();
            });
            paint = new Paint();
            paint.setStyle(Paint.Style.FILL);
        }

        @Override
        protected void onDraw(final Canvas canvas) {
            super.onDraw(canvas);
            paint.setColor(core.status.getColor());
            canvas.drawRect(0, 0, STATUS_BAR_WIDTH, getWidth(), paint);
        }
    }
}
