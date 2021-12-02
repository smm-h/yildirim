package ir.smmh.fy.net;

import android.graphics.Bitmap;

public class LazyBitmap extends LazyValue<Bitmap> {

    private final String url;

    public LazyBitmap(final String url) {
        this.url = url;
    }

    @Override
    protected void load() throws Exception {
        setValue(Network.loadImage(url));
    }

}