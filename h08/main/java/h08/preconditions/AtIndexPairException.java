package h08.preconditions;

/**
 * Thrown when any value in a secondary array is outside the valid range.
 */
public class AtIndexPairException extends Exception {
    public AtIndexPairException(int i, int j) {
        super("Index: (" + i + "," + j + ")"); // and once more, though here one might be able to make the String using other methods as well
    } // this is the most straight-forward and works, so I am happy with it, also I have yet to look at the StringBuilder
}
