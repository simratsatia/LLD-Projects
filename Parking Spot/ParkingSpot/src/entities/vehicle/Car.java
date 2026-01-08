package entities.vehicle;

public class Car extends Vehicle{
    Car(String licensePlate) {
        super(VehicleType.CAR, licensePlate);
    }
}
