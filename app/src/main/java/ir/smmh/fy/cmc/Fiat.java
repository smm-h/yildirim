package ir.smmh.fy.cmc;

public final class Fiat extends Currency {
    protected Fiat(final int id) {
        super(id);
        // https://coinmarketcap.com/api/documentation/v1/#operation/getV1FiatMap TODO
    }
}
