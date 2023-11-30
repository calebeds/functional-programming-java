package assignment;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Q1 {
    public static void main(String[] args) {
        IntStream.range(0, 5)
                .peek(System.out::println)
                .average()
                .ifPresent(System.out::println);
    }
}
