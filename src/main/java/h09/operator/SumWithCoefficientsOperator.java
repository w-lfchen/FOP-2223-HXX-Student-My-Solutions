package h09.operator;

import h09.basic.BasicBinaryOperations;

import java.util.function.BinaryOperator;

public class SumWithCoefficientsOperator<X, Y> implements BinaryOperator<X> {
    private final Y coeff1;
    private final Y coeff2;
    private final BasicBinaryOperations<X, Y> op;

    public SumWithCoefficientsOperator(BasicBinaryOperations<X, Y> op, Y coeff1, Y coeff2) {
        this.op = op;
        this.coeff1 = coeff1;
        this.coeff2 = coeff2;
    }

    @Override
    public X apply(X left, X right) {
        // we now need to rely on a correct implementation of BasicBinaryOperations, where the add and mul methods
        // do whatever is considered addition and multiplication with the actual types.
        return op.add(op.mul(left, coeff1), op.mul(right, coeff2));
    }
}
/* voluntary questions:
the restrictions are different because the classes do different things.
 */
