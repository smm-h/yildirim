package ir.smmh.jile.math.relations;

public interface BinaryRelation<A, B> extends FinitaryRelation {

    @Override
    default Integer getArity() {
        return 2;
    }

    boolean holds(A a, B b);

    @SuppressWarnings("unchecked")
    default boolean holdsUnchecked(Object a, Object b) throws ClassCastException {
        return holds((A) a, (B) b);
    }
}
