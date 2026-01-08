package models;

import entities.vehicle.Vehicle;

public class AllocateParkingSpotRequest {
    public Vehicle vehicle;

    public AllocateParkingSpotRequest(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
    
}
