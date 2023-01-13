package h09.operator;

import java.util.function.BinaryOperator;

public class MaxOfTwoOperator<T extends Comparable<? super T>> implements BinaryOperator<T> {
    @Override
    public T apply(T left, T right) {
        // if left.compareTo returns a value greater than 0, left is considered to be greater than whatever it is compared to.
        // If the number is negative, right is considered to be greater.
        // If the comparison returns 0, it does not matter which one we return as they are considered equal.
        return left.compareTo(right) > 0 ? left : right;
    }
}
/* voluntary questions:
If we limit T to number, we cannot compare other things one might consider to be comparable.
If we don't limit T, we can compare anything as long as we define how to compare it.
 */
