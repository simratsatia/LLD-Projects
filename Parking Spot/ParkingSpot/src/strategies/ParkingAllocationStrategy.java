package strategies;

import entities.parkingspot.ParkingSpot;
import entities.vehicle.VehicleType;
import java.util.ArrayList;

public interface ParkingAllocationStrategy {
    public ParkingSpot findAvailableParkingSpot(VehicleType vehicleType, ArrayList<ParkingSpot> parkingSpots);
}
