
import entities.parkingspot.ParkingSpotFactory;
import entities.vehicle.Vehicle;
import entities.vehicle.VehicleFactory;
import entities.vehicle.VehicleType;
import models.AllocateParkingSpotRequest;
import models.AllocateParkingSpotResponse;
import models.DeAllocateParkingSpotRequest;
import models.DeAllocateParkingSpotResponse;
import repositories.InMemoryParkingSpotRepository;
import repositories.ParkingSpotRepsitory;
import strategies.ParkingAllocationStrategyFactory;
import usecases.AllocateParkingSpotUseCase;
import usecases.DeAllocateParkingSpotUseCase;

public class App {

    public static class Controller{
        private AllocateParkingSpotUseCase allocateParkingSpotUseCase;
        private DeAllocateParkingSpotUseCase deAllocateParkingSpotUseCase;
        
        public Controller(AllocateParkingSpotUseCase allocateParkingSpotUseCase, DeAllocateParkingSpotUseCase deAllocateParkingSpotUseCase){
            this.allocateParkingSpotUseCase = allocateParkingSpotUseCase;
            this.deAllocateParkingSpotUseCase = deAllocateParkingSpotUseCase;
        }

        public void allocateParkingSpot(Vehicle vehicle){
            AllocateParkingSpotRequest allocateParkingSpotRequest = new AllocateParkingSpotRequest(vehicle);
            AllocateParkingSpotResponse response = allocateParkingSpotUseCase.execute(allocateParkingSpotRequest);
            if (response.isSuccess) {System.out.println(response.message + " parking spot: " + response.parkingSpot.getSpotNumber());}
            else {System.out.println(response.message);}
        }

        public void deAllocateParkingSpot(int spotNumber){
            DeAllocateParkingSpotRequest allocateParkingSpotRequest = new DeAllocateParkingSpotRequest(spotNumber);
            DeAllocateParkingSpotResponse response = deAllocateParkingSpotUseCase.execute(allocateParkingSpotRequest);
            if (response.isReleased) {System.out.println(response.message);}
            else {System.out.println(response.message);}
        }
    }


    public static void main(String[] args) throws Exception {
        //create vehicle using factory
        Vehicle car1 = VehicleFactory.createVehicle(VehicleType.CAR, "KA-01-HH-1234");
        Vehicle bike1 = VehicleFactory.createVehicle(VehicleType.BIKE, "KA-01-HH-1235");
        
        //create repository
        ParkingSpotRepsitory parkingSpotRepository = new InMemoryParkingSpotRepository(ParkingAllocationStrategyFactory.getParkingAllocationStrategy("FindLast"));
        parkingSpotRepository.addParkingSpot(ParkingSpotFactory.createParkingSpot(VehicleType.BIKE, 0));
        parkingSpotRepository.addParkingSpot(ParkingSpotFactory.createParkingSpot(VehicleType.BIKE, 1));
        parkingSpotRepository.addParkingSpot(ParkingSpotFactory.createParkingSpot(VehicleType.BIKE, 2));
        parkingSpotRepository.addParkingSpot(ParkingSpotFactory.createParkingSpot(VehicleType.BIKE, 3));

        parkingSpotRepository.addParkingSpot(ParkingSpotFactory.createParkingSpot(VehicleType.CAR,  4));
        parkingSpotRepository.addParkingSpot(ParkingSpotFactory.createParkingSpot(VehicleType.CAR,  5));
        parkingSpotRepository.addParkingSpot(ParkingSpotFactory.createParkingSpot(VehicleType.CAR,  6));
        parkingSpotRepository.addParkingSpot(ParkingSpotFactory.createParkingSpot(VehicleType.CAR,  7));

        //Create usecases
        AllocateParkingSpotUseCase allocateParkingSpotUseCase = new AllocateParkingSpotUseCase(parkingSpotRepository);
        DeAllocateParkingSpotUseCase deAllocateParkingSpotUseCase = new DeAllocateParkingSpotUseCase(parkingSpotRepository);

        //create controller
        Controller controller = new Controller(allocateParkingSpotUseCase, deAllocateParkingSpotUseCase);
        controller.allocateParkingSpot(car1);
        controller.allocateParkingSpot(bike1);
        controller.deAllocateParkingSpot(0);
        controller.deAllocateParkingSpot(3);
        
    }
}
