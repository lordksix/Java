package transport;

public class StationWagon extends Cargo {

    public StationWagon(String name) {
        super(name);
    }

    public StationWagon() {
        super();
    }

    public StationWagon(String name, int speed, int gasoline, boolean engineState) {
        super(name, speed, gasoline, engineState);
    }

    @Override
    public void setSpeed(int speed) {
        if (speed>50) speed=50;
        super.setSpeed(speed);
    }

    

}
