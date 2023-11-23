import java.util.stream.Stream;

public class InfiniteStream {

    public static void main(String[] args) {
        // infinite stream of random unordered numbers
        // between 0..9 inclusive
        // Stream<T> generate(Supplier <T> s)
        // Supplier is a functional interface:
        // T get()
        Stream<Integer> infStream = Stream.generate(() -> {
            return (int) (Math.random() * 10);
        });
        //keeps going until i kill it
        infStream.forEach(System.out::println);

        // infinite stream of unordered numbers
        // 2, 4, 6, 8, 10, 12 etc...
        // iterate(T seed, UnaryOperator<T> fn)
        // UnaryOperator is-a Function<T, T>
        // T apply(T t)
        Stream<Integer> infStreamUN = Stream.iterate(2, n -> n + 2);

        // keeps going until i kill it
        infStreamUN.forEach(System.out::println);

        // finite stream of ordered numbers
        // 2, 4, 6, 8, 10, 12, 14, 16, 18, 20
        Stream
                .iterate(2, n -> n + 2)
                // limit() is a short-circuiting stateful
                // intermediate operation
                .limit(10)
                // forEach(Consumer) is a terminal operation
                .forEach(System.out::println);
    }
}
