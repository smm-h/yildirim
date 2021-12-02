package ir.smmh.gebra;

import android.view.View;

import ir.smmh.fy.R;
import ir.smmh.fy.Util;
import ir.smmh.fy.ui.Tab;

public class GebraTab implements Tab {
    @Override
    public String getTitle() {
        return "Gebra";
    }

    @Override
    public int getIconResource() {
        return R.drawable.ic_baseline_calculate_24;
    }

    @Override
    public View getView() {
        return new GebraTabView(Util.getMainActivity());
    }
}
