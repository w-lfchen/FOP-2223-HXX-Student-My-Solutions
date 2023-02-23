package h07.doubleoperators;

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
    private final DoubleBinaryOperator OP_1;

    /**
     * Second operator.
     */
    private final DoubleBinaryOperator OP_2;

    /**
     * Third operator.
     */
    private final DoubleBinaryOperator OP_3;

    /**
     * Constructor initializes the three operators.
     *
     * @param op1 First operator.
     * @param op2 Second operator.
     * @param op3 Third operator.
     */
    public ComposedDoubleBinaryOperator(DoubleBinaryOperator op1, DoubleBinaryOperator op2, DoubleBinaryOperator op3) {
        // Assign first parameter to first operator
        this.OP_1 = op1;

        // Assign second parameter to second operator
        this.OP_2 = op2;

        // Assign third parameter to third operator
        this.OP_3 = op3;
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
        return OP_3.applyAsDouble(OP_1.applyAsDouble(left, right), OP_2.applyAsDouble(left, right));
    }
}
/* voluntary questions:
the commutativity depends on the operators used.
 */
