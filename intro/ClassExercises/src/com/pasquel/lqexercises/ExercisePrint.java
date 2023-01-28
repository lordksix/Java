package com.pasquel.lqexercises;

import static java.lang.System.out;

public class ExercisePrint {
    public static void main(String[] args) {
        Shape[] shapes = new Shape[5];

        shapes[0]= new Rectangle(5.0, 4.0,"blue","rectangle1");
        shapes[1]= new Square(8.0, "Red", "Square1");
        shapes[2]= new Cube(5.0,"Yellow","Cube1");
        shapes[3]= new Box(12.0,15.0,6.0,"Orange","Box1");
        shapes[4]= new Circle(3.0,"Lavender","Circle1");

        for (Shape shape : shapes) {
            out.println(shape);
        }
    }
    
}
