package assignment;

import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class Q12 {
    public static void main(String[] args) {
        double sum = DoubleStream.of(0.0, 2.0, 4.0)
                .filter(n -> n % 2 != 0)
                .sum();

        System.out.println(sum);

        Stream.of(1.0, 3.0)
                .mapToDouble(Double::doubleValue)
                .filter(n -> n % 2 == 0)
                .average()
                .ifPresent(System.out::println);
    }
}
