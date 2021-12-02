package ir.smmh.fy.code;

import android.content.Context;
import android.widget.LinearLayout;

import ir.smmh.fy.R;
import ir.smmh.fy.Util;

public class TabView extends LinearLayout {
    public TabView(final Context context) {
        super(context);

        setOrientation(VERTICAL);

        int p = Util.dipToPixel(8);
        setPadding(p, p, p, p);

        setBackgroundColor(Util.getColor(context, R.color.active_back));
    }
}
