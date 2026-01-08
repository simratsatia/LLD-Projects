package usecases;

import entities.parkingspot.ParkingSpot;
import models.DeAllocateParkingSpotRequest;
import models.DeAllocateParkingSpotResponse;
import repositories.ParkingSpotRepsitory;

public class DeAllocateParkingSpotUseCase {
    ParkingSpotRepsitory parkingSpotRepsitory;

    public DeAllocateParkingSpotUseCase(ParkingSpotRepsitory parkingSpotRepsitory) {
        this.parkingSpotRepsitory = parkingSpotRepsitory;
    }

    public DeAllocateParkingSpotResponse execute(DeAllocateParkingSpotRequest request) {
        ParkingSpot parkingSpot = this.parkingSpotRepsitory.findParkingSpotByNumber(request.parkingSpotNumber);
        parkingSpotRepsitory.releaseParkingSpot(parkingSpot);
        return new DeAllocateParkingSpotResponse(true, "Parking spot deallocated successfully");
    }
    
}
