package transport;

public interface Vehicle {
    public String getName();
    public void setName(String name);

    public int getSpeed();
    public void setSpeed(int speed);

    public default void stop(){setSpeed(0);}
    public default void slow(){setSpeed(getSpeed()/2);}

    public static void travelAtSpeedLimit(Vehicle vehicle){
        vehicle.setSpeed(55);
    }
}
