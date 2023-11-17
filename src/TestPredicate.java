import java.util.function.Predicate;

//My own custom Functional Interface
interface Evaluate<T> {
    boolean isNegative(T t); // similar to Predicate
}

public class TestPredicate {
    public static void main(String[] args) {
        // Evaluate<T> is a functional interface i.e. one abstract method:
        // boolean isNegative(T t); // similar to java.util.function.Predicate
        Evaluate<Integer> lambda = i -> i < 0;
        System.out.println("Evaluate: " + lambda.isNegative(-1));
        System.out.println("Evaluate: " + lambda.isNegative(+1));

        // Predicate<T> is a functional interface i.e. one abstract method:
        // boolean test(T t)
        Predicate<Integer> predicate = i -> i < 0;
        System.out.println("Predicate: " + predicate.test(-1));
        System.out.println("Predicate: " + predicate.test(+1));
    }
}
