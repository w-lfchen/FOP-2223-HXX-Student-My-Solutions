package h09.operator.primitive;

import java.util.function.DoubleBinaryOperator;

/**
 * Class implementing the interface DoubleBinaryOperator, such that
 * the overwritten method applyAsDouble returns the bigger value of
 * the two given parameters.
 */
public class DoubleMaxOfTwoOperator implements DoubleBinaryOperator {

    /**
     * Returns the bigger value of the two given parameters.
     *
     * @param left  The first parameter.
     * @param right The second parameter.
     * @return The bigger value of the two.
     */
    @Override
    public double applyAsDouble(double left, double right) {
        // Get bigger value with a Math.max()-call
        return Math.max(left, right);

        // Optionally with conditional statement:
        // return left < right ? right : left;
    }
}
