package ir.smmh.fy.cmc;

import java.util.HashMap;
import java.util.Map;

import ir.smmh.fy.net.LazyResizedBitmap;

public final class Crypto extends Currency {

    private static final Map<Integer, Crypto> map = new HashMap<>();

    public static Crypto find(int id, String name, String symbol) {
        if (map.containsKey(id)) {
            return map.get(id);
        } else {
            Crypto c = new Crypto(id, name, symbol);
            map.put(id, c);
            return c;
        }
    }

    public final String name;
    public final String symbol;

    static final int iconSize = 128;
    public LazyResizedBitmap icon;

    private Crypto(int id, String name, String symbol) {
        super(id);
        this.name = name;
        this.symbol = symbol;

        // String url = "https://cryptoicon-api.vercel.app/api/icon/" + symbol.toLowerCase(Locale.ROOT);
        String url = "https://s2.coinmarketcap.com/static/img/coins/128x128/" + id + ".png";

        icon = new LazyResizedBitmap(url, iconSize, iconSize);
    }
}
