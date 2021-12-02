package ir.smmh.fy.cmc;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;

import ir.smmh.fy.R;
import ir.smmh.fy.Util;
import ir.smmh.fy.cards.Card;
import ir.smmh.fy.net.LazyValue;

public class CryptoCard extends Card {

    public final Crypto src;
    protected Paint paintFore = new Paint();

    public CryptoCard(Context context) {
        this(context, Crypto.BITCOIN);
    }

    private static int getDefaultWidth() {
        int w = Resources.getSystem().getDisplayMetrics().widthPixels - Util.dipToPixel(16);
        return w / Util.greatestNearDivisor(w, Util.dipToPixel(320));
    }

    public CryptoCard(final Context context, Crypto crypto) {
        super(context, getDefaultWidth(), Math.round(getDefaultWidth() / Util.GOLDEN_RATIO), false);
        src = crypto;
        if (src.icon.getState() == LazyValue.State.NOT_REQUESTED) {
            src.icon.addOnLoaded(this::invalidate);
            src.icon.request();
        }
        paintFore.setColor(Util.getColor(getContext(), R.color.active_fore));
        paintFore.setTextSize(40);
        paintFore.setLetterSpacing(0.2f);
        // setFontVariationSettings("'wdth' 150, 'slnt' 20, 'ital' 1");
    }

    @Override
    public void drawDetails(final Canvas canvas, final int x, final int y, final int w, final int h) {
        float cx = x + w / 2f;
        float cy = y + h / 2f;
        if (src.icon.getState() == LazyValue.State.LOADED) {
            float ty, iy;
            iy = y + h / 3f - Crypto.iconSize / 2f;
            ty = y + h / 4f * 3f;
            canvas.drawBitmap(src.icon.getValue(), cx - Crypto.iconSize / 2f, iy, paintFore);
            Util.drawAlignedText(canvas, cx, ty, src.symbol, paintFore, Paint.Align.CENTER, Util.TextVerticalAlignment.TOP, false);
        } else {
            Util.drawAlignedText(canvas, cx, cy, src.symbol, paintFore, Paint.Align.CENTER, Util.TextVerticalAlignment.MIDDLE, false);
        }
    }

    @Override
    protected void onClick() {
        Util.getMainActivity().addTab(new CryptoTab(src));
    }
}
