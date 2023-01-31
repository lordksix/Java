package com.pasquel.lqexercises;

public class Coffee {
    private int temperature;

    public Coffee(int temperature) throws TooHotException {
        super();
        this.setTemperature(temperature);
    }

    public Coffee() {
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) throws TooHotException{
        if(temperature>120){throw new TooHotException("Coffee is too hot");}
        else{this.temperature = temperature;}  
    }    
}
