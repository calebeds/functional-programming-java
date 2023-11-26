import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class IntermediateOperations {


    public static void main(String[] args) {
//        filter();
//        distinct();
//        limit();
//        map();
//        flatMap();
//        sorted();
        sortedWithoutComparator();
    }

    public static void filter() {
        // Stream<T> filter(Predicate)
        // The filter() method returns a Stream with the elements that
        // MATCH the given predicate.
        Stream.of("galway", "mayo", "roscommon")
                .filter(countyName -> countyName.length() > 5)
                .forEach(System.out::println);
    }

    public static void distinct() {
        // Stream<T> distinct()
        // distinct() is a stateful intermediate operation
        // Output: 1.eagle 2.eagle 1.eagle 1.EAGLE 2.EAGLE
        Stream.of("eagle", "eagle", "EAGLE")
                .peek(s -> System.out.print(" 1." + s))
                .distinct()
                .forEach(s -> System.out.print(" 2." + s));
    }

    public static void limit() {
        // Stream<T> limit(long maxSize)
        // limit is a short-circuiting stateful
        // intermediate operation. Lazy evaluation - 66, 77, 88 and 99
        // are not streamed as they are not needed (limit of 2 i.e. 44 and 55).
        // Output:
        // A - 11 A - 22 A - 33 A - 44 B - 44 C - 44 A - 55 B - 55 C - 55
        System.out.println();
        Stream.of(11, 22, 33, 44, 55, 66, 77, 88, 99)
                .peek(n -> System.out.print(" A - " + n))
                .filter(n -> n > 40)
                .peek(n -> System.out.print(" B - " + n))
                .limit(2)
                .forEach(n -> System.out.print(" C - " + n));
    }

    public static void map() {
        // R Stream<R> map(Funtion<T, R> mapper)
        // Function's functional method: R apply(T t);
        System.out.println();
        Stream.of("book", "pen", "ruler")
                .map(s -> s.length()) // String::length
                .forEach(System.out::print);
    }

    public static void flatMap() {
        List<String> list1 = Arrays.asList("sean", "desmond");
        List<String> list2 = Arrays.asList("mary", "ann");
        Stream<List<String>> streamOfLists = Stream.of(list1, list2);
        System.out.println();

        // flatMap(Function(T, R)) IN:T OUT:R
        // flatMap(List<String>, Stream<String>)
        streamOfLists
                .flatMap(list -> list.stream())
                .forEach(System.out::println);
    }

    private static void sorted() {
        //Stream<T> sorted(Comparator<T> comparator)
        PersonForSorting john = new PersonForSorting("John", 23);
        PersonForSorting mary = new PersonForSorting("mary", 25);
        Stream.of(mary, john)
                .sorted(Comparator.comparing(PersonForSorting::getAge))
                .forEach(System.out::println);
    }

    private static void sortedWithoutComparator() {
        // Stream<T> sorted()
        // Stream<T> sorted(Comparator<T> comparator)
        Stream.of("Tim", "Jim", "Peter", "Ann", "Mary")
                .peek(name -> System.out.println(" 0." + name)) // Tim, Jim, Peter, Ann, Mary
                .filter(name -> name.length() == 3)
                .peek(name -> System.out.println(" 1." + name)) // Tim, Jim, Ann
                .sorted()
                .peek(name -> System.out.println(" 2." + name)) // Tim, Jim, Ann (stored)
                .limit(2)                                       // Ann, Jim
                .forEach(name -> System.out.println(" 3." + name)); // Ann, Jim
    }

}

class PersonForSorting {
    private String name;
    private int age;

    public PersonForSorting(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "PersonForSort{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}