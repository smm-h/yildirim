package ir.smmh.gebra.expressions;

import ir.smmh.gebra.Expression;
import ir.smmh.gebra.Gebra;
import ir.smmh.gebra.VisualizationContext;
import ir.smmh.gebra.expressionviews.UnderVinculumView;

public class UnderVinculum extends Wrapped {

    public UnderVinculum(final Expression core, final Gebra.Effect effect) {
        super(core, effect);
    }

    @Override
    public ExpressionView visualize(final VisualizationContext vctx) {
        return new UnderVinculumView(vctx, getCore());
    }

}
