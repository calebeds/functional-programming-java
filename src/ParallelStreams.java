import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ParallelStreams {
    public static void main(String[] args) {
        Stream<String> animalsStream = List.of("sheep", "pigs", "horses")
                .parallelStream(); // Collection<E> method

        Stream<String> animalsStream2 = Stream.of("sheep", "pigs", "horses")
                .parallel(); // Stream<T> method

        doSequentialStream();
        doParallelStream();
        doStreamOrderExample();
    }

    public static void doSequentialStream() {
        // Sequential stream
        int sum = Stream.of(10, 20, 30, 40, 50, 60)
                // IntStream has the sum() method so we use
                // the mapToInt() method to map from Stream<Integer>
                // to an IntStream (i. e. a stream of int primitives).
                // IntStream mapToInt(ToIntFunction) is a funtional interface:
                // int applyAsInt(T value)
                .mapToInt(n -> n.intValue())
                // .mapToInt(Integer::intValue)
                // .mapToInt(n -> n)
                .sum();

        System.out.println("Sum == " + sum);
    }

    public static void doParallelStream() {
        int sum = Stream.of(10, 20, 30, 40, 50, 60)
                .parallel()
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println("Sum == " + sum);
    }

    public static void doStreamOrderExample() {
        Arrays.asList("a", "b", "c") // create List
                .stream() // stream the list
                .forEach(System.out::print);

        System.out.println();

        Arrays.asList("a", "b", "c") // create List
                .stream() // stream the List
                .parallel()
                .forEach(System.out::print);
    }
}
