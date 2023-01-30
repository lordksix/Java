package transport;

import exception.SpeedException;

public class SUV extends Cargo implements Convertible{
    private boolean topIsUp;

    public SUV(String name) throws SpeedException {
        super(name);
    }

    public SUV() {
        super();
    }

    public SUV(String name, int speed, int gasoline, boolean engineState) throws SpeedException {
        super(name, speed, gasoline, engineState);
    }

    @Override
    public void putTopDown() {
        topIsUp=false;
        
    }

    @Override
    public void putTopUp() {
        topIsUp=true;
        
    }

    public boolean isTopUp(){
        return topIsUp;
    } 
}
