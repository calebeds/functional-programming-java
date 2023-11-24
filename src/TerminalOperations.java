import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class TerminalOperations {
    public static void main(String[] args) {
        // Optional<T> min(Comparator)
        // Optional<T> max(Comparator)
        // Optional introduce in Java 8 to replace 'null'. If the stream is
        // empty then the optional will be empty (and we won't have to deal
        // with null)
        Optional<String> min = Stream.of("deer", "horse", "pig")
                .min((s1, s2) -> s1.length() - s2.length());

        min.ifPresent(System.out::println);//pig

        Optional<Integer> max = Stream.of(4, 6, 2, 12, 9)
                .max((i1, i2) -> i1 - i2);

        max.ifPresent(System.out::println);//12

        // Optional<T> findAny()
        // Optional<T> findFirst()
        // These are terminal operations but not reductions
        // as they sometimes return without processing all
        // the elements in the stream. Reductions reduce the
        // entire stream into one value.
        Optional<String> any = Stream.of("John", "Paul")
                .findAny();
        any.ifPresent(System.out::println);//John (usually)

        Optional<String> first = Stream.of("John", "Paul")
                .findFirst();
        first.ifPresent(System.out::println);//John

        // boolean anyMatch(Predicate)
        // boolean allMatch(Predicate)
        // boolean noneMatch(Predicate)

        List<String> names = Arrays.asList("Alan", "Brian", "Colin");
        Predicate<String> pred = name -> name.startsWith("A");
        System.out.println(names.stream().anyMatch(pred)); // true one does
        System.out.println(names.stream().allMatch(pred)); // false (two don't)
        System.out.println(names.stream().noneMatch(pred)); // false (one does)

        // void forEach(Consumer)
        // As there is no return value, forEach() is not a reduction.
        // As the return type is 'void', if you want something to
        // happen, it has to happen inside the Consumer (side-effect)
        Stream<String> namesFE = Stream.of("Cathy", "Pauline", "Zoe");
        namesFE.forEach(System.out::print); //CathyPaulineZoe

        // Notes: forEach is also a method in the Collection interface.
        // Streams cannot be the source of a for-each loop
        // because streams do not implement the Iterable interface
        Stream<Integer> s = Stream.of(1);
//        for(Integer i: s) {} // error: required array of Iterable
    }
}
