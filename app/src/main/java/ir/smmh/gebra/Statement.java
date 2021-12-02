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

    private int status = 2;
    public final EvaluationContext ectx;

    public Statement(final EvaluationContext ectx) {
        this.ectx = ectx;
    }

    public abstract int execute(@Nullable StatementView sv);

    public abstract StatementView visualize(final VisualizationContext vctx);

    public abstract static class StatementView extends LinearLayout {

        protected static final int STATUS_BAR_WIDTH = Util.dipToPixel(2);
        protected static final int STATUS_BAR_MARGIN = Util.dipToPixel(8);

        private static final Paint[] PAINTS = new Paint[3];

        static {
            PAINTS[0] = new Paint();
            PAINTS[0].setColor(Color.RED);
            PAINTS[0].setStyle(Paint.Style.FILL);
            PAINTS[1] = new Paint();
            PAINTS[1].setColor(Color.GREEN);
            PAINTS[1].setStyle(Paint.Style.FILL);
            PAINTS[2] = new Paint();
            PAINTS[2].setColor(Color.YELLOW);
            PAINTS[2].setStyle(Paint.Style.FILL);
        }

        public final Statement core;

        public StatementView(final VisualizationContext vctx, final Statement core) {
            super(vctx.androidContext);
            this.core = core;
            setWillNotDraw(false);
            setLayoutParams(Gebra.WRAP_BOTH);
            setGravity(Gravity.CENTER_VERTICAL);
            setOnClickListener(v -> {
                core.execute(this);
                invalidate();
            });
        }

        @Override
        protected void onDraw(final Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawRect(0, 0, STATUS_BAR_WIDTH, getWidth(), PAINTS[core.status]);
        }
    }
}
