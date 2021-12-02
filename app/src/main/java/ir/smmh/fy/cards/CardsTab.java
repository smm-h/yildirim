package ir.smmh.fy.cards;

import android.view.View;

import ir.smmh.fy.R;
import ir.smmh.fy.Util;
import ir.smmh.fy.ui.Tab;

public class CardsTab implements Tab {

    private final String title;
    public final Iterable<Cardable> cards;

    public CardsTab(final String title, final Iterable<Cardable> cards) {
        this.title = title;
        this.cards = cards;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public View getView() {
        return new CardsTabView(Util.getMainActivity(), this);
    }

    @Override
    public int getIconResource() {
        return R.drawable.ic_baseline_view_comfy_24;
    }
}
