package h08.preconditions;

public class Preconditions {
    private Preconditions() {
    }

    /**
     * Throws an {@link ArrayIsNullException} iff the given primary array is null.
     *
     * @param primaryArray The primary array to be validated
     * @throws ArrayIsNullException Thrown when the given primary array is null
     */
    public static void checkPrimaryArrayNotNull(double[][] primaryArray) throws ArrayIsNullException { // declaring what this method throws isn't strictly necessary.
        if (primaryArray == null) { // I am declaring it to make it clear that this method will throw it, also javadoc says it so why not
            throw new ArrayIsNullException();
        }
    }

    /**
     * Throws an {@link AtIndexException} iff any secondary array in primaryArray is
     * null.
     * The exception is thrown with the smallest index possible, i.e. the first
     * occurrence of null in the primary array triggers the exception.
     *
     * @param primaryArray The primary array containing the secondary arrays to be
     *                     validated
     * @throws AtIndexException Thrown when any secondary array in primaryArray is
     *                          null
     */
    public static void checkSecondaryArraysNotNull(double[][] primaryArray) throws AtIndexException {
        for (int i = 0; i < primaryArray.length; i++) { // unable to use improved for-loop due to the index being relevant
            if (primaryArray[i] == null) {
                throw new AtIndexException(i);
            }
        }
    }

    /**
     * Throws a {@link WrongNumberException} iff the {@code number} is negative.
     *
     * @param number The number to be validated
     * @throws WrongNumberException Thrown when the {@code number} is negative
     */
    public static void checkNumberNotNegative(double number) throws WrongNumberException {
        if (number < 0) { // not much to say here
            throw new WrongNumberException(number);
        }
    }

    /**
     * Throws an {@link AtIndexPairException} iff any value in any secondary array
     * in {@code primaryArray} is negative or bigger than {@code max}. The
     * {@link AtIndexPairException}
     * will be thrown for the smallest indices possible, i.e. for the first
     * occurrence of an invalid value.
     *
     * @param primaryArray The primary array containing the secondary arrays with
     *                     values to be validated
     * @param max          The maximum that may not be exceeded by any value in the
     *                     secondary arrays
     * @throws AtIndexPairException Thrown when any value in any secondary array
     *                              {@code primaryArray} is negative or
     *                              bigger than {@code max}
     */
    public static void checkValuesInRange(double[][] primaryArray, double max) throws AtIndexPairException {
        for (int i = 0; i < primaryArray.length; i++) {
            for (int j = 0; j < primaryArray[i].length; j++) { // once again, the indices are relevant
                if (primaryArray[i][j] > max || primaryArray[i][j] < 0) {
                    throw new AtIndexPairException(i, j);
                }
            }
        }
    }
}
/* voluntary questions:
1. Exceptions have to be handled, otherwise no compilation will happen. This leads to certain things being mandatory.
RuntimeExceptions don't have to be handled but will stop execution if they aren't taken care of.

2. If we include additional information, we make it easier to understand for the end user or other programmers.
But it is extra work...

3. some throw-declarations are mandatory due to the classes being thrown extending Exception and not RuntimeException.
One could declare everything to throw Exception but that would lead to huge ambiguity and confusion, so it is best to be more specific
 */
