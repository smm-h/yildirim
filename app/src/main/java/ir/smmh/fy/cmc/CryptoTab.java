package ir.smmh.fy.cmc;

import android.view.View;

import ir.smmh.fy.R;
import ir.smmh.fy.Util;
import ir.smmh.fy.ui.Tab;

public class CryptoTab implements Tab {

    private final Crypto src;

    public CryptoTab(final Crypto src) {
        this.src = src;
    }

    @Override
    public String getTitle() {
        return src.name;
    }

    @Override
    public View getView() {
        return new CryptoTabView(Util.getMainActivity());
    }

    @Override
    public int getIconResource() {
        return R.drawable.ic_baseline_monetization_on_24;
    }
}
