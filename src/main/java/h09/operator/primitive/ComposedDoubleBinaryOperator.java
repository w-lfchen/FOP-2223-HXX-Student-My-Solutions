package h09.operator.primitive;

import java.util.function.DoubleBinaryOperator;

/**
 * Class implementing the interface DoubleBinaryOperator, such that
 * the overwritten method applyAsDouble first applies two operators
 * to the given parameters and returns the application of a third
 * operator to the intermediate results.
 */
public class ComposedDoubleBinaryOperator implements DoubleBinaryOperator {

    /**
     * First operator.
     */
    private final DoubleBinaryOperator op1;

    /**
     * Second operator.
     */
    private final DoubleBinaryOperator op2;

    /**
     * Third operator.
     */
    private final DoubleBinaryOperator op3;

    /**
     * Constructor initializes the three operators.
     *
     * @param op1 First operator.
     * @param op2 Second operator.
     * @param op3 Third operator.
     */
    public ComposedDoubleBinaryOperator(DoubleBinaryOperator op1, DoubleBinaryOperator op2, DoubleBinaryOperator op3) {
        // Assign first parameter to first operator
        this.op1 = op1;

        // Assign second parameter to second operator
        this.op2 = op2;

        // Assign third parameter to third operator
        this.op3 = op3;
    }

    /**
     * First applies the first and the second operator to the given parameters.
     * Then applies the third operator to the result of the application of the
     * first two operators.
     *
     * @param left  The first parameter.
     * @param right The second parameter.
     * @return Application of the third operator on the results of the
     * first and second operator.
     */
    @Override
    public double applyAsDouble(double left, double right) {
        // Apply first operator to given parameters
        double result1 = op1.applyAsDouble(left, right);

        // Apply second operator to given parameters
        double result2 = op2.applyAsDouble(left, right);

        // Return application of third operator to the intermediate results
        return op3.applyAsDouble(result1, result2);
    }
}
