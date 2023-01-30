package coursetwoapps;

import demos.DequeStack;
import demos.Stack;
import demos.StackArray;

public class GenericApp {

    public static void printStackInteger(Stack<Integer> stack) {
        for (Object e = stack.pop(); e != null; stack.pop()) {
            System.out.println(e);
        }
    }
    public static void printStackString(Stack<String> stack) {
        for (Object e = stack.pop(); e != null; stack.pop()) {
            System.out.println(e);
        }
    }
    public static void main(String[] args) {
        Stack<Integer> ints2 = new StackArray<>();
        Stack<String> strings2 = new StackArray<>(5);

        if(ints2.getClass()== strings2.getClass())
            System.out.printf("Stack<Integer> and Stack<String> are both %s!\n", ints2.getClass());
        
        strings2.push("World");
        strings2.push("Hello");

        ints2.push(5);
        ints2.push(3);
        ints2.push(2);
        ints2.push(1);
        ints2.push(1);

        Stack<Integer> ints1 = new DequeStack<>();
        Stack<String> strings1 = new DequeStack<>();

        if(ints1.getClass()== strings1.getClass())
            System.out.printf("Stack<Integer> and Stack<String> are both %s!\n", ints1.getClass());
        
        strings1.push("World");
        strings1.push("Hello");

        ints1.push(5);
        ints1.push(3);
        ints1.push(2);
        ints1.push(1);
        ints1.push(1);

        printStackInteger(ints1);      
        printStackString(strings1);  

    }
}
