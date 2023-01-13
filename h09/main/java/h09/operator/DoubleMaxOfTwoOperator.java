package h09.operator;

import java.util.function.BinaryOperator;

public class DoubleMaxOfTwoOperator implements BinaryOperator<Double> {
    @Override
    public Double apply(Double left, Double right) {
        return Math.max(left, right);
    }
}
// most of this is simply copied from primitive/DoubleMaxOfTwoOperator.
