package com.pasquel.calculator;

public class CalculatorDriver {
    public static void main(String[] args) {
        BasicCalculator bc = new BasicCalculator();

        System.out.println(" 5*7 = " + bc.multiply(5, 7));

        ScientificCalculator sc = new ScientificCalculator();
        System.out.println("Exponent of 20= " + ScientificCalculator.exp(20));
        System.out.println("Log of 100= " + sc.log(100));

        TrigonometricCalculator tc = new TrigonometricCalculator();
        System.out.println("sin of pi/2= " + TrigonometricCalculator.sine(ScientificCalculator.PI/2));
    }
}
