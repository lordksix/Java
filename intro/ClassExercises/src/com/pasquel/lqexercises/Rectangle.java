package com.pasquel.lqexercises;

public class Rectangle extends Shape implements TwoDimensional{
    private double length;
    private double width;

    public Rectangle(double length, double width) {
        this(length,width,"White","Unknown");
    }

    public Rectangle(double length, double width, String color, String name){
        super();
        setLength(length);
        setWidth(width);
        setColor(color);
        setName(name);
    }

    public double getLength() {
        return length;
    }
    public void setLength(double length) {
        this.length = length;
    }
    public double getWidth() {
        return width;
    }
    public void setWidth(double width) {
        this.width = width;
    }

    public double getArea(){
        return getLength()*getWidth();
    }
    
    public double getPerimeter(){
        return (getLength()+getWidth())*2;
    }

    @Override
    public String toString() {
        return "Rectangle [length=" + getLength() + ", width=" + getWidth() + ", color=" + getColor() +", name=" + getName() +"]";
    }

    

}
