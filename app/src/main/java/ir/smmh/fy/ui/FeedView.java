package ir.smmh.fy.ui;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.widget.TableLayout;
import android.widget.TableRow;

import androidx.core.widget.NestedScrollView;

import ir.smmh.fy.Util;
import ir.smmh.fy.cards.Card;
import ir.smmh.fy.code.TabView;

public class FeedView extends TabView {

    // WARNING: only use this class with uniform cards; i.e. of equal size
    private final Feed<Card> src;

    private final TableLayout table;
    private final NestedScrollView sv;
    private TableRow row;

    private boolean firstTime = true;
    private int x = 0, y = 0, cardW = 0, cardH = 0;

    private boolean atBottom;

    public FeedView(Context context, Feed<Card> src) {
        super(context);
        this.src = src;

        src.clear();

        final DisplayMetrics dm = Resources.getSystem().getDisplayMetrics();

        table = new TableLayout(getContext());
        table.addView(row = new TableRow(getContext()));

        sv = Util.View.wrapInScrollView(getContext(), table);

        // sv.getViewTreeObserver().addOnScrollChangedListener(() -> {
        //     // y = y;
        //     // int svy = table.getScrollY();
        //     // int scbs = table.getScrollBarSize();
        //     // svy = svy;
        //     // scbs = scbs;
        //     // if (svy >= scbs * 0.9f) {
        //     //     src.load(6);
        //     // }
        //     double scrollViewHeight = sv.getChildAt(0).getBottom() - sv.getHeight();
        //     double getScrollY = sv.getScrollY();
        //     double scrollPosition = (getScrollY / scrollViewHeight) * 100d;
        //     Log.i("sv", "scroll Percent Y: " + (int) scrollPosition);
        //     atBottom = table.getBottom() <= (sv.getHeight() + sv.getScrollY());
        //     table.setBackgroundColor(atBottom ? Color.RED : Color.BLUE);
        //     postDelayed(() -> table.setBackgroundColor(Color.GRAY), 100);
        // });

        // I should try this: https://stackoverflow.com/questions/10316743/detect-end-of-scrollview

        // and https://stackoverflow.com/questions/10713312/can-i-have-onscrolllistener-for-a-scrollview
        sv.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            int verticalScrollableHeight = v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight();
            float vp = ((float) scrollY) / verticalScrollableHeight;
            Util.toast(getContext(), Float.toString(vp));
        });

        addView(sv);

        src.setOnSubmit((Card card) -> {
            if (firstTime) {
                firstTime = false;
                cardW = card.getOuterWidth();
                cardH = card.getOuterHeight();
                src.load((dm.widthPixels / cardW) * (dm.heightPixels / cardH + 1) - 1);
            }
            x += cardW;
            if (x > dm.widthPixels) {
                row = new TableRow(getContext());
                table.addView(row);
                x = card.getOuterWidth();
                y += cardH;
                cardH = 0;
            }
            row.addView(card);
            invalidate();
        });

        src.load(1);
    }
}

