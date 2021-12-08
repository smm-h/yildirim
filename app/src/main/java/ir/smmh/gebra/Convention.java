package ir.smmh.gebra;

public abstract class Convention extends Statement {
    public Convention(final Namespace ns) {
        super(ns);
    }

    @Override
    public StatementView visualize(final VisualizationContext vctx) {
        return null;
    }
}
