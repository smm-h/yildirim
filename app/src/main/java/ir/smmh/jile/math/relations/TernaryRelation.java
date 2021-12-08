package ir.smmh.jile.math.relations;

public interface TernaryRelation<A, B, C> extends FinitaryRelation {

    @Override
    default Integer getArity() {
        return 2;
    }

    boolean holds(A a, B b, C c);

    @SuppressWarnings("unchecked")
    default boolean holdsUnchecked(Object a, Object b, Object c) throws ClassCastException {
        return holds((A) a, (B) b, (C) c);
    }
}
