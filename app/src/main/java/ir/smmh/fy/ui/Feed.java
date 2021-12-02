package ir.smmh.fy.ui;

import java.util.LinkedList;
import java.util.List;

public abstract class Feed<T> {

    private final List<T> list = new LinkedList<>();
    private int index;

    public Feed() {
        clear();
    }

    public void clear() {
        list.clear();
        index = 0;
    }

    public void load(int count) {
        load(index, count);
        index += count;
    }

    protected void submit(T loaded) {
        list.add(loaded);
        if (onSubmit != null)
            onSubmit.onSubmit(loaded);
    }

    interface OnSubmit<T> {
        void onSubmit(T submitted);
    }

    private OnSubmit<T> onSubmit = null;

    public void setOnSubmit(final OnSubmit<T> onSubmit) {
        this.onSubmit = onSubmit;
    }

    protected abstract void load(int index, int count);

    public int getIndex() {
        return index;
    }
}
