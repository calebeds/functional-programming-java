package assignment;

import java.util.Arrays;
import java.util.List;

public class Q6 {
    public static void main(String[] args) {
        List<Book> booksList = Arrays.asList(new Book("Thinking in Java", 30),
                new Book("Java in 25hrs", 20), new Book("Java Recipes", 10));

        booksList.stream()
                .filter(book -> book.getPrice() > 90)
                .mapToDouble(book -> book.getPrice())
                .average()
                .ifPresent(System.out::println);
    }
}
