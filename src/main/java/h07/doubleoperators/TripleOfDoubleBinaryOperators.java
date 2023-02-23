package h07.doubleoperators;

import java.util.function.DoubleBinaryOperator;

/**
 * Class encapsulating three DoubleBinaryOperators.
 */
public class TripleOfDoubleBinaryOperators {

    /**
     * First operator.
     */
    public DoubleBinaryOperator operator1;

    /**
     * Second operator.
     */
    public DoubleBinaryOperator operator2;

    /**
     * Third operator.
     */
    public DoubleBinaryOperator operator3;

    /**
     * Constructor initializing all the operators.
     *
     * @param operator1 The first operator.
     * @param operator2 The second operator.
     * @param operator3 The third operator.
     */
    public TripleOfDoubleBinaryOperators(DoubleBinaryOperator operator1, DoubleBinaryOperator operator2, DoubleBinaryOperator operator3) {
        this.operator1 = operator1;
        this.operator2 = operator2;
        this.operator3 = operator3;
    }

}
