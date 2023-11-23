import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamLaziness {
    public static void main(String[] args) {
        Stream.of("Alex", "David", "April", "Edward")
                .filter(s -> {
                    System.out.println("filter : " + s);
                    return true;
                })
                .forEach(s -> System.out.println("forEach: " + s));

        /**
         * This can help in reducing the actual number of operations - instead of
         * mapping "Alex", "David", "April" and "Edward" and then anyMatch() on
         * "Alex" (5 operations in total), we process the elements vertically resulting in
         * only 2 operations. While this is a small example, it shows the benefits to be
         * had if we had millions of data elements to be processed.
         * map: Alex
         * anyMatch: ALEX
         */
        Stream.of("Alex", "David", "April", "Edward")
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .anyMatch(s -> {//ends when first true is returned (Alex)
                    System.out.println("anyMatch: " + s);
                    return s.startsWith("A");
                });

        List<String> names = Arrays.asList("April", "Ben", "Charlie",
                "David", "Benildus", "Christian");

        names.stream()
                .peek(System.out::println)
                .filter(s -> {
                    System.out.println("filter1: " + s);
                    return s.startsWith("B") || s.startsWith("C");
                })
                .filter(s -> {
                    System.out.println("filter2: " + s);
                    return s.length() > 3;
                })
                .limit(1)
                .forEach(System.out::println);
    }
}
