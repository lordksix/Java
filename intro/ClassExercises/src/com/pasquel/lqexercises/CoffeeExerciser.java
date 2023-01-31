package com.pasquel.lqexercises;
import static java.lang.System.out;

public class CoffeeExerciser {
    public static void main(String[] args) {
        Coffee coffee = new Coffee();
        try {
            coffee.setTemperature(110);
        } catch (TooHotException e) {
            out.println(e.getMessage());
        }finally{
            out.println("Coffee is set to " + coffee.getTemperature());
        }
    }
}
