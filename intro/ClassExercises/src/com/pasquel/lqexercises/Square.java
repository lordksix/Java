package com.pasquel.lqexercises;

public class Square extends Rectangle{

    public Square(double side) {
		super(side, side);
	}

	public Square(double side, String color, String name) {
		super(side, side, color, name);
	}

}
