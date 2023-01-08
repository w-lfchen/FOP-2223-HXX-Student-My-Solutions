package h08.calculation;

public class ArrayCalculatorWithRuntimeExceptions implements ArrayCalculator {
    /**
     * Adds up all double values in an array of arrays of double values. The double
     * values may not be negative or bigger than max.
     *
     * @param theArray The primary array containing the secondary arrays with their
     *                 double values
     * @param max      The maximum value any double value contained in the arrays may
     *                 have
     * @return double The sum of all double values contained on all secondary
     * arrays.
     */
    @Override
    public double addUp(double[][] theArray, double max) {
        if (theArray == null) {
            throw new NullPointerException("Primary array is void!"); // the first check
        }
        for (int i = 0; i < theArray.length; i++) { // check whether secondary arrays are null
            if (theArray[i] == null) { // counting upwards because we should use the smallest index
                throw new NullPointerException("Secondary array at " + i + " is void!");
            }
        }
        if (max < 0) { // first two cases did not happen if this statement is reached
            throw new ArithmeticException("Upper bound is negative!");
        }
        double sum = 0;
        for (int i = 0; i < theArray.length; i++) {
            for (int j = 0; j < theArray[i].length; j++) { // I think what this does should be clear to everyone by now
                if (theArray[i][j] > max || theArray[i][j] < 0) { // check for range with an or, could also be refactored with an and, I think DeMorgan was the guy who said that
                    throw new ArithmeticException("Value at (" + i + "," + j + ") is not in range!");
                } else { // this else is unnecessary and I watched a YouTube video explaining to me that this is bad practice.
                    sum += theArray[i][j]; // maybe I should rethink the way my code is formatted...
                } // but the else makes me feel good
            }
        }
        return sum; // we reach this statement, or we don't because an exception was thrown.
    }
}
/* voluntary question:
we do not need to declare that RuntimeExceptions are thrown as they are not checked for at the time of compilation.
this is intentional behaviour, see chapter 5 starting from page 100 for more information on this topic.
 */
