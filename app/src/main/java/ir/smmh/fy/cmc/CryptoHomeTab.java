package ir.smmh.fy.cmc;

import android.view.View;

import ir.smmh.fy.R;
import ir.smmh.fy.Util;
import ir.smmh.fy.ui.Tab;

public class CryptoHomeTab implements Tab {

    @Override
    public String getTitle() {
        return "CryptoHome";
    }

    @Override
    public int getIconResource() {
        return R.drawable.ic_baseline_monetization_on_24;
    }

    @Override
    public View getView() {
        return new CryptoHomeTabView(Util.getMainActivity());
    }
}
