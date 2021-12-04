package ir.smmh.gebra;

import android.content.Context;
import android.widget.Space;

import ir.smmh.fy.Util;
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

    final EvaluationContext ectx;
    final VisualizationContext vctx;

    public GebraTabView(final Context context) {
        super(context);
        ectx = new EvaluationContext.Default();
        vctx = new VisualizationContext(context, 1);

        add("Welcome to Gebra!");
        add(s(d(4), frac(d(10), d(5))));
        add(fracR(5));
        add(s(p(d(2), pow(v("alpha"), s(d(2), v("pi")))), neg(p(d(3), v("beta"))), v("epsilon")));
        add(root(d(25), d(2)));
        add(s(d(1), d(2), d(3), d(4)));
        add(p(d(1), d(2), d(3), d(4)));
        add("More coming soon!");
    }

    void add(String text) {
        addView(new Text(vctx, text));
    }

    void add(Expression e) {
        Space p = new Space(getContext());
        Space q = new Space(getContext());
        p.setMinimumHeight(Util.dipToPixel(8));
        q.setMinimumHeight(Util.dipToPixel(8));
        addView(p);
        addView(e.getEvaluation(ectx).visualize(vctx));
        addView(q);
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
        return depth > 0 ? s(Gebra._1, frac(Gebra._1, fracR(depth - 1))) : Gebra._1;
    }

}
