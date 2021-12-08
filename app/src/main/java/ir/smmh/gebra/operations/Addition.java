package ir.smmh.gebra.operations;

import androidx.annotation.NonNull;

import ir.smmh.gebra.Expression;
import ir.smmh.gebra.Type;
import ir.smmh.gebra.errors.InferenceError;
import ir.smmh.gebra.errors.OperationError;
import ir.smmh.gebra.ontologies.Numbers;

public class Addition {
    public static Type infer(Type t1, Type t2) throws InferenceError {
        if (t1.isq(Numbers.NUMBER) && t2.isq(Numbers.NUMBER)) {
            return t1.smallestCommonSupertype(t2);
            // } else if (t1.isq(Dimensions.TENSOR) && t2.isq(Dimensions.TENSOR)) {
            //     return t1.smallestCommonSupertype(t2);
            // } else if (t1.isq(Structures.SEQUENCE) && t2.isq(Structures.SEQUENCE)) {
            //     return t1.smallestCommonSupertype(t2);
        } else {
            throw new InferenceError(t1 + "+" + t2);
        }
    }

    @NonNull
    public static Expression operate(@NonNull final Type inferredType, @NonNull final Expression p1, @NonNull final Expression p2) throws OperationError {
        if (inferredType.isq(Numbers.RATIONAL) && !inferredType.isq(Numbers.INTEGER)) {
            // rational addition
            // return Expression.of(((Fraction) p1).add( (Fraction) p2));
        } else if (inferredType.isq(Numbers.REAL)) {
            // real addition
            return Expression.of((double) p1 + (double) p2);
        } else if (inferredType.isq(Numbers.COMPLEX)) {
            return Expression.of((double) p1 + (double) p2);
        } else {
            throw new OperationError("TODO");
        }
        // TODO tensor addition
        // TODO concatenation
        // TODO complex addition
    }
}
