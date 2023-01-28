package com.pasquel.lqexercises;

public class CubeDriver {
    public static void main(String[] args) {
        Cube cube1 = new Cube(5);
        Cube cube2 = new Cube(8);

        System.out.println("Cube 1 height = " + cube1.getHeight());
        System.out.println("Cube 1 lenght = " + cube1.getLength());
        System.out.println("Cube 1 width = " + cube1.getWidth());
        System.out.println();

        System.out.println("Cube 2 height = " + cube2.getHeight());
        System.out.println("Cube 2 lenght = " + cube2.getLength());
        System.out.println("Cube 2 width = " + cube2.getWidth());
        System.out.println();

        cube1.setLength(20);
        cube1.printBox();
        System.out.println();

        cube1.setSides(40);
        cube1.printBox();
        System.out.println();

        cube2.setWidth(-5);
        cube2.printBox();
    }
}
