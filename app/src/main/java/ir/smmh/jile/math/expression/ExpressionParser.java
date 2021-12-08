package ir.smmh.jile.math.expression;

import ir.smmh.jile.common.Convertor;
import ir.smmh.jile.math.abstractalgebra.Ring;

/**
 * An {@link ExpressionParser} takes a file or a string, and produces an
 * {@link Expression}.
 *
 * @see ArrayExpressionParser
 * @see StackExpressionParser
 */
public abstract class ExpressionParser<T> {

    private final Ring<T> ring;
    private final Convertor<String, T> decoder;
    private final Convertor<T, String> encoder;

    public ExpressionParser(Ring<T> ring, Convertor<String, T> decoder, Convertor<T, String> encoder) {
        this.ring = ring;
        this.decoder = decoder;
        this.encoder = encoder;
    }

    public Ring<T> getRing() {
        return ring;
    }

    public T decode(String source) {
        return decoder.convert(source);
    }

    public String encode(T source) {
        return encoder.convert(source);
    }

    /**
     * For example: {@code evaluate("a+b", "a=5\nb=6");} will return "11".
     */
    public String evaluate(String expression, String variables) {
        int i = 0;
        char key = 0;
        String value = "";
        boolean readingDigits = false;
        char[] v = (variables + key).toCharArray();
        while (i < v.length) {
            if (readingDigits) {
                if (isDigit(v[i])) {
                    value += v[i];
                } else {
                    readingDigits = false;
                    setValue(key, decode(value));
                    value = "";
                }
            } else {
                if (isLetter(v[i])) {
                    key = v[i];
                    readingDigits = true;
                    i++;
                } else {
                    // System.out.println("Error: unknown character: " + v[i]);
                }
            }
            i++;
        }
        return encode(evaluate(expression));
    }

    private boolean isLetter(char c) {
        return Character.isLetter(c);
    }

    private boolean isDigit(char c) {
        return Character.isDigit(c);
    }

    abstract protected void setValue(char key, T value);

    abstract protected T evaluate(String expression);
}