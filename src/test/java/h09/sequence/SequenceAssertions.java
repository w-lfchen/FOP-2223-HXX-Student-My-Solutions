package h09.sequence;

import org.junit.jupiter.api.Assertions;

/**
 * Assertions for sequences.
 */
public class SequenceAssertions {

    /**
     * Asserts that the actual sequence equals the expected sequence.
     *
     * @param expected the expected sequence
     * @param actual   the actual sequence
     */
    public void assertSequenceEquals(Sequence<?> expected, Sequence<?> actual) {
        var expectedIterator = expected.iterator();
        var actualIterator = actual.iterator();
        int i = 0;
        while (expectedIterator.hasNext() && actualIterator.hasNext()) {
            var expectedElement = expectedIterator.next();
            var actualElement = actualIterator.next();
            Assertions.assertEquals(expectedElement, actualElement, String.format("Element at index %d is not equal", i));
            i++;
        }
        if (expectedIterator.hasNext()) {
            Assertions.fail(String.format("Expected more elements, actual sequence ended at index %d", i));
        }
        if (actualIterator.hasNext()) {
            throw new AssertionError(String.format("Expected less elements, actual sequence should have ended at index %d", i));
        }
    }

    /**
     * Asserts that the actual sequence equals the expected sequence, supporting deep equality.
     *
     * @param expected the expected sequence
     * @param actual   the actual sequence
     */
    public void assertDeepSequenceEquals(Sequence<?> expected, Sequence<?> actual) {
        var expectedIterator = expected.iterator();
        var actualIterator = actual.iterator();
        int i = 0;
        while (expectedIterator.hasNext() && actualIterator.hasNext()) {
            var expectedElement = expectedIterator.next();
            var actualElement = actualIterator.next();
            if (expectedElement instanceof Sequence && actualElement instanceof Sequence) {
                assertDeepSequenceEquals((Sequence<?>) expectedElement, (Sequence<?>) actualElement);
            } else {
                Assertions.assertEquals(expectedElement, actualElement, String.format("Element at index %d is not equal", i));
            }
            i++;
        }
        if (expectedIterator.hasNext()) {
            Assertions.fail(String.format("Expected more elements, actual sequence ended at index %d", i));
        }
        if (actualIterator.hasNext()) {
            throw new AssertionError(String.format("Expected less elements, actual sequence should have ended at index %d", i));
        }
    }

    public void assertSequenceEmpty(Sequence<?> sequence) {
        Assertions.assertFalse(sequence.iterator().hasNext());
    }

    public void assertSequenceNotEmpty(Sequence<?> sequence) {
        Assertions.assertTrue(sequence.iterator().hasNext());
    }

    public void assertSequenceSize(int expectedSize, Sequence<?> sequence) {
        var iterator = sequence.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            iterator.next();
            i++;
        }
        Assertions.assertEquals(expectedSize, i);
    }

    public void assertSequenceContains(Object expectedElement, Sequence<?> sequence) {
        var iterator = sequence.iterator();
        while (iterator.hasNext()) {
            var element = iterator.next();
            if (expectedElement.equals(element)) {
                return;
            }
        }
        Assertions.fail(String.format("Sequence does not contain element %s", expectedElement));
    }

    public void assertSequenceContainsAll(Sequence<?> expectedElements, Sequence<?> sequence) {
        var iterator = expectedElements.iterator();
        while (iterator.hasNext()) {
            var element = iterator.next();
            assertSequenceContains(element, sequence);
        }
    }
}
