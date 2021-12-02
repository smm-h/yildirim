package ir.smmh.fy.home;

import android.content.Context;

import ir.smmh.fy.Util;
import ir.smmh.fy.code.TabView;
import ir.smmh.fy.ui.ActionCard;
import ir.smmh.gebra.GebraTab;

public class HomeTabView extends TabView {

    public HomeTabView(final Context context) {
        super(context);

        // addView(new ActionCard(getContext(), "Open the CryptoHome", (Runnable callback) -> {
        //     Util.getMainActivity().addTab(new CryptoHomeTab());
        //     callback.run();
        // }));

        // addView(new ActionCard(getContext(), "Open the Trader", (Runnable callback) -> {
        //     Util.getMainActivity().addTab(new TraderTab());
        //     callback.run();
        // }));

        addView(new ActionCard(getContext(), "Open Gebra", (Runnable callback) -> {
            Util.getMainActivity().addTab(new GebraTab());
            callback.run();
        }));
    }
}
