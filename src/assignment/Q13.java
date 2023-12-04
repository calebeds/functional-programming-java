package assignment;

import java.util.Arrays;
import java.util.List;

public class Q13 {
    public static void main(String[] args) {
        List<Integer> ls = Arrays.asList(11, 11, 22, 33, 33, 55, 66);
        boolean has11 =  ls.stream()
                .distinct()
                .anyMatch(n -> n == 11);

        System.out.println(has11);

        boolean divisibleBy11 = ls.stream()
                .noneMatch(n -> n % 11 > 0);

        System.out.println(divisibleBy11);
    }
}
