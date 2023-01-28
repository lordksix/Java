package com.pasquel.lqexercises;

public class Cube extends Box{

    public Cube(double side) {
        super(side);
        setColor("White");
        setName("unknown");
    }

    public Cube(double sides, String color, String name){
        super(sides);
        setSides(sides);
        setColor(color);
        setName(name);
    }

    @Override
    public void setHeight(double height) {
        if(super.getHeight()!=height)
        setSides(height);
    }

    @Override
    public void setLength(double length) {
        if (super.getLength()!=length)
        setSides(length);
    }

    @Override
    public void setWidth(double width) {
        if (super.getWidth()!=width)
        setSides(width);
    }

    public void setSides( double sides){
        if(sides>0){
            super.setHeight(sides);
            super.setLength(sides);
            super.setWidth(sides);
        }else{System.err.println("Passed value must be greater than zero  -- Cube unchanged");}
    }

    public double getSide(){
        return super.getHeight();
    }
    
}
