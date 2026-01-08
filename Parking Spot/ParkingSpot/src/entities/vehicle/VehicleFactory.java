package entities.vehicle;

public class VehicleFactory{
    public static Vehicle createVehicle(VehicleType type, String licensePlate){
        switch(type){
            case BIKE:
                return new Bike(licensePlate);
            case CAR:
                return new Car(licensePlate);
            default:
                return null;
        }
    }
    
}
