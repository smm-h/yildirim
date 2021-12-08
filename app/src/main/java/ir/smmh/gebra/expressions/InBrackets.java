package ir.smmh.gebra.expressions;

import ir.smmh.gebra.Expression;
import ir.smmh.gebra.Gebra;
import ir.smmh.gebra.VisualizationContext;
import ir.smmh.gebra.expressionviews.Bracket;
import ir.smmh.gebra.expressionviews.InBracketsView;

public class InBrackets extends Wrapped {

    private final Bracket bracket;

    public InBrackets(final Expression core, final Gebra.Effect effect, final Bracket bracket) {
        super(core, effect);
        this.bracket = bracket;
    }

    @Override
    public ExpressionView visualize(final VisualizationContext vctx) {
        return new InBracketsView(vctx, getCore(), bracket);
    }

}
