package ir.smmh.jile.math.plotting;

public interface CartesianEquation {
    double y(double x);

    default CartesianEquation negate() {
        return x -> -y(x);
    }
}
