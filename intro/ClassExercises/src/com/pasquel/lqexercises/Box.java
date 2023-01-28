package com.pasquel.lqexercises;

public class Box  extends Shape implements ThreeDimensional{
    private double length=1;
    private double width=1;
    private double height=1;

    public Box(double length, double width, Double height, String color, String name){
        super();
        setLength(length);
        setWidth(width);
        setHeight(height);
        setColor(color);
        setName(name);
    }
    public Box(double length, double width, double height) {
        super();
        if (length>0){this.length = length;}
        if (width>0){this.width = width;}
        if (height>0){this.height = height;}
        setColor("White");
        setName("unknown");
    }

    Box(double side) {
        this(side, side, side);
        setColor("White");
        setName("unknown");
    }

    /** 
     * @return double
     */
    public double getLength() {
        return length;
    }

    
    /** 
     * @param length
     */
    public void setLength(double length) {
        if (length>0){this.length = length;}else{System.out.println("Length must be greater than 0");}
    }

    
    /** 
     * @return double
     */
    public double getWidth() {
        return width;
    }

    
    /** 
     * @param width
     */
    public void setWidth(double width) {
        if (width>0){this.width = width;}else{System.out.println("Width must be greater than 0");}
    }

    
    /** 
     * @return double
     */
    public double getHeight() {
        return height;
    }

    
    /** 
     * @param height
     */
    public void setHeight(double height) {
        if (height>0){this.height = height;}else{System.out.println("Height must be greater than 0");}
    }

    
    /** 
     * @return double
     */
    public double getVolume() {
        return (height * length * width);
    }

    
    /** 
     * @return double
     */
    public double getSurfaceArea() {
        return ((height * length + height * width + length * width) * 2);
    }

    public void printBox() {
        if (length <= 0 || height <= 0 || width <= 0) {
            System.out.println("Box contains  invalid attributes.");
        } else {
            System.out.println("Length is " + length);
            System.out.println("Heigth is " + height);
            System.out.println("Width is " + width);
            System.out.println("Volume is " + getVolume());
            System.out.println("Surface area is " + getSurfaceArea());
        }
    }
}
