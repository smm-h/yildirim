package ir.smmh.fy.cards;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import ir.smmh.fy.R;
import ir.smmh.fy.Util;

public class TextCard extends Card {

    public final Paint paint = new Paint();
    public String text;

    public TextCard(Context context, int w, int h, String text) {
        super(context, w, h, true);
        this.text = text;
        paint.setTextSize(40);
        paint.setColor(Util.getColor(getContext(), R.color.active_fore));
    }

    @Override
    public void drawDetails(final Canvas canvas, final int x, final int y, final int w, final int h) {
        String t = text.substring(0, paint.breakText(text, true, w, null));
        canvas.drawText(t, x, y - paint.getFontMetrics().top, paint);
    }

    @Override
    protected void onClick() {
        // do nothing
    }
}
