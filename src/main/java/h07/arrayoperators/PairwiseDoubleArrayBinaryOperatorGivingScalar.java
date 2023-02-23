package h07.arrayoperators;

import java.util.function.DoubleBinaryOperator;

/**
 * Class implementing the interface DoubleArrayBinaryOperatorGivingArray,
 * such that the overwritten method applyAsDoubleArray returns an array
 * only containing as many elements as the smaller array of the given
 * ones.
 * Applies the operator to the elements in the old arrays and fills the
 * new array with the results.
 */
public class PairwiseDoubleArrayBinaryOperatorGivingScalar implements DoubleArrayBinaryOperatorGivingScalar {

    /**
     * First operator, "Komponentenverknüpfung" (join-fct).
     */
    private final DoubleBinaryOperator OPERATOR_1;

    /**
     * Second operator, "Faltungsoperation" (fold-fct).
     */
    private final DoubleBinaryOperator OPERATOR_2;

    /**
     * Initial value (init).
     */
    private double init;

    /**
     * Constructor initializes the operators ("Komponentenverknüpfung" and "Faltungsoperation")
     * and the initial value (init).
     *
     * @param operator1 The first operator, "Komponentenverknüpfung" (join-fct).
     * @param operator2 The second operator, "Faltungsoperation" (fold-fct).
     * @param d         The initial value (init).
     */
    public PairwiseDoubleArrayBinaryOperatorGivingScalar(DoubleBinaryOperator operator1, DoubleBinaryOperator operator2, double d) {
        // Assign first parameter to first operator
        this.OPERATOR_1 = operator1;

        // Assign second parameter to second operator
        this.OPERATOR_2 = operator2;

        // Assign third parameter to initial value
        this.init = d;
    }

    /**
     * Returns a scalar that is the result of the application of the two operators
     * ("Komponentenverknüpfung" and "Faltungsoperation") on the two given arrays
     * according to the following racket code:
     * <p>
     * ( define (apply lst1 lst2 join-fct fold-fct init )
     * ( cond
     * [ ( or ( empty? lst1 ) ( empty? lst2 ) ) init ]
     * [ else ( fold-fct
     * ( join-fct ( first lst1 ) ( first lst2 ) )
     * ( apply ( rest lst1 ) ( rest lst2 ) join-fct fold-fct init ) ] ) )
     *
     * @param left  The first array.
     * @param right The second array.
     * @return The result of the fold.
     */
    @Override
    public double applyAsDoubleArray(double[] left, double[] right) { // this one is going to be hard to explain if you don't understand the recursion
        int length = Math.min(left.length, right.length); // determine the length. in combination with the loop, this is what cancels the recursion. could be simplified, but is easier to read this way
        double result = init; // I could just use init, but this is easier to understand
        for (int i = length - 1; i >= 0; i--) { // the recursion goes backwards through the list, so we do the same to the array
            result = OPERATOR_2.applyAsDouble(OPERATOR_1.applyAsDouble(left[i], right[i]), result);
        } // this just applies everything. result is basically the return of the recursive call.
        return result;
    }
}
/* voluntary questions:
Right-associativity would be reached by reversing the direction with which we process the array/list.

the second "fold-fct" is not recursion. It is simply the parameter of the recursive call of apply.

I believe the requirements to be met. Examples would be any mathematical operations. I forgot how the fold/join/map
functions worked as I didn't fully pay attention during that lecture, so I apologise for not being able to give a full answer here.
 */
