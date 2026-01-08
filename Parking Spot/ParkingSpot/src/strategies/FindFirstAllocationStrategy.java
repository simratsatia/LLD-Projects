package strategies;

import entities.parkingspot.ParkingSpot;
import entities.vehicle.VehicleType;
import java.util.ArrayList;

public class FindFirstAllocationStrategy implements ParkingAllocationStrategy {
    public ParkingSpot findAvailableParkingSpot(VehicleType vehicleType, ArrayList<ParkingSpot> parkingSpots) {
        System.out.println("Allocating parking by finding the first available slot");
        return parkingSpots.get(0);
    }
    
}
