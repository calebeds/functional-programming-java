import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class StreamsCreation {

    public static void main(String[] args) {
        Double[] numbers = {1.1, 2.2, 3.3};
        // Arrays.stream() creates a stream from the array 'numbers'.
        // The array is considered the source of the stream and while the
        // data is flowing through the stream, we have an opportunity to
        // operate on the data.
        Stream<Double> stream1 = Arrays.stream(numbers);

        // lets perform an operation on the data
        // note that count() is a "terminal operation" - this means that
        // you cannot perform any more operations on the stream.
        long n = stream1.count();
        System.out.println("Number of elements: " + n); //3

        List<String> animalList = Arrays.asList("cat", "dog", "sheep");
        // using stream() which is a default method in Collection interface and therefore
        // is inherited by all classes that implement Collection. Map is NOT one
        // of those i.e. Map is not a Collection. To bridge between the two, we
        // use the Map method entrySet() to return a Set view of the Map (Set
        // IS-A Collection
        Map<String, Integer> nameToAges = new HashMap<>();
        nameToAges.put("Mike", 22);
        nameToAges.put("Mary", 24);
        nameToAges.put("Alice", 31);
        System.out.println("Number of entries: " +
                nameToAges
                        .entrySet() // get a Set (i.e. Collection) view of the Map
                        .stream()   // stream() is a default method in Collection
                        .count());  // 3
    }
}
