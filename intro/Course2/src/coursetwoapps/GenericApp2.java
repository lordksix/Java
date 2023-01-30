package coursetwoapps;

import demos.Stack;
import demos.StackArray;
import transport.SportsCar;
import transport.Car;

public class GenericApp2 {
    public static Stack<String> makeStackString(Stack<String> strings) {
        strings.push("World");
        strings.push("Hello");
        return strings;
    }

    public static Stack<Integer> makeStackInteger(Stack<Integer> ints) {
    ints.push(5);
    ints.push(3);
    ints.push(2);
    ints.push(1);
    ints.push(1);
    return ints;
    }

    public static Stack<SportsCar> makeStackSportsCar(Stack<SportsCar> cars) {
        cars.push(new SportsCar("Mach I", 250, 21, true));
        cars.push(new SportsCar("Mach II", 250, 21, true));
        cars.push(new SportsCar("Mach III", 250, 21, true));
        cars.push(new SportsCar("Mach IV", 250, 21, true));
        cars.push(new SportsCar("Mach V", 250, 21, true));
        return cars;
    }

    public static void printStackRaw(Stack stack) {
        for (Object e = stack.pop(); e != null; stack.pop()) {
            System.out.println(e);
        }
    }

    public static void raceStackRaw(Stack stack) {
        for (Object e = stack.pop(); e != null; stack.pop()) {
            ((SportsCar)e).race();
        }
    }
    public static void printStackObject(Stack<Object> stack) {
        for (Object e = stack.pop(); e != null; stack.pop()) {
            System.out.println(e);
        }
    }
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
    public static void printStackSportsCar(Stack<SportsCar> stack) {
        for (Object e = stack.pop(); e != null; stack.pop()) {
            System.out.println(e);
        }
    }

    public static void printStackCar(Stack<Car> stack) {
        for (Object e = stack.pop(); e != null; stack.pop()) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        // Create stacks of differents types
        Stack<Integer> ints =  new StackArray<>();
        Stack<String> strings =  new StackArray<>();
        Stack<SportsCar> racers =  new StackArray<>();

        //Print them raw ... it works
        /* printStackRaw(makeStackInteger(ints));
        printStackRaw(makeStackString(strings));
        printStackRaw(makeStackSportsCar(racers)); */

        //But this is why  we use Generics instead of raw types
        //raceStackRaw(makeStackInteger(ints));        // blowsup
        //raceStackRaw(makeStackString(strings));      // blowsup
        //raceStackRaw(makeStackSportsCar(racers));    // actually works;

        //So lets switch to Generics, and since Object defines println
        // we can use Stack<Object>, right? no!!
        //printStackObject(makeStackInteger(ints));      
        //printStackObject(makeStackString(strings));      
        //printStackObject(makeStackSportsCar(racers));    

        // Must be same type
        printStackInteger(makeStackInteger(ints));      
        printStackString(makeStackString(strings));      
        printStackSportsCar(makeStackSportsCar(racers));    

        //Not even this
        //printStackCar(makeStackSportsCar(racers));
    }
}
