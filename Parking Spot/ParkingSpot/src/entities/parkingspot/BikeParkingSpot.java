package entities.parkingspot;

import entities.vehicle.VehicleType;

public class BikeParkingSpot extends ParkingSpot {
    public BikeParkingSpot(int spotNumber){
        super(VehicleType.BIKE, spotNumber);
        this.price = 5;
    }
}
