package ir.smmh.jile.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("unused")
public interface Convert {

    public static <T> LinkedList<T> Array_to_LinkedList(T[] array) {
        return new LinkedList<T>(Arrays.asList(array));
    }

    public static <T> ArrayList<T> Array_to_ArrayList(T[] array) {
        return new ArrayList<T>(Arrays.asList(array));
    }

    @SuppressWarnings("unchecked")
    public static <T> T[] List_to_Array(List<T> list) {
        T[] array = (T[]) new Object[list.size()];
        int i = 0;
        for (T t : list)
            array[i++] = t;
        return array;
    }

    public static <T> ArrayList<T> List_to_ArrayList(List<T> list) {
        return new ArrayList<T>(list);
    }

    public static <T> String Array_to_String(T[] array) {
        StringBuilder builder = new StringBuilder(array.length * 5 + 2);
        builder.append("[");
        for (int i = 0; i < array.length; i++) {
            if (i != 0)
                builder.append(", ");
            builder.append(array[i]);
        }
        builder.append("]");
        return builder.toString();
    }
}
