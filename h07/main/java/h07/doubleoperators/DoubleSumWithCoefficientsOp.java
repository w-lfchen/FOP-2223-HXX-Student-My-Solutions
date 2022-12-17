package h07.doubleoperators;

import java.util.function.DoubleBinaryOperator;

/**
 * Class implementing the interface DoubleBinaryOperator, such that
 * the overwritten method applyAsDouble sums the products of the in
 * the class encapsulated coefficients with the given parameters.
 */
public class DoubleSumWithCoefficientsOp implements DoubleBinaryOperator {

    /**
     * First coefficient.
     */
    private final double COEFF_1;

    /**
     * Second coefficient.
     */
    private final double COEFF_2;

    /**
     * Constructor initializes the two coefficients.
     *
     * @param coeff1 The first coefficient.
     * @param coeff2 The second coefficient.
     */
    public DoubleSumWithCoefficientsOp(double coeff1, double coeff2) {
        // Assign first parameter to first coefficient
        this.COEFF_1 = coeff1;

        // Assign second parameter to second coefficient
        this.COEFF_2 = coeff2;
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
        return ((left * COEFF_1) + (right * COEFF_2));
    }
}
/* voluntary questions:
this operator is indeed commutative, as + is.

if the coefficients are equal, one could express the result as coeff * (left + right).
*/
