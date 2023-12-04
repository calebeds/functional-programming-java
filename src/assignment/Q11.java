package assignment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q11 {
    public static void main(String[] args) {
        List<AnotherBook> books = Arrays.asList(new AnotherBook("Gone with the wind", "Fiction"), new AnotherBook("Bourne Ultimatum", "Thriller"),
                new AnotherBook("The Client", "Thriller"));

        List<String> genreList = new ArrayList<>();

        books.stream()
                .map(AnotherBook::getGenre)
                .forEach(genreList::add);

        System.out.println(genreList);
    }
}
