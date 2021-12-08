package ir.smmh.jile;

import java.util.Enumeration;
import java.util.Iterator;

public class Backward {
    private Backward() {
    }

    public static String repeat(String string, int count) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < count; i++) {
            builder.append(string);
        }
        return builder.toString();
    }

    public static boolean isBlank(final String s) {
        // TODO
        throw new RuntimeException("TODO");
    }

    public static String strip(final String input) {
        // TODO
        throw new RuntimeException("TODO");
    }

    public static <T> Iterator<T> asIterator(final Enumeration<T> entries) {
        // TODO
        throw new RuntimeException("TODO");
    }
}
