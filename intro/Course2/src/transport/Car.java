package transport;
import java.time.LocalDate;
import java.time.Period;
import static java.lang.Math.min;

public abstract class Car implements Vehicle{
    private String name;
    protected int speed;
    private int gasoline;
    private boolean engineState;
    private LocalDate manufactured;

    //private static final int MAX_SPEED = 350;
    public abstract int getMaxSpeed();
    
    public Car(String name) {
        this(name,0,0,false);
    }
    public Car(){}    

    public Car(String name, int speed, int gasoline, boolean engineState) {
        setName(name);
        setSpeed(speed);
        setGasoline(gasoline);
        setEngineState(engineState);
    }



    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof Car)){return false;}

        Car other = (Car) obj;

        return name.equals(other.getName());
    }
    @Override
    public int hashCode() {
        return name.hashCode();
    }
    @Override
    public String toString() {
        if(manufactured == null){return String.format("%s is  at  %d mph", getName(), getSpeed());}
		else{return String.format("%s is %d years old and is  at  %d mph", getName(), getAge(), getSpeed());}
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    

    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        //this.speed = min(speed,MAX_SPEED);
        this.speed = min(speed,getMaxSpeed());
    }
    
    public int getGasoline() {
        return gasoline;
    }
    public void setGasoline(int gasoline) {
        this.gasoline = gasoline;
    }
    public boolean isEngineState() {
        return engineState;
    }
    public void setEngineState(boolean engineState) {
        this.engineState = engineState;
    }
   
    public LocalDate getManufactured() {
        return manufactured;
    }
    public void setManufactured(LocalDate manufactured) {
        this.manufactured = manufactured;
    }
    public int getAge() {
		return Period.between(manufactured, LocalDate.now()).getYears();
	}
    
}
