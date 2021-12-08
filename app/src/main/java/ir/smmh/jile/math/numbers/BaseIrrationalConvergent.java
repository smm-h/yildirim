package ir.smmh.jile.math.numbers;

public abstract class BaseIrrationalConvergent extends BaseNumber implements IrrationalConvergent {

    private final double convergence;

    public BaseIrrationalConvergent(int depth) {
        convergence = converge(depth);
    }

    @Override
    public double approximate() {
        return convergence;
    }

    @Override
    public boolean isPrecise() {
        return false;
    }
}
