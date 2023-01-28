package coursetwoapps;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import transport.Car;
import transport.SUV;
import transport.SportsCar;
import transport.StationWagon;
import transport.Train;
import transport.Vehicle;

public class VehicleApp {

    public static void main(String[] args) {

        
        Car car54 = new SportsCar("Car 54");
        car54.setSpeed(20);
        car54.setSpeed(20);
        car54.setManufactured(LocalDate.of(1961, 9, 17));

        Car mach5 = new SportsCar("Mach V",250,21,true);
        mach5.setManufactured(LocalDate.of(1967, 4, 2));

        StationWagon wagon = new StationWagon("Wagon Queen Family Truckster");
        wagon.setManufactured(LocalDate.of(1979, 7, 29));
        wagon.setCurrenntCargoLoad(500);
        wagon.setSpeed(75);

        SUV suv = new SUV("Jurrasic Explorer");
        suv. setManufactured(LocalDate.of(1993, 6, 11));
        suv.setCurrenntCargoLoad(500);
        suv.setSpeed(25);

        Train train = new Train("City of New Orleans");

        for (Car car : new Car[]{car54,mach5,wagon,suv}) {
            System.out.println(car);            
        }

        SportsCar sc = new SportsCar();
        Car c = sc;
        sc.race();
        System.out.println("\\\\\\\\----------------------------////");
/*         for (Vehicle vehicle : new Vehicle[]{car54,mach5,wagon,suv,train}) {
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
        List<Vehicle> vehicles = Arrays.asList(car54,mach5,wagon,suv,train);
        vehicles.forEach(vehicle -> {
            if (vehicle instanceof SportsCar)((SportsCar) vehicle).race();
            else  Vehicle.travelAtSpeedLimit(vehicle);   
        });
        vehicles.forEach(vehicle -> System.out.println(vehicle));
        System.out.println("\\\\\\\\----------------------------////");
        vehicles.forEach(Vehicle::travelAtSpeedLimit);
        vehicles.forEach(System.out::println);
        System.out.println("\\\\\\\\----------------------------////");
        vehicles.forEach(Vehicle::stop);
        vehicles.forEach(System.out::println);
    }
}
