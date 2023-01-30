package transport;

import exception.SpeedException;

public abstract class Cargo extends Car{

    public Cargo(String name) throws SpeedException {
        super(name);
    }
    

    public Cargo() {
        super();
    }


    public Cargo(String name, int speed, int gasoline, boolean engineState) throws SpeedException {
        super(name, speed, gasoline, engineState);
    }
    public void setCurrenntCargoLoad(int currentCargoLoad) {
        this.currentCargoLoad = currentCargoLoad;
    }

    public int getCurrentCargoLoad() {
        return currentCargoLoad;
    }

    @Override
    public void setSpeed(int speed) throws SpeedException {
        super.setSpeed(speed - getCurrentCargoLoad()/100);
    }

    private int cargoCapacity;
    private int currentCargoLoad;
    
    @Override
    public int getMaxSpeed() {
        return 100;
    }

}
