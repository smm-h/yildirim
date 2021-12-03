package ir.smmh.gebra;

import android.content.Context;

import ir.smmh.fy.code.TabView;
import ir.smmh.gebra.expressions.DoubleValue;
import ir.smmh.gebra.expressions.Fraction;
import ir.smmh.gebra.expressions.Negation;
import ir.smmh.gebra.expressions.Power;
import ir.smmh.gebra.expressions.Production;
import ir.smmh.gebra.expressions.Root;
import ir.smmh.gebra.expressions.Summation;
import ir.smmh.gebra.expressions.Text;
import ir.smmh.gebra.expressions.Variable;

public class GebraTabView extends TabView {

    final Expression ONE = d(1);

    public GebraTabView(final Context context) {
        super(context);

        VisualizationContext vctx = new VisualizationContext(context, 1);

        // addView(new Text(vctx, "Hello, world!"));

        EvaluationContext ectx = new EvaluationContext.Default();

        // Expression e = frac(d(10), d(5));
        Expression e = fracR(5);
        Expression e2 = s(p(d(2), pow(v("alpha"), s(d(2), v("pi")))), neg(p(d(3), v("beta"))), v(
            "epsilon"));
        // Expression e = root(d(25), d(2));
        addView(e.getEvaluation(ectx).visualize(vctx));

        // Expression e = new Variable("alpha");
        // addView(e.getEvaluation(ectx).visualize(context));

        // MultiaryOperation e = new Production();
        //
        // e.addTerm(new DoubleValue(1));
        // e.addTerm(new DoubleValue(2));
        // e.addTerm(new DoubleValue(3));
        // e.addTerm(new DoubleValue(4));
        //
        // addView(e.getEvaluation(ectx).visualize(context));
    }

    Expression d(Number n) {
        return new DoubleValue(n.doubleValue());
    }

    Expression v(String s) {
        return new Variable(s);
    }

    Expression s(Expression... args) {
        Summation e = new Summation();
        for (Expression arg : args)
            e.addTerm(arg);
        return e;
    }

    Expression p(Expression... args) {
        Production e = new Production();
        for (Expression arg : args)
            e.addTerm(arg);
        return e;
    }

    Expression pow(Expression b, Expression p) {
        return new Power(b, p);
    }

    Expression root(Expression b, Expression p) {
        return new Root(b, p);
    }

    Expression neg(Expression o) {
        return new Negation(o);
    }

    Expression frac(Expression p, Expression q) {
        return new Fraction(p, q);
    }

    Expression fracR(int depth) {
        return depth > 0 ? s(ONE, frac(ONE, fracR(depth - 1))) : ONE;
    }

}
