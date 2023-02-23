package h09.operator.primitive;

import java.util.function.DoubleBinaryOperator;

/**
 * Class implementing the interface DoubleBinaryOperator, such that
 * the overwritten method applyAsDouble sums the products of the in
 * the class encapsulated coefficients with the given parameters.
 */
public class DoubleSumWithCoefficientsOperator implements DoubleBinaryOperator {

    /**
     * First coefficient.
     */
    private final double coeff1;

    /**
     * Second coefficient.
     */
    private final double coeff2;

    /**
     * Constructor initializes the two coefficients.
     *
     * @param coeff1 The first coefficient.
     * @param coeff2 The second coefficient.
     */
    public DoubleSumWithCoefficientsOperator(double coeff1, double coeff2) {
        // Assign first parameter to first coefficient
        this.coeff1 = coeff1;

        // Assign second parameter to second coefficient
        this.coeff2 = coeff2;
    }

    /**
     * Returns the sum of the first parameter multiplied by the first coefficient
     * with the second parameter multiplied by the second coefficient.
     *
     * @param left  The first parameter.
     * @param right The second parameter.
     * @return The sum of the products.
     */
    @Override
    public double applyAsDouble(double left, double right) {
        // Multiply first coefficient with first parameter and add it to the product
        // of the second coefficient with the second parameter
        return left * coeff1 + right * coeff2;
    }
}
