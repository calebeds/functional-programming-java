package assignment;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class Q4 {
    public static void main(String[] args) {
        List<Integer> integerList = Arrays.asList(1, 2, 3);
        int sum =  integerList.stream()
                .mapToInt(Integer::intValue)
                .sum();

        System.out.println("Sum: " + sum);

        integerList.stream()
                .mapToInt(Integer::intValue)
                .max()
                .ifPresent(max -> System.out.println("Max: " + max));

        List<Person> personList = Arrays.asList(new Person("Alan", "Burke", 22),
                new Person("Zoe", "Peters", 20), new Person("Peter", "Castle", 29));

        personList.stream()
                .max(Comparator.comparing(Person::getAge))
                .ifPresent(oldestPerson -> System.out.println("Oldest person: " + oldestPerson));

        Stream.of(10, 47, 33, 23)
                .reduce((n1, n2) -> n1 > n2 ? n1 : n2)
                .ifPresent(System.out::println);

        int greaterNumber = Stream.of(10, 47, 33, 23)
                .reduce(1, (n1, n2) -> n1 > n2 ? n1 : n2);

        System.out.println("Greater number: " + greaterNumber);
    }
}
