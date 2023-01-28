package transport;

public class SUV extends Cargo implements Convertible{
    private boolean topIsUp;

    public SUV(String name) {
        super(name);
    }

    public SUV() {
        super();
    }

    public SUV(String name, int speed, int gasoline, boolean engineState) {
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
