package h10;

import org.jetbrains.annotations.Nullable;

import java.util.Comparator;
import java.util.Objects;

/**
 * Represents a skip list. A skip list is a randomized data structure that allows fast access to elements in a sorted
 * list.
 *
 * <p>Its a two-dimensional linked list where the lowest level contains all elements and each higher level contains a
 * subset of the elements of the lower level.
 *
 * <p>Example:
 * <pre>{@code
 *  head ----------------- 47
 *   |                     |
 *   |------ 12 --------- 47
 *   |       |            |
 *   |------ 12 -------- 47 -- 72
 *   |       |           |    |
 *   | 5 -- 12 -- 17 -- 47 -- 72 -- 98
 * }</pre>
 *
 * @param <T> the type of the elements in this list
 * @author Nhan Huynh
 * @see <a href="https://en.wikipedia.org/wiki/Skip_list">Skip list</a>
 */
public class SkipList<T> {

    /**
     * The comparator used to maintain order in this list.
     */
    protected final Comparator<? super T> cmp;

    /**
     * The maximum height of the skip list.
     */
    final int maxHeight;

    /**
     * The probability function used to determine if a node should be added on another level.
     */
    private Probability probability;

    /**
     * The head of the skip list.
     */
    @Nullable ListItem<ExpressNode<T>> head;

    /**
     * The current height of the skip list.
     */
    int height = 0;

    /**
     * The number of items in the skip list.
     */
    int size = 0;

    /**
     * Constructs and initializes an empty skip list without the probability to add elements on higher levels.
     *
     * @param cmp       the comparator used to maintain order in this list
     * @param maxHeight the maximum height of the skip list
     */
    public SkipList(Comparator<? super T> cmp, int maxHeight) {
        this(cmp, maxHeight, DEFAULT);
    }

    /**
     * Constructs and initializes an empty skip list.
     *
     * @param cmp         the comparator used to maintain order in this list
     * @param maxHeight   the maximum height of the skip list
     * @param probability the probability function used to determine if a node should be added on another level
     */
    public SkipList(Comparator<? super T> cmp, int maxHeight, Probability probability) {
        this.cmp = cmp;
        this.maxHeight = maxHeight;
        this.probability = probability;
    }

    /**
     * Returns the current height of this skip list.
     *
     * @return the current height of this skip list
     */
    public int getHeight() {
        return height;
    }

    /**
     * Returns the probability function used to determine if a node should be added on another level.
     *
     * @return the probability function used to determine if a node should be added on another leve
     */
    public Probability getProbability() {
        return probability;
    }

    /**
     * Sets the probability function used to determine if a node should be added on another level.
     *
     * @param probability the probability function
     */
    public void setProbability(Probability probability) {
        this.probability = probability;
    }

    /**
     * Returns the number of items in this skip list.
     *
     * @return the number of items in this skip list
     */
    public int size() {
        return size;
    }

    /**
     * Returns {@code true} if this list contains the specified element.
     *
     * @param key the element whose presence in this list is to be tested
     * @return {@code true} if this list contains the specified element
     */
    public boolean contains(T key) {
        ListItem<ExpressNode<T>> walker = head;
        while (walker != null) {
            // if there is no next element to compare to, we can move down immediately
            if (walker.next == null) {
                walker = walker.key.down;
            } else {
                // since walker.next is not null, we can initiate a comparison.
                // we save the value to minimise the amount of invocations of cmp.compare
                int comparison = cmp.compare(walker.next.key.value, key);
                // if the key is found, we just return true immediately
                if (comparison == 0) {
                    return true;
                    // if the next node is considered greater, we move down
                } else if (comparison > 0) {
                    walker = walker.key.down;
                    // now comparison is negative, which means that our key is considered to be bigger than walker.next
                } else {
                    // this means that we move forwards instead of downwards
                    walker = walker.next;
                }
            }
        }
        // if the walker is null, we exit the loop.
        // the walker can only be null if it moved down, since we don't go forwards if walker.next is null.
        // if we still did not find it on the lowest level, the element is not in the list.
        return false;
    }

    /**
     * Adds the specified element to this list. The element will be added on the highest floor of the skip list and on
     * the next levels if the probability function returns {@code true}.
     *
     * @param key the element to be added
     */
    public void add(T key) {
        // create a PreviousList object to save the previous entries that we need to be able to reference later
        PreviousList previousList = new PreviousList();
        ListItem<ExpressNode<T>> walker = head;
        // this is equal to calling isEmpty() or head == null
        if (walker != null) {
            // the purpose of this loop is to get the walker to the lowest level where we still might not be in the correct position
            // we use the algorithm from contains with some slight modifications
            while (walker.key.down != null) {
                if (walker.next == null) {
                    // we save the current position before moving down, because this is the node we'll need to reference
                    previousList.add(walker);
                    walker = walker.key.down;
                } else {
                    int comparison = cmp.compare(walker.next.key.value, key);
                    if (comparison > 0) {
                        previousList.add(walker);
                        walker = walker.key.down;
                    } else {
                        walker = walker.next;
                    }
                }
            }
            // now we should be on the lowest level and just need to check whether the walker is in the correct position
            while (walker.next != null && cmp.compare(walker.next.key.value, key) <= 0) {
                walker = walker.next;
            }
        } else {
            // since the list is empty, we need to create a new level first by creating a new sentinel node
            head = nodeBuilder(null, null, null, null, null);
            walker = head;
        }
        // now we should be at the lowest level and at the correct position.
        // the walker references the previous node for the lowest level and the bottom node on every other level
        ListItem<ExpressNode<T>> tmp = nodeBuilder(key, walker.next, null, walker, null);
        walker.next = tmp;
        if (tmp.next != null) {
            tmp.next.key.prev = tmp;
        }
        // now for the stacking bit. walker will now reference the node below the new one
        walker = tmp;
        // track the height of the current stack, we already added an element to it
        int stackHeight = 1;
        // this is a safety measure
        boolean nextBooleanWasFalse = false;
        // whenever we went down, we added an element, therefore we can use this to count until we need to create new levels
        while (previousList.hasNext()) {
            // catches cases of nextBoolean being true, then false, then true again
            if (!probability.nextBoolean()) {
                nextBooleanWasFalse = true;
                break;
            } else {
                // create the new node
                ListItem<ExpressNode<T>> previousNode = previousList.next();
                tmp = nodeBuilder(key, previousNode.next, null, previousNode, walker);
                // set references
                previousNode.next = tmp;
                if (tmp.next != null) {
                    tmp.next.key.prev = tmp;
                }
                walker.key.up = tmp;
                // preparations for the next iteration of the loop
                walker = tmp;
                ++stackHeight;
            }
        }
        // now we need to make new levels
        while (!nextBooleanWasFalse && stackHeight < maxHeight && probability.nextBoolean()) {
            // new head sentinel node
            tmp = nodeBuilder(null, null, null, null, head);
            head = tmp;
            // normal node
            tmp = nodeBuilder(key, null, null, head, walker);
            // references
            walker.key.up = tmp;
            head.next = tmp;
            // preparations
            walker = tmp;
            ++stackHeight;
            ++height;
        }
        // once we are done adding everything, we need to adjust the size and height
        height = Math.max(stackHeight, height);
        size++;
    }

    /**
     * Creates a new ListItem for the SkipList from the given parameters.
     *
     * @param value the value attribute of key
     * @param next  the next node
     * @param up    the node above
     * @param prev  the previous node
     * @param down  the node below
     * @return a ListItem with every attribute set according to the parameters.
     */
    private ListItem<ExpressNode<T>> nodeBuilder(T value, ListItem<ExpressNode<T>> next, ListItem<ExpressNode<T>> up, ListItem<ExpressNode<T>> prev, ListItem<ExpressNode<T>> down) {
        ListItem<ExpressNode<T>> item = new ListItem<>();
        item.next = next;
        item.key = new ExpressNode<>();
        item.key.value = value;
        item.key.up = up;
        item.key.prev = prev;
        item.key.down = down;
        return item;
    }

    /**
     * A simple class with the functionality that I need to save my previous nodes.
     */
    private class PreviousList {
        // a list needs a head
        ListItem<ListItem<ExpressNode<T>>> head = null;
        // we need a walker to insert items
        ListItem<ListItem<ExpressNode<T>>> walker = null;

        /**
         * Adds a given key at position 0 to the list.
         *
         * @param key the key to be added
         */

        public void add(ListItem<ExpressNode<T>> key) {
            head = new ListItem<>();
            head.key = key;
            head.next = walker;
            walker = head;
        }

        /**
         * Returns the next element of the list. Is intended to be used until the list is empty or no longer needed.
         *
         * @return the next entry
         */
        public ListItem<ExpressNode<T>> next() {
            ListItem<ListItem<ExpressNode<T>>> tmp = walker;
            walker = walker.next;
            return tmp.key;
        }

        /**
         * Returns whether a next element is in the list
         *
         * @return whether a next element is in the list
         */
        public boolean hasNext() {
            return walker != null;
        }
    }

    /**
     * Removes the first occurrence of the specified element from this list, if it is present. The element will be
     * removed from all levels.
     *
     * @param key the element to be removed from this list, if present
     */
    public void remove(T key) {
        ListItem<ExpressNode<T>> walker = head;
        // the first element is the highest one
        while (walker != null) {
            if (walker.next == null) {
                walker = walker.key.down;
            } else {
                int comparison = cmp.compare(walker.next.key.value, key);
                if (comparison == 0) {
                    // if we found the element, we go there and break
                    walker = walker.next;
                    break;
                } else if (comparison > 0) {
                    walker = walker.key.down;
                } else {
                    walker = walker.next;
                }
            }
        }
        // now walker should be either at correct position or null.
        if (walker == null) {
            return;
        }
        // removal process, we go down with the walker
        while (walker != null) {
            // if we are the only element in the level, we need to remove it
            if (walker.next == null && walker.key.prev == head && head != null) {
                // remove references to head
                head.next = null;
                if (head.key.down != null) {
                    head.key.down.key.up = null;
                }
                // finish the sentinel node removal
                head = head.key.down;
                walker.key.prev = null;
                --height;
            }
            // remove all references to the node
            if (walker.next != null) {
                walker.next.key.prev = walker.key.prev;
            }
            if (walker.key.prev != null) {
                walker.key.prev.next = walker.next;
            }
            if (walker.key.down != null) {
                walker.key.down.key.up = null;
            }
            // this might be unnecessary but makes me feel safe
            walker.next = null;
            walker.key.prev = null;
            walker.key.up = null;
            walker = walker.key.down;
        }
        // last of all, we adjust size and are done with the removal process
        --size;
    }

    /**
     * Returns {@code true} if this list contains no elements.
     *
     * @return {@code true} if this list contains no elements
     */
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        // Check instance of to avoid ClassCastException
        if (!(o instanceof SkipList<?> other)) {
            return false;
        }
        return height == other.height
            && size == other.size
            && Objects.equals(head, other.head);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maxHeight, head, height, size);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (ListItem<ExpressNode<T>> walker = head; walker != null; walker = walker.key.down) {
            sb.append("[");
            for (ListItem<ExpressNode<T>> walker2 = walker.next; walker2 != null; walker2 = walker2.next) {
                sb.append(walker2.key.value);
                if (walker2.next != null) {
                    sb.append(", ");
                }
            }
            sb.append("]");
            if (walker.key.down != null) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * Default probability of 0%
     */
    public static final Probability DEFAULT = new Probability() {
        @Override
        public boolean nextBoolean() {
            return false;
        }

        @Override
        public String toString() {
            return "0%";
        }
    };

}
