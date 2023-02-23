package h10;

import org.jetbrains.annotations.Nullable;

import java.util.Objects;

/**
 * An instance of this class represents an express node of a {@link SkipList} which allows access to the upper and lower
 * levels and previous nodes of the skip list.
 *
 * <p>Example:
 * <pre>{@code
 *         1
 *         |
 * node -> 1
 *         |
 *         1 -- 2
 * }</pre>
 *
 * <p>Code:
 * <pre>{@code
 * ExpressNode<Integer> node = new ExpressNode<>();
 * node.value = 1;
 *
 * // Upper level
 * ListItem<ExpressNode> upperLevel = new ListItem<>();
 * upperLevel.key = new ExpressNode<>();
 * upperLevel.key.value = 1; // First element
 * node.down = lowerLevel;
 *
 * // lower level
 * ListItem<ExpressNode> lowerLevel = new ListItem<>();
 * lowerLevel.key = new ExpressNode<>();
 * lowerLevel.key.value = 1; // First element
 * lowerLevel.next.key = new ExpressNode<>();
 * lowerLevel.next.key.value = 2; // Second element
 * lowerLevel.next.prev = lowerLevel;
 * node.up = upperLevel;
 * }</pre>
 *
 * @param <T> type of value
 *
 * @author Nhan Huynh
 */
public class ExpressNode<T> {

    /**
     * The value of this express node.
     */
    public T value;

    /**
     * The predecessor node of this node.
     */
    public @Nullable ListItem<ExpressNode<T>> prev;

    /**
     * The predecessor node to the previous level of this node.
     */
    public @Nullable ListItem<ExpressNode<T>> up;

    /**
     * The successor node to the next level of this node.
     */
    public @Nullable ListItem<ExpressNode<T>> down;

    /**
     * Constructs and initializes an empty express node.
     */
    public ExpressNode() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ExpressNode<?> other)) {
            return false;
        }
        return Objects.equals(value, other.value)
            && Objects.equals(prev, other.prev)
            && Objects.equals(up, other.up)
            && Objects.equals(down, other.down);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, hashCode(prev), hashCode(up), hashCode(down));
    }

    /**
     * Returns the hash code for the specified list item sequence using the following formula:
     * <pre>{@code
     *    for each value in the sequence:
     *      31 * hashCode + (value == null ? 0 : value.hashCode())
     *    }</pre>
     *
     * @param item the list item sequence to compute the hash code for
     *
     * @return the hash code for the specified list item sequence
     */
    private int hashCode(ListItem<ExpressNode<T>> item) {
        int hashCode = 1;
        for (ListItem<ExpressNode<T>> current = item; current != null; current = current.next) {
            hashCode = 31 * hashCode + ((current.key == null || current.key.value == null) ?
                0 : current.key.value.hashCode());
        }
        return hashCode;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

}
