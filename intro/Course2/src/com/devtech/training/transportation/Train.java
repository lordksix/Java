package com.devtech.training.transportation;

public class Train {
    public Train(String name){
        setName(name);
        setWheelSize(60);
        setRPM(300);
    }
    public String getName(){return name;}
    public void setName(String name){this.name=name;}
    public int getRPM(){return rpms;}
    public void setRPM(int rpms1){rpms=rpms1;}
    public int getWheelSize(){return wheel_size;}
    public void setWheelSize(int sz){wheel_size=sz;}

    private String name;
    private int rpms;
    private int wheel_size;
    public int mph;

}
