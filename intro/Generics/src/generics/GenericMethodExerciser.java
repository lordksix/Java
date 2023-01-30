package generics;
import static java.lang.System.out;
import static generics.GenericMethod.*;

public class GenericMethodExerciser {
    public static void main(String[] args) {
        Integer[] integerArray = {1,2,3,4,5};
        Double[] doubleArray = {1.1,2.2,3.3,4.4,5.5};
        Character[] charArray = {'H','E','L','L','O'};
        
        out.println("Integer array contains: ");
        printArray(integerArray);
        out.println("Double array contains: ");
        printArray(doubleArray);
        out.println("Character array contains: ");
        printArray(charArray);

    }
}
