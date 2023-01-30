package generics;
import static java.lang.System.out;

public class GenericsBoxExercise {
    
    /** 
     * @param args
     */
    public static void main(String[] args) {
        GenericsBox<Integer> IntegerBox = new GenericsBox<>();
        GenericsBox<String> StringBox = new GenericsBox<>();
        IntegerBox.setT(10);
        StringBox.setT("Hello World");

        out.println("IntegerBox value: " + IntegerBox.getT());
        out.println("StringBox value: " + StringBox.getT());
    }
}
