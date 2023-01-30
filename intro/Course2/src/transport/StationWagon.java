package transport;

import exception.SpeedException;

public class StationWagon extends Cargo {

    public StationWagon(String name) throws SpeedException {
        super(name);
    }

    public StationWagon() {
        super();
    }

    public StationWagon(String name, int speed, int gasoline, boolean engineState) throws SpeedException {
        super(name, speed, gasoline, engineState);
    }

    @Override
    public void setSpeed(int speed) throws SpeedException {
        if (speed>50) speed=50;
        super.setSpeed(speed);
    }

    

}
