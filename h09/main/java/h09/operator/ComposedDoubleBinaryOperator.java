package h09.operator;

import java.util.function.BinaryOperator;

public class ComposedDoubleBinaryOperator implements BinaryOperator<Double> {
    private final BinaryOperator<Double> op1;
    private final BinaryOperator<Double> op2;
    private final BinaryOperator<Double> op3;

    public ComposedDoubleBinaryOperator(BinaryOperator<Double> op1, BinaryOperator<Double> op2, BinaryOperator<Double> op3) {
        this.op1 = op1;
        this.op2 = op2;
        this.op3 = op3;
    }

    @Override
    public Double apply(Double left, Double right) {
        return op3.apply(op1.apply(left, right), op2.apply(left, right));
    }
}
// most of this is simply copied from primitive/ComposedDoubleBinaryOperator.
