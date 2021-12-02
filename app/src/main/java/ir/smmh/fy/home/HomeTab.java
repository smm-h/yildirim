package ir.smmh.fy.home;

import android.view.View;

import ir.smmh.fy.R;
import ir.smmh.fy.Util;
import ir.smmh.fy.ui.Tab;

public class HomeTab implements Tab {
    @Override
    public String getTitle() {
        return "Home";
    }

    @Override
    public int getIconResource() {
        return R.drawable.ic_baseline_home_24;
    }

    @Override
    public View getView() {
        return new HomeTabView(Util.getMainActivity());
    }

}
