package models;

import entities.parkingspot.ParkingSpot;

public class AllocateParkingSpotResponse {
    public boolean isSuccess;
    public String message;
    public ParkingSpot parkingSpot;
    public AllocateParkingSpotResponse(boolean isSuccess, ParkingSpot parkingSpot, String message){
        this.isSuccess = isSuccess;
        this.parkingSpot = parkingSpot;
        this.message = message;
    }
    
}
