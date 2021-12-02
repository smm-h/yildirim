package ir.smmh.fy.net;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import ir.smmh.fy.Util;

public abstract class LazyValue<T> {

    public enum State {
        NOT_REQUESTED, REQUESTED, LOADED, FAILED
    }

    private T value = null;
    private State state = State.NOT_REQUESTED;

    public State getState() {
        return state;
    }

    public T getValue() {
        return value;
    }

    protected void setValue(final T value) {
        this.value = value;
    }

    public void request() {
        state = State.REQUESTED;
        if (onRequested != null)
            onRequested.run();
        Util.phew(() -> {
            value = null;
            try {
                load();
                state = State.LOADED;
                onLoaded();
            } catch (Exception e) {
                if (value == null) {
                    state = State.FAILED;
                    if (onFailed != null)
                        onFailed.onExceptionCaught(e);
                } else {
                    // partial load
                    state = State.LOADED;
                    if (onFailed != null)
                        onFailed.onExceptionCaught(e);
                    onLoaded();
                }
            }
        });
    }

    protected abstract void load() throws Exception;

    private Runnable onRequested;
    private final Queue<Runnable> onLoaded = new ConcurrentLinkedQueue<>();
    private Util.OnExceptionCaught onFailed;

    public void setOnRequested(final Runnable onRequested) {
        this.onRequested = onRequested;
    }

    public void addOnLoaded(final Runnable onLoaded) {
        this.onLoaded.add(onLoaded);
    }

    public void setOnFailed(final Util.OnExceptionCaught onFailed) {
        this.onFailed = onFailed;
    }

    private void onLoaded() {
        while (!onLoaded.isEmpty()) {
            onLoaded.remove().run();
        }
    }
}
