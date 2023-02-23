package h09;

import h09.basic.StringBasicBinaryOperations;
import h09.basic.StringFactory;
import h09.sequence.FibonacciSequence;
import h09.sequence.PrimitiveSequence;
import h09.sequence.Sequence;
import h09.sequence.operation.*;

import java.util.Iterator;

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
        StringFactory factory = new StringFactory(1, new String[]{"Hallo", "Welt", "!"});
        System.out.println(factory.create());
        System.out.println(factory.create());
        System.out.println(factory.create());
        System.out.println(factory.create());
        System.out.println(factory.create());
        System.out.println(factory.create());

        Sequence<String> originalSeq = Sequence.of("a", "b", "c", "d", "e");
        Sequence<String> limitedSeq = new LimitSequence<>(originalSeq, 2);
        Sequence<String> onEachSeq = new OnEachSequence<>(limitedSeq, s -> System.out.println("OnEach: " + s));
        Iterator<String> it = onEachSeq.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }

        StringBasicBinaryOperations op = new StringBasicBinaryOperations();
        System.out.println(op.add("Hallo", "Welt")); // Gibt "HalloWelt" aus
        System.out.println(op.mul("Hallo", 3)); // Gibt "HalloHalloHallo" aus
        System.out.println(op.mul("Hallo", 0)); // Gibt "" aus
        System.out.println(op.mul("Hallo", -1)); // Gibt "" aus

        Sequence<Integer> seq = new FilteringSequence<>(new FibonacciSequence(), x -> x % 2 == 0);
        Sequence<Integer> limitedSeq2 = new LimitSequence<>(seq, 6);
        Iterator<Integer> it2 = limitedSeq2.iterator();
        while (it2.hasNext()) {
            System.out.println(it2.next());
        }

        Sequence<String> seq3 = new TransformingSequence<>(new FibonacciSequence(), x -> "Next number: " + x * x);
        Sequence<String> limitedSeq3 = new LimitSequence<>(seq3, 6);
        Iterator<String> it3 = limitedSeq3.iterator();
        while (it3.hasNext()) {
            System.out.println(it3.next());
        }

        Sequence<String> ogSeq = Sequence.of("1", "23", "456");
        Sequence<Character> charSeq = new FlatteningTransformingSequence<>(ogSeq, s -> PrimitiveSequence.of(s.toCharArray()));
        Sequence<Integer> seq4 = new TransformingSequence<>(charSeq, Character::getNumericValue);
        Sequence<Integer> limitedSeq4 = new LimitSequence<>(seq4, 6);
        Iterator<Integer> it4 = limitedSeq4.iterator();
        while (it4.hasNext()) {
            System.out.println(it4.next());
        }

        Sequence<String> seq0 =
            Sequence.of("Generics", "sind", "nicht", "toll", "und", "super");

        Sequence<String> seq1 = new FilteringSequence<>(seq0, s -> !s.equals("nicht"));
        Sequence<String> seq2 = new TransformingSequence<>(seq1, String::toUpperCase);
        Sequence<String> seq33 = new TransformingSequence<>(seq2, s -> s + "!");
        Sequence<String> seq43 = new LimitSequence<>(seq33, 3);
        Iterator<String> it5 = seq43.iterator();
        while (it5.hasNext()) {
            System.out.println(it5.next());
        }

        Sequence<String> seq9 =
            Sequence.of("Generics", "sind", "nicht", "toll", "und", "super")
                .then(FilteringSequence.of(s -> !s.equals("nicht")))
                .then(TransformingSequence.of(String::toUpperCase))
                .then(TransformingSequence.of(s -> s + "!"))
                .then(LimitSequence.of(3));

        Iterator<String> it6 = seq9.iterator();
        while (it6.hasNext()) {
            System.out.println(it6.next());
        }
    }
}
