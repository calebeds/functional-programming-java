import java.util.stream.Stream;

public class BuildStreams {
    public static void main(String[] args) {
        Stream<Integer> streamI = Stream.of(1, 2,3 );
        System.out.println(streamI.count()); //3

        Stream<String> streamS = Stream.of("a", "b", "c", "d");
        System.out.println(streamS.count()); //4

        Stream<Dog> streamD = Stream.of(new Dog());
        System.out.println(streamD.count()); //1
    }
}

class Dog {

}
