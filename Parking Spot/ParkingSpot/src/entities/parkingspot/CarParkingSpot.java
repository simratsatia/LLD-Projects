package entities.parkingspot;

import entities.vehicle.VehicleType;

public class CarParkingSpot extends ParkingSpot{
    public CarParkingSpot(int spotNumber){
        super(VehicleType.CAR, spotNumber);
        this.price = 10;
    }
    
}
