package lambdalab;

import jdk.jshell.spi.SPIResolutionException;

import java.util.*;
import java.util.function.*;

public class LambdasAndMethodReferences {
    public static void main(String[] args) {
        staticMR();
        boundMR();
        unboundMR();
        constructorMR();
    }

    public static void staticMR() {
        List<Integer> integerList = Arrays.asList(1,2, 7, 4, 5);

        //Consumer<T>
        // void accept(T t)
        // The code on the next line translates into:
        // void accept(List<Integer> li) {
        //  Collections.sort(li)
        //}
        Consumer<List<Integer>> lambda = l -> Collections.sort(l);
        System.out.println(integerList);
        lambda.accept(integerList);
        System.out.println(integerList);

        integerList = Arrays.asList(1,2, 7, 4, 5);
        // Collections.sort(List)
        // Collections.sort(List, Comparator)
        // Java infers what to do from the *context* i.e. we are creating a Consumer
        // and its functional method 'accept(T t)' takes one parameter; thus, it
        // invokes the 'sort(List)' as per the lambda sinxtax above
        Consumer<List<Integer>> methodRef = Collections::sort;
        System.out.println(integerList);
        methodRef.accept(integerList);
        System.out.println(integerList);
;

    }

    public static void boundMR() {
        // calling instance methods on a particular object

        String name = "Mr. Joe Bloggs";

        //Predicate<T>
        // boolean test(T t)
        // The code on the next line translates into:
        // boolean test(String prefix) {
        //    return name.startsWith(prefix);
        // }
        Predicate<String> lambda = prefix -> name.startsWith(prefix); // 'name' is a local variable in the method
        System.out.println(lambda.test("Mr. "));
        System.out.println(lambda.test("Ms. "));

        //String method:
        // boolean startsWith(String)
        // 'name' is a String and String has a 'startWith' method.
        // The argument required by 'startsWith' will be passed in when
        // we invoke the method reference i.e. name.startsWith(arg).
        Predicate<String> methodRef = name::startsWith;
        System.out.println(methodRef.test("Mr. "));
        System.out.println(methodRef.test("Ms. "));

    }

    public static void unboundMR() {
        // calling instance methods on a parameter

        // Predicate<T>
        // boolean test(T t)
        // The code on the next line translates into:
        // boolean test(String str) {
        //  return str.isEmpty();
        // }
        Predicate<String> lambda = str -> str.isEmpty(); // no 'str' local variable in the method
        System.out.println(lambda.test("")); //true "".isEmpty();
        System.out.println(lambda.test("xyz")); //false "xyz".isEmpty()

        // 'isEmpty' is a String method and will be executed on a String instance passed in
        // when we invoke the method reference.
        Predicate<String> methodRef = String::isEmpty;
        System.out.println(methodRef.test("")); //true "".isEmpty();
        System.out.println(methodRef.test("xyz")); //false "xyz".isEmpty()

        BiPredicate<String, String> lambda2 = (str, prefix) -> str.startsWith(prefix);
        System.out.println(lambda2.test("Mr. Joe Bloggs", "Mr."));
        System.out.println(lambda2.test("Mr. Joe Bloggs", "Ms."));

        // Now we are working with a  "Bi"Predicate i.e. 2 parameters.
        // What does Java do with them? The first will always be the instance of the
        // object for instance methods. The second (and other) arguments are method parameters

        BiPredicate<String, String> methodRef2 = String::startsWith;

        // Context - BiPredicate takes two args, the first is the one that 'startsWith' will be
        // executed on; the second argument and subsequent arguments are passed in as
        // arguments to 'startsWith'
        System.out.println(methodRef2.test("Mr. Joe Bloggs", "Mr."));
        System.out.println(methodRef2.test("Mr. Joe Bloggs", "Ms."));
    }

    public static void constructorMR() {
        // 'new' is used instead of a method; it instantiates an object.
        // Supplier is commonly used here.

        // Supplier<T>
        // T get()
        // The code on the next line translates into:
        // List<String> get() {
        //  return new ArrayList<>();// initial capactiy of ten
        Supplier<List<String>> lambda = () -> new ArrayList<>();
        List<String> list = lambda.get();
        list.add("Lambda");
        System.out.println(list);

        Supplier<List<String>> methodReference =  ArrayList::new;
        list = methodReference.get();
        list.add("Method Reference");
        System.out.println(list);


        //Now, we want to call the overloaded ArrayList(int initialCapacity) constructor.
        // We need to pass IN something and get something OUT i.e. use a Function.
        Function<Integer, List<String>> lambda2 = n -> new ArrayList<>(n);
        list = lambda2.apply(20);
        System.out.println(list);

        Function<Integer, List<String>> methodReference2 = ArrayList::new;
        list = methodReference2.apply(20);
        System.out.println(list);
    }

}
