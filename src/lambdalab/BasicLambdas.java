package lambdalab;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class BasicLambdas {
    public static void main(String[] args) {
        BasicLambdas basicLambdas = new BasicLambdas();
        basicLambdas.consumer();
        basicLambdas.supplier();
        basicLambdas.predicate();

        List<Person> listPeople = getPeople();
        sortAge(listPeople);
        sortName(listPeople);
        sortHeight(listPeople);
    }

    private static void sortHeight(List<Person> listPeople) {
        listPeople.sort(Comparator.comparing(Person::getHeight));

        listPeople.forEach(person -> {
            System.out.println("Person: " + person.getHeight());
        });
    }

    private static void sortName(List<Person> listPeople) {
        listPeople.sort(Comparator.comparing(Person::getName));

        listPeople.forEach(person -> {
            System.out.println("Person: " + person.getName());
        });
    }

    private static void sortAge(List<Person> listPeople) {
        listPeople.sort(Comparator.comparingInt(Person::getAge));

        listPeople.forEach((person -> System.out.println("Age: " + person.getAge())));
    }

    public  void consumer() {
        //Printable<T> is a functional interface i.e. one abstract method:
        // void print(T t); // similar to java.util.function.Consumer
        Printable<String> lambda = s -> System.out.println(s);
        lambda.print("Printable lambda");

        // Consumer<T> is a functional interface i.e. one abstract method:
        // void accept(T t)
        Consumer<String> consumer = s -> System.out.println(s); //lambda
        consumer.accept("Consumer lambda");

        Consumer<String> consumerMR = System.out::println; // method reference
        consumerMR.accept("Consumer method reference");
    }

    public void supplier() {
        //Retrievable<T> is a functional interface i.e. one abstract method:
        // T retrieve(); // similar to java.util.function.Supplier
        Retrievable<Integer> lambda = () -> 77;
        System.out.println("Retrievable: " + lambda.retrieve());


        // Supplier<T> is a functional interface i.e. one abstract method:
        // T get()
        Supplier<Integer> supplier = () -> 77; //lambda
        System.out.println("Retrievable: " + supplier.get());
    }

    public void predicate() {
        // Evaluate<T> is a functional interface i.e. one abstract method:
        // boolean isNegative(T t); // similar to java.util.function.Predicate
        Evaluate<Integer> lambda = (n) -> n < 0;
        System.out.println("-1 is negative? " + lambda.isNegative(-1));
        System.out.println("+1 is negative? " + lambda.isNegative(1));

        //Predicate<T> is a functional interface i.e. one abstract method:
        // boolean test(T t);

        Predicate<Integer> predicate = (n) -> n < 0;
        System.out.println("-1 is negative? " + predicate.test(-1));
        System.out.println("+1 is negative? " + predicate.test(1));

        Predicate<Integer> isEven = (n) -> n % 2 == 0;

        System.out.println("4 is even? " + check(4, isEven));
        System.out.println("7 is even? " + check(7, isEven));

        Predicate<String> startsWithMr = s -> s.startsWith("Mr.");
        System.out.println("Mr. Joe Bloggs starts with Mr. ? " + check("Mr. Joe Bloggs", startsWithMr));
        System.out.println("Ms. Ann Bloggs starts with Mr. ? " + check("Ms. Ann Bloggs", startsWithMr));

        Predicate<Integer> isAdult = age -> age >= 18;

        System.out.println("Mike is an adult? " + check(33, isAdult));
        System.out.println("Ann is an adult? " + check(14, isAdult));

    }

    public void function() {
        // Functionable<T, R> is a functional interface i.e. one abstract method:
        // R call(T t); // similar to java.util.function.Function
        Functionable<Integer, String> functionable = (number) -> "Number is" + number;
        System.out.println(functionable.call(25));

        //Function<T, R> is a functional interface i.e. one abstract method:
        // R apply(T t)
        Function<Integer, String> function = (number) -> "Number is" + number;
        System.out.println(function.apply(25));
    }

    public <T> boolean check(T t, Predicate predicate) {
        return predicate.test(t);
    }
    private static List<Person> getPeople() {

        List<Person> result = new ArrayList<>();

        result.add(new Person("Mike", 33, 1.8));

        result.add(new Person("Mary", 25, 1.4));

        result.add(new Person("Alan", 34, 1.7));

        result.add(new Person("Zoe", 30, 1.5));

        return result;

    }

}

interface Printable<T> {
    void print(T t);
}

interface Retrievable<T> {
    T retrieve();
}

interface Evaluate<T> {
    boolean isNegative(T t);
}

interface Functionable<T, R> {
    R call(T t);
}

class Person {
    private String name;
    private Integer age;
    private Double height;

    public Person(String name, Integer age, Double height) {
        this.name = name;
        this.age = age;
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }
}