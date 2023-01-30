package transport;

import exception.SpeedException;

public interface Vehicle {
    public String getName();
    public void setName(String name);

    public int getSpeed();
    public void setSpeed(int speed) throws SpeedException;

    public default void stop(){try {
        setSpeed(0);
    } catch (SpeedException e) {
        e.printStackTrace();
    }}
    public default void slow(){try {
        setSpeed(getSpeed()/2);
    } catch (SpeedException e) {
        e.printStackTrace();
    }}

    public static void travelAtSpeedLimit(Vehicle vehicle){
        try {
            vehicle.setSpeed(55);
        } catch (SpeedException e) {
            e.printStackTrace();
        }
    }
}
