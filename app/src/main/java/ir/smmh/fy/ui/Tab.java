package ir.smmh.fy.ui;

import android.view.View;

public interface Tab {
    String getTitle();

    int getIconResource();

    View getView();

    default Tab cache() {
        if (this instanceof CachedTab) {
            return this;
        } else {
            Tab src = this;
            return new CachedTab() {
                @Override
                protected View makeView() {
                    return src.getView();
                }

                @Override
                public String getTitle() {
                    return src.getTitle();
                }

                @Override
                public int getIconResource() {
                    return src.getIconResource();
                }
            };
        }
    }
}