import java.util.function.BinaryOperator;
import java.util.stream.Stream;

public class ReduceTerminalOperation {
    public static void main(String[] args) {

        // The reduce() method combines a stream into a single object.
        // It is a reduction, which means it processes all elements.
        // The common way of doing a reduction is to start with
        // an initial value and keep merging it with the next value.

        // T reduce(T identity, BinaryOperator<T> accumulator)
        // BinaryOperator<T> functional method:
        // T apply(T, T);
        // The "identity" is the initial value of the reduction and also
        // what is returned if the stream is empty. This means that there
        // will always be a result and thus Optional is not the return type
        // (on this version of reduce()).
        // The "accumulator" combines the current result with the
        // current value in the stream.
        String name = Stream.of("s", "e", "a", "n")
//                .filter(s -> s.length() > 2)
//                .reduce("nothing", (s, c) -> s + c);
                .reduce("", (s, c) -> s + c);

        System.out.println(name);//sean

        Integer product = Stream.of(2, 3, 4)
                .reduce(1, (a, b) -> a * b);

        System.out.println(product); //24

        // Optional<T> reduce(BinaryOperator<T> accumulator)
        // When you leave out the identity, an Optional is
        // returned because there may not be any data (all the
        // elements could have been filtered out earlier). There are
        // 3 possible results:
        // a) empty stream => empty Optional returned
        // b) one element in stream => that element is returned
        // c) multiple elements in stream => accumulator is applied
        BinaryOperator<Integer> op = (a, b) -> a + b;
        Stream<Integer> empty = Stream.empty();
        Stream<Integer> oneElement = Stream.of(6);
        Stream<Integer> multipleElements = Stream.of(3, 4, 5);

        empty.reduce(op).ifPresent(System.out::println); //
        oneElement.reduce(op).ifPresent(System.out::println); //6
        multipleElements.reduce(op).ifPresent(System.out::println); //12

        // Why not just require the identity and remove this method?
        // Sometimes it is nice know if the stream is empty as opposed
        // to the case where there is a value returned from the accumulator
        // that happens to match the identity (however unlikely).
        Integer val = Stream.of(1, 1, 1)
//                .filter(n -> n > 5)         // val is 1 this way
                .reduce(1, (a, b) -> a); //val is 1 this way too

        System.out.println(val);

        // <U> U reduce (U identity,
        //               BiFunction accumulator,
        //               BinaryOperator combiner)
        // We use this version when we are dealing with different types,
        // allowing us to create intermediate reductions and then combine
        // them at the end. This is useful when working with parallel
        // streams - the streams can be decomposed and reassembled by
        // separate threads. For example, if we wanted to count the length
        // of four 1000-characters strings, the first 2 values and the last
        // two values could be calculated independently. The intermediate
        // results (2000) would then be combined into a final value (4000).
        // Example: we want to count the number of characters in each String
        Stream<String> streamS = Stream.of("car", "bus", "train", "aeroplane");
        int length = streamS.reduce(0, //identity
                (n, str) -> n + str.length(), //n is Integer
                (n1, n2) -> n1 + n2); // both are integers

        System.out.println(length);
    }
}
