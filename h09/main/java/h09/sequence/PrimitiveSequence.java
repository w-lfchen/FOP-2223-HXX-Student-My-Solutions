package h09.sequence;

/**
 * Helper methods to create sequences from arrays
 * of primitive types.
 */
public interface PrimitiveSequence {

    static Sequence<Long> of(long... values) {
        final Long[] boxed = new Long[values.length];
        for (int i = 0; i < values.length; i++) {
            boxed[i] = values[i];
        }
        return Sequence.of(boxed);
    }

    static Sequence<Integer> of(int... values) {
        final Integer[] boxed = new Integer[values.length];
        for (int i = 0; i < values.length; i++) {
            boxed[i] = values[i];
        }
        return Sequence.of(boxed);
    }

    static Sequence<Short> of(short... values) {
        final Short[] boxed = new Short[values.length];
        for (int i = 0; i < values.length; i++) {
            boxed[i] = values[i];
        }
        return Sequence.of(boxed);
    }

    static Sequence<Byte> of(byte... values) {
        final Byte[] boxed = new Byte[values.length];
        for (int i = 0; i < values.length; i++) {
            boxed[i] = values[i];
        }
        return Sequence.of(boxed);
    }

    static Sequence<Character> of(char... values) {
        final Character[] boxed = new Character[values.length];
        for (int i = 0; i < values.length; i++) {
            boxed[i] = values[i];
        }
        return Sequence.of(boxed);
    }

    static Sequence<Boolean> of(boolean... values) {
        final Boolean[] boxed = new Boolean[values.length];
        for (int i = 0; i < values.length; i++) {
            boxed[i] = values[i];
        }
        return Sequence.of(boxed);
    }

    static Sequence<Double> of(double... values) {
        final Double[] boxed = new Double[values.length];
        for (int i = 0; i < values.length; i++) {
            boxed[i] = values[i];
        }
        return Sequence.of(boxed);
    }

    static Sequence<Float> of(float... values) {
        final Float[] boxed = new Float[values.length];
        for (int i = 0; i < values.length; i++) {
            boxed[i] = values[i];
        }
        return Sequence.of(boxed);
    }
}
