package ir.smmh.gebra.expressionviews;

import android.annotation.SuppressLint;
import android.graphics.Typeface;

import ir.smmh.fy.Util;
import ir.smmh.gebra.Symbols;
import ir.smmh.gebra.VisualizationContext;

@SuppressLint("AppCompatCustomView")
public class Symbol extends Text {
    public Symbol(final VisualizationContext vctx, final String symbol) {
        super(vctx, symbol);
        setTypeface(Typeface.SERIF);
        setTextSize(Util.dipToPixel(13 * vctx.scale));
        char s = Symbols.getSymbol(symbol);
        setText(s == 0 ? symbol : String.valueOf(s));
    }
}
