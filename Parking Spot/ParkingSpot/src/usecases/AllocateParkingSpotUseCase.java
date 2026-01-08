package usecases;

import entities.parkingspot.ParkingSpot;
import models.AllocateParkingSpotRequest;
import models.AllocateParkingSpotResponse;
import repositories.ParkingSpotRepsitory;
import strategies.ParkingAllocationStrategy;

public class AllocateParkingSpotUseCase {
    ParkingSpotRepsitory parkingSpotRepsitory;
    ParkingAllocationStrategy parkingAllocationStrategy;

    public AllocateParkingSpotUseCase(ParkingSpotRepsitory parkingSpotRepsitory) {
        this.parkingSpotRepsitory = parkingSpotRepsitory;

    }

    public AllocateParkingSpotResponse execute(AllocateParkingSpotRequest request) {
        ParkingSpot parkingSpot = this.parkingSpotRepsitory.findAvailableParkingSpot(request.vehicle.getType());
        if (parkingSpot == null) {
            return new AllocateParkingSpotResponse(false, null, "No available parking spot");
        }
        this.parkingSpotRepsitory.allocateParkingSpot(parkingSpot);
        return new AllocateParkingSpotResponse(true, parkingSpot, "Parking spot allocated successfully");
    }
    
    
}
