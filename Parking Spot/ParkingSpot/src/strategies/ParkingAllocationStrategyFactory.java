package strategies;

public class ParkingAllocationStrategyFactory {
    public static ParkingAllocationStrategy getParkingAllocationStrategy(String strategyType) {
        if (strategyType.equals("FindFirst")) {
            return new FindFirstAllocationStrategy();
        } else {
            return new FindLastAllocationStrategy();
        }
    }
}
