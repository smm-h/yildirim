package ir.smmh.fy.trader;

import android.view.View;

import ir.smmh.fy.R;
import ir.smmh.fy.Util;
import ir.smmh.fy.ui.Tab;

public class TraderTab implements Tab {

    @Override
    public String getTitle() {
        return "Trader";
    }

    @Override
    public int getIconResource() {
        return R.drawable.ic_baseline_mediation_24;
    }

    @Override
    public View getView() {
        return new TraderTabView(Util.getMainActivity());
    }
}
