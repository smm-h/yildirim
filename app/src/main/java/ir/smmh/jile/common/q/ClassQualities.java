package ir.smmh.jile.common.q;

// import static ir.smmh.jile.common.q.Quality.*;

public interface ClassQualities {

    public static final Quality<Class<?>> _interface = new Quality<Class<?>>() {
        @Override
        public boolean holdsFor(Class<?> c) {
            return c.isInterface();
        }
    };

}
