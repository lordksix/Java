package com.pasquel.calculator;

public class ScientificCalculator {
    public static final double PI = 3.14159;
	private double holdValue;
	
	public static final double exp(double value) {
		return Math.exp(value);
	}
	
	public final double log(double value) {
		return Math.log(value);
	}
	
	public final void putValueMemory (double value) {
		holdValue = value;
	}
	
	public final double getValueMemory() {
		return holdValue;
	}
}
