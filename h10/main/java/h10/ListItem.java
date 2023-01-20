package h10;

import org.jetbrains.annotations.Nullable;

import java.util.Objects;

/**
 * An instance of this class represents a sequence of list items (wrapped elements) of an ordered sequence that can be addressed
 * by means of a reference to the direct successor.
 *
 * <p>Example:
 * <ul>
 * <li> List containing the elements: 1, 2, 3</li>
 * </ul>
 *
 * <pre>{@code
 *    ListItem<Integer> head = new ListItem<>();
 *    head.key = 1; // First element of the list
 *    head.next = new ListItem<>();
 *    head.next.key = 2; // Second element of the list
 *    head.next.next = new ListItem<>();
 *    head.next.next.key = 3; // Third element of the list
 * }</pre>
 *
 * @param <T> type of key
 *
 * @author Nhan Huynh
 */
public class ListItem<T> {

    /**
     * The value of this list item.
     */
    public T key;

    /**
     * The successor node of this list item.
     */
    public @Nullable ListItem<T> next;

    /**
     * Constructs and initializes an empty list item.
     */
    public ListItem() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        // instance of
        if (!(o instanceof ListItem<?> other)) {
            return false;
        }
        return Objects.equals(key, other.key) && Objects.equals(next, other.next);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, next);
    }

    @Override
    public String toString() {
        return "a ListItem with key: " + key + " and next: " + (next == null ? "null" : next.key);
    }

}
