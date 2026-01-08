package models;

public class DeAllocateParkingSpotResponse {
    public boolean isReleased;
    public String message;
    public DeAllocateParkingSpotResponse(boolean isReleased, String message) {
        this.isReleased = isReleased;
        this.message = message;
    }
    
}
