package com.pasquel.lqexercises;

public abstract class Shape {
    private String name;
    private String color;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Shape [color: ");
        builder.append(color);
        builder.append(", name: ");
        builder.append(name);
        builder.append("]");
        return builder.toString();
    }
    
    
}
