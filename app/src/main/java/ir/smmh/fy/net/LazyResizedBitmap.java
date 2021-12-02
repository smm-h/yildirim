package ir.smmh.fy.net;

import android.graphics.Bitmap;

public class LazyResizedBitmap extends LazyBitmap {

    private final int w, h;

    public LazyResizedBitmap(final String url, final int w, final int h) {
        super(url);
        this.w = w;
        this.h = h;
    }

    @Override
    protected void load() throws Exception {
        super.load();
        Bitmap b = getValue();
        if (b != null) {
            if (b.getWidth() != w || b.getHeight() != h) {
                setValue(Bitmap.createScaledBitmap(b, w, h, false));
            }
        }
    }
}