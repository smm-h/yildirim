package ir.smmh.fy.ui;

import android.view.View;

public abstract class CachedTab implements Tab {

    protected View cachedView = null;

    @Override
    public View getView() {
        if (cachedView == null) {
            cachedView = makeView();
        }
        return cachedView;
    }

    protected abstract View makeView();

}
