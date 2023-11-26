import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorsOtherExamples {

    public static void main(String[] args) {
        partitioningBy();
        partitioningByLength();
        partitioningByEmptyList();
        partitioningBySet();
    }

    public static void partitioningBy() {
        Stream<String> names = Stream.of("Thomas", "Theresa", "Mike", "Alan", "Peter");
        Map<Boolean, List<String>> map =
                names.collect(
                        // pass in a Predicate
                        Collectors.partitioningBy(s -> s.startsWith("T"))
                );
        System.out.println(map);
    }

    public static void partitioningByLength() {
        Stream<String> names = Stream.of("Thomas", "Theresa", "Mike", "Alan", "Peter");
        Map<Boolean, List<String>> map =
                names.collect(
                        // pass in a Predicate
                        Collectors.partitioningBy(s -> s.length() > 4)
                );

        System.out.println(map);
    }

    public static void partitioningByEmptyList() {
        Stream<String> names = Stream.of("Thomas", "Theresa", "Mike", "Alan", "Peter");
        Map<Boolean, List<String>> map =
                names.collect(
                        // forcing an empty list
                        Collectors.partitioningBy(s -> s.length() > 10)
                );

        System.out.println(map);
    }

    public static void partitioningBySet() {
        Stream<String> names = Stream.of("Alan", "Teresa", "Mike", "Alan", "Peter");
        Map<Boolean, Set<String>> map =
                names.collect(
                        Collectors.partitioningBy(
                                s -> s.length() > 4, // predicate
                                Collectors.toSet()
                        )
                );
        System.out.println(map);
    }
}
