package entities.parkingspot;

import entities.vehicle.VehicleType;

public class ParkingSpot {
    boolean isOccupied;
    int price;
    int spotNumber;
    VehicleType type;

    ParkingSpot(VehicleType type, int spotNumber) {
        this.type = type;
        this.spotNumber = spotNumber;
        this.isOccupied = false;
        this.price = 0;
    }

    // add getters and setters
    public boolean isOccupied() {
        return isOccupied;
    }

    public void occupy() {
        this.isOccupied = true;
    }

    public void release() {
        this.isOccupied = false;
    }

    public int getPrice() {
        return price;
    }

    public int getSpotNumber() {
        return spotNumber;
    }
    
    public VehicleType getVehicleType(){
        return type;
    }

}
