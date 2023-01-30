package transport;

import exception.SpeedException;

public class SportsCar extends Car implements Convertible{
    private boolean topIsUp;
    
    public SportsCar(String name) throws SpeedException {
        super(name);
    }
    

    public SportsCar() {
        super();
    }


    public SportsCar(String name, int speed, int gasoline, boolean engineState) throws SpeedException {
        super(name, speed, gasoline, engineState);
    }

    @Override
    public void setSpeed(int speed) throws SpeedException {
        super.setSpeed((int)(speed*1.1));
    }

    protected double speed;

    public void setSpeed(double newSpeed){
        this.speed = newSpeed;
    }

    @Override
    public int getMaxSpeed() {       
        return 350;
    }
    
    public void race() {
    	try {
            setSpeed(getMaxSpeed());
        } catch (SpeedException e) {
            System.out.println("Unexpected error: we tried to race, but we were told that the maximum speed was too fast. See stack track for more details.");
            e.printStackTrace();
        }
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
