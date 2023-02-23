package h08;

import h08.calculation.ArrayCalculatorWithPreconditions;
import h08.preconditions.AtIndexException;
import h08.preconditions.AtIndexPairException;
import h08.preconditions.WrongNumberException;

/**
 * Main entry point in executing the program.
 */
public class Main {

    /**
     * Main entry point in executing the program.
     *
     * @param args program arguments, currently ignored
     */
    public static void main(String[] args) {
        var array1 = new double[][]{new double[]{42}, null, new double[]{42}};
        print(array1, 50);

        var array2 = new double[][]{new double[]{1}, new double[]{1, 2}, new double[]{1, -2, 3}, new double[]{1, 2, 3, 4}};
        print(array2, 10);

        var array3 = new double[][]{new double[]{5}, new double[]{12, 42}};
        print(array3, -4);
        print(array3, 50);
        print(null, 0);
    }

    /**
     * Adds up all double values in an array of arrays of double values and prints
     * the sum to the console.
     * If an exception occurs during calculation, an error message will be printed
     * instead. The double
     * values may not be negative or bigger than max.
     *
     * @param theArray The array of arrays of double values to be added up.
     * @param max      The maximum value any double value may have.
     */
    public static void print(double[][] theArray, double max) {
        ArrayCalculatorWithPreconditions calc = new ArrayCalculatorWithPreconditions(); // addUp is not static, so we need an object to call the method
        try { // I wonder whether a lambda expression is an option
            System.out.println("Sum: " + calc.addUp(theArray, max)); // this println will not happen if an exception is thrown.
        } catch (AtIndexException | AtIndexPairException exc) { // catch these two and act accordingly
            System.out.println("Bad array: " + exc.getMessage());
        } catch (WrongNumberException wrongNumberException) {
            System.out.println("Bad max value: " + wrongNumberException.getMessage());
        } // catch this specific one too, we are instructed to catch nothing else
    }
}
/* voluntary questions:
We don't have to catch the ArrayIsNullException since it extends RuntimeException.
However, our execution will terminate, should we happen to come across one.
 */
