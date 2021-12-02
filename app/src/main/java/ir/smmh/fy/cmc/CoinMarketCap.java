package ir.smmh.fy.cmc;

import androidx.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import ir.smmh.fy.Util;
import ir.smmh.fy.net.Network;

public class CoinMarketCap {

    private static final String DOMAIN = "https://pro-api.coinmarketcap.com";
    private static final String API_KEY = "114f2a93-5c15-47fe-86b9-54c5eba2a616";
    private static final Map<String, String> HEADERS = new HashMap<>();

    static {
        HEADERS.put("Accept", "application/json");
        HEADERS.put("X-CMC_PRO_API_KEY", API_KEY);
    }

    public interface ResponseCallback {
        void onResponse(JSONArray data);
    }

    public static void request(String call, Map<String, String> params, ResponseCallback r, @Nullable Runnable onFinish) {
        Util.getNetwork().request(Network.buildURI(DOMAIN + call, params), response -> {
            try {
                JSONObject root = new JSONObject(response);
                // JSONObject status = root.getJSONObject("status");
                // if (status.getInt("error_code") == 0) {
                r.onResponse(root.getJSONArray("data"));
                // } else {
                //     String errorMessage = status.getString("error_message");
                //     Util.toast(Util.getMainActivity(), "API Error: " + errorMessage);
                // }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (onFinish != null)
                onFinish.run();
        }, e -> {
            Util.toast(Util.getMainActivity(), "Volley error occurred.");
            if (onFinish != null)
                onFinish.run();
        }, HEADERS);
    }

    public static Crypto find(JSONObject obj) {
        try {
            final int id = Integer.parseInt(obj.getString("id"));
            final String name = obj.getString("name");
            final String symbol = obj.getString("symbol");
            return Crypto.find(id, name, symbol);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
