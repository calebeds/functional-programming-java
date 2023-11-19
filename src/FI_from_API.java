import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.*;

public class FI_from_API {
    public static void main(String[] args) {
        FI_from_API fiAPI = new FI_from_API();
        fiAPI.predicate();
        fiAPI.supplier();
        fiAPI.consumer();
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

    public void consumer() {
        // Consumer<T> is a functional interface i.e. one abstract method:
        // void accept(T t)
        Consumer<String> printC = s -> System.out.println(s);
        printC.accept("To be or not to be, that is the question");

        List<String> names = new ArrayList<>();
        names.add("John");
        names.add("Mary");
        names.forEach(printC);

        // BiConsumer<T, U> is a functional interface i.e. one abstract method:
        // void accept(T t, U u)
        var mapCapitalCities = new HashMap<String, String>();
        // Note: The return value of put(k,v) is just ignored (and not returned from the lambda)
        BiConsumer<String, String> biCon = (key, value) -> mapCapitalCities.put(key, value);
        biCon.accept("Dublin", "Ireland");
        biCon.accept("Washington D.C.", "USA");

        System.out.println(mapCapitalCities);

        BiConsumer<String, String> mapPrint = (key, value) -> System.out.println(key + " is the capital of: " + value);
        mapCapitalCities.forEach(mapPrint);

    }
}
