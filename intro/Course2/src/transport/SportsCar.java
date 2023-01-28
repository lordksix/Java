package transport;

public class SportsCar extends Car implements Convertible{
    private boolean topIsUp;
    
    public SportsCar(String name) {
        super(name);
    }
    

    public SportsCar() {
        super();
    }


    public SportsCar(String name, int speed, int gasoline, boolean engineState) {
        super(name, speed, gasoline, engineState);
    }

    @Override
    public void setSpeed(int speed) {
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
    	setSpeed(getMaxSpeed());
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
