package generics;
import static java.lang.System.out;

public class GenericMethod {
    public static <E> void printArray(E[] inputArray) {
        for(E element: inputArray){
            out.printf("%s ", element);
        }
        out.println();
    }
}
