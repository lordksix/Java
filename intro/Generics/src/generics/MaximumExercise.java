package generics;
import static java.lang.System.out;
import static generics.Maximum.*;

public class MaximumExercise {
    public static void main(String[] args) {
        out.printf("Maximum of %d, %d and %d is %d%n", 5,4,3,maximum(5, 4, 3));
        out.printf("Maximum of %.1f, %.1f and %.1f is %.1f%n", 6.6,8.8,7.7,maximum(6.6, 8.8, 7.7));
        out.printf("Maximum of %s, %s and %s is %s%n", "pear","apple","orange",maximum("pear", "apple", "orange"));
    }
}
