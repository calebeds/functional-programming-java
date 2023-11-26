import java.util.IntSummaryStatistics;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class CommonPrimitiveMethods {


    public static void main(String[] args) {
        OptionalInt max = IntStream.of(10,20,30)
                .max();//terminal operation
        max.ifPresent(System.out::println);

        OptionalDouble min = DoubleStream.of(10.0, 20.0, 30.0)
                .min();//terminal operation
        // NoSuchElementExcpetion is thrown if no value present
        System.out.println(min.orElseThrow());//10.0

        OptionalDouble average = LongStream.of(10L, 20L, 30L)
                .average(); //terminal operation
        System.out.println(average.orElseGet(() -> Math.random()));//20.0

        stats(IntStream.of(5, 10, 15, 20));
        stats(IntStream.empty());
    }

    public static void stats(IntStream numbers) {
        IntSummaryStatistics intStats =
                numbers.summaryStatistics(); //terminal operation
        int min = intStats.getMin();
        System.out.println(min);// 5 (2147483647 if nothing in stream)
        int max = intStats.getMax();
        System.out.println(max);// 20 (-2147483648 if nothing in stream)
        double avg = intStats.getAverage();
        System.out.println(avg);//12.5 (0.0 if nothing in stream)
        long count = intStats.getCount();
        System.out.println(count);//4 (0 if nothing in stream)
        long sum = intStats.getSum();
        System.out.println(sum);//50 (0 if nothing in stream)
    }
}
