import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectTerminalOperation {
    public static void main(String[] args) {
        // StringBuilder collect(Supplier<StringBuilder> supplier,
        //                          BiConsumer<StringBuilder, String> accumulator,
        //                          BiConsumer<StringBuilder, StringBuilder> combiner)
        // This version is used when you want complete control over
        // how collecting should work. The accumulator adds an element
        // to the collection e.g. the next String to the StringBuilder.
        // The combiner takes two collections and merges them. It is useful
        // in parallel processing.
        StringBuilder word = Stream.of("ad", "jud", "i", "cate")
                .collect(() -> new StringBuilder(),      //StringBuilder::new
                        (sb, str) -> sb.append(str),     //StringBuilder::append
                        (sb1, sb2) -> sb1.append(sb2));  //StringBuilder::append

        System.out.println(word); //adjudicate

        String s = Stream.of("cake", "biscuits", "apple tart")
                .collect(Collectors.joining(", "));
        System.out.println(s); //cake, biscuits, apple tart

        Double avg = Stream.of("cake", "biscuits", "apple tart")
                // averageInt(ToIntFunction) functional method is:
                // int applyAsInt(T value);
                .collect(Collectors.averagingInt(str -> str.length()));
        System.out.println(avg);

        // We want a map: dessert name -> number of characters is dessert name
        // Output:
        // {biscuit=8, cake=4, apple tart=10}
        Map<String, Integer> map =
                Stream.of("cake", "biscuits", "apple tart")
                        .collect(Collectors.toMap(
                                str -> str,
                                str -> str.length())
                        );

        System.out.println(map);

        // We want a map: number of characters in dessert name -> dessert name
        // However, 2 of the desserts have the same length (cake and tart) and as
        // length is our key and we can't have duplicate keys, this leads to an
        // exception as Java does not know what to do...
        // IllegalStateExcpetion: Duplicate key (attempted merging values cake and tart)
        // To get around this, we can supply a merge function, whereby we append the
        // colliding keys values together.
        Map<Integer, String> map2 =
                Stream.of("cake", "biscuits", "tart")
                        .collect(Collectors.toMap(str -> str.length(), // key is the length
                                str -> str,                             // value is the String
                                (s1, s2) -> s1 + "," + s2));            // Merge function - what to
                                                                        // do if we have dupplicate keys
                                                                        // - append the values

        System.out.println(map2);

        // The maps returned are HashMaps but this is not guaranteed. What if we wanted
        // a TreeMap implementation so our keys would be sorted. The last argument
        // caters for this.

        TreeMap<String, Integer> treeMap =
                Stream.of("cake", "biscuits", "apple tart", "cake")
                        .collect(Collectors.toMap(str -> str,   //key is the String
                                str -> str.length(),            // value is the length of the String
                                (len1, len2) -> len1 + len2,    // what to do if we have
                                                                // duplicate keys
                                                                // - add the *values*
                                () -> new TreeMap<>()));        // TreeMap::new works

        System.out.println(treeMap);
        System.out.println(treeMap.getClass());


        Stream<String> names = Stream.of("Joe", "Tom", "Tom", "Alan", "Peter");
        Map<Integer, List<String>> mapNames =
                names.collect(
                        // passing in a Function that determines the
                        // key in the Map
                        Collectors.groupingBy(String::length)); // s -> s.length()

        System.out.println(mapNames);


        Stream<String> namesD = Stream.of("Joe", "Tom", "Tom", "Alan", "Peter");
        Map<Integer, Set<String>> mapNamesNoDuplicates = namesD.collect(
                Collectors.groupingBy(
                        String::length,     //key Function
                        Collectors.toSet()  // what to do with the values
                )
        );

        System.out.println(mapNamesNoDuplicates);

        Stream<String> streamNamesTreeMap = Stream.of("Joe", "Tom", "Tom", "Alan", "Peter");
        TreeMap<Integer, List<String>> groupByTreeMap =
                streamNamesTreeMap.collect(
                        Collectors.groupingBy(
                                String::length,
                                TreeMap::new,    //map type Supplier
                                Collectors.toList() //downstream collector
                        )
                );

        System.out.println(groupByTreeMap);
        System.out.println(groupByTreeMap.getClass());

    }
}
