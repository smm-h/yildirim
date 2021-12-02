package ir.smmh.fy.cmc;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import ir.smmh.fy.Util;
import ir.smmh.fy.cards.Card;
import ir.smmh.fy.code.TabView;
import ir.smmh.fy.ui.ActionCard;
import ir.smmh.fy.ui.Feed;
import ir.smmh.fy.ui.FeedTab;
import ir.smmh.fy.ui.Tab;

public class CryptoHomeTabView extends TabView {

    public CryptoHomeTabView(Context context) {
        super(context);

        addView(new ActionCard(getContext(), "Make the coolest feed ever", callback -> {

            Feed<Card> feed = new Feed<Card>() {

                private final Map<String, String> params = new HashMap<>();

                {
                    params.put("sort", "cmc_rank");
                }

                @Override
                protected void load(final int index, final int count) {

                    params.put("start", Integer.toString(index + 1));
                    params.put("limit", Integer.toString(count));

                    CoinMarketCap.request("/v1/cryptocurrency/map", params, (JSONArray data) -> {
                        Context context = getContext();
                        for (JSONObject obj : Util.over(Util.JSONArray.iterate(data))) {
                            Crypto crypto = CoinMarketCap.find(obj);
                            if (crypto != null) {
                                submit(new CryptoCard(context, crypto));
                            }
                        }
                    }, null);

                }
            };

            // feed.load();

            Tab tab = new FeedTab("Cryptocurrencies", feed);

            // tab = tab.cache(); // TODO try caching FeedTab

            Util.getMainActivity().addTab(tab);

        }));
    }
}
