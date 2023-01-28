package com.pasquel.lqexercises;

public class Circle extends Shape implements TwoDimensional {
    private double radius;

    public Circle(double radius) {
        this(radius,"White","Unknown");
    }

    public Circle(double radius, String color, String name){
        setRadius(radius);
        setColor(color);
        setName(name);
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return radius*radius*Math.PI;
    }

    @Override
    public double getPerimeter() {
        return 2*Math.PI*radius;
    }

    @Override
    public String toString() {
        return "Circle [radius=" + getRadius() + ", color=" + getColor() +", name=" + getName() + "]";
    } 
    
}
