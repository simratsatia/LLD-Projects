package repositories;
import entities.parkingspot.ParkingSpot;
import entities.vehicle.VehicleType;
import java.util.*;
import strategies.ParkingAllocationStrategy;

public class InMemoryParkingSpotRepository extends ParkingSpotRepsitory{
    TreeMap<Integer, ParkingSpot> parkingSpots;
    public InMemoryParkingSpotRepository(ParkingAllocationStrategy parkingAllocationStrategy) {
        parkingSpots = new TreeMap<>();
        this.parkingAllocationStrategy = parkingAllocationStrategy;
    }
    public ParkingSpot findAvailableParkingSpot(VehicleType vehicleType) {
        // Find available parking spot in memory
        //find all available partking spots that are not occupied, put them in arraylist
        ArrayList<ParkingSpot> availableParkingSpots = new ArrayList<>();
        for (ParkingSpot spot : parkingSpots.values()) {
            if (!spot.isOccupied() && spot.getVehicleType() == vehicleType) {
                availableParkingSpots.add(spot);
            }
        }
        return this.parkingAllocationStrategy.findAvailableParkingSpot(vehicleType, availableParkingSpots);
    }

    @Override
    public void allocateParkingSpot(ParkingSpot parkingSpot) {
        // Allocate parking spot in memory
        ParkingSpot spot = parkingSpots.get(parkingSpot.getSpotNumber());
        spot.occupy();
        parkingSpots.put(spot.getSpotNumber(), spot);
    }

    public void releaseParkingSpot(ParkingSpot parkingSpot) {
        // Release parking spot in memory
        ParkingSpot spot = parkingSpots.get(parkingSpot.getSpotNumber());
        spot.release();
        parkingSpots.put(spot.getSpotNumber(), spot);
    }

    public void addParkingSpot(ParkingSpot parkingSpot) {
        // Add parking spot in memory
        parkingSpots.put(parkingSpot.getSpotNumber(), parkingSpot);
        
    }

    public void removeParkingSpot(ParkingSpot parkingSpot) {
        // Remove parking spot in memory
        parkingSpots.remove(parkingSpot.getSpotNumber());
    }

    public ParkingSpot findParkingSpotByNumber(int spotNumber) {
        // Find parking spot by number in memory
        return parkingSpots.get(spotNumber);
    }
    
}
