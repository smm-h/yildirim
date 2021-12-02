package ir.smmh.fy.cards;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import ir.smmh.fy.MainActivity;
import ir.smmh.fy.Util;
import ir.smmh.fy.code.CodeTab;
import ir.smmh.fy.code.PlainText;

public class ErrorCard extends Card {

    private static final Paint p = new Paint();

    static {
        p.setStrokeWidth(12);
        p.setColor(Util.colorAddAlpha(Color.RED, 222));
    }

    private final Exception e;

    public ErrorCard(final Context context, final int w, final int h, Exception e) {
        super(context, w, h, false);
        this.e = e;
    }

    @Override
    public void drawDetails(final Canvas canvas, final int x, final int y, final int w, final int h) {
        canvas.drawLine(x, y, x + w, y + h, p);
        canvas.drawLine(x + w, y, x, y + h, p);
    }

    @Override
    protected void onClick() {
        MainActivity m = Util.getMainActivity();
        if (m != null) {
            // throw new RuntimeException(e);
            m.addTab(new CodeTab(new PlainText(e.getMessage())));
            // e.printStackTrace();
            // Log.e("general", e.getStackTrace().toString());
        } else {
            Util.toast(getContext(), "Could not find MainActivity to report the error");
        }
    }
}
