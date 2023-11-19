import java.util.function.Predicate;
import java.util.function.Supplier;

public class MethodReferenceTypes {

    public static void main(String[] args) {
        boundMethodReference();
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

}
