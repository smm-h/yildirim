package ir.smmh.jile.math.expression;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

import ir.smmh.jile.common.Convertor;
import ir.smmh.jile.math.abstractalgebra.Ring;

/**
 * A {@link StackExpressionParser} is an {@link ExpressionParser} that uses
 * {@link Stack}s.
 *
 * @see ArrayExpressionParser
 */
public class StackExpressionParser<T> extends ExpressionParser<T> {

    private final Map<Character, T> variables;

    public StackExpressionParser(Ring<T> ring, Convertor<String, T> decoder, Convertor<T, String> encoder) {
        super(ring, decoder, encoder);
        variables = new HashMap<Character, T>();
    }

    @Override
    protected void setValue(char key, T value) {
        variables.put(key, value);
    }

    @Override
    protected T evaluate(String expression) {

        // int pushes = 0;
        // int pops = 0;

        Stack<Character> stack = new Stack<Character>();
        LinkedList<Character> temp = new LinkedList<Character>();

        char[] e = expression.toCharArray();
        int n = e.length;

        for (int i = 0; i < n; i++) {

            if (i % 2 == 0) {
                stack.push(e[i]);
            } else {
                while (precedenceOf(stack.peek()) >= precedenceOf(e[i])) {
                    temp.add(stack.pop());
                    // pushes++;
                }
                stack.push(e[i]);
                while (!temp.isEmpty()) {
                    stack.push(temp.removeLast());
                    // pops++;
                }
            }
        }

        return null;
    }

    private int precedenceOf(Character symbol) {
        return 0;
    }

}