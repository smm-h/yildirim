package ir.smmh.fy.cmc;

public abstract class Currency {

    public static final Crypto BITCOIN = Crypto.find(1, "Bitcoin", "BTC");

    public final int id;

    protected Currency(final int id) {
        this.id = id;
    }
}
