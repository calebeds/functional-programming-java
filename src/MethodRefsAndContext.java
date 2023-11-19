import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class MethodRefsAndContext {
    public static void main(String[] args) {
        // No person being passed in => Supplier
        Supplier<Integer> lambda1 = () -> Person.howMany();
        Supplier<Integer> mr1 = Person::howMany;
        System.out.println(lambda1.get());
        System.out.println(mr1.get());

        // One Person to be passed in => Function
        Function<Person, Integer> lambda2 = person -> Person.howMany(person);
        Function<Person, Integer> mr2 = Person::howMany;
        System.out.println(lambda2.apply(new Person())); //1
        System.out.println(mr2.apply(new Person())); //1

        // Two persons to be passed in => Function
        BiFunction<Person, Person, Integer> lambda3 = (person1, person2) -> Person.howMany(person1, person2);
        BiFunction<Person, Person, Integer> mr3 = Person::howMany;
        System.out.println(lambda3.apply(new Person(), new Person())); //2
        System.out.println(mr3.apply(new Person(), new Person())); //2

    }
}

class Person {
    public static Integer howMany(Person... people) {
        return people.length;
    }
}
