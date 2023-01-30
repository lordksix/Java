package coursetwoapps;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

import exception.SpeedException;
import transport.Car;
import transport.SUV;
import transport.SportsCar;
import transport.StationWagon;
import transport.Train;
import transport.Vehicle;

public class VehicleApp {

    public static void race(Object[] raceCars) {
        for(Object raceCar: raceCars){
            if(raceCar instanceof SportsCar){SportsCar racer = (SportsCar) raceCar; racer.race();}}}

    public static void race(SportsCar[] raceCars) {
        for(SportsCar raceCar: raceCars){raceCar.race();}}

    public static void race(Collection<SportsCar> raceCars) {
        for(SportsCar raceCar: raceCars){raceCar.race();}}

    public static List<Vehicle> populate() {      
        List<Vehicle> vehicles = new ArrayList<>();
        try {
            Car car54 = new SportsCar("Car 54");
            car54.setSpeed(20);
            car54.setSpeed(20);
            car54.setManufactured(LocalDate.of(1961, 9, 17));
            vehicles.add(car54);
        } catch (SpeedException e) {
            e.printStackTrace();
        }
    
        try {
            Car mach5 = new SportsCar("Mach V",250,21,true);
            mach5.setManufactured(LocalDate.of(1967, 4, 2));
            vehicles.add(mach5);
        } catch (SpeedException e) {
            e.printStackTrace();
        }

        try {
            StationWagon wagon = new StationWagon("Wagon Queen Family Truckster");
            wagon.setManufactured(LocalDate.of(1979, 7, 29));
            wagon.setCurrenntCargoLoad(500);
            wagon.setSpeed(75);
            vehicles.add(wagon);
        } catch (SpeedException e) {
            e.printStackTrace();
        }

        try {
            SUV suv = new SUV("Jurrasic Explorer");
            suv. setManufactured(LocalDate.of(1993, 6, 11));
            suv.setCurrenntCargoLoad(500);
            suv.setSpeed(25);
            vehicles.add(suv);
        } catch (SpeedException e) {
            e.printStackTrace();
        }

        Train train = new Train("City of New Orleans");
        vehicles.add(train);

        return vehicles;
    }

    public static void main(String[] args) {
  /* 
        System.out.println("\\\\\\\\----------------------------////");
        for (Vehicle vehicle : new Vehicle[]{car54,mach5,wagon,suv,train}) {
            if(vehicle instanceof SportsCar){
                ((SportsCar) vehicle).race();
            }else{
                Vehicle.travelAtSpeedLimit(vehicle);
            }
            System.out.println(vehicle);
        }
        System.out.println("\\\\\\\\----------------------------////");
        for (Vehicle vehicle : new Vehicle[]{car54,mach5,wagon,suv,train}) {
            vehicle.stop();
            System.out.println(vehicle);
        } */

        // List<Vehicle> vehicles = Arrays.asList(car54,mach5,wagon,suv,train);

        List<Vehicle> vehicles = populate();

        /* vehicles.forEach(vehicle -> {
            if (vehicle instanceof SportsCar)((SportsCar) vehicle).race();
            else  Vehicle.travelAtSpeedLimit(vehicle);   
        });

        vehicles.forEach(vehicle -> System.out.println(vehicle));

        System.out.println("\\\\\\\\----------------------------////");

        //More method reference as shorthand
        vehicles.forEach(Vehicle::travelAtSpeedLimit);
        vehicles.forEach(System.out::println);

        System.out.println("\\\\\\\\----------------------------////");

        vehicles.forEach(Vehicle::stop);
        vehicles.forEach(System.out::println);

        //go with flesh out with lambda
        vehicles.forEach(vehicle->vehicle.setName(25));
        vehicles.forEach(vehicle->System.out.printf("%s is traveling at %d mph %n", vehicle.getName()));
         */

         Consumer<Vehicle> go25 = vehicle ->{
            try {
                vehicle.setSpeed(25);
            } catch (SpeedException e) {
                e.printStackTrace();
            }
        };
         vehicles.forEach(go25.andThen(System.out::println));

         Collection<Integer> speeds = Arrays.asList(12,25,35,55,70);
         Consumer<Vehicle> stop = Vehicle::stop;

         speeds.forEach(speed->{
            Consumer<Vehicle> setSpeed = vehicle -> {
                try {
                    vehicle.setSpeed(speed);
                } catch (SpeedException e) {
                    e.printStackTrace();
                }
            };
            vehicles.forEach(setSpeed.andThen(System.out::println));
            Consumer<Vehicle> speedAndPrint = setSpeed.andThen(System.out::println);
            vehicles.forEach(speedAndPrint.andThen(stop).andThen(System.out::println));
         });

         SportsCar mach5;
         try {
            mach5 = new SportsCar("Mach V",250,21,true);
            mach5.setManufactured(LocalDate.of(1967, 4, 2));
            vehicles.add(mach5);
            mach5.race();
        } catch (SpeedException e) {
            e.printStackTrace();
        }
        

    }
}
