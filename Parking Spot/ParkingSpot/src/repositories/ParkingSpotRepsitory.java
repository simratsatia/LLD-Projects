package repositories;

import entities.parkingspot.ParkingSpot;
import entities.vehicle.VehicleType;
import strategies.ParkingAllocationStrategy;

public abstract class ParkingSpotRepsitory {
    ParkingAllocationStrategy parkingAllocationStrategy;
    public abstract ParkingSpot findAvailableParkingSpot(VehicleType vehicleType);
    public abstract void allocateParkingSpot(ParkingSpot parkingSpot);
    public abstract void releaseParkingSpot(ParkingSpot parkingSpot);
    public abstract void addParkingSpot(ParkingSpot parkingSpot);
    public abstract void removeParkingSpot(ParkingSpot parkingSpot);
    public abstract ParkingSpot findParkingSpotByNumber(int spotNumber);
    
}
