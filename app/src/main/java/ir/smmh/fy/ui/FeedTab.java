package ir.smmh.fy.ui;

import android.view.View;

import ir.smmh.fy.R;
import ir.smmh.fy.Util;
import ir.smmh.fy.cards.Card;

public class FeedTab implements Tab {

    private final String title;
    private final Feed<Card> src;

    public FeedTab(final String title, final Feed<Card> src) {
        this.title = title;
        this.src = src;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public View getView() {
        return new FeedView(Util.getMainActivity(), src);
    }

    @Override
    public int getIconResource() {
        return R.drawable.ic_baseline_view_comfy_24;
    }
}
