package ir.smmh.fy.cmc;

import android.view.View;

import ir.smmh.fy.R;
import ir.smmh.fy.ui.Tab;

public class RequestTab implements Tab {
    @Override
    public String getTitle() {
        return "Send a request";
    }

    @Override
    public int getIconResource() {
        return R.drawable.ic_baseline_send_24;
    }

    @Override
    public View getView() {
        return null; // new RequestTabView(Util.getMainActivity());
    }
}
