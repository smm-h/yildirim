package ir.smmh.gebra.expressions;

import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import ir.smmh.gebra.Expression;
import ir.smmh.gebra.Gebra;
import ir.smmh.gebra.VisualizationContext;

public class LinearExpressionView extends LinearLayout implements Expression.ExpressionView {

    private final VisualizationContext vctx;

    public LinearExpressionView(final VisualizationContext vctx) {
        super(vctx.androidContext);
        this.vctx = vctx;
        setGravity(Gravity.CENTER);
        setLayoutParams(Gebra.WRAP_BOTH);
    }

    @Override
    public View getView() {
        return this;
    }

    @Override
    public VisualizationContext getVisualizationContext() {
        return vctx;
    }
}
