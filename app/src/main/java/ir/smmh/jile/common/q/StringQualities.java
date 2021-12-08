package ir.smmh.jile.common.q;

import static ir.smmh.jile.common.q.Quality.is;

import ir.smmh.jile.Backward;
import ir.smmh.jile.common.Common;

public interface StringQualities {

    public static final Quality<String> empty = new Quality<String>() {
        @Override
        public boolean holdsFor(String s) {
            return s.isEmpty();
        }
    };

    public static final Quality<String> blank = new Quality<String>() {
        @Override
        public boolean holdsFor(String s) {
            return Backward.isBlank(s);
        }
    };

    public static final Quality<String> palindrome = new Quality<String>() {
        @Override
        public boolean holdsFor(String s) {
            if (is(s, empty))
                return true;
            int n = s.length() - 1;
            int h = Common.ceil(n / 2.0);
            for (int i = 0; i < h; i++)
                if (s.charAt(i) != s.charAt(n - i))
                    return false;
            return true;
        }
    };

}
