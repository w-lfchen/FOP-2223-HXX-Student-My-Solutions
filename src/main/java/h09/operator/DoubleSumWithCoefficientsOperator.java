package h09.operator;

import java.util.function.BinaryOperator;

public class DoubleSumWithCoefficientsOperator implements BinaryOperator<Double> {
    private final Double coeff1;
    private final Double coeff2;

    public DoubleSumWithCoefficientsOperator(Double coeff1, Double coeff2) {
        this.coeff1 = coeff1;
        this.coeff2 = coeff2;
    }

    @Override
    public Double apply(Double left, Double right) {
        return left * coeff1 + right * coeff2;
    }
}
// most of this is simply copied from primitive/DoubleSumWithCoefficientsOperator.
