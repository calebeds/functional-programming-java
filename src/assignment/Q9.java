package assignment;

import java.util.Arrays;
import java.util.List;

public class Q9 {
    public static void main(String[] args) {
        List<Person> personList = Arrays.asList(new Person("Bob", "", 31),
                new Person("Paul", "", 32), new Person("John", "", 33));

        double avg = personList.stream()
                .filter(person -> person.getAge() < 30)
                .mapToInt(Person::getAge)
                .average()
                .orElse(0.0);

        System.out.println(avg);
    }
}
