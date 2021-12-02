package ir.smmh.fy.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import ir.smmh.fy.R;
import ir.smmh.fy.Util;
import ir.smmh.fy.cards.Card;

public class ActionCard extends Card {

    public final String text;
    private final Paint cardTextPaint = new Paint();
    private final float tx, ty;
    private final Finishable onClick;

    public interface Finishable {
        void onClick(Runnable callback);
    }

    public ActionCard(Context context, String text, Finishable onClick) {
        super(context);
        this.text = text;
        this.onClick = onClick;

        cardTextPaint.setColor(Util.getColor(getContext(), R.color.active_fore));
        cardTextPaint.setTextSize(Util.dipToPixel(16));

        Rect r = new Rect();
        cardTextPaint.getTextBounds(text, 0, text.length(), r);

        setInnerSize(r.width(), Math.round(Util.Paint_getLineHeight(cardTextPaint)));

        tx = r.width() / 2f;
        ty = r.top + r.height() / 2f;
    }

    @Override
    public void drawDetails(final Canvas canvas, final int x, final int y, final int w, final int h) {
        float dx = x + w / 2f - tx;
        float dy = y + h / 2f - ty;
        canvas.drawText(text, dx, dy, cardTextPaint);
    }

    private enum AnimationStage {
        READY, FADING_OUT, FADING_BACK_IN, WAITING
    }

    private AnimationStage stage = AnimationStage.READY;
    private float animationProgress = 1f;

    @Override
    protected void onClick() {
        if (stage == AnimationStage.READY) {
            stage = AnimationStage.FADING_OUT;
            animationProgress = 1f;
            invalidate();
        }
    }

    private void callback() {
        assert stage == AnimationStage.WAITING;
        stage = AnimationStage.FADING_BACK_IN;
        post(this::invalidate);
    }

    @Override
    public void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        if (stage == AnimationStage.FADING_OUT || stage == AnimationStage.FADING_BACK_IN) {
            final float animationSpeed = 0.1f;
            if (stage == AnimationStage.FADING_BACK_IN) {
                animationProgress += animationSpeed;
                if (animationProgress >= 1f) {
                    animationProgress = 1f;
                    stage = AnimationStage.READY;
                }
            } else {
                animationProgress -= animationSpeed;
                if (animationProgress <= 0f) {
                    animationProgress = 0f;
                    stage = AnimationStage.WAITING;
                    onClick.onClick(this::callback);
                }
            }
            int c = Util.colorAddAlpha(Util.getColor(getContext(), R.color.active_fore), 63 + Math.round(192 * animationProgress));
            paintBorder.setColor(c);
            cardTextPaint.setColor(c);
            invalidate();
        }
    }
}