package ir.smmh.fy.cards;

import android.content.Context;
import android.content.res.Resources;
import android.widget.TableLayout;
import android.widget.TableRow;

import ir.smmh.fy.Util;
import ir.smmh.fy.code.TabView;

public class CardsTabView extends TabView {
    private final CardsTab src;

    public CardsTabView(Context context) {
        this(context, new CardsTab("Untitled", Util.getEmptyIterable()));
    }

    public CardsTabView(Context context, CardsTab src) {
        super(context);
        this.src = src;

        int widthLimit = Resources.getSystem().getDisplayMetrics().widthPixels;

        Context c = getContext();
        TableLayout table = new TableLayout(c);
        TableRow row = new TableRow(c);
        int width = 0;
        for (Cardable cardable : src.cards) {
            Card card;
            try {
                card = cardable.toCard();
            } catch (Exception e) {
                card = new ErrorCard(c, 160, 160, e);
            }
            width += card.getOuterWidth();
            if (width > widthLimit) {
                table.addView(row);
                row = new TableRow(c);
                width = card.getOuterWidth();
            }
            row.addView(card);
        }
        table.addView(row);

        addView(Util.View.wrapInScrollView(c, table));
    }
}

