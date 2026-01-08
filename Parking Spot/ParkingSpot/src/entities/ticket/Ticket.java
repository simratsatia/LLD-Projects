package entities.ticket;
import java.time.LocalDateTime;

import entities.parkingspot.ParkingSpot;
import entities.vehicle.Vehicle;

public class Ticket {
    Vehicle vehicle;
    LocalDateTime entryTime;
    ParkingSpot parkingSpot;
    public Ticket(Vehicle vehicle, LocalDateTime entryTime, ParkingSpot parkingSpot){
        this.vehicle = vehicle;
        this.entryTime = entryTime;
        this.parkingSpot = parkingSpot;
    }
}
