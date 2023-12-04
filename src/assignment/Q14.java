package assignment;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Q14 {
    public static void main(String[] args) {
//        AtomicInteger ai = new AtomicInteger();
//
//        Stream.of(11, 11, 22, 33)
//                .parallel()
//                .filter(n -> {
//                    ai.incrementAndGet();
//                    return n % 2 == 0;
//                })
//                .forEach(System.out::println);
//
//        System.out.println(ai);

        AtomicInteger ai = new AtomicInteger(); // initial value of 0
        Stream<Integer> stream = Stream.of(11, 11, 22, 33).parallel();
        Stream<Integer> stream2 = stream.filter( e->{
            ai.incrementAndGet();
            return e%2==0; });
        stream2.forEach(System.out::println);// java.lang.IllegalStateException
        System.out.println(ai);

    }
}


class Employee {
    private String name;
    private int age;
    private double salary;

    public Employee(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getSalary() {
        return salary;
    }

    public String toString() {
        return name;
    }
}