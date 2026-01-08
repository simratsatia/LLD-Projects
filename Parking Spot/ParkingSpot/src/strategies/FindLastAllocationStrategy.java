package strategies;

import entities.parkingspot.ParkingSpot;
import entities.vehicle.VehicleType;
import java.util.ArrayList;

public class FindLastAllocationStrategy implements ParkingAllocationStrategy {

    public ParkingSpot findAvailableParkingSpot(VehicleType vehicleType, ArrayList<ParkingSpot> parkingSpots) {
        System.out.println("Allocating parking by finding the last available slot");
        return parkingSpots.get(parkingSpots.size() - 1);
    }

}
