package ir.smmh.fy.cards;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

import ir.smmh.fy.R;
import ir.smmh.fy.Util;

public abstract class Card extends View {

    private int width, height;
    private RectF rectFill, rectStroke;
    private boolean ready = false;

    protected int margin = Util.dipToPixel(8);
    protected int padding = Util.dipToPixel(10);
    protected int shadowAlpha = Util.dipToPixel(10);
    protected int shadowRadius = Util.dipToPixel(8);
    protected int shadowOffsetX = Util.dipToPixel(0);
    protected int shadowOffsetY = Util.dipToPixel(8);
    protected int cornerRadius = Util.dipToPixel(12);
    protected int backColor = Util.getColor(getContext(), R.color.card_enabled_back);

    protected int borderWidth = 1;
    protected int borderColor = Util.getColor(getContext(), R.color.active_fore);

    protected Paint paintBack = new Paint();
    protected Paint paintBorder = new Paint();

    public Card(Context context) {
        super(context);

        paintBack.setColor(backColor);
        paintBack.setAntiAlias(true);
        paintBack.setStyle(Paint.Style.FILL);

        paintBorder.setColor(borderColor);
        paintBorder.setAntiAlias(true);
        paintBorder.setStyle(Paint.Style.STROKE);
        paintBorder.setStrokeWidth(borderWidth);
        paintBorder.setStrokeCap(Paint.Cap.ROUND);
        paintBorder.setStrokeJoin(Paint.Join.ROUND);
        paintBorder.setPathEffect(Util.DASH);

        Util.paintEnableShadow(paintBack, shadowAlpha, shadowRadius, shadowOffsetX, shadowOffsetY);
        Util.viewDisableHardwareAcceleration(this);

        setOnClickListener(v -> onClick());
    }

    public Card(Context context, int w, int h, boolean inner) {
        this(context);

        if (inner)
            setInnerSize(w, h);
        else
            setOuterSize(w, h);
    }

    @Override
    protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {
        setMeasuredDimension(getOuterWidth(), getOuterHeight());
    }

    @Override
    public void onDraw(final Canvas canvas) {
        if (ready) {
            canvas.drawRoundRect(rectFill, cornerRadius, cornerRadius, paintBack);
            canvas.drawRoundRect(rectStroke, cornerRadius, cornerRadius, paintBorder);
            drawDetails(canvas, margin + padding, margin + padding, getInnerWidth(), getInnerHeight());
        }
    }

    public abstract void drawDetails(final Canvas canvas, int x, int y, int w, int h);

    protected abstract void onClick();

    public int getInnerWidth() {
        return width - padding * 2;
    }

    public int getInnerHeight() {
        return height - padding * 2;
    }

    public int getOuterWidth() {
        return width + margin * 2 + shadowOffsetX;
    }

    public int getOuterHeight() {
        return height + margin * 2 + shadowOffsetY;
    }

    public void setInnerSize(int innerWidth, int innerHeight) {
        width = innerWidth + padding * 2;
        height = innerHeight + padding * 2;
        remakeRects();
    }

    public void setOuterSize(int outerWidth, int outerHeight) {
        width = outerWidth - (margin * 2 + shadowOffsetX);
        height = outerHeight - (margin * 2 + shadowOffsetY);
        remakeRects();
    }

    private void remakeRects() {
        float q = borderWidth / 2f;
        rectFill = new RectF(margin, margin, margin + width, margin + height);
        rectStroke = new RectF(margin + q, margin + q, margin + width - q, margin + height - q);
        ready = true;
    }

    private class DetailsOnly extends View {

        private final int w, h;

        public DetailsOnly() {
            super(Card.this.getContext());
            this.w = getInnerWidth();
            this.h = getInnerHeight();
        }

        @Override
        protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {
            setMeasuredDimension(w, h);
        }

        @Override
        protected void onDraw(final Canvas canvas) {
            // canvas.drawRect(-padding, -padding, -padding+width, -padding+height, Util.HIGHLIGHTER);
            drawDetails(canvas, -padding, -padding, width, height);
        }
    }

    public View detailsOnly() {
        return new DetailsOnly();
    }
}
