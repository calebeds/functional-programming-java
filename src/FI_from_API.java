import java.time.LocalTime;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FI_from_API {
    public static void main(String[] args) {
        FI_from_API fiAPI = new FI_from_API();
        fiAPI.predicate();
        fiAPI.supplier();
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

    public void supplier() {
        // Supplier<T> is a functional interface i.e. one abstract method:
        // T get()
        Supplier<StringBuilder> supSB = () -> new StringBuilder();
        System.out.println("Supplier SB: " + supSB.get().append("SK"));//Supplier SB: SK

        Supplier<LocalTime> supTime = () -> LocalTime.now();
        System.out.println("Supplier time: " + supTime.get());//Supplier time: the time now

        Supplier<Double> sRandom = () -> Math.random();
        System.out.println(sRandom.get());//e.g. 0.8453940688463555
    }
}
