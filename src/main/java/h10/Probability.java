package h10;

/**
 * Represents a probability function that returns {@code true} if the specified probability is reached.
 *
 * @author Nhan Huynh
 */
public interface Probability {

    /**
     * Returns {@code true} if the specified probability is reached.
     *
     * @return {@code true} if the specified probability is reached
     */
    boolean nextBoolean();

}
