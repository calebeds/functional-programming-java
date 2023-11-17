import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class FI_from_API {
    public static void main(String[] args) {
        FI_from_API fiAPI = new FI_from_API();
        fiAPI.predicate();
    }

    public void predicate() {
        // Predicate<T> is a functional interface i.e. one abstract method:
        // boolean test(T t);
        Predicate<String> pStr = s -> s.contains("City");
        System.out.println(pStr.test("Vatican City"));//true

        //BiPredicate<T, U> is a functional interface i.e. one abstract method:
        // boolean test(T t, U u)
        BiPredicate<String, Integer> checkLength = (str, len) -> str.length() == len;
        System.out.println(checkLength.test("Vatican City", 8));//false (length is 12)
    }
}
