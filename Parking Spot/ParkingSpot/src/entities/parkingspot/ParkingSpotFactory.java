package entities.parkingspot;

import entities.vehicle.VehicleType;

public class ParkingSpotFactory {

    public static ParkingSpot createParkingSpot(VehicleType type, int spotNumber){
        switch(type){
            case BIKE:
                return new BikeParkingSpot(spotNumber);
            case CAR:
                return new CarParkingSpot(spotNumber);
            default:
                return null;
        }
    }
    
}
