import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class MappingStreams {
    public static void main(String[] args) {
        mappingObjectsStreams();
    }

    public static void mappingObjectsStreams() {
        // Stream<T> to Stream<T>
        Stream.of("ash", "beech", "sycamore")
                // map(Function)
                // Function<T, R> => Function<String, String>
                // String apply(String s)
                .map(tree -> tree.toUpperCase())
                .forEach(System.out::println);// ASH, BEECH, SYCAMORE

        // Stream<T> to DoubleStream
        DoubleStream dblStream = Stream.of("ash", "beech", "sycamore")
                // mapToDouble(ToDoubleFunction)
                // ToDoubleFunction is a functional interface:
                // double applyAsDouble(T value)
                .mapToDouble(tree -> tree.length()); // upcast in background

        dblStream.forEach(System.out::println);

        // Stream<T> to IntStream
        IntStream intStream = Stream.of("ash", "beech", "sycamore")
                // mapToInt(ToIntFunction)
                // ToIntFunction is a function interface:
                // int applyAsInt(T value) -> int applyAsInt(String tree)
                .mapToInt(tree -> tree.length());
        intStream.forEach(System.out::println); // 3, 5, 8

        // Stream<T> to LongStream
        LongStream longStream = Stream.of("ash", "beech", "sycamore")
            // mapToLong(ToLongFunction)
            // ToLongFunction is a function interface:
            // long applyAsLong(T value)
                .mapToLong(tree -> tree.length());
        longStream.forEach(System.out::println);
    }

}
