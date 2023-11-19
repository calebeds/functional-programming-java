import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.*;

public class MethodReferenceTypes {

    public static void main(String[] args) {
        boundMethodReference();
        unboundMethodReferences();
        staticMethodReferences();
    }

    public static void boundMethodReference() {
        String name = "Mr. Joe Bloggs";
        // Supplier<T>
        // T get()
        Supplier<String> lowerL = () -> name.toLowerCase(); //lambda
        Supplier<String> lowerMR = name::toLowerCase; //method reference

        // No need to say which instance to call it on - the supplier is bound to name
        System.out.println(lowerL.get());
        System.out.println(lowerMR.get());

        // Predicate<T>
        // boolean test<T t>
        // Even though startsWith is overloaded, boolean startsWith(String) and
        // boolean startsWith(String, int), because we are creating a Predicate which
        // has a functional method of test(T t), the startsWith(String) is used.
        // This is where "context" is important
        Predicate<String> titleL = (title) -> name.startsWith(title);
        Predicate<String> titleMr = name::startsWith;

        System.out.println(titleL.test("Mr. "));//true
        System.out.println(titleMr.test("Ms. "));//false
    }

    public static void unboundMethodReferences() {
        // Function<T, R>
        // R apply(T)
        // String apply(String)
        Function<String, String> upperL = s -> s.toUpperCase();
        Function<String, String> upperMR = String::toUpperCase;
        // The function is unbound, so you need to specify which instance to call it on
        System.out.println(upperL.apply("sean")); //SEAN
        System.out.println(upperMR.apply("sean")); //SEAN

        // BiFunction<T, U, R>
        // R apply(T t, U u)
        // String apply(String, String)
        BiFunction<String, String, String> concatL = (s1, s2) -> s1.concat(s2);
        BiFunction<String, String, String> concatMR = String::concat;
        System.out.println(concatL.apply("Sean ", "Kennedy")); //Sean Kennedy

        // 1st parameter is used for executing the instance method
        // "Sean".concat("Kennedy")
        System.out.println(concatMR.apply("Sean ", "Kennedy"));
    }

    public static void staticMethodReferences() {
        // Static method references are considered UNBOUND also. An example static method
        // is Collections.sort(List)
        // Consumer<T>
        // void accept(T t)
        // void void accept(List<Integer>)
        // NB: Consumer takes one parameter => sort(List) is used, as opposed to sort(List, Comparator)
        Consumer<List<Integer>> sortL = list -> Collections.sort(list);
        Consumer<List<Integer>> sortMR = Collections::sort;

        List<Integer> listOfNumbers = Arrays.asList(2, 1, 5, 4, 9);
        sortL.accept(listOfNumbers);//execution
        System.out.println(listOfNumbers); //[1, 2, 4, 5, 9]

        listOfNumbers = Arrays.asList(8, 12, 4, 3, 7);
        sortMR.accept(listOfNumbers);//execution
        System.out.println(listOfNumbers); //[3, 4, 7, 8, 12]

    }

}
