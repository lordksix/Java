package com.pasquel.lqexercises;

public class BoxDriver {
    public static void main(String[] args) {
        Box box1 = new Box(4,5,7);
        Box box2 = new Box(10);

        // Exercise 1
        System.out.println("Box1 length is " + box1.getLength());
        System.out.println("Box1 height is " + box1.getHeight());
        System.out.println("Box1 width is " + box1.getWidth());
        System.out.println("Box2 length is " + box2.getLength());
        System.out.println("Box2 height is " + box2.getHeight());
        System.out.println("Box2 width is " + box2.getWidth());

        //Exercise 2

        box1.setLength(3);
        box1.setHeight(5);
        box1.setWidth(4);
        box1.printBox();

        box1.setLength(-3);
        box1.setHeight(5);
        box1.setWidth(4);
        box1.printBox();

        Box box3 = new Box(7, -4, 0);
        box3.printBox();
    }
}
