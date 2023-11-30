package assignment;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Q8 {
    public static void main(String[] args) {
        List<Book> books = Arrays.asList(new Book("Gone with the wind", 5.0), new Book("Gone with the wind", 10.0),
                new Book("Atlas Shrugged", 15.0));

        books.stream()
                .collect(Collectors.toMap(Book::getTitle,
                        Book::getPrice,
                        (price1, price2) -> price1 + price2))
                .forEach((title, price) -> {
                    System.out.println(title);
                    System.out.println(price);
                });
    }
}
