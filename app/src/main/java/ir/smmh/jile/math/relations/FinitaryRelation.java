package ir.smmh.jile.math.relations;

import ir.smmh.jile.math.tuples.FiniteTuple;

public interface FinitaryRelation extends Relation {
    @Override
    default boolean isFinite() {
        return true;
    }

    @Override
    default boolean isFinitary() {
        return true;
    }

    @Override
    default boolean contains(FiniteTuple tuple) {
        return holdsUnchecked(tuple.toArray());
    }

    default boolean holdsUnchecked(Object... objects) throws ClassCastException {
        if (getArity() == objects.length) {
            switch (getArity()) {
                case 0:
                    return ((NullaryRelation) this).holdsUnchecked();
                case 1:
                    return ((UnaryRelation<?>) this).holdsUnchecked(objects[0]);
                case 2:
                    return ((BinaryRelation<?, ?>) this).holdsUnchecked(objects[0], objects[1]);
                case 3:
                    return ((TernaryRelation<?, ?, ?>) this).holdsUnchecked(objects[0], objects[1], objects[2]);
                default:
                    throw new UnsupportedOperationException("undefined arity");
            }
        } else {
            throw new UnsupportedOperationException("wrong number of arguments provided");
        }
    }
}
