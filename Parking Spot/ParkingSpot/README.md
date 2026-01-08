# Parking Lot System - Low Level Design

A Java-based parking lot management system demonstrating clean architecture principles, design patterns, and SOLID principles for a Low Level Design (LLD) interview problem.

## What This Project Is About

This is a complete implementation of a parking lot system that handles:
- Vehicle entry and exit management
- Dynamic parking spot allocation based on vehicle type (Car/Bike)
- Pluggable allocation strategies (FindFirst, FindLast)
- Type-safe parking with dedicated spot types for different vehicle types
- In-memory storage with repository pattern

This project serves as a practice implementation for system design interviews and showcases proficiency in object-oriented design, design patterns, and clean code architecture.

## What It Does

The system provides the following functionality:

### Core Features
1. **Allocate Parking Spot**: Assigns an available parking spot to a vehicle based on its type
2. **Deallocate Parking Spot**: Releases a parking spot when a vehicle exits
3. **Type-based Allocation**: Ensures cars only park in car spots and bikes only park in bike spots
4. **Strategy-based Allocation**: Supports multiple allocation strategies:
   - **FindFirst**: Allocates the first available spot
   - **FindLast**: Allocates the last available spot
5. **Spot Management**: Add/remove parking spots dynamically

### Current Capabilities
- Creates vehicles with license plates using VehicleFactory
- Manages 4 bike parking spots (spots 0-3, price: $5 each)
- Manages 4 car parking spots (spots 4-7, price: $10 each)
- Allocates spots based on configurable strategy
- Tracks spot occupancy status
- Provides success/failure feedback for operations

## Project Structure

```
ParkingSpot/
├── src/
│   ├── entities/
│   │   ├── vehicle/
│   │   │   ├── Vehicle.java              # Base vehicle class
│   │   │   ├── Car.java                  # Car entity
│   │   │   ├── Bike.java                 # Bike entity
│   │   │   ├── VehicleType.java          # Enum (CAR, BIKE)
│   │   │   └── VehicleFactory.java       # Factory for vehicle creation
│   │   ├── parkingspot/
│   │   │   ├── ParkingSpot.java          # Base parking spot class
│   │   │   ├── CarParkingSpot.java       # Car-specific spot
│   │   │   ├── BikeParkingSpot.java      # Bike-specific spot
│   │   │   └── ParkingSpotFactory.java   # Factory for spot creation
│   │   └── ticket/
│   │       └── Ticket.java               # Ticket entity (basic implementation)
│   ├── models/
│   │   ├── AllocateParkingSpotRequest.java
│   │   ├── AllocateParkingSpotResponse.java
│   │   ├── DeAllocateParkingSpotRequest.java
│   │   └── DeAllocateParkingSpotResponse.java
│   ├── repositories/
│   │   ├── ParkingSpotRepsitory.java     # Abstract repository
│   │   └── InMemoryParkingSpotRepository.java
│   ├── strategies/
│   │   ├── ParkingAllocationStrategy.java
│   │   ├── FindFirstAllocationStrategy.java
│   │   ├── FindLastAllocationStrategy.java
│   │   └── ParkingAllocationStrategyFactory.java
│   ├── usecases/
│   │   ├── AllocateParkingSpotUseCase.java
│   │   └── DeAllocateParkingSpotUseCase.java
│   └── App.java                          # Main entry point with Controller
└── README.md
```

## Clean Code Patterns & Design Principles

This implementation follows multiple clean code patterns and SOLID principles:

### 1. **Clean Architecture / Use Case Pattern**
- **Separation of Concerns**: Business logic (usecases) is separated from data access (repositories) and presentation (controller)
- **Use Cases**: `AllocateParkingSpotUseCase` and `DeAllocateParkingSpotUseCase` orchestrate business operations
- **Dependencies flow inward**: Controller → Use Cases → Repository → Entities

```
App.Controller → Use Cases → Repository → Entities
                                ↓
                           Strategies
```

### 2. **Factory Pattern**
Three factories provide centralized object creation:
- **VehicleFactory**: Creates Car/Bike instances based on VehicleType
- **ParkingSpotFactory**: Creates CarParkingSpot/BikeParkingSpot based on VehicleType
- **ParkingAllocationStrategyFactory**: Creates allocation strategy instances

**Benefits**:
- Encapsulates complex object creation
- Single point of modification for object instantiation
- Follows Open/Closed Principle

### 3. **Strategy Pattern**
The `ParkingAllocationStrategy` interface allows different allocation algorithms:
- `FindFirstAllocationStrategy`: Returns first available spot
- `FindLastAllocationStrategy`: Returns last available spot

**Benefits**:
- Algorithms are interchangeable at runtime
- Easy to add new strategies without modifying existing code
- Follows Open/Closed Principle

### 4. **Repository Pattern**
Abstract `ParkingSpotRepsitory` with concrete `InMemoryParkingSpotRepository`:
- **Abstraction**: Hides data access details from business logic
- **Flexibility**: Can easily swap to database-backed implementation
- **Testability**: Can mock repository for unit testing

### 5. **Request/Response Pattern (DTOs)**
All use cases communicate via dedicated request/response objects:
- Decouples use case interface from internal implementation
- Makes API explicit and type-safe
- Easy to extend with new fields

### 6. **Inheritance & Polymorphism**
- Base classes (`Vehicle`, `ParkingSpot`) define common behavior
- Subclasses (`Car`, `Bike`, `CarParkingSpot`, `BikeParkingSpot`) specialize
- Type-safe operations through polymorphism

### 7. **SOLID Principles**

#### Single Responsibility Principle (SRP)
- Each class has one reason to change
- Entities handle data, Use Cases handle business logic, Repositories handle storage

#### Open/Closed Principle (OCP)
- Easy to add new vehicle types, spot types, or allocation strategies
- No need to modify existing code

#### Liskov Substitution Principle (LSP)
- Subclasses (Car, Bike) can replace base class (Vehicle) without breaking functionality
- Same for ParkingSpot hierarchy

#### Interface Segregation Principle (ISP)
- `ParkingAllocationStrategy` has single focused method
- Clients depend only on what they need

#### Dependency Inversion Principle (DIP)
- Use cases depend on abstract `ParkingSpotRepsitory`, not concrete implementation
- Strategies injected via constructor (Dependency Injection)

### 8. **Encapsulation**
- Entities have private fields with public getters
- State changes through methods (`occupy()`, `release()`)
- Business logic encapsulated in use cases

## How to Run

### Prerequisites
- Java JDK 8 or higher
- Terminal/Command Prompt

### Compilation

Navigate to the project root directory and compile all Java files:

```bash
cd "ParkingSpot"
javac -d bin src/**/*.java src/*.java
```

This will compile all source files and place the class files in the `bin` directory.

### Execution

Run the main application:

```bash
cd bin
java App
```

### Expected Output

```
Successfully allocated parking spot: 7
Successfully allocated parking spot: 3
Successfully released parking spot: 3
No parking spot with number: 0 is occupied
```

**Note**: The output shows spot 7 for car and spot 3 for bike because the system is configured with "FindLast" strategy. The last deallocate fails because spot 0 was never allocated (only spot 3 was).

### Modifying Allocation Strategy

In `App.java` (line 49), change the strategy:

```java
// For FindFirst strategy:
ParkingSpotRepsitory parkingSpotRepository = new InMemoryParkingSpotRepository(
    ParkingAllocationStrategyFactory.getParkingAllocationStrategy("FindFirst")
);

// For FindLast strategy:
ParkingSpotRepsitory parkingSpotRepository = new InMemoryParkingSpotRepository(
    ParkingAllocationStrategyFactory.getParkingAllocationStrategy("FindLast")
);
```

## Extending the System

### Adding a New Vehicle Type
1. Add new type to `VehicleType` enum (e.g., `TRUCK`)
2. Create new class extending `Vehicle` (e.g., `Truck.java`)
3. Update `VehicleFactory` to handle new type
4. Create corresponding parking spot class (e.g., `TruckParkingSpot.java`)
5. Update `ParkingSpotFactory` to handle new type

### Adding a New Allocation Strategy
1. Create new class implementing `ParkingAllocationStrategy` (e.g., `RandomAllocationStrategy`)
2. Implement `findAvailableParkingSpot()` method
3. Update `ParkingAllocationStrategyFactory` to return new strategy

### Adding Pricing/Payment
1. Enhance `Ticket` entity with getter methods
2. Create `CalculateParkingFeeUseCase`
3. Use ticket entry time and current time to calculate duration
4. Multiply duration by parking spot price

## Learning Outcomes

This project demonstrates understanding of:
- Object-oriented programming principles
- Design patterns (Factory, Strategy, Repository)
- Clean architecture and separation of concerns
- SOLID principles
- Dependency injection
- Type safety in system design
- Extensible and maintainable code structure

## License

This is a practice project for educational purposes.
