package ir.smmh.jile.math.settheory;

public interface OrderedSpecificSet<T> extends OrderedSet, SpecificSet<T> {

    Integer findOrdinalOfElement(T element);

    @Override
    T getElementAt(int ordinal);
}
