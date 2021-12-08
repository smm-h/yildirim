package ir.smmh.gebra;

import ir.smmh.jile.common.q.Quality;

public class Operation {


    private final Notation defaultNotation;
    private final int arity;
    private final Type[] inputType;
    private final Type outputType;

    private Operation(final Notation defaultNotation, final int arity, final Type[] inputType, final Type outputType) {
        this.defaultNotation = defaultNotation;
        this.arity = arity;
        this.inputType = inputType;
        this.outputType = outputType;
    }

    public static class Nullary extends Operation {
        private Nullary(final Notation defaultNotation, final Type outputType) {
            super(defaultNotation, 0, new Type[0], outputType);
        }
    }

    public static class Unary extends Operation {

        private final boolean isTotal;

        private Unary(final Notation defaultNotation, final Type inputType, final Type outputType, final boolean isTotal) {
            super(defaultNotation, 1, new Type[] {inputType}, outputType);
            this.isTotal = isTotal;
        }
    }

    public static class Binary extends Operation {

        private final boolean isTotal;
        private final boolean isAssociative;
        private final boolean isCommutative;

        private Binary(final Notation defaultNotation, final Type lhsType, final Type rhsType, final Type outputType, final boolean isTotal, final boolean isAssociative, final boolean isCommutative) {
            super(defaultNotation, 2, new Type[] {lhsType, rhsType}, outputType);
            this.isTotal = isTotal;
            this.isAssociative = isAssociative;
            this.isCommutative = isCommutative;
        }
    }

    public static class Qualities {
        private Qualities() {
        }

        public static Quality<Operation> TOTAL = op -> op.isTotal;
    }
}
